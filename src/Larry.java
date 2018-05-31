import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

public class Larry extends Entity {

    private String direction;
    private static final float SPEED = 0.1f;
    private float walkcd = 0;

    Larry(float x, float y, String[] equipment) throws SlickException {
        super(equipment);
        pos = new Vector2f(x, y);
    }

    @Override
    void update(GameContainer gc, int delta, Play gps) {
        if (hp > 0) {
            if (In.buttonPressed("rmb")) {
                thrust();
                walkcd = 1000;
            } else if (In.buttonPressed("r")) {
                cast();
                walkcd = 2000;
            } else if (In.buttonPressed("lmb")) {
                slash();
                walkcd = 1000;
            }
            if (walkcd <= 0) {
                walk(gps, delta);
            } else if (walkcd > 0) {
                walkcd -= delta;
            }

        } else die();
        if (In.keyHeld("space")) {
            hp = 0;
        } else if (hp <= 0) {
            hp = 100;
        }
        animation.update(direction, delta);

    }


    void render() {
        for (Action act : animation.current) {
            act.Current.draw(pos.x, pos.y);
        }
    }

    void walk(Play gps, int delta) {
        boolean movedX = false;
        boolean movedY = false;
        float[] hitbox = hitbox();

        if ((In.keyHeld("a") && !In.keyHeld("d")) || (In.keyHeld("left") && !In.keyHeld("right"))) {
            if (!gps.isBlocked(hitbox[0] - delta * SPEED, hitbox[1], w / 2, h / 2)) {
                pos.x -= delta * SPEED;
                movedX = true;
            }
            direction = "left";

        } else if ((In.keyHeld("d") && !In.keyHeld("a")) || (In.keyHeld("right") && !In.keyHeld("left"))) {

            if (!gps.isBlocked(hitbox[0] + delta * SPEED, hitbox[1], w / 2, h / 2)) {
                pos.x += delta * SPEED;
                movedX = true;
            }
            direction = "right";
        }

        if ((In.keyHeld("w") && !In.keyHeld("s")) || In.keyHeld("up") && !In.keyHeld("down")) {
            if (!gps.isBlocked(hitbox[0], hitbox[1] - delta * SPEED, w / 2, h / 2)) {
                pos.y -= delta * SPEED;
                movedY = true;
                if (!(gps.isBlocked(hitbox[0], hitbox[1] - delta * SPEED, w / 2, h / 2) || movedX))
                    pos.y -= delta * SPEED;
            }
            direction = "up";


        } else if ((In.keyHeld("s") && !In.keyHeld("w")) || In.keyHeld("down") && !In.keyHeld("up")) {

            if (!gps.isBlocked(hitbox[0], hitbox[1] + delta * SPEED, w / 2, h / 2)) {
                pos.y += delta * SPEED;
                movedY = true;
                if (!(gps.isBlocked(hitbox[0], hitbox[1] + delta * SPEED, w / 2, h / 2) || movedX))
                    pos.y += delta * SPEED;
            }
            direction = "down";
        }

        if (movedX && !movedY) {
            if (!gps.isBlocked(hitbox[0] - delta * SPEED, hitbox[1], w / 2, h / 2) && (In.keyHeld("a") || In.keyHeld("left"))) {
                pos.x -= delta * SPEED;
            } else if (!gps.isBlocked(hitbox[0] + delta * SPEED, hitbox[1], w / 2, h / 2)) pos.x += delta * SPEED;
        }
        if (movedX || movedY) {
            animation.setState("walk");
        } else animation.setState("stop");

    }
}