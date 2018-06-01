import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;

class Anim {
    private static String shootdir = "anim/shoot/";
    private static String deathdir = "anim/die/";
    private static String slashdir = "anim/slash/";
    private static String castdir = "anim/cast/";
    private static String thrustdir = "anim/thrust/";
    private static String walkdir = "anim/walk/";


    ArrayList<Action> current = new ArrayList<>();
    private ArrayList<Action> shoot = new ArrayList<>();
    private ArrayList<Action> die = new ArrayList<>();
    private ArrayList<Action> slash = new ArrayList<>();
    private ArrayList<Action> cast = new ArrayList<>();
    private ArrayList<Action> thrust = new ArrayList<>();
    private ArrayList<Action> walk = new ArrayList<>();
    private ArrayList<Action> stop = new ArrayList<>();
    private String state;
    private Action sword, spear, bow;

    Anim(ArrayList<String> strList) throws SlickException {


        for (String st : strList) { //carregamento das animações
            shoot.add(new Action(new Image(shootdir + st), 64, 64, 120));
            die.add(new Action(new Image(deathdir + st), 64, 64, 270));
            slash.add(new Action(new Image(slashdir + st), 64, 64, 120));
            cast.add(new Action(new Image(castdir + st), 64, 64, 120));
            thrust.add(new Action(new Image(thrustdir + st), 64, 64, 120));
            walk.add(new Action(new Image(walkdir + st), 64, 64, 120));
            stop.add(new Action(new Image(walkdir + st), 64, 64, 120));
        }

        // Weapon handling
        bow = new Action(new Image(shootdir+"WEAPON_bow.png"),64,64,120);
        sword = new Action(new Image(slashdir+"WEAPON_sword.png"),64,64,120);
        spear = new Action(new Image(thrustdir+"WEAPON_spear.png"),64,64,120);
        for(Animation an :new Animation[] {spear.up, spear.down, spear.left, spear.right, spear.Current}) {
            an.setDuration(spear.up.getFrameCount() - 1, 800);
            //an.setLooping(false);
        }


        /*
        configuração especial de cada animação
        */

        for(Action act : walk) {
            for(Animation an :new Animation[] {act.up, act.down, act.left, act.right, act.Current}) {
                an.setDuration(0, 0);
                }
        }

        for(Action act : thrust){
            for(Animation an :new Animation[] {act.up, act.down, act.left, act.right, act.Current}) {
                an.setDuration(act.up.getFrameCount() - 1, 800);
                //an.setLooping(false);
            }
        }

        for(Action act : die){
            for(Animation an : new Animation[] {act.up, act.down, act.left, act.right, act.Current}) {
                an.setDuration(0, 0);
                an.setLooping(false);
            }
        }

        for(Action act : cast) {
            for(Animation an : new Animation[] {act.up, act.down, act.left, act.right, act.Current}) {
                an.setDuration(0, 0);
                //an.setLooping(false);
            }
        }

        for(Action act : stop){
            for(Animation an : new Animation[] {act.up, act.down, act.left, act.right, act.Current}){
                an.stop();
            }
        }

        state = "";
        setState("stop");
    }

    void update(String direction, int delta){
        for (Action a : current){
            a.update(direction, delta);
        }
    }

    void setState(String state){
        if(this.state.equals(state)) return;

        current.clear();
        if ("thrust".equals(state)){
            current.addAll(thrust);
            current.add(spear);
        } else if ("slash".equals(state)){
            current.addAll(slash);
            current.add(sword);
        } else if ("cast".equals(state)){
            current.addAll(cast);

        } else if ("die".equals(state)){
            current.addAll(die);

        } else if ("shoot".equals(state)){
            current.addAll(shoot);
            current.add(bow);

        } else if ("walk".equals(state)){
            current.addAll(walk);
        } else current.addAll(stop);

        this.state = state;
    }
}

