import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class Play extends BasicGameState {

    private boolean[][] blocked;
    private TiledMap map;
    private Larry player;
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
        player = new Larry(32, 128);
        camera = new Camera(mapWidth, mapHeight);
        blocked = new boolean[map.getWidth()][map.getHeight()];
        initializeBlocked();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        camera.translate(g, player);
        map.render(0, 0);
        player.render();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        player.update(gc, delta, this);

        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(0);
        }

        if (player.getX() > 4*32 - 10 & player.getX() < 5*32 + 10 & player.getY() > 39*32 - 10 & player.getY() < 40*32 + 10){
            if (gc.getInput().isKeyPressed(Input.KEY_TAB)) player.setpos(32,128);
        }

        if (player.getX() == 32 & player.getY() == 128 & gc.getInput().isKeyPressed(Input.KEY_LSHIFT)) player.setpos(3*32, 38*32);
    }

    boolean isBlocked(float x, float y) {
        int xBlock = (int) x / map.getTileWidth();
        int yBlock = (int) y / map.getTileHeight();
        return blocked[xBlock][yBlock];
    }

    public Vector2f getHeroPosition() {
        return player.getpos();
    }

    public void setHeroPosition(Vector2f pos) {
        player.setpos((int) pos.x,(int) pos.y);
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