import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;

class Anim {
    private static String dir = "anim/lpc_entry/png/";
    private static String walkdir = dir+"walkcycle/";
    private static String castdir = dir+"spellcast/";
    private static String thrustdir = dir+"thrust/";
    private static String deathdir = dir+"hurt/";
    private ArrayList<Action> current = new ArrayList<>();
    private ArrayList<Action> walk = new ArrayList<>();
    private ArrayList<Action> thrust = new ArrayList<>();
    private ArrayList<Action> death = new ArrayList<>();
    private ArrayList<Action> spell = new ArrayList<>();

    Anim(ArrayList<String> strList) throws SlickException {
        for (String st : strList){
            walk.add(new Action(new Image(walkdir+st),64,64, 120));
            thrust.add(new Action(new Image(thrustdir+st),64,64,120));
            death.add(new Action(new Image(deathdir+st),64,64,120));
            spell.add(new Action(new Image(castdir+st),64,64,120));
            current = walk;
        }
    }

    ArrayList<Action> getAnimList(){
        return current;
    }

    void update(int direction, int delt){
        for (Action a : current){
            a.update(a, direction, delt);
        }
    }

    void lastDir(char last){
        for (Action a : current) {
            switch (last) {
                case 'd':
                    a.Current = a.downStill;
                    break;

                case 'u':
                    a.Current = a.upStill;
                    break;

                case 'l':
                    a.Current = a.leftStill;
                    break;

                case 'r':
                    a.Current = a.rightStill;
                    break;
            }
        }
    }

    void thrust(){
        current.clear();
        current.addAll(thrust);
    }

}