import org.newdawn.slick.*;

import org.newdawn.slick.geom.Rectangle;

import org.newdawn.slick.geom.Vector2f;

import org.newdawn.slick.state.StateBasedGame;



public class Larry {



    private Animation hero, movementUp, movementDown, movementLeft, movementRight, stillUp, stillDown, stillLeft, stillRight;

    private char lastDirection;

    private Vector2f pos;

    private Rectangle rectangle;

    private static final int ANIMATIONSPEED = 500;

    private static final float SPEED = 0.4f;

    private int w, h;



    public Larry(float x, float y) throws SlickException {

        /*

         * Set the Image arrays needed for the animations.

         */

        Image[] animationUp = {new Image("img/hero20.png"), new Image("img/hero22.png")};

        Image[] animationDown = {new Image("img/hero00.png"), new Image("img/hero02.png")};

        Image[] animationLeft = {new Image("img/hero30.png"), new Image("img/hero32.png")};

        Image[] animationRight = {new Image("img/hero10.png"), new Image("img/hero12.png")};

        Image[] up = {new Image("img/hero21.png")};

        Image[] down = {new Image("img/hero01.png")};

        Image[] left = {new Image("img/hero31.png")};

        Image[] right = {new Image("img/hero11.png")};


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

        movementUp = new Animation(animationUp, ANIMATIONSPEED);

        movementDown = new Animation(animationDown, ANIMATIONSPEED);

        movementLeft = new Animation(animationLeft, ANIMATIONSPEED);

        movementRight = new Animation(animationRight, ANIMATIONSPEED);



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