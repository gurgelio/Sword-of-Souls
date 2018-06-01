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
    private Action sword, spear, bow, arrow;

    Anim(ArrayList<String> strList, int dexterity) throws SlickException {

        for (String st : strList) { //carregamento das animações
            shoot.add(new Action(new Image(shootdir + st), 64, 64, (int) (230 - Math.sqrt(dexterity))));
            die.add(new Action(new Image(deathdir + st), 64, 64, 340));
            slash.add(new Action(new Image(slashdir + st), 64, 64, (int) (300 - Math.sqrt(dexterity))));
            cast.add(new Action(new Image(castdir + st), 64, 64, 350));
            thrust.add(new Action(new Image(thrustdir + st), 64, 64, (int) (250 - Math.pow(dexterity, 0.3))));
            walk.add(new Action(new Image(walkdir + st), 64, 64, (int) (100/(0.05f + dexterity))));
            stop.add(new Action(new Image(walkdir + st), 64, 64, 120));
        }
        // Weapon handling
        sword = new Action(new Image(slashdir + "WEAPON_sword.png"), 64, 64, (int) (300 - Math.sqrt(dexterity)));
        spear = new Action(new Image(thrustdir + "WEAPON_spear.png"), 64, 64, (int) (250 - Math.pow(dexterity, 0.3)));
        bow = new Action(new Image(shootdir + "WEAPON_bow.png"), 64, 64, (int) (230 - Math.sqrt(dexterity)));
        arrow = new Action(new Image(shootdir + "WEAPON_arrow.png"), 64, 64, (int) (230 - Math.sqrt(dexterity)));

        sword.up.setDuration(0, 0);
        sword.up.setPingPong(true);
        sword.down.setDuration(0, 0);
        sword.down.setPingPong(true);
        sword.left.setDuration(0, 0);
        sword.left.setPingPong(true);
        sword.right.setDuration(0, 0);
        sword.right.setPingPong(true);

        spear.up.setDuration(7, 530);
        spear.down.setDuration(7, 530);
        spear.left.setDuration(7, 530);
        spear.right.setDuration(7, 530);

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
        for (Action act : walk) {
            act.up.setDuration(0, 0);
            act.down.setDuration(0, 0);
            act.left.setDuration(0, 0);
            act.right.setDuration(0, 0);
        }


        for (Action act : die) {
            act.up.setLooping(false);
            act.down.setLooping(false);
            act.left.setLooping(false);
            act.right.setLooping(false);
        }


        for (Action act : cast) {
            act.up.setLooping(false);
            act.down.setLooping(false);
            act.left.setLooping(false);
            act.right.setLooping(false);
        }

        for (Action act : stop) {
            act.up.stop();
            act.left.stop();
            act.right.stop();
            act.down.stop();
        }

        state = "";
        setState("stop");
    }

    void update(String direction, int delta) {
        for (Action a : current) {
            a.update(direction, delta);
        }
    }

    void setState(String state) {
        if (this.state.equals(state)) return;
        current.clear();
        if ("thrust".equals(state)) {
            current.addAll(thrust);
            current.add(spear);
        } else if ("slash".equals(state)) {
            current.addAll(slash);
            current.add(sword);
        } else if ("cast".equals(state)) {
            current.addAll(cast);
        } else if ("die".equals(state)) {
            current.addAll(die);
        } else if ("shoot".equals(state)) {
            current.addAll(shoot);
            current.add(bow);
            current.add(arrow);
        } else if ("walk".equals(state)) {
            current.addAll(walk);
        } else current.addAll(stop);

        this.state = state;
        this.setFrame(0);
    }

    void render(float x, float y){
        for (Action act : current){
            act.render(x, y);
        }
    }

    String getState() {
        return state;
    }

    int getFrame(){
        return this.current.get(0).getFrame();
    }

    void setFrame(int index){
        for(Action act : current){
            act.setFrame(index);
        }
    }
}