import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;

class Anim {
    private static String dir = "anim/lpc_entry/png/";
    private static String walkdir = dir+"walkcycle/";
    private static String castdir = dir+"spellcast/";
    private static String thrustdir = dir+"thrust/";
    private static String deathdir = dir+"hurt/";
    ArrayList<Action> current = new ArrayList<>();
    private ArrayList<Action> walk = new ArrayList<>();
    private ArrayList<Action> thrust = new ArrayList<>();
    private ArrayList<Action> death = new ArrayList<>();
    private ArrayList<Action> spell = new ArrayList<>();
    private ArrayList<Action> stop = new ArrayList<>();

    Anim(ArrayList<String> strList) throws SlickException {
        for (String st : strList){
            walk.add(new Action(new Image(walkdir+st),64,64, 120));
            thrust.add(new Action(new Image(thrustdir+st),64,64,120));
            death.add(new Action(new Image(deathdir+st),64,64,270));
            spell.add(new Action(new Image(castdir+st),64,64,120));
            stop.add(new Action(new Image(walkdir+st),64,64, 120));
        }

        for(Action act : walk) {
            act.up.setDuration(0, 0);
            act.down.setDuration(0, 0);
            act.left.setDuration(0, 0);
            act.right.setDuration(0, 0);
        }

        for(Action act : thrust){
            act.up.setDuration(0, 0);
            act.up.setDuration(act.up.getFrameCount() - 1, 800);
            act.down.setDuration(0, 0);
            act.down.setDuration(act.down.getFrameCount() - 1, 800);
            act.left.setDuration(0, 0);
            act.left.setDuration(act.left.getFrameCount() - 1, 800);
            act.right.setDuration(0, 0);
            act.right.setDuration(act.right.getFrameCount() - 1, 800);
        }


        for(Action act : death){
            act.up.setDuration(0, 0);
            act.up.setLooping(false);
            act.down.setDuration(0, 0);
            act.down.setLooping(false);
            act.left.setDuration(0, 0);
            act.left.setLooping(false);
            act.right.setDuration(0, 0);
            act.right.setLooping(false);
        }


        for(Action act : spell) {
            act.up.setDuration(0, 0);
            act.up.setDuration(act.up.getFrameCount() - 1, 2000);
            act.down.setDuration(0, 0);
            act.down.setDuration(act.down.getFrameCount() - 1, 2000);
            act.left.setDuration(0, 0);
            act.left.setDuration(act.left.getFrameCount() - 1, 2000);
            act.right.setDuration(0, 0);
            act.right.setDuration(act.right.getFrameCount() - 1, 2000);
        }

        for(Action act : stop){
            act.Current.stop();
            act.up.stop();
            act.left.stop();
            act.right.stop();
            act.down.stop();
        }
        current.addAll(walk);
    }

    void update(int direction, int delt){
        for (Action a : current){
            a.update(a, direction, delt);
        }
    }

    void thrust(){
        this.current.clear();
        this.current.addAll(thrust);
    }

    void walk(){
        this.current.clear();
        this.current.addAll(walk);
    }

    void stop(){
        this.current.clear();
        this.current.addAll(stop);
    }

    void death(){
        this.current.clear();
        this.current.addAll(death);
    }

    void spell(){
        this.current.clear();
        this.current.addAll(spell);
    }

}