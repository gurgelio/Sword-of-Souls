import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;

class Mapa {
    private boolean[][] blocked;
    private TiledMap map;
    private TiledMap overEntitiesMap;
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
        }
    }

    void spawnEnemies(ArrayList<Entity> entities) throws SlickException {
        for (int[] i : spawnPos){
            entities.add(new SpearSkeleton(i[0]*32, i[1]*32, new int[]{2, 2, 2}, new String[]{"Skeleton", "Leather Hood", "Spear"}));
        }
    }

    void setEnemySpawnPosition(int[][] pos){
        this.spawnPos = pos;
    }

    //renderiza o mapa com os personagens dentro, em um sistema de três camadas
    void renderWithEntities(ArrayList<Entity> entities){
        map.render(0, 0);
        EntityHandling.render(entities);
        overEntitiesMap.render(0, 0);
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
