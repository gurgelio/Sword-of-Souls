import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

abstract class Skeleton extends Entity{
    private Vector2f guardposition;
    private int killTime = 0;

    Skeleton(float x, float y, int[] stats, String[] equipment) throws SlickException {
        super(equipment, stats);
        pos = new Vector2f(x, y);
        guardposition = new Vector2f(x, y);
    }

    @Override
    void update(int delta, Mapa map, Larry larry){
        killTime += delta;
        if(hp <= 0) {
            die();
            if (killTime > 4000) {
                larry.getInventory().addGold(50);
                larry.hp += 10;
                killTime = 0;
            }
            return;
        }

        for (Action act : charAnimation) act.update(direction, delta);
        float[] larryHitbox = Play.larry.hitbox();
        float[] hitbox = hitbox();
        double distance = Math.sqrt(Math.pow((hitbox[0] + hitbox[2])/2 - (larryHitbox[0]+larryHitbox[2])/2, 2) + Math.pow((hitbox[1] + hitbox[3])/2 - (larryHitbox[1]+larryHitbox[3])/2, 2));
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
        if(distance <= 400){
            if(distance <= 64){
                attack();
            } else follow(Play.larry.getX(), Play.larry.getY(), delta, map);

        } else follow(guardposition.x, guardposition.y, delta, map);
    }

    private void follow(float x, float y, int delta, Mapa map){
        boolean movedX = false, movedY = false;
        float[] hitbox;
        if(Math.abs(getX() - x) > 0.1) {
            if (this.getX() > x) {
                hitbox = collisionBox();
                if (!map.isBlocked(hitbox[0] - delta * speed, hitbox[1], hitbox[2] - 1)) {
                    pos.x -= delta * speed;
                    movedX = true;
                }
                direction = "left";
            } else if (getX() < x) {
                hitbox = collisionBox();
                if (!map.isBlocked(hitbox[0] + delta * speed, hitbox[1], hitbox[2] - 1)) {
                    pos.x += delta * speed;
                    movedX = true;
                }
                direction = "right";
            }
        }
        if(Math.abs(getY() - y) > 0.1) {
            if (this.getY() > y) {
                hitbox = collisionBox();
                if (!map.isBlocked(hitbox[0], hitbox[1] - delta * speed, hitbox[2] - 1)) {
                    pos.y -= delta * speed;
                    movedY = true;
                    hitbox = collisionBox();
                    if (!(map.isBlocked(hitbox[0], hitbox[1] - delta * speed, hitbox[2] - 1) || movedX))
                        pos.y -= delta * speed;
                }
                direction = "up";
            } else if (this.getY() < y) {
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
        }

        if (movedX && !movedY) {
            hitbox = collisionBox();
            if (!map.isBlocked(hitbox[0] - delta * speed, hitbox[1], hitbox[2] - 1) && getX() > x) {
                pos.x -= delta * speed;
            } else if (!map.isBlocked(hitbox[0] + delta * speed, hitbox[1], hitbox[2] - 1)) pos.x += delta * speed;
        }

        if (movedX || movedY) {
            for (Action act : charAnimation) act.setState("walk", speed);
        } else for (Action act : charAnimation) act.setState("stop", speed);
    }


    void attack(){
    }


}
