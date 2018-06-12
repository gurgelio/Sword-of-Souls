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
    private int mapHeight, mapWidth;
    private int tileHeight, tileWidth;
    private float inX = 0 , inY = 0;
    private int stateid;
    private Larry larry;
    private ArrayList<Entity> entities;
    private MiniMap minimap;
    private Mapa map;

    Play(int id){
        stateid = id;
    }

    @Override
    public int getID() {
        return stateid;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        In.init();
        map = new Mapa("map/mapa.tmx", "map/mapa2.tmx");
        map.init();

        camera = new Camera();
        minimap = new MiniMap(new Image("map/mapa128.png"), map.size());

        Items.init();
        entities = new ArrayList<>();
        //declarar na ordem BEHIND, BODY, FEET, LEGS, TORSO, BELT, HEAD, HANDS, DON'T PLACE WEAPONS HERE
        larry = new Larry(32,128, new String[]{"quiver", "male body", "armor shoes", "green pants", "white shirt", "rope belt", "blonde hair"});
        entities.add(larry);
        entities.add(new Skeleton(1000, 1000, new int[]{1, 1, 1, 1},new String[]{"skeleton body", "armor shoes", "armor pants", "plate armor"}));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        camera.render(g, larry, map.size());
        map.renderWithEntities(entities, g);

        /*
        for(int x=0; x < map.getWidth(); x++){
            for(int y=0; y < map.getHeight(); y++){
                if(blocked[x][y]){
                    //g.drawRect((float) x * tileWidth, (float) y * tileHeight, (float) tileWidth, (float) tileHeight);
                }
            }
        }
        */
        
        g.drawImage(new Image("img/lifeHud.png"),camera.getX(),camera.getY() + Game.height - 64);
        minimap.render(g, camera, larry);
        if (In.buttonHeld("rmb")) renderInventory(g);
        g.drawImage(new Image("img/lifeHud.png"),camera.getX(),camera.getY() + Game.height - 64);
        minimap.render(g, camera, larry);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        inX = camera.getX() + 640;
        inY = camera.getY() + 20;
        In.update();
        Entity.update(entities, gc, delta, this);
        if (In.keyPressed("escape")) {
            sbg.enterState(0);
        }
    }





    void renderInventory(Graphics g) throws SlickException {
        g.drawImage(new Image("img/equipInventory.png"),inX, inY);
    }
}