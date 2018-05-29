import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import java.util.HashMap;
import java.util.Map;

public class Play extends BasicGameState {

    private boolean[][] blocked;
    private TiledMap map;
    private Larry larry;
    private Camera camera;
    private int mapHeight, mapWidth;
    private int tileHeight, tileWidth;
    private int stateid;

    Play(int id) {
        stateid = id;
    }

    @Override
    public int getID() {
        return stateid;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        map = new TiledMap("map/mapa.tmx");
        mapWidth = map.getWidth() * map.getTileWidth();
        mapHeight = map.getHeight() * map.getTileHeight();
        tileHeight = map.getTileHeight();
        tileWidth = map.getTileWidth();
        Items.init();
        In.init();
        larry = new Larry(32,128, new String[]{"male body", "blonde hair", "white shirt", "leather skirt", "brown shoes"});
        camera = new Camera(mapWidth, mapHeight);
        blocked = new boolean[map.getWidth()][map.getHeight()];
        initializeBlocked();

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        camera.translate(g, larry);
        map.render(0, 0);
        larry.render();
        float[] hitbox = larry.hitbox();
        g.drawRect(larry.getX(), larry.getY(), larry.w, larry.h);
        g.drawRect(hitbox[0], hitbox[1], 32, 32);
        for(int x=0; x < map.getWidth(); x++){
            for(int y=0; y < map.getHeight(); y++){
                if(blocked[x][y]){
                    g.drawRect((float) x * tileWidth, (float) y * tileHeight, (float) tileWidth, (float) tileHeight);
                }
            }
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        In.update();
        larry.update(gc, delta, this);


        if (In.keyPressed("escape")) {
            sbg.enterState(0);
        }

        if (larry.getX() > 4*32 - 10 & larry.getX() < 5*32 + 10 & larry.getY() > 39*32 - 10 & larry.getY() < 40*32 + 10){
            if (In.keyPressed("tab")) larry.setpos(32,128);
        }

        if (In.keyPressed("lshift")) larry.setpos(3*32, 38*32);

    }

    boolean isBlocked(float x, float y, int width, int height) {
        int xBlock = (int) x / tileWidth;
        int yBlock = (int) y / tileHeight;
        int nx = (int) Math.ceil(width / tileWidth);
        int ny = (int) Math.ceil(height / tileHeight);
        for(int i=0; i<=nx; i++){
            for(int j=0; j<=ny; j++){
                if(blocked[xBlock + i][yBlock + j]) return true;
            }
        }
        return false;
    }

    Vector2f getHeroPosition() {
        return larry.getpos();
    }

    void setHeroPosition(Vector2f pos) {
        larry.setpos((int) pos.x,(int) pos.y);
    }

    private void initializeBlocked() {
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