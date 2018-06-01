import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

public class Larry extends Entity {

    Larry(float x, float y, String[] equipment) throws SlickException {
        super(equipment, 1, 1, 1, 1);
        pos = new Vector2f(x, y);
    }

    @Override
    void update(GameContainer gc, int delta, Play gps) {
        if (hp > 0) {
            if (In.buttonHeld("lmb")) {
                slash();
            }else if (In.keyHeld("q")){
                thrust();
            }else if (In.keyHeld("r")) {
                cast();
            }else if (In.keyHeld("e")) {
                shoot();
            } else {
                animation.setState("stop");
                walk(gps, delta);
            }
        } else die();
        if (In.keyHeld("space")) {
            hp = 0;
        } else if (hp <= 0) {
            hp = 100;
        }
        animation.update(direction, delta);

    }

    void walk(Play gps, int delta) {
        boolean movedX = false;
        boolean movedY = false;
        float[] hitbox;

        if ((In.keyHeld("a") && !In.keyHeld("d")) || (In.keyHeld("left") && !In.keyHeld("right"))) {
            hitbox = hitbox();
            if (!gps.isBlocked(hitbox[0] - delta * speed, hitbox[1], w / 2, h / 2)) {
                pos.x -= delta * speed;
                movedX = true;
            }
            direction = "left";

        } else if ((In.keyHeld("d") && !In.keyHeld("a")) || (In.keyHeld("right") && !In.keyHeld("left"))) {
            hitbox = hitbox();
            if (!gps.isBlocked(hitbox[0] + delta * speed, hitbox[1], w / 2, h / 2)) {
                pos.x += delta * speed;
                movedX = true;
            }
            direction = "right";
        }
        if ((In.keyHeld("w") && !In.keyHeld("s")) || In.keyHeld("up") && !In.keyHeld("down")) {
            hitbox = hitbox();
            if (!gps.isBlocked(hitbox[0], hitbox[1] - delta * speed, w / 2, h / 2)) {
                pos.y -= delta * speed;
                movedY = true;
                hitbox = hitbox();
                if (!(gps.isBlocked(hitbox[0], hitbox[1] - delta * speed, w / 2, h / 2) || movedX))
                    pos.y -= delta * speed;
            }
            direction = "up";
        } else if ((In.keyHeld("s") && !In.keyHeld("w")) || In.keyHeld("down") && !In.keyHeld("up")) {
            hitbox = hitbox();
            if (!gps.isBlocked(hitbox[0], hitbox[1] + delta * speed, w / 2, h / 2)) {
                pos.y += delta * speed;
                movedY = true;
                hitbox = hitbox();
                if (!(gps.isBlocked(hitbox[0], hitbox[1] + delta * speed, w / 2, h / 2) || movedX))
                    pos.y += delta * speed;
            }
            direction = "down";
        }

        if (movedX && !movedY) {
            hitbox = hitbox();
            if (!gps.isBlocked(hitbox[0] - delta * speed, hitbox[1], w / 2, h / 2) && (In.keyHeld("a") || In.keyHeld("left"))) {
                pos.x -= delta * speed;
            } else if (!gps.isBlocked(hitbox[0] + delta * speed, hitbox[1], w / 2, h / 2)) pos.x += delta * speed;
        }
        if (movedX || movedY) animation.setState("walk");
    }
}