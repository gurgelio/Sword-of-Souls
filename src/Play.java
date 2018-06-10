import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;

public class Play extends BasicGameState {

    private boolean[][] blocked;
    private TiledMap map;
    private Camera camera;
    private int stateid;
    private Larry larry;
    private ArrayList<Entity> entities;
    private MiniMap minimap;

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

        map = new TiledMap("map/mapa.tmx");
        initBlocks();

        camera = new Camera(map.getWidth(), map.getHeight());
        minimap = new MiniMap(new Image("map/mapa128.png"), camera);

        Items.init();
        entities = new ArrayList<>();
        //declarar na ordem BEHIND, BODY, FEET, LEGS, TORSO, BELT, HEAD, HANDS, DON'T PLACE WEAPONS HERE
        larry = new Larry(32,128, new String[]{"quiver", "male body", "armor shoes", "green pants", "white shirt", "rope belt", "blonde hair"});
        entities.add(larry);
        entities.add(new Skeleton(1000, 1000, new int[]{1, 1, 1, 1},new String[]{"skeleton body", "armor shoes", "armor pants", "plate armor"}));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        camera.translate(g, larry);
        map.render(0, 0);
        Entity.render(entities);
        g.drawImage(new Image("img/lifeHud.png"),camera.getX(),camera.getY() + Game.height - 64);
        minimap.render(g, camera, larry);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        In.update();
        Entity.update(entities, gc, delta, this);
        if (In.keyPressed("escape")) {
            sbg.enterState(0);
        }
    }

    boolean isBlocked(float x, float y, float radius) {
        int xBlock0 = (int) ((x-radius) / map.getTileWidth());
        int yBlock0 = (int) ((y-radius) / map.getTileHeight());
        int xBlock1 = (int) ((x + radius) / map.getTileWidth());
        int yBlock1 = (int) ((y + radius) / map.getTileHeight());
        return (blocked[xBlock0][yBlock0] || blocked[xBlock0][yBlock1] || blocked[xBlock1][yBlock0] || blocked[xBlock1][yBlock1]);
    }

    private void initBlocks() {
        blocked = new boolean[map.getWidth()][map.getHeight()];
        for (int l = 0; l < map.getLayerCount(); l++) {
            String layerValue = map.getLayerProperty(l, "blocked", "false");

            if (layerValue.equals("true")) {
                for (int c = 0; c < map.getWidth(); c++) {
                    for (int r = 0; r < map.getHeight(); r++) {

                        if (map.getTileId(c, r, l) != 0) {
                            blocked[c][r] = true;
                        }
                    }
                }
            }
        }
    }

}