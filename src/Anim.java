import java.util.ArrayList;
import java.util.Collections;

class Anim {
    private ArrayList<Action> action = new ArrayList<>();

    Anim(ArrayList<Action> actList){
        action.addAll(actList);
    }

    ArrayList<Action> getAnimList(){
        return action;
    }
}