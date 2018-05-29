import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import java.util.HashMap;
import java.util.Map;

public class Larry extends Entity{

    private char lastDirection;
    private static Vector2f pos;
    private Rectangle rectangle;
    private static final float SPEED = 0.1f;
    int w, h;

    private CharAnimation charAnimation = chAnim;

    Larry(float x, float y, String[] equipment) throws SlickException {
        super(equipment);
        w = 64;
        h = 64;
        pos = new Vector2f(x, y);
        rectangle = new Rectangle(x, y, w, h);



    }

    void update(GameContainer gc, int delta, Play gps) {
        //movimentação do personagem, caso não haja, para a animação do mesmo
        if(!move(gps, delta)){
            charAnimation.lastDir(lastDirection);
        }
    }


    void render() {
        for (Action act : walk.getAnimList()){
            act.Current.draw(pos.x, pos.y);
        }
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
        float[] hitbox = hitbox();
        if ((In.keyHeld("a") && !In.keyHeld("d")) || (In.keyHeld("left") && !In.keyHeld("right"))){
            if (!gps.isBlocked(hitbox[0] - delta * SPEED, hitbox[1], w/2, h/2)){
                pos.x -= delta * SPEED;
                movedX = true;
            }

            charAnimation.update(3,delta);
            lastDirection = 'l';

        }else if ((In.keyHeld("d") && !In.keyHeld("a")) || (In.keyHeld("right") && !In.keyHeld("left"))){

            if (!gps.isBlocked(hitbox[0] + delta * SPEED, hitbox[1], w/2, h/2)){
                pos.x += delta * SPEED;
                movedX = true;
            }

            charAnimation.update(4,delta);
            lastDirection = 'r';
        }

        if ((In.keyHeld("w") && !In.keyHeld("s")) || In.keyHeld("up") && !In.keyHeld("down")){
            if (!gps.isBlocked(hitbox[0], hitbox[1] - delta * SPEED, w/2, h/2)){
                pos.y -= delta * SPEED;
                movedY = true;
                if(!(gps.isBlocked(hitbox[0], hitbox[1] - delta * SPEED, w/2, h/2) || movedX)) pos.y -= delta * SPEED;
            }

            charAnimation.update(1,delta);
            lastDirection = 'u';


        }else if ((In.keyHeld("s") && !In.keyHeld("w")) || In.keyHeld("down") && !In.keyHeld("up")){

            if (!gps.isBlocked(hitbox[0], hitbox[1] + delta * SPEED, w/2, h/2)){
                pos.y += delta * SPEED;
                movedY = true;
                if(!(gps.isBlocked(hitbox[0], hitbox[1] + delta * SPEED, w/2, h/2) || movedX)) pos.y += delta * SPEED;
            }

            charAnimation.update(2,delta);
            lastDirection = 'd';
        }

        if(movedX && !movedY){
            if(!gps.isBlocked(hitbox[0] - delta * SPEED, hitbox[1], w/2, h/2) && (In.keyHeld("a") || In.keyHeld("left"))){
                pos.x -= delta*SPEED;
            } else if(!gps.isBlocked(hitbox[0] + delta * SPEED, hitbox[1], w/2, h/2)) pos.x += delta*SPEED;
        }
        return movedX || movedY;
    }

    float[] hitbox(){
        return new float[]{(float) (getX() + w/4), (float) (getY() + h/2)};
    }
}