import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

class Play extends BasicGameState {
    private Camera camera;
    private int stateid;
    private Larry larry;
    private ArrayList<Entity> entities;
    private MiniMap minimap;
    Mapa map, cave, currentmap;
    private Hud hud;

    Play(int id){
        stateid = id;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        String[] larryEquip = {"anim/NPC/LarryBase.png"}; // Usando NPC Lara
        In.init();
        map = new Mapa("map/mapa.tmx", "map/mapa2.tmx");
        map.init();
        currentmap = map;

        hud = new Hud("img/lifeHud.png");
        camera = new Camera();
        minimap = new MiniMap(new Image("map/mapa128.png"), map);
        Items.init();
        entities = new ArrayList<>();
        //declarar na ordem BEHIND, BODY, FEET, LEGS, TORSO, BELT, HEAD, HANDS, DON'T PLACE WEAPONS HERE
        larry = new Larry(3*32,128, larryEquip);
        entities.add(larry);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        camera.render(g, larry, currentmap);
        currentmap.renderWithEntities(entities, g);
        minimap.render(g, camera, larry);
        hud.render(camera.getX(), camera.getY(), larry.hp, larry.mana);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        In.update();
        Entity.update(entities, delta, currentmap);

        if (In.keyPressed("escape")) {
            sbg.enterState(0);
        }

        if (larry.getX() > 41*32 & larry.getX() < 43*32){
            if (larry.getY() < 4*32 & currentmap == map){
                cave = new Mapa("map/cave.tmx", "map/cave2.tmx");
                cave.init();
                currentmap = cave;
                minimap.setMinimap(new Image("map/cave128.png"), cave);
                larry.setpos(47*32,40*32);
            }
        }

        if (In.buttonReleased("mb4")) larry.setpos((int) camera.getX() + In.getMouse()[0] - 16,(int) camera.getY() + In.getMouse()[1] - 32);
    }


    @Override
    public int getID() {
        return stateid;
    }

}