import java.util.ArrayList;
import java.util.Collections;

class Anim {
    private ArrayList<Action> action = new ArrayList<>();

    Anim(Action[] actList){
        Collections.addAll(action, actList);
    }

    ArrayList<Action> getAnimList(){
        return action;
    }
}