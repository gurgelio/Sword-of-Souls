import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Larry {

    private char lastDirection;
    private Vector2f pos;
    private Rectangle rectangle;
    private static final float SPEED = 0.17f;
    private int w, h;

    private Animation hero;
    private Anim larry = new Anim(new Image("anim/larry_move.png"),24,32, 200);

    public Larry(float x, float y) throws SlickException {

        /*

         * Set the width and the height of Hero's image

         */

        w = 24;

        h = 32;

        pos = new Vector2f(x, y);

        rectangle = new Rectangle(x, y, w, h);

        /*

         * Set the Animations needed.

         */

        hero = larry.upStill;

    }



    void update(GameContainer gc, int delta, Play gps) {

        Input input = gc.getInput();

        /*

         * If the hero is moving we have to deal with changing hero's position and

         * his movement animations.

         */

        if (input.isKeyDown(Input.KEY_UP) | input.isKeyDown(Input.KEY_W)) {

            if (!gps.isBlocked(pos.x + w -4, pos.y - delta * SPEED) && !gps.isBlocked(pos.x + 4, pos.y - delta * SPEED)) {
                pos.y -= delta * SPEED;
            }

            hero = larry.down;
            hero.update(delta);
            lastDirection = 'u';

        } else if (input.isKeyDown(Input.KEY_DOWN) | input.isKeyDown(Input.KEY_S)) {

            if (!gps.isBlocked(pos.x + w - 4, pos.y + h + delta * SPEED) && !gps.isBlocked(pos.x + 4, pos.y + h + delta * SPEED)) {
                pos.y += delta * SPEED;
            }

            hero = larry.up;
            hero.update(delta);
            lastDirection = 'd';

        } else if (input.isKeyDown(Input.KEY_LEFT) | input.isKeyDown(Input.KEY_A)) {

            if (!gps.isBlocked(pos.x - delta * SPEED, pos.y + 4) && !gps.isBlocked(pos.x - delta * SPEED, pos.y + h - 4)) {
                pos.x -= delta * SPEED;
            }

            hero = larry.left;
            hero.update(delta);
            lastDirection = 'l';

        } else if (input.isKeyDown(Input.KEY_RIGHT) | input.isKeyDown(Input.KEY_D)) {

            if (!gps.isBlocked(pos.x + w + delta * SPEED, pos.y + h - 4) && !gps.isBlocked(pos.x + w + delta * SPEED, pos.y + 4)) {
                pos.x += delta * SPEED;
            }

            hero = larry.right;
            hero.update(delta);
            lastDirection = 'r';

        }

        /*

         * If hero isn't moving he must be still so we have to change the

         * animations.

         */

        else {
            switch (lastDirection) {

                case 'd':
                    hero = larry.upStill;
                    break;

                case 'u':
                    hero = larry.leftStill;
                    break;

                case 'l':
                    hero = larry.downStill;
                    break;

                case 'r':
                    hero = larry.rightStill;
                    break;

            }
        }
    }


    void render() {

        hero.draw(pos.x, pos.y);

    }


    float getX() {
        return pos.x;
    }



    float getY() {
        return pos.y;
    }


    Vector2f getpos() {
        return pos;
    }


    public void setpos(Vector2f pos) {
        this.pos = pos;
    }


    private Rectangle getRectangle() {
        return rectangle;
    }


    private void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}