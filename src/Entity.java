import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import java.util.ArrayList;
import java.util.Map;

abstract class Entity {
    private Vector2f pos;
    private int hp = 100, atack = 15, armor = 40;
    private Map<String, String> inventory;
    Action body, torso, head, legs, feet;
    Anim walk;
    CharAnimation chAnim;

    void setAnimation(ArrayList<Action> animationSet){
        chAnim = new CharAnimation(animationSet);
    }

    Entity(String[]equipment) throws SlickException {
        this.inventory.putAll(Items.createinventory(equipment));
        this.body = new Action(new Image(Items.walkdir+ Items.items.get(inventory.get("body"))),64,64, 120);
        this.torso = new Action(new Image(Items.walkdir+ Items.items.get(inventory.get("torso"))),64,64,120);
        this.head = new Action(new Image(Items.walkdir+ Items.items.get(inventory.get("head"))),64,64,120);
        this.legs = new Action(new Image(Items.walkdir+ Items.items.get(inventory.get("legs"))),64,64,120);
        this.feet = new Action(new Image(Items.walkdir+ Items.items.get(inventory.get("feet"))),64,64,120);
        this.walk = new Anim(new Action[] {body, torso, head, legs, feet});
        this.chAnim = new CharAnimation(walk.getAnimList());
    }
}