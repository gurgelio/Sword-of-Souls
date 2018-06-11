import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

class Camera {

    private int x, y;

    public Camera() {

        x = 0;
        y = 0;
    }


    void render(TiledMap map, Graphics g, Entity larry) {

        if (larry.getX() - Game.width / 2 + 16 < 0) {
            x = 0;
        } else if (larry.getX() + Game.width / 2 + 16 > map.getWidth()) {
            x = -map.getWidth() + Game.width;
        } else {
            x = (int) -larry.getX() + Game.width / 2 - 16;
        }
        if (larry.getY() - Game.height / 2 + 16 < 0) {
            y = 0;
        } else if (larry.getY() + Game.height / 2 + 16 > map.getHeight()) {
            y = -map.getHeight() + Game.height;
        } else {
            y = (int) -larry.getY() + Game.height / 2 - 16;
        }

        g.translate(x, y);
    }

    float getX(){
        return -this.x;
    }

    float getY(){
        return -this.y;
    }

}