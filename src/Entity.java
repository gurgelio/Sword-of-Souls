import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import java.util.ArrayList;

abstract class Entity {
    int hp, strength, dexterity, constitution;
    float speed, atkDmg;

    String direction;
    Vector2f pos;
    ArrayList<Action> charAnimation = new ArrayList<>();
    private int w = 64, h = 64;

    private Inventory inventory;

    Entity(String[]equipment, int[] stats) throws SlickException {
        inventory = new Inventory(equipment);

        hp = (int) (100 + (Math.log10(1 + stats[2])/3));
        speed = (float) (Math.log10(stats[1] + 1)/9);
        atkDmg = (int) (10 + (Math.log10(1+ stats[0])/3));


        for (String st : inventory.getEquipped()){
            charAnimation.add(new Action(new Image("anim/"+st+".png"),64,64,120));
        }
        direction = "down";
    }

    static void render(ArrayList<Entity> entities){
        for(Entity e : entities) {
            for (Action act : e.charAnimation) act.render(e.pos.x, e.pos.y);
        }
    }

    void update(int delta, Mapa map){
    }

    void setItem(Image img, Action act){
        act.setItem(img, 120, 64, 64);
    }

    static void update(ArrayList<Entity> entities, int delta, Mapa map){
        for(Entity e : entities){
            e.update(delta, map);
        }
    }

    float getX() {
        return pos.x;
    }

    float getY() {
        return pos.y;
    }


    void setpos(float x, float y) {
        this.pos.x = 32*x;
        this.pos.y = 32*y;
    }

    float[] collisionBox() {
        return new float[]{getX() + w/2, getY() + 3*h/4, w/4};
    }

    float[] hitbox() { return new float[]{getX(), getY(), getX() + w, getY() + h}; }

    void die() {
        for (Action act : charAnimation) {
            act.setState("die", speed);
        }
    }

    void thrust() {
        for (Action act : charAnimation) {
            act.setState("thrust", speed);
            if (act.isStopped()) {
                act.setFrame(4);
                act.start();
                DamageBox.createBox(this.getX(), this.getY(), 8, atkDmg, direction, 120);
            }
        }
    }

    void cast() {
        for (Action act : charAnimation) {
            act.start();
            act.setState("cast", speed);
            if (act.isStopped()) {
                //spell effect
                act.setFrame(0);
            }
        }
    }

    void slash() {
        for (Action act : charAnimation) {
            act.setState("slash", speed);
            if (act.getFrame() == act.slash.length - 1){
                DamageBox.createBox(this.getX(), this.getY(), 8, atkDmg, direction,120);
            }
        }
    }

    void shoot() {
        for (Action act : charAnimation) {
            act.setState("shoot", speed);
            if (act.getFrame() == 12) {
                act.setFrame(4);
            }
        }
    }

    Inventory getInventory(){
        return this.inventory;
    }

    static void deleteDeadEntities(ArrayList<Entity> entities){
        ArrayList<Entity> delete = new ArrayList<>();
        for(Entity e : entities) if(e.hp <= 0 && e.charAnimation.get(0).isStopped()) delete.add(e);
        entities.removeAll(delete);
    }
}