import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class Entity {
    //private Vector2f pos;
    //private int hp = 100, atack = 15, armor = 40;
    private Map<String, String> inventory;
    Anim current;
    CharAnimation chAnim;

    void setAnimation(Anim animationSet){
        chAnim = new CharAnimation(animationSet);
    }

    Entity(String[]equipment) throws SlickException {
        this.inventory = new HashMap<>();
        this.inventory.putAll(Items.createinventory(equipment));
        ArrayList<Action> actions = new ArrayList<>();
        if(inventory.containsKey("body")) actions.add(new Action(new Image(Anim.walkdir+ Items.items.get(inventory.get("body"))),64,64, 120));
        if(inventory.containsKey("head")) actions.add(new Action(new Image(Anim.walkdir+ Items.items.get(inventory.get("head"))),64,64,120));
        if(inventory.containsKey("torso")) actions.add(new Action(new Image(Anim.walkdir+ Items.items.get(inventory.get("torso"))),64,64,120));
        if(inventory.containsKey("hands")) actions.add(new Action(new Image(Anim.walkdir+ Items.items.get(inventory.get("hands"))),64,64,120));
        if(inventory.containsKey("belt")) actions.add(new Action(new Image(Anim.walkdir+ Items.items.get(inventory.get("belt"))),64,64,120));
        if(inventory.containsKey("legs")) actions.add(new Action(new Image(Anim.walkdir+ Items.items.get(inventory.get("legs"))),64,64,120));
        if(inventory.containsKey("feet")) actions.add(new Action(new Image(Anim.walkdir+ Items.items.get(inventory.get("feet"))),64,64,120));
        if(inventory.containsKey("weapon")) actions.add(new Action(new Image(Anim.walkdir+ Items.items.get(inventory.get("weapon"))),64,64,120));
        if(inventory.containsKey("behind")) actions.add(new Action(new Image(Anim.walkdir+ Items.items.get(inventory.get("behind"))),64,64,120));


        current = new Anim(actions);
        this.chAnim = new CharAnimation(current);

    }
}