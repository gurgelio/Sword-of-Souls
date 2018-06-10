import org.newdawn.slick.tiled.TiledMap;

public class Map {
    private TiledMap map;
    public Map(TiledMap m){
        map = m;
    }

    float getWidth(){
        return this.map.getWidth()*this.map.getTileWidth();
    }

    float getHeight(){
        return this.map.getHeight()*this.map.getTileHeight();
    }
}
