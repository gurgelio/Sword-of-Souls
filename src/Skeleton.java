import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

class Skeleton extends Entity{
    private Vector2f guardposition;

    Skeleton(float x, float y, int[] stats, String[] equipment) throws SlickException {
        super(equipment, stats);
        pos = new Vector2f(x, y);
        guardposition = new Vector2f(x, y);
    }

    @Override
    void update(int delta, Mapa map){
        if(hp <= 0) {
            die();
            return;
        }

        for (Action act : charAnimation) act.update(direction, delta);

        double distance = Math.sqrt(Math.pow(getX() + 24 - Play.larry.getX() , 2) + Math.pow(getY() + 32 - Play.larry.getY(), 2));
        if(distance <= 500){
            if(distance <= 50){
                attack();
            } else follow(Play.larry.getX(), Play.larry.getY(), delta, map);

        } else follow(guardposition.x, guardposition.y, delta, map);
    }

    private void follow(float x, float y, int delta, Mapa map){
        boolean movedX = false, movedY = false;
        float[] hitbox;

        if(this.getX() > x) {
            hitbox = collisionBox();
            if (!map.isBlocked(hitbox[0] - delta * speed, hitbox[1], hitbox[2] - 1)) {
                pos.x -= delta * speed;
                movedX = true;
            }
            direction = "left";
        } else if(getX() < x){
            hitbox = collisionBox();
            if (!map.isBlocked(hitbox[0] + delta * speed, hitbox[1], hitbox[2] - 1)) {
                pos.x += delta * speed;
                movedX = true;
            }
            direction = "right";
        }
        if(this.getY() > y){
            hitbox = collisionBox();
            if (!map.isBlocked(hitbox[0], hitbox[1] - delta * speed, hitbox[2] - 1)) {
                pos.y -= delta * speed;
                movedY = true;
                hitbox = collisionBox();
                if (!(map.isBlocked(hitbox[0], hitbox[1] - delta * speed, hitbox[2] - 1) || movedX))
                    pos.y -= delta * speed;
            }
            direction = "up";
        } else if(this.getY() < y){
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


    void attack(){
    }

}
