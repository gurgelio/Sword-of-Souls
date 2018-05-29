import java.util.*;

public class Items {
    static Map<String, String> items = new HashMap<>();

    Map<String, String> equipment;

    static void init(){
        items.put("male body", "BODY_male.png");
        items.put("skeleton body", "BODY_skeleton.png");

        items.put("chain helmet", "HEAD_chain_armor_helmet.png");
        items.put("chain hood", "HEAD_chain_armor_hood.png");
        items.put("leather hat", "HEAD_leather_armor_hat.png");
        items.put("plate helmet", "HEAD_plate_armor_helmet.png");
        items.put("hood", "HEAD_robe_hood.png");
        items.put("blonde hair", "HEAD_hair_blonde.png");

        items.put("purple armor", "TORSO_chain_armor_jacket_purple.png");
        items.put("chain armor", "TORSO_chain_armor_torso.png");
        items.put("leather bracers", "TORSO_leather_armor_bracers.png");
        items.put("white shirt", "TORSO_leather_armor_shirt_white.png");
        items.put("leather shoulders", "TORSO_leather_armor_shoulders.png");
        items.put("leather armor", "TORSO_leather_armor_torso.png");
        items.put("plate shoulders", "TORSO_plate_armor_arms_shoulders.png");
        items.put("plate armor", "TORSO_plate_armor_torso.png");
        items.put("brown torso", "TORSO_robe_shirt_brown.png");

        items.put("armor gloves", "HANDS_plate_armor_gloves.png");

        items.put("leather belt", "BELT_leather.png");
        items.put("rope belt", "BELT_rope.png");

        items.put("armor pants", "LEGS_plate_armor_pants.png");
        items.put("leather skirt", "LEGS_robe_skirt.png");
        items.put("green pants", "LEGS_pants_greenish.png");

        items.put("brown shoes", "FEET_shoes_brown.png");
        items.put("armor shoes", "FEET_plate_armor_shoes.png");

        items.put("body shield", "WEAPON_shield_cutout_body.png");
        items.put("shield", "WEAPON_shield_cutout_chain_armor_helmet.png");

        items.put("quiver", "BEHIND_quiver.png");
    }

    static HashMap<String, String> createinventory(String[] inventory){
        HashMap<String, String> x = new HashMap<>();
        for(String item : inventory){
            if(items.get(item).contains("BODY")){
                x.put("body", item);
            } else if(items.get(item).contains("HEAD")){
                x.put("head", item);
            } else if(items.get(item).contains("TORSO")){
                x.put("torso", item);
            } else if(items.get(item).contains("HANDS")){
                x.put("hands", item);
            } else if(items.get(item).contains("BELT")){
                x.put("belt", item);
            } else if(items.get(item).contains("LEGS")){
                x.put("legs", item);
            } else if(items.get(item).contains("FEET")){
                x.put("feet", item);
            } else if(items.get(item).contains("WEAPON")){
                x.put("weapon", item);
            } else if(items.get(item).contains("BEHIND")){
                x.put("behind", item);
            }
        }
        return x;
    }
}
