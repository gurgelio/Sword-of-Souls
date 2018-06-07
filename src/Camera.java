import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Camera {

    private int x, y;
    private int mapWidth, mapHeight;
    private Rectangle viewPort;

    public Camera(int mapWidth, int mapHeight) {

        x = 0;
        y = 0;

        viewPort = new Rectangle(0, 0, Game.width, Game.height);

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
        viewPort.setX(-x);
        viewPort.setY(-y);
    }
}