import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Larry {

    private char lastDirection;
    private Vector2f pos;
    private Rectangle rectangle;
    private static final float SPEED = 0.17f;
    private int w, h;

    private Animation hero, shirt, hair, legs;
    private Anim larry = new Anim(new Image("anim/lpc_entry/png/walkcycle/BODY_male.png"),64,64, 120);
    private Anim shirtAnim = new Anim(new Image("anim/lpc_entry/png/walkcycle/TORSO_robe_shirt_brown.png"),64,64,120);
    private Anim hairAnim = new Anim(new Image("anim/lpc_entry/png/walkcycle/HEAD_hair_blonde.png"),64,64,120);
    private Anim legsAnim = new Anim(new Image("anim/lpc_entry/png/walkcycle/LEGS_pants_greenish.png"),64,64,120);

    private Anim[] clAnim = {larry, shirtAnim, hairAnim, legsAnim};
    private Animation[] clothes = {hero, shirt, hair, legs};

    public Larry(float x, float y) throws SlickException {

        /*

         * Set the width and the height of Hero's image

         */

        w = 32;

        h = 32;

        pos = new Vector2f(x, y);

        rectangle = new Rectangle(x, y, w, h);

        /*

         * Set the Animations needed.

         */

        //for (Animation a : clothes){
        //    for (Anim b : clAnim){
        //        a = b.upStill;
        //    }
        //}
        hero = larry.upStill;
        shirt = shirtAnim.upStill;
        legs = legsAnim.upStill;
        hair = hairAnim.upStill;

    }



    void update(GameContainer gc, int delta, Play gps) {

        Input input = gc.getInput();

        /*

         * If the hero is moving we have to deal with changing hero's position and

         * his movement animations.

         */

        if (input.isKeyDown(Input.KEY_UP) | input.isKeyDown(Input.KEY_W)) {

            if (!gps.isBlocked(pos.x - w/2, pos.y + delta * SPEED) && !gps.isBlocked(pos.x + w/2, pos.y - delta * SPEED)) {
                pos.y -= delta * SPEED;
            }

            hero = larry.up;
            shirt = shirtAnim.up;
            hair = hairAnim.up;
            legs = legsAnim.up;
            hero.update(delta);
            shirt.update(delta);
            hair.update(delta);
            legs.update(delta);
            lastDirection = 'u';

        } else if (input.isKeyDown(Input.KEY_DOWN) | input.isKeyDown(Input.KEY_S)) {

            if (!gps.isBlocked(pos.x + w - 4, pos.y + h + delta * SPEED) && !gps.isBlocked(pos.x + 4, pos.y + h + delta * SPEED)) {
                pos.y += delta * SPEED;
            }

            hero = larry.down;
            shirt = shirtAnim.down;
            hair = hairAnim.down;
            legs = legsAnim.down;
            hero.update(delta);
            shirt.update(delta);
            hair.update(delta);
            legs.update(delta);
            lastDirection = 'd';

        } else if (input.isKeyDown(Input.KEY_LEFT) | input.isKeyDown(Input.KEY_A)) {

            if (!gps.isBlocked(pos.x - delta * SPEED, pos.y + 4) && !gps.isBlocked(pos.x - delta * SPEED, pos.y + h - 4)) {
                pos.x -= delta * SPEED;
            }

            hero = larry.left;
            shirt = shirtAnim.left;
            hair = hairAnim.left;
            legs = legsAnim.left;
            hero.update(delta);
            shirt.update(delta);
            hair.update(delta);
            legs.update(delta);
            lastDirection = 'l';

        } else if (input.isKeyDown(Input.KEY_RIGHT) | input.isKeyDown(Input.KEY_D)) {

            if (!gps.isBlocked(pos.x + w + delta * SPEED, pos.y + h - 4) && !gps.isBlocked(pos.x + w + delta * SPEED, pos.y + 4)) {
                pos.x += delta * SPEED;
            }

            hero = larry.right;
            shirt = shirtAnim.right;
            hair = hairAnim.right;
            legs = legsAnim.right;
            hero.update(delta);
            shirt.update(delta);
            hair.update(delta);
            legs.update(delta);
            lastDirection = 'r';

        }

        /*

         * If hero isn't moving he must be still so we have to change the

         * animations.

         */

        else {
            switch (lastDirection) {

                case 'd':
                    hero = larry.downStill;
                    shirt = shirtAnim.downStill;
                    hair = hairAnim.downStill;
                    legs = legsAnim.downStill;
                    break;

                case 'u':
                    hero = larry.upStill;
                    shirt = shirtAnim.upStill;
                    hair = hairAnim.upStill;
                    legs = legsAnim.upStill;
                    break;

                case 'l':
                    hero = larry.leftStill;
                    shirt = shirtAnim.leftStill;
                    hair = hairAnim.leftStill;
                    legs = legsAnim.leftStill;
                    break;

                case 'r':
                    hero = larry.rightStill;
                    shirt = shirtAnim.rightStill;
                    hair = hairAnim.rightStill;
                    legs = legsAnim.rightStill;
                    break;

            }
        }
    }


    void render() {

        hero.draw(pos.x, pos.y);
        shirt.draw(pos.x,pos.y);
        hair.draw(pos.x,pos.y);
        legs.draw(pos.x,pos.y);

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