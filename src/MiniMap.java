import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Graphics;

public class MiniMap {
    private Image minimap;
    //private Rectangle minimapRect = new Rectangle(0,0,10,10);
    private float x = 0, y = 0;

    public MiniMap(Image map){
        minimap = map;
    }

    void render(Graphics g, Camera camera) {
        g.drawImage(minimap,camera.getX() + Game.width - 128, camera.getY() + Game.height - 128);
        g.drawRect(camera.getX() + x - 128 + Game.width,camera.getY() + y - 128 + Game.height,1,1);
    }

    void update(Entity larry, int mapWidth, int mapHeight){
        x = larry.getX()/(mapWidth/128);
        y = larry.getY()/(mapHeight/128);
    }
}
