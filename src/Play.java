import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

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
        larry = new Larry(tileHeight, tileWidth*4);
        camera = new Camera(mapWidth, mapHeight);
        blocked = new boolean[map.getWidth()][map.getHeight()];
        initializeBlocked();
        In.init();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        camera.translate(g, larry);
        map.render(0, 0);
        larry.render();
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

    boolean isBlocked(float x, float y, int radius) {
        int xBlock = (int) x / tileWidth;
        int yBlock = (int) y / tileHeight;
        int n = (int) Math.ceil(radius / tileWidth);
        if(n == 1) {
            return blocked[xBlock][yBlock];
        } else {
            for(int i = n*(-1); i <= n; i++){
                for(int j = n*(-1); j <= n; j++){
                    if(blocked[xBlock+i][yBlock+j]) return true;
                }
            }
            return false;
        }
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