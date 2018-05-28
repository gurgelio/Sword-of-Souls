import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Animation;

class CharAnimation {
    private ArrayList<Anim> complete = new ArrayList<>();

    CharAnimation(Anim[] anims){
        complete.addAll(Arrays.asList(anims));
    }

    void update(int direction, int delt){
        for (Anim a : complete){
            a.update(a, direction, delt);
        }
    }

}
