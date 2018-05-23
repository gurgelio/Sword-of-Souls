import java.util.ArrayList;
import org.newdawn.slick.Animation;

public class CharAnimation {
    private ArrayList<Anim> complete = new ArrayList<>();

    public CharAnimation(Anim[] anims){
        for (Anim a: anims){
            complete.add(a);
        }
    }

    public void update(int direction, int delt){
        for (Anim a : complete){
            a.update(a, direction, delt);
        }
    }

}
