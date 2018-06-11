import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;

class MiniMap {
    private Image minimap;
    private float scale;
    private float x = 0, y = 0;

    MiniMap(Image map){
        minimap = map;
        scale = 128/1600;
    }

    void render(Graphics g, Camera camera, Entity larry) {

        x = camera.getX() + Game.width - 128;
        y = camera.getY() + Game.height - 128;

        g.drawImage(minimap, x, y);
        g.drawRect(x + toScale(camera.getX()), y + toScale(camera.getY()),Game.width/minimap.getWidth(),Game.height/minimap.getHeight());
        g.drawRect(x + toScale(camera.getX() + larry.getX()), y + toScale(camera.getY() + larry.getY()), 1, 1);
    }

    private float toScale(float x){
        return x * scale;
    }

}
