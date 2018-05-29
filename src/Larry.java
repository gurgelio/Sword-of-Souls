import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Larry extends Entity{

    private char lastDirection;
    private static Vector2f pos;
    private Rectangle rectangle;
    private static final float SPEED = 0.1f;
    int w, h;
    private float tpCooldown = 0, auxTpCooldown = 0;

    Larry(float x, float y, String[] equipment) throws SlickException {
        super(equipment);
        w = 64;
        h = 64;
        pos = new Vector2f(x, y);
        rectangle = new Rectangle(x, y, w, h);
    }

    void update(GameContainer gc, int delta, Play gps) {
        tpCooldown += delta;
        //movimentação do personagem, caso não haja, para a animação do mesmo
        if (!(hp <= 0)) {
            animation.walk();
            if (In.keyHeld("r")) {
                for (Action act : animation.current) {
                    switch (lastDirection) {
                        case 'r':
                            act.Current.draw(pos.x + 64,pos.y);
                            break;
                        case 'l':
                            act.Current.draw(pos.x - 64, pos.y);
                            break;
                        case 'u':
                            act.Current.draw(pos.x, pos.y - 64);
                            break;
                        case 'd':
                            act.Current.draw(pos.x,pos.y + 64);
                            break;
                    }
                }
                animation.spell();
            }
            else if (In.keyReleased("r") && tpCooldown > 2000) {
                switch (lastDirection) {
                    case 'r':
                        pos.x += 64;
                        break;
                    case 'l':
                        pos.x -= 64;
                        break;
                    case 'u':
                        pos.y -= 64;
                        break;
                    case 'd':
                        pos.y += 64;
                        break;
                }

                tpCooldown = 0;
            }
            else if (In.buttonHeld("lmb")) {
                animation.thrust();
            } else if (!move(gps, delta)) {
                animation.stop();
            }
        } else animation.death();
        if (In.keyHeld("space")) {
            hp = 0;
        } else if (hp <= 0) {
            hp = 100;
            for (Action act : animation.current) {
                act.up.restart();
                act.left.restart();
                act.right.restart();
                act.down.restart();
            }
        }



            if (lastDirection == 'u') {
                animation.update(1, delta);
            } else if (lastDirection == 'l') {
                animation.update(3, delta);
            } else if (lastDirection == 'r') {
                animation.update(4, delta);
            } else if (lastDirection == 'd') {
                animation.update(2, delta);
            }
    }



    void render() {
        for (Action act : animation.current){
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
        if ((In.keyHeld("a") && !In.keyHeld("d")) || (In.keyHeld("left") && !In.keyHeld("right"))) {
            if (!gps.isBlocked(hitbox[0] - delta * SPEED, hitbox[1], w / 2, h / 2)) {
                pos.x -= delta * SPEED;
                movedX = true;
            }
            lastDirection = 'l';

        } else if ((In.keyHeld("d") && !In.keyHeld("a")) || (In.keyHeld("right") && !In.keyHeld("left"))) {

            if (!gps.isBlocked(hitbox[0] + delta * SPEED, hitbox[1], w / 2, h / 2)) {
                pos.x += delta * SPEED;
                movedX = true;
            }
            lastDirection = 'r';
        }

        if ((In.keyHeld("w") && !In.keyHeld("s")) || In.keyHeld("up") && !In.keyHeld("down")) {
            if (!gps.isBlocked(hitbox[0], hitbox[1] - delta * SPEED, w / 2, h / 2)) {
                pos.y -= delta * SPEED;
                movedY = true;
                if (!(gps.isBlocked(hitbox[0], hitbox[1] - delta * SPEED, w / 2, h / 2) || movedX))
                    pos.y -= delta * SPEED;
            }
            lastDirection = 'u';


        } else if ((In.keyHeld("s") && !In.keyHeld("w")) || In.keyHeld("down") && !In.keyHeld("up")) {

            if (!gps.isBlocked(hitbox[0], hitbox[1] + delta * SPEED, w / 2, h / 2)) {
                pos.y += delta * SPEED;
                movedY = true;
                if (!(gps.isBlocked(hitbox[0], hitbox[1] + delta * SPEED, w / 2, h / 2) || movedX))
                    pos.y += delta * SPEED;
            }
            lastDirection = 'd';
        }

        if (movedX && !movedY) {
            if (!gps.isBlocked(hitbox[0] - delta * SPEED, hitbox[1], w / 2, h / 2) && (In.keyHeld("a") || In.keyHeld("left"))) {
                pos.x -= delta * SPEED;
            } else if (!gps.isBlocked(hitbox[0] + delta * SPEED, hitbox[1], w / 2, h / 2)) pos.x += delta * SPEED;
        }

    return movedX || movedY;
}

    float[] hitbox(){
        return new float[]{(float) (getX() + w/4), (float) (getY() + h/2)};
    }
}