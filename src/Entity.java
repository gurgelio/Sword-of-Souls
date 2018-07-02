/*
A base de todos os personagens do jogo
Reune todos os atributos e métodos em comum de cada tipo de personagem para permitir manipulação destes em uma ArrayList
 */

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import java.util.ArrayList;

abstract class Entity {
    int hp;
    float speed, atkDmg;
    float[] knockback = new float[2];
    boolean isKnockedBack = false, dead = false;
    int knockBackTime;
    String direction;
    Vector2f pos;
    ArrayList<Action> charAnimation = new ArrayList<>();
    private int w = 64, h = 64;
    private Inventory inventory;

    Entity(String[]equipment, int[] stats) throws SlickException {
        inventory = new Inventory(equipment);

        hp = (int) (100 + (Math.log10(1 + stats[2])/3));
        speed = (float) (Math.log10(stats[1] + 1)/9);
        atkDmg = 10 + stats[0];


        for (String st : inventory.getEquipped()){
            charAnimation.add(new Action(new Image("anim/"+st+".png"),64,64,120));
        }
        direction = "down";
    }

    void update(int delta, Mapa map, Larry larry){}

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

    void cast() { //não implementado
        for (Action act : charAnimation) {
            act.setState("cast", speed);
            act.start();
            if (act.isStopped()) {
                //spell effect
                act.setState("stop", speed);
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

    void shoot() { //não implementado
        for (Action act : charAnimation) {
            act.setState("shoot", speed);
            if (act.getFrame() == 12) {
                act.setFrame(4);
                //lançamento de projétil (subclasse de DamageBox a ser criada)
            }
        }
    }

    void setKnockback(float deltaX, float deltaY){
        knockback[0] = deltaX;
        knockback[1] = deltaY;
        isKnockedBack = true;
        knockBackTime = 50;
        for(Action an : charAnimation){
            an.setFrame(0);
            an.setState("stop", 1);
        }
    }

    Inventory getInventory(){
        return this.inventory;
    }

    boolean isDead(){
        return dead;
    }
}