import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Larry extends Entity {

    private char lastDirection;
    private static Vector2f pos;
    private Rectangle rectangle;
    private static final float SPEED = 0.17f;
    private int w, h;

    private Anim body = new Anim(new Image("anim/lpc_entry/png/walkcycle/BODY_male.png"),64,64, 120);
    private Anim shirt = new Anim(new Image("anim/lpc_entry/png/walkcycle/TORSO_robe_shirt_brown.png"),64,64,120);
    private Anim hair = new Anim(new Image("anim/lpc_entry/png/walkcycle/HEAD_hair_blonde.png"),64,64,120);
    private Anim legs = new Anim(new Image("anim/lpc_entry/png/walkcycle/LEGS_pants_greenish.png"),64,64,120);
    private Anim[] anims = {body, shirt, hair, legs};

    private CharAnimation charAnimation = new CharAnimation(anims);

    public Larry(float x, float y) throws SlickException {

        /*

         * Set the width and the height of Hero's image

         */

        w = 64;

        h = 64;

        pos = new Vector2f(x, y);

        rectangle = new Rectangle(x, y, w, h);

    }



    void update(GameContainer gc, int delta, Play gps) {

        Input input = gc.getInput();

        /*

         * If the hero is moving we have to deal with changing hero's position and

         * his movement animations.

         */

        if (input.isKeyDown(Input.KEY_UP) | input.isKeyDown(Input.KEY_W)) {

            if (!gps.isBlocked(pos.x, pos.y - delta * SPEED) && !gps.isBlocked(pos.x + w, pos.y - delta * SPEED)) {
                pos.y -= delta * SPEED;
            }

            charAnimation.update(1,delta);
            lastDirection = 'u';


        } else if (input.isKeyDown(Input.KEY_DOWN) | input.isKeyDown(Input.KEY_S)) {

            if (!gps.isBlocked(pos.x, pos.y + h + delta * SPEED) && !gps.isBlocked(pos.x + w, pos.y + h + delta * SPEED)) {
                pos.y += delta * SPEED;
            }

            charAnimation.update(2,delta);
            lastDirection = 'd';

        } else if (input.isKeyDown(Input.KEY_LEFT) | input.isKeyDown(Input.KEY_A)) {

            if (!gps.isBlocked(pos.x - delta * SPEED, pos.y) && !gps.isBlocked(pos.x - delta * SPEED, pos.y + h)) {
                pos.x -= delta * SPEED;
            }

            charAnimation.update(3,delta);
            lastDirection = 'l';

        } else if (input.isKeyDown(Input.KEY_RIGHT) | input.isKeyDown(Input.KEY_D)) {

            if (!gps.isBlocked(pos.x + w + delta * SPEED, pos.y + h) && !gps.isBlocked(pos.x + w + delta * SPEED, pos.y)) {
                pos.x += delta * SPEED;
            }

            charAnimation.update(4,delta);
            lastDirection = 'r';

        }

        /*

         * If hero isn't moving he must be still so we have to change the

         * animations.

         */

        else {
            switch (lastDirection) {

                case 'd':
                    body.Current = body.downStill;
                    shirt.Current = shirt.downStill;
                    hair.Current = hair.downStill;
                    legs.Current = legs.downStill;
                    break;

                case 'u':
                    body.Current = body.upStill;
                    shirt.Current = shirt.upStill;
                    hair.Current = hair.upStill;
                    legs.Current = legs.upStill;
                    break;

                case 'l':
                    body.Current = body.leftStill;
                    shirt.Current = shirt.leftStill;
                    hair.Current = hair.leftStill;
                    legs.Current = legs.leftStill;
                    break;

                case 'r':
                    body.Current = body.rightStill;
                    shirt.Current = shirt.rightStill;
                    hair.Current = hair.rightStill;
                    legs.Current = legs.rightStill;
                    break;

            }
        }
    }


    void render() {

        body.Current.draw(pos.x, pos.y);
        shirt.Current.draw(pos.x,pos.y);
        hair.Current.draw(pos.x,pos.y);
        legs.Current.draw(pos.x,pos.y);

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


    public void setpos(int x, int y) {
        this.pos.x = x;
        this.pos.y = y;
    }


    private Rectangle getRectangle() {
        return rectangle;
    }


    private void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}