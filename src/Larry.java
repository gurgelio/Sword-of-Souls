import org.newdawn.slick.*;

import org.newdawn.slick.geom.Rectangle;

import org.newdawn.slick.geom.Vector2f;

import org.newdawn.slick.state.StateBasedGame;



public class Larry {



    private Animation hero, movementUp, movementDown, movementLeft, movementRight, stillUp, stillDown, stillLeft, stillRight;

    private Animation charNew, moveUp, moveDown, moveLeft, moveRight, moveStillUp;
    private Animation charcNew, movecUp, movecDown, movecLeft, movecRight, movecStillUp;


    private char lastDirection;

    private Vector2f pos;

    private Rectangle rectangle;

    private static final int ANIMATIONSPEED = 200, speed2 = 120;

    private static final float SPEED = 0.17f;

    private int w, h;



    public Larry(float x, float y) throws SlickException {

        /*

         * Set the Image arrays needed for the animations.

         */
        SpriteSheet animUp = new SpriteSheet("img/animSheetBack.png",24,32,0);
        SpriteSheet animDown = new SpriteSheet("img/animSheetFront.png",24,32,0);
        SpriteSheet animLeft = new SpriteSheet("img/animSheetLeft.png",24,32,0);
        SpriteSheet animRight = new SpriteSheet("img/animSheetRight.png",24,32,0);
        Image[] up = {new Image("img/stillUp.png")};
        Image[] down = {new Image("img/stillDown.png")};
        Image[] left = {new Image("img/stillLeft.png")};
        Image[] right = {new Image("img/stillRight.png")};

        SpriteSheet upNew = new SpriteSheet("img/1.png", 64,64);
        SpriteSheet leftNew = new SpriteSheet("img/2.png", 64,64);
        SpriteSheet downNew = new SpriteSheet("img/3.png", 64,64);
        SpriteSheet rightNew = new SpriteSheet("img/4.png", 64,64);
        SpriteSheet upcNew = new SpriteSheet("img/c1.png", 64,64);
        SpriteSheet leftcNew = new SpriteSheet("img/c2.png", 64,64);
        SpriteSheet downcNew = new SpriteSheet("img/c3.png", 64,64);
        SpriteSheet rightcNew = new SpriteSheet("img/c4.png", 64,64);
        Image[] sup = {new Image("img/1still.png")};
        Image[] supc = {new Image("img/c1still.png")};


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

        moveUp = new Animation(upNew, speed2);
        moveDown = new Animation(downNew, speed2);
        moveLeft = new Animation(leftNew, speed2);
        moveRight = new Animation(rightNew, speed2);
        movecUp = new Animation(upcNew, speed2);
        movecDown = new Animation(downcNew, speed2);
        movecLeft = new Animation(leftcNew, speed2);
        movecRight = new Animation(rightcNew, speed2);
        moveStillUp = new Animation(sup, speed2);
        movecStillUp = new Animation(supc, speed2);




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
        //moveUp.draw(300,300);
        //moveStillUp.draw(300,364);
        //movecUp.draw(300,300);
        //movecStillUp.draw(300,364);
        //moveDown.draw(364,300);
        //movecDown.draw(364,300);
        //moveLeft.draw(428,300);
        //movecLeft.draw(428,300);
        //moveRight.draw(492,300);
        //movecRight.draw(492,300);

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