import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

class Larry extends Entity {

    Larry(float x, float y, String[] equipment, int[] stats) throws SlickException {
        super(equipment, stats);
        pos = new Vector2f(x, y);
    }

    @Override
    void update(int delta, Mapa map, Larry larry) {
        if(hp <= 0){
            die();
            return;
        }

        if(hp > 100) hp = 100;

        if(isKnockedBack){
            if(knockBackTime <= 0){
                isKnockedBack = false;
            } else {
                pos.x += knockback[0] * delta / 1000;
                pos.y += knockback[1] * delta / 1000;
                knockBackTime -= delta;
                return;
            }
        }

        if (In.buttonHeld("lmb") && !getInventory().overArea()){
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


        for (Action act : charAnimation) {
            act.update(direction, delta);

        }

        if (In.keyPressed("lshift")) setpos(3, 38);
        if (In.keyPressed("tab")) setpos(1,4);
        if (In.keyPressed("o")) setpos(43,25);

    }
  
    private void walk(Mapa map, int delta) {
        boolean movedX = false;
        boolean movedY = false;
        float[] hitbox;

        if ((In.keyHeld("a") && !In.keyHeld("d")) || (In.keyHeld("left") && !In.keyHeld("right"))) {
            hitbox = collisionBox();
            if (!map.isBlocked(hitbox[0] - delta * speed, hitbox[1], hitbox[2] - 1)) {
                pos.x -= delta * speed;
                movedX = true;
            }
            direction = "left";

        } else if ((In.keyHeld("d") && !In.keyHeld("a")) || (In.keyHeld("right") && !In.keyHeld("left"))) {
            hitbox = collisionBox();
            if (!map.isBlocked(hitbox[0] + delta * speed, hitbox[1], hitbox[2] - 1)) {
                pos.x += delta * speed;
                movedX = true;
            }
            direction = "right";
        }
        if ((In.keyHeld("w") && !In.keyHeld("s")) || In.keyHeld("up") && !In.keyHeld("down")) {
            hitbox = collisionBox();
            if (!map.isBlocked(hitbox[0], hitbox[1] - delta * speed, hitbox[2] - 1)) {
                pos.y -= delta * speed;
                movedY = true;
                hitbox = collisionBox();
                if (!(map.isBlocked(hitbox[0], hitbox[1] - delta * speed, hitbox[2] - 1) || movedX))
                    pos.y -= delta * speed;
            }
            direction = "up";
        } else if ((In.keyHeld("s") && !In.keyHeld("w")) || In.keyHeld("down") && !In.keyHeld("up")) {
            hitbox = collisionBox();
            if (!map.isBlocked(hitbox[0], hitbox[1] + delta * speed, hitbox[2] - 1)) {
                pos.y += delta * speed;
                movedY = true;
                hitbox = collisionBox();
                if (!(map.isBlocked(hitbox[0], hitbox[1] + delta * speed, hitbox[2] - 1) || movedX))
                    pos.y += delta * speed;
            }
            direction = "down";
        }

        if (movedX && !movedY) {
            hitbox = collisionBox();
            if (!map.isBlocked(hitbox[0] - delta * speed, hitbox[1], hitbox[2] - 1) && (In.keyHeld("a") || In.keyHeld("left"))) {
                pos.x -= delta * speed;
            } else if (!map.isBlocked(hitbox[0] + delta * speed, hitbox[1], hitbox[2] - 1)) pos.x += delta * speed;
        }
        if (movedX || movedY) {
            for (Action act : charAnimation) act.setState("walk", speed);
        } else for (Action act : charAnimation) act.setState("stop", speed);
    }
}