import org.newdawn.slick.Graphics;

public class Camera {

    private int x, y;
    private int mapWidth, mapHeight;

    public Camera(int mapWidth, int mapHeight) {

        x = 0;
        y = 0;

        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }


    void translate(Graphics g, Entity larry) {

        if (larry.getX() - Game.width / 2 + 16 < 0) {
            x = 0;
        } else if (larry.getX() + Game.width / 2 + 16 > mapWidth) {
            x = -mapWidth + Game.width;
        } else {
            x = (int) -larry.getX() + Game.width / 2 - 16;
        }
        if (larry.getY() - Game.height / 2 + 16 < 0) {
            y = 0;
        } else if (larry.getY() + Game.height / 2 + 16 > mapHeight) {
            y = -mapHeight + Game.height;
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