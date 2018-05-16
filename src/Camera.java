import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Camera {


    private int x, y;

    private int mapWidth, mapHeight;

    private Rectangle viewPort;


    public Camera(int mapWidth, int mapHeight) {

        x = 0;

        y = 0;

        viewPort = new Rectangle(0, 0, Game.gamewidth, Game.gameheight);

        this.mapWidth = mapWidth;

        this.mapHeight = mapHeight;

    }


    public void translate(Graphics g, Larry larry) {


        if (larry.getX() - Game.gamewidth / 2 + 16 < 0) {

            x = 0;

        } else if (larry.getX() + Game.gamewidth / 2 + 16 > mapWidth) {

            x = -mapWidth + Game.gamewidth;

        } else {

            x = (int) -larry.getX() + Game.gamewidth / 2 - 16;

        }


        if (larry.getY() - Game.gameheight / 2 + 16 < 0) {

            y = 0;

        } else if (larry.getY() + Game.gameheight / 2 + 16 > mapHeight) {

            y = -mapHeight + Game.gameheight;

        } else {

            y = (int) -larry.getY() + Game.gameheight / 2 - 16;

        }

        g.translate(x, y);

        viewPort.setX(-x);

        viewPort.setY(-y);

    }
}