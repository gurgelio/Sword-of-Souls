import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

class Camera {

    private int x, y;

    public Camera() {

        x = 0;
        y = 0;
    }


    void render(TiledMap map, Graphics g, Entity larry) {

        if (larry.getX() - Game.width / 2 + 32 < 0) {
            x = 0;
        } else if (larry.getX() + Game.width / 2 + 32 > map.getWidth()*map.getTileWidth()) {
            x = -map.getWidth()*map.getTileWidth() + Game.width;
        } else {
            x = (int) -larry.getX() + Game.width / 2 - 32;
        }
        if (larry.getY() - Game.height / 2 + 32 < 0) {
            y = 0;
        } else if (larry.getY() + Game.height / 2 + 32 > map.getHeight()*map.getTileHeight()) {
            y = -map.getHeight()*map.getTileHeight() + Game.height;
        } else {
            y = (int) -larry.getY() + Game.height / 2 - 32;
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