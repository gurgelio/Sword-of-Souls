/*
Classe do jogador
*/

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

class Larry extends Entity {

    Larry(float x, float y, String[] equipment, int[] stats) throws SlickException {
        super(equipment, stats);
        pos = new Vector2f(x, y);
    }

    @Override
    void update(int delta, Mapa map, Larry larry) {
        if (hp < 0) die();
        if (hp > 100) hp = 100;

        if (Controls.intendedActions().contains("run")) super.setRunningSpeed();
        else super.setWalkingSpeed();

        handleKnockback(delta);

        // Attacks
        if (In.buttonHeld("lmb") && !getInventory().overArea()) slash();
        else if (In.keyHeld("q")) thrust();
        else if (In.keyHeld("r")) cast();
        else if (In.keyHeld("e")) shoot();
        else walk(map, delta);

        // Update animations
        for (Action act : charAnimation) act.update(direction, delta);

        // Teleport points (Debugging purposes)
        if (In.keyPressed("rshift")) setpos(3, 38);
        if (In.keyPressed("tab")) setpos(1,4);
        if (In.keyPressed("o")) setpos(43,25);

    }
  
    private void walk(Mapa map, int delta) {
        float[] hitbox = collisionBox();

        if (Controls.intendedActions().contains("moveLeft") && canMove(map, delta, hitbox, "left"))
            move("left", delta);
        if (Controls.intendedActions().contains("moveRight") && canMove(map, delta, hitbox, "right"))
            move("right", delta);
        if (Controls.intendedActions().contains("moveUp") && canMove(map, delta, hitbox, "up"))
            move("up", delta);
        if (Controls.intendedActions().contains("moveDown") && canMove(map, delta, hitbox, "down"))
            move("down", delta);

        if (Controls.isMoving())
            for (Action act : charAnimation) act.setState("walk", speed);
        else
            for (Action act : charAnimation) act.setState("stop", speed);
    }

    private boolean canMove(Mapa map, int delta, float[] hitbox, String dir) {
        switch (dir) {
            case "up":
                return !map.isBlocked(hitbox[0] + delta * speed, hitbox[1] - delta * speed, hitbox[2] - 1);
            case "down":
                return !map.isBlocked(hitbox[0] + delta * speed, hitbox[1] + delta * speed, hitbox[2] - 1);
            case "left":
                return !map.isBlocked(hitbox[0], hitbox[1], hitbox[2] - 1);
            case "right":
                return !map.isBlocked(hitbox[0] + 2 * delta * speed, hitbox[1], hitbox[2] - 1);
            default:
                return true;
        }
    }

    private void move(String dir, float delta) {
        direction = dir;
        if (dir.equals("up")) {
            pos.y -= delta * speed;
        } else if (dir.equals("down")) {
            pos.y += delta * speed;
        } else if (dir.equals("left")) {
            pos.x -= delta * speed;
        } else {
            pos.x += delta * speed;
        }
    }

    private void handleKnockback(float delta) {
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
    }
}