import org.newdawn.slick.Graphics;

import java.util.ArrayList;

class DamageBox {
    private float x, y;
    private float damage, radius, duration;
    static private ArrayList<DamageBox> dmgBoxes = new ArrayList<>();

    private DamageBox(){}

    private DamageBox(float x, float y, int radius, float damage, String direction, int duration){
        this.damage = damage;
        this.radius = radius;
        this.duration = duration;
        this.x = x + 32;
        this.y = y + 32;

        switch (direction) {
            case "up":
                this.y -= (32 + radius);
                break;
            case "down":
                this.y += (32 + radius);
                break;
            case "right":
                this.x += (32 + radius);
                break;
            default:
                this.x -= (32 + radius);
                break;
        }

    }

    static void render(Graphics g){
        for(DamageBox box : dmgBoxes){
            g.drawOval(box.x, box.y, box.radius, box.radius);
        }
    }

    static void update(ArrayList<Entity> entities, int delta){
        ArrayList<DamageBox> deadBoxes = new ArrayList<>();
        for(DamageBox box : dmgBoxes){
            for(Entity e : entities){
                if(box.isOver(e)) {
                    box.hit(e);
                    deadBoxes.add(box);
                }
            }
            box.duration -= delta;
            if(box.duration <= 0) deadBoxes.add(box);
        }
        dmgBoxes.removeAll(deadBoxes);
    }

    private boolean isOver(Entity e){
        float[] hitbox = e.hitbox();
        if(hitbox[0] <= x && x <= hitbox[2]){
            return hitbox[1] <= y && y <= hitbox[3];
        }
        return false;
    }

    private void hit(Entity e){
        if(!e.isKnockedBack) {
            e.hp -= damage;
            float deltaX = 0, deltaY = 0;
            float[] hitbox = e.hitbox();
            float[] center = new float[]{(hitbox[0] + hitbox[2]) / 2, (hitbox[1] + hitbox[3]) / 2};
            e.setKnockback(center[0] - x, center[1] - y);
        }
    }

    static void createBox(float x, float y, int radius, float damage, String direction,int duration){
        dmgBoxes.add(new DamageBox(x, y, radius, damage, direction, duration));
    }


}
