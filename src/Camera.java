import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

class Camera {

    private int x, y;

    public Camera() {

        x = 0;
        y = 0;
    }


    void render(Graphics g, Entity larry, int mapSize) {

        if (larry.getX() - Game.width / 2 + 32 < 0) {
            x = 0;
        } else if (larry.getX() + Game.width / 2 + 32 > mapSize) {
            x = -mapSize + Game.width;
        } else {
            x = (int) -larry.getX() + Game.width / 2 - 32;
        }
        if (larry.getY() - Game.height / 2 + 32 < 0) {
            y = 0;
        } else if (larry.getY() + Game.height / 2 + 32 > mapSize) {
            y = -mapSize + Game.height;
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