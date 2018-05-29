import java.util.HashMap;
import java.util.Map;

public class Inventory {
    static String dir = "anim/lpc_entry/png/";
    static String walkdir = dir+"walkcycle/";
    static String castdir = dir+"spellcast/";
    static String thrustdir = dir+"thrust/";
    static Map<String, String> Items = new HashMap<>();

    static void init(){
        Items.put("blondehair", walkdir+"HEAD_hair_blonde.png");
    }

    //Walk Cycle:

    static String blondeHair = walkdir+"HEAD_hair_blonde.png";
    static String brownShirt = walkdir+"TORSO_robe_shirt_brown.png";
    static String brownShoes = walkdir+"FEET_shoes_brown.png";
    static String greenPants = walkdir+"LEGS_pants_greenish.png";
    static String maleBody = walkdir+"BODY_male.png";
    static String quiver = walkdir+"BEHIND_quiver.png";
    static String leatherBelt = walkdir+"BELT_leather.png";
    static String ropeBelt = walkdir+"BELT_rope.png";
    static String armorShoes = walkdir+"FEET_plate_armor_shoes.png";
    static String armorGloves = walkdir+"HANDS_plate_armor_gloves.png";
    static String chainHelmet = walkdir+"HEAD_chain_armor_helmet.png";
    static String chainHood = walkdir+"HEAD_chain_armor_hood.png";
    static String leatherHat = walkdir+"HEAD_leather_armor_hat.png";
    static String plateHelmet = walkdir+"HEAD_plate_armor_helmet.png";
    static String hood = walkdir+"HEAD_robe_hood.png";
    static String armorPants = walkdir+"LEGS_plate_armor_pants.png";
    static String leatherSkirt = walkdir+"LEGS_robe_skirt.png";
    static String purpleArmor = walkdir+"TORSO_chain_armor_jacket_purple.png";
    static String chainArmor = walkdir+"TORSO_chain_armor_torso.png";
    static String leatherBracers = walkdir+"TORSO_leather_armor_bracers.png";
    static String whiteShirt = walkdir+"TORSO_leather_armor_shirt_white.png";
    static String leatherShoulders = walkdir+"TORSO_leather_armor_shoulders.png";
    static String leatherArmor = walkdir+"TORSO_leather_armor_torso.png";
    static String plateShoulders = walkdir+"TORSO_plate_armor_arms_shoulders.png";
    static String plateArmorTorso = walkdir+"TORSO_plate_armor_torso.png";
    static String bodyShield = walkdir+"WEAPON_shield_cutout_body.png";
    static String shield = walkdir+"WEAPON_shield_cutout_chain_armor_helmet.png";
    static String skeletonBody = walkdir+"BODY_skeleton.png";

    //Spell Cast:


    static String maleBodyCasting = castdir+"BODY_male.png";
    static String greenPantsCasting = castdir+"LEGS_pants_greenish.png";
    static String blondeHairCasting = castdir+"HEAD_hair_blonde.png";
    static String brownShirtCasting = castdir+"TORSO_robe_shirt_brown.png";
    static String brownShoesCasting = castdir+"FEET_shoes_brown.png";

    //Death Cycle:

    static String deathdir = dir+"hurt/";
    static String maleBodyDying = deathdir+"BODY_male.png";
    static String greenPantsDying = deathdir+"LEGS_pants_greenish.png";
    static String blondeHairDying = deathdir+"HEAD_hair_blonde.png";
    static String brownShirtDying = deathdir+"TORSO_robe_shirt_brown.png";
    static String brownShoesDying = deathdir+"FEET_shoes_brown.png";

    //Thrust:


    static String maleBodyLance = thrustdir +"BODY_male.png";
    static String greenPantsLance = thrustdir +"LEGS_pants_greenish.png";
    static String blondeHairLance = thrustdir +"HEAD_hair_blonde.png";
    static String brownShirtLance = thrustdir +"TORSO_robe_shirt_brown.png";
    static String brownShoesLance = thrustdir +"FEET_shoes_brown.png";

}
