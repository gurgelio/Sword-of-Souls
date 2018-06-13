import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;

class Play extends BasicGameState {
    private Camera camera;
    private int stateid;
    private Larry larry;
    private ArrayList<Entity> entities;
    private MiniMap minimap;
    Mapa map;
    Play(int id){
        stateid = id;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        String[] larryEquip = {"anim/Universal-LPC-spritesheet/body/male/light.png"};
        In.init();
        map = new Mapa("map/mapa.tmx", "map/mapa2.tmx");
        map.init();

        camera = new Camera();
        minimap = new MiniMap(new Image("map/mapa128.png"), map);

        Items.init();
        entities = new ArrayList<>();
        //declarar na ordem BEHIND, BODY, FEET, LEGS, TORSO, BELT, HEAD, HANDS, DON'T PLACE WEAPONS HERE
        larry = new Larry(32,128, larryEquip);
        entities.add(larry);
        //entities.add(new Skeleton(1000, 1000, new int[]{1, 1, 1, 1},new String[]{"skeleton body", "armor shoes", "armor pants", "plate armor"}));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        camera.render(g, larry, map);
        map.renderWithEntities(entities, g);
        //map.renderCollisionRectangles(g);
        if (In.buttonHeld("rmb")) renderInventory(g);
        minimap.render(g, camera, larry);
        renderHud(g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        In.update();
        Entity.update(entities, delta, map);
        if (In.keyPressed("escape")) {
            sbg.enterState(0);
        }
    }

    @Override
    public int getID() {
        return stateid;
    }


    void renderInventory(Graphics g) throws SlickException {
        g.drawImage(new Image("img/equipInventory.png"), camera.getX() + 640, camera.getY() + 20);
    }

    void renderHud(Graphics g) throws SlickException {
        g.drawImage(new Image("img/lifeHud.png"),camera.getX(),camera.getY() + Game.height - 64);
    }
}