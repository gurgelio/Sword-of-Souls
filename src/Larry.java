import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

class Larry extends Entity {

    Larry(float x, float y, String[] equipment) throws SlickException {
        super(equipment, 1, 1, 1, 1);
        pos = new Vector2f(x, y);
    }

    @Override
    void update(int delta, Mapa map) {
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
                walk(map, delta);
            }
        } else die();
        if (In.keyHeld("space")) {
            hp = 0;
        } else if (hp <= 0) {
            hp = 100;
        }
        animation.update(direction, delta);
        if (In.keyPressed("lshift")) {
            cast();
            setpos(3*w/2, 38*h/2);
        }

        if (In.keyPressed("tab")){
            setpos(32,128);
        }


    }
  
    private void walk(Mapa map, int delta) {
        boolean movedX = false;
        boolean movedY = false;
        float[] hitbox;

        if ((In.keyHeld("a") && !In.keyHeld("d")) || (In.keyHeld("left") && !In.keyHeld("right"))) {
            hitbox = hitbox();
            if (!map.isBlocked(hitbox[0] - delta * speed, hitbox[1], hitbox[2])) {
                pos.x -= delta * speed;
                movedX = true;
            }
            direction = "left";

        } else if ((In.keyHeld("d") && !In.keyHeld("a")) || (In.keyHeld("right") && !In.keyHeld("left"))) {
            hitbox = hitbox();
            if (!map.isBlocked(hitbox[0] + delta * speed, hitbox[1], hitbox[2])) {
                pos.x += delta * speed;
                movedX = true;
            }
            direction = "right";
        }
        if ((In.keyHeld("w") && !In.keyHeld("s")) || In.keyHeld("up") && !In.keyHeld("down")) {
            hitbox = hitbox();
            if (!map.isBlocked(hitbox[0], hitbox[1] - delta * speed, hitbox[2])) {
                pos.y -= delta * speed;
                movedY = true;
                hitbox = hitbox();
                if (!(map.isBlocked(hitbox[0], hitbox[1] - delta * speed, hitbox[2]) || movedX))
                    pos.y -= delta * speed;
            }
            direction = "up";
        } else if ((In.keyHeld("s") && !In.keyHeld("w")) || In.keyHeld("down") && !In.keyHeld("up")) {
            hitbox = hitbox();
            if (!map.isBlocked(hitbox[0], hitbox[1] + delta * speed, hitbox[2])) {
                pos.y += delta * speed;
                movedY = true;
                hitbox = hitbox();
                if (!(map.isBlocked(hitbox[0], hitbox[1] + delta * speed, hitbox[2]) || movedX))
                    pos.y += delta * speed;
            }
            direction = "down";
        }

        if (movedX && !movedY) {
            hitbox = hitbox();
            if (!map.isBlocked(hitbox[0] - delta * speed, hitbox[1], hitbox[2]) && (In.keyHeld("a") || In.keyHeld("left"))) {
                pos.x -= delta * speed;
            } else if (!map.isBlocked(hitbox[0] + delta * speed, hitbox[1], hitbox[2])) pos.x += delta * speed;
        }
        if (movedX || movedY) {
            animation.setState("walk");
        } else animation.setState("stop");
    }
}