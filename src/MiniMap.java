import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;

class MiniMap {
    private Image minimap;
    private float scale;
    private float x, y;

    MiniMap(Image map, Camera camera){
        minimap = map;
        scale = minimap.getWidth()/Game.width;
        x = camera.getX() + Game.width - 128;
        y = camera.getY() + Game.height - 128;
    }

    void render(Graphics g, Camera camera, Entity larry) {

        g.drawImage(minimap, x, y);
        g.drawRect(x + toScale(camera.getX()), y + toScale(camera.getY()),Game.width/minimap.getWidth(),Game.height/minimap.getHeight());
        g.drawOval(x + toScale(camera.getX() + larry.getX()), y + toScale(camera.getY() + larry.getY()), 1, 1);
    }

    private float toScale(float x){
        return x * scale;
    }
}
