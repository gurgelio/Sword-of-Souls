import java.util.HashMap;
import java.util.Map;

public class Inventory {
    static String dir = "anim/lpc_entry/png/";
    static String walkdir = dir+"walkcycle/";
    static String castdir = dir+"spellcast/";
    static String thrustdir = dir+"thrust/";
    static String deathdir = dir+"hurt/";
    static Map<String, String> Items = new HashMap<>();

    static void init(){
        Items.put("blondehair", walkdir+"HEAD_hair_blonde.png");
    }

    //Walk Cycle:

    static String blondeHair = "HEAD_hair_blonde.png";
    static String brownShirt = "TORSO_robe_shirt_brown.png";
    static String brownShoes = "FEET_shoes_brown.png";
    static String greenPants = "LEGS_pants_greenish.png";
    static String maleBody = "BODY_male.png";
    static String quiver = "BEHIND_quiver.png";
    static String leatherBelt = "BELT_leather.png";
    static String ropeBelt = "BELT_rope.png";
    static String armorShoes = "FEET_plate_armor_shoes.png";
    static String armorGloves = "HANDS_plate_armor_gloves.png";
    static String chainHelmet = "HEAD_chain_armor_helmet.png";
    static String chainHood = "HEAD_chain_armor_hood.png";
    static String leatherHat = "HEAD_leather_armor_hat.png";
    static String plateHelmet = "HEAD_plate_armor_helmet.png";
    static String hood = "HEAD_robe_hood.png";
    static String armorPants = "LEGS_plate_armor_pants.png";
    static String leatherSkirt = "LEGS_robe_skirt.png";
    static String purpleArmor = "TORSO_chain_armor_jacket_purple.png";
    static String chainArmor = "TORSO_chain_armor_torso.png";
    static String leatherBracers = "TORSO_leather_armor_bracers.png";
    static String whiteShirt = "TORSO_leather_armor_shirt_white.png";
    static String leatherShoulders = "TORSO_leather_armor_shoulders.png";
    static String leatherArmor = "TORSO_leather_armor_torso.png";
    static String plateShoulders = "TORSO_plate_armor_arms_shoulders.png";
    static String plateArmorTorso = "TORSO_plate_armor_torso.png";
    static String bodyShield = "WEAPON_shield_cutout_body.png";
    static String shield = "WEAPON_shield_cutout_chain_armor_helmet.png";
    static String skeletonBody = "BODY_skeleton.png";
}
