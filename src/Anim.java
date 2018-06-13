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


    private ArrayList<Action> current = new ArrayList<>();

    private String state;
    private Action sword, spear, bow, arrow;

    Anim(ArrayList<String> strList) throws SlickException {

        for (String st : strList) { //carregamento das animações
            current.add(new Action(new Image(st), 64, 64, 120));
        }
        // Weapon handling
        sword = new Action(new Image(slashdir + "WEAPON_short sword_male.png"), 64, 64, 120);
        spear = new Action(new Image(thrustdir + "WEAPON_spear.png"), 64, 64, 120);
        bow = new Action(new Image(shootdir + "WEAPON_bow.png"), 64, 64, 120);
        arrow = new Action(new Image(shootdir + "WEAPON_arrow.png"), 64, 64, 120);

        for (Animation an : sword.slash) {
            an.setDuration(0, 0);
        }

        for(Animation an : spear.thrust){
            an.setDuration(7, 530);
            an.setLooping(false);
        }

        /*
        configuração especial de cada animação
         */



        state = "";
    }

    void update(String direction, int delta) { // Não pronto
        for (Action a : current) {
            a.update(direction, delta);
        }
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

    boolean isStopped(){
        return this.current.get(0).isStopped();
    }

    void start(){
        for(Action act : this.current){
            act.start();
        }
    }
}