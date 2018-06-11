import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;

class MiniMap {
    private Image minimap;
    private double scale;

    MiniMap(Image map, int mapSize){
        minimap = map;
        scale = (double) map.getWidth()/mapSize;
    }

    void render(Graphics g, Camera camera, Entity larry) {

        g.drawImage(minimap, camera.getX() + Game.width - 128, camera.getY() + Game.height - 128);
        g.drawRect(camera.getX() + Game.width - 128 + toScale(camera.getX()), camera.getY() + Game.height - 128 + toScale(camera.getY()), toScale(Game.width), toScale(Game.height));
        g.drawRect(camera.getX() + Game.width - 128 + toScale(larry.getX()), camera.getY() + Game.height - 128 + toScale(larry.getY()), 1, 1);
    }

    private float toScale(float x){
        return x * (float) scale;
    }

}
