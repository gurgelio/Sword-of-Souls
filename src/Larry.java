import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Larry {

    private char lastDirection;
    private Vector2f pos;
    private Rectangle rectangle;
    private static final float SPEED = 0.09f;
    private int w, h;

    private Anim body = new Anim(new Image("anim/lpc_entry/png/walkcycle/BODY_male.png"),64,64, 120);
    private Anim shirt = new Anim(new Image("anim/lpc_entry/png/walkcycle/TORSO_robe_shirt_brown.png"),64,64,120);
    private Anim hair = new Anim(new Image("anim/lpc_entry/png/walkcycle/HEAD_hair_blonde.png"),64,64,120);
    private Anim legs = new Anim(new Image("anim/lpc_entry/png/walkcycle/LEGS_pants_greenish.png"),64,64,120);
    private Anim[] anims = {body, shirt, hair, legs};

    private CharAnimation charAnimation = new CharAnimation(anims);

    Larry(float x, float y) throws SlickException {
        w = 32;
        h = 32;
        pos = new Vector2f(x, y);
        rectangle = new Rectangle(x, y, w, h);

    }


    void update(GameContainer gc, int delta, Play gps) {

        //movimentação do personagem, caso não haja, para a animação do mesmo
        if(!move(gps, delta))
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

    boolean move(Play gps, int delta){
        boolean movedX = false;
        boolean movedY = false;
        if ((In.keyHeld("a") && !In.keyHeld("d")) || (In.keyHeld("left") && !In.keyHeld("right"))){
            if (!gps.isBlocked(pos.x - delta * SPEED, pos.y, 16)){
                pos.x -= delta * SPEED;
                movedX = true;
            }

            charAnimation.update(3,delta);
            lastDirection = 'l';

        }else if ((In.keyHeld("d") && !In.keyHeld("a")) || (In.keyHeld("right") && !In.keyHeld("left"))){

            if (!gps.isBlocked(pos.x + delta * SPEED, pos.y, 16)){
                pos.x += delta * SPEED;
                movedX = true;
            }

            charAnimation.update(4,delta);
            lastDirection = 'r';
        }

        if ((In.keyHeld("w") && !In.keyHeld("s")) || In.keyHeld("up") && !In.keyHeld("down")){
            if (!gps.isBlocked(pos.x, pos.y + delta * SPEED, 16)){
                pos.y -= delta * SPEED;
                movedY = true;
                if(!movedX) pos.y -= delta * SPEED;
            }

            charAnimation.update(1,delta);
            lastDirection = 'u';


        }else if ((In.keyHeld("s") && !In.keyHeld("w")) || In.keyHeld("down") && !In.keyHeld("up")){

            if (!gps.isBlocked(pos.x, pos.y + delta * SPEED, 16)){
                pos.y += delta * SPEED;
                movedY = true;
                if(!movedX) pos.y += delta * SPEED;
            }

            charAnimation.update(2,delta);
            lastDirection = 'd';
        }

        if(movedX && !movedY){
            if(In.keyHeld("a") || In.keyHeld("left")){
                pos.x -= delta*SPEED;
            } else pos.x += delta*SPEED;
        }

        return movedX || movedY;
    }

}