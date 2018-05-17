import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Larry {

    private char lastDirection;

    private Vector2f pos;

    private Rectangle rectangle;

    private static final float SPEED = 0.17f;

    private int w, h, ANIMATIONSPEED = 200;


    private Animation hero, movementUp, movementDown, movementLeft, movementRight, stillUp, stillDown, stillLeft, stillRight;

    private Anim skeleton = new Anim(new Image("img/skelTest.png"),64,64);
    private Anim armor = new Anim(new Image("anim/armor_torso.png"),64,64);
    private Anim pants = new Anim(new Image("anim/armor_pants_green.png"),64,64);
    private Image img = new Image("anim/armor_torso.png");


    public Larry(float x, float y) throws SlickException {

        SpriteSheet animUp = new SpriteSheet("anim/animSheetBack.png",24,32,0);
        SpriteSheet animDown = new SpriteSheet("anim/animSheetFront.png",24,32,0);
        SpriteSheet animLeft = new SpriteSheet("anim/animSheetLeft.png",24,32,0);
        SpriteSheet animRight = new SpriteSheet("anim/animSheetRight.png",24,32,0);
        Image[] up = {new Image("anim/stillUp.png")};
        Image[] down = {new Image("anim/stillDown.png")};
        Image[] left = {new Image("anim/stillLeft.png")};
        Image[] right = {new Image("anim/stillRight.png")};

        /*

         * Set the width and the height of Hero's image

         */

        w = down[0].getWidth();

        h = down[0].getHeight();



        pos = new Vector2f(x, y);

        rectangle = new Rectangle(x, y, w, h);

        /*

         * Set the Animations needed.

         */

        stillUp = new Animation(up, ANIMATIONSPEED);
        stillDown = new Animation(down, ANIMATIONSPEED);
        stillLeft = new Animation(left, ANIMATIONSPEED);
        stillRight = new Animation(right, ANIMATIONSPEED);
        movementUp = new Animation(animUp, ANIMATIONSPEED);
        movementDown = new Animation(animDown, ANIMATIONSPEED);
        movementLeft = new Animation(animLeft, ANIMATIONSPEED);
        movementRight = new Animation(animRight, ANIMATIONSPEED);

        hero = stillDown;

    }



    public void update(GameContainer gc, StateBasedGame sbg, int delta, Play gps) {

        Input input = gc.getInput();


        /*

         * If the hero is moving we have to deal with changing hero's position and

         * his movement animations.

         */

        if (input.isKeyDown(Input.KEY_UP) | input.isKeyDown(Input.KEY_W)) {

            if (!gps.isBlocked(pos.x + w -4, pos.y - delta * SPEED) && !gps.isBlocked(pos.x + 4, pos.y - delta * SPEED)) {

                pos.y -= delta * SPEED;

            }

            hero = movementUp;

            hero.update(delta);

            lastDirection = 'u';

        } else if (input.isKeyDown(Input.KEY_DOWN) | input.isKeyDown(Input.KEY_S)) {

            if (!gps.isBlocked(pos.x + w - 4, pos.y + h + delta * SPEED) && !gps.isBlocked(pos.x + 4, pos.y + h + delta * SPEED)) {

                pos.y += delta * SPEED;

            }hero = movementDown;

            hero.update(delta);

            lastDirection = 'd';

        } else if (input.isKeyDown(Input.KEY_LEFT) | input.isKeyDown(Input.KEY_A)) {

            if (!gps.isBlocked(pos.x - delta * SPEED, pos.y + 4) && !gps.isBlocked(pos.x - delta * SPEED, pos.y + h - 4)) {

                pos.x -= delta * SPEED;

            }

            hero = movementLeft;

            hero.update(delta);

            lastDirection = 'l';



        } else if (input.isKeyDown(Input.KEY_RIGHT) | input.isKeyDown(Input.KEY_D)) {

            if (!gps.isBlocked(pos.x + w + delta * SPEED, pos.y + h - 4) && !gps.isBlocked(pos.x + w + delta * SPEED, pos.y + 4)) {

                pos.x += delta * SPEED;

            }

            hero = movementRight;

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

                    hero = stillDown;

                    break;

                case 'u':

                    hero = stillUp;

                    break;

                case 'l':

                    hero = stillLeft;

                    break;

                case 'r':

                    hero = stillRight;

                    break;

            }

        }

    }



    public void render() {

        hero.draw(pos.x, pos.y);
        skeleton.up.draw(300,300);
        armor.up.draw(300,300);
        pants.up.draw(300,300);
        skeleton.down.draw(364,300);
        armor.down.draw(364,300);
        pants.down.draw(364,300);
        skeleton.left.draw(364,364);
        armor.left.draw(364,364);
        pants.left.draw(364,364);
        skeleton.right.draw(300,364);
        armor.right.draw(300,364);
        pants.right.draw(300,364);

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