import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class Entity {
    //private Vector2f pos;
    //private int hp = 100, atack = 15, armor = 40;
    private Map<String, String> inventory;
    Anim animation;

    Entity(String[]equipment) throws SlickException {
        this.inventory = new HashMap<>();
        this.inventory.putAll(Items.createinventory(equipment));
        ArrayList<String> actions = new ArrayList<>();
        for(String str : new String[] {"body", "head", "torso", "hands", "belt", "legs", "feet", "weapon", "behind"}){
            if(inventory.containsKey(str)) actions.add(Items.items.get(inventory.get(str)));
        }

        animation = new Anim(actions);

    }
}