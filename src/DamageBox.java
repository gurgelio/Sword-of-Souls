import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import java.util.ArrayList;

class DamageBox {
    private float x, y;
    private int damage, radius, duration;
    static private ArrayList<DamageBox> dmgBoxes = new ArrayList<>();

    private DamageBox(){}

    private DamageBox(float x, float y, int radius, int damage, String direction, int duration){
        this.damage = damage;
        this.radius = radius;
        this.duration = duration;
        this.x = x + 24;
        this.y = y + 32;

        switch (direction) {
            case "up":
                this.y -= 32;
                break;
            case "down":
                this.y += 32;
                break;
            case "right":
                this.x += 32;
                break;
            default:
                this.x -= 32;
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
                if(box.isOver(e)) box.hit(e);
            }
            box.duration -= delta;
            if(box.duration <= 0) deadBoxes.add(box);
        }

        dmgBoxes.removeAll(deadBoxes);
    }

    private boolean isOver(Entity e){
        float[] hitbox = e.hitbox();
        if(hitbox[0] <= x && x <= hitbox[2]){
            if(hitbox[1] <= y && y <= hitbox[3]){
                return true;
            }
        }
        return false;
    }

    private void hit(Entity e){
        e.hp -= damage;
        float deltaX = 0, deltaY = 0;
        float[] hitbox = e.hitbox();
        switch (e.direction){
            case "up":
                deltaY += radius;
                break;
            case "down":
                deltaY -= radius;
                break;
            case "left":
                deltaX -= radius;
            case "right":
                deltaX += radius;
        }
        e.pos = new Vector2f(e.getX() + deltaX, e.getY() + deltaY);
    }

    static void createBox(float x, float y, int radius, int damage, String direction,int duration){
        dmgBoxes.add(new DamageBox(x, y, radius, damage, direction, duration));
    }


}
