import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;

class Mapa {
    private boolean[][] blocked;
    private TiledMap map;
    private TiledMap overEntitiesMap;
    //private ArrayList<Integer> spawnPos = new ArrayList<>();
    private int[][] spawnPos;

    Mapa(String ref, String refOverEntities) throws SlickException {
        map = new TiledMap(ref);
        overEntitiesMap = new TiledMap(refOverEntities);
    }


    void init() {
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

            /*if (map.getLayerProperty(l, "spawned", "false").equals("true")) {
                for (int c = 0; c < map.getWidth(); c++) {
                    for (int r = 0; r < map.getHeight(); r++) {
                         if (map.getTileId(c, r, l) != 0) {
                            spawnPos.add(c*32);
                            spawnPos.add(r*32);

                        }
                    }
                }
            }*/
        }

    }

    /*void spawnEnemies(ArrayList<Entity> entities) throws SlickException {
        for (int i : new int[spawnPos.size()/2]){
                entities.add(new SpearSkeleton(spawnPos.get(i), spawnPos.get(i+1), new int[]{2, 2, 2}, new String[]{"Skeleton", "Leather Hood", "Spear"}));
        }
    }
    */

    void spawnEnemies(ArrayList<Entity> entities) throws SlickException {
        for (int[] i : spawnPos){
            entities.add(new SpearSkeleton(i[0]*32, i[1]*32, new int[]{2, 2, 2}, new String[]{"Skeleton", "Leather Hood", "Spear"}));
        }
    }

    void setEnemySpawnPosition(int[][] pos){
        this.spawnPos = pos;
    }

    void renderWithEntities(ArrayList<Entity> entities, Graphics g){
        map.render(0, 0);
        EntityHandling.render(entities);
        overEntitiesMap.render(0, 0);
    }

    void renderCollisionRectangles(Graphics g){
        for(int x=0; x < map.getWidth(); x++){
            for(int y=0; y < map.getHeight(); y++){
                if(blocked[x][y]){
                    g.drawRect((float) x * 32, (float) y * 32, (float) 32, (float) 32);
                }
            }
        }
    }

    boolean isBlocked(float x, float y, float radius) {
        int xBlock0 = (int) ((x-radius) / map.getTileWidth());
        int yBlock0 = (int) ((y-radius) / map.getTileHeight());
        int xBlock1 = (int) ((x + radius) / map.getTileWidth());
        int yBlock1 = (int) ((y + radius) / map.getTileHeight());
        return (blocked[xBlock0][yBlock0] || blocked[xBlock0][yBlock1] || blocked[xBlock1][yBlock0] || blocked[xBlock1][yBlock1]);
    }

    int getWidth(){
        return map.getWidth()*map.getTileWidth();
    }

    int getHeight(){
        return map.getHeight()*map.getTileHeight();
    }
}
