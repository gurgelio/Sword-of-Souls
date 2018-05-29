import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

class Anim {
    private static String dir = "anim/lpc_entry/png/";
    static String walkdir = dir+"walkcycle/";
    static String castdir = dir+"spellcast/";
    static String thrustdir = dir+"thrust/";
    static String deathdir = dir+"hurt/";
    ArrayList<Action> current = new ArrayList<>();
    private ArrayList<Action> walk = new ArrayList<>();
    private ArrayList<Action> thrust = new ArrayList<>();
    private ArrayList<Action> death = new ArrayList<>();
    private ArrayList<Action> spell = new ArrayList<>();

    Anim(ArrayList<Action> actList){
        current.addAll(actList);
    }

    Anim(ArrayList<String> strList, String directory) throws SlickException {
        for (String st : strList){
            walk.add(new Action(new Image(walkdir+st),64,64, 120));
            thrust.add(new Action(new Image(thrustdir+st),64,64,120));
            death.add(new Action(new Image(deathdir+st),64,64,120));
            spell.add(new Action(new Image(castdir+st),64,64,120));
        }
    }

    ArrayList<Action> getAnimList(){
        return current;
    }
}