import java.util.ArrayList;
import java.util.Arrays;

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

    void lastDir(char last){
        for (Anim a : complete) {
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

}
