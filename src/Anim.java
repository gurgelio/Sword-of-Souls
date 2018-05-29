import org.newdawn.slick.SlickException;

import java.util.ArrayList;

class Anim {
    ArrayList<Actions> action = new ArrayList<>();

    Anim(Actions[] actList) throws SlickException {
        for(Actions ac : actList){
            action.add(ac);
        }
    }

    ArrayList<Actions> getAnimList(){
        return action;
    }
}