import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
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

    Play(int id){
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
        //declarar na ordem BEHIND, BODY, FEET, LEGS, TORSO, BELT, HEAD, HANDS, DON'T PLACE WEAPONS HERE
        larry = new Larry(32,128, new String[]{"quiver", "male body", "armor shoes", "green pants", "white shirt", "rope belt", "blonde hair"});
        camera = new Camera(mapWidth, mapHeight);
        blocked = new boolean[map.getWidth()][map.getHeight()];
        initBlocks();

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        camera.translate(g, larry);
        map.render(0, 0);
        larry.render();
        float[] hitbox = larry.hitbox();
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
            if (In.keyPressed("tab")){
                larry.setpos(32,128);
            }
        }

        if (In.keyPressed("lshift")) larry.setpos(3*32, 38*32);

    }

    boolean isBlocked(float x, float y, int width, int height) {
        int xBlock = (int) x / tileWidth;
        int yBlock = (int) y / tileHeight;
        int xBlock2 = (int) ((x + width) / tileWidth);
        int yBlock2 = (int) ((y + height) / tileHeight);
         return (blocked[xBlock][yBlock] || blocked[xBlock2][yBlock] || blocked[xBlock][yBlock2] || blocked[xBlock2][yBlock2]);
    }



    private void initBlocks() {
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