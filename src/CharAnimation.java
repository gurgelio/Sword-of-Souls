import java.util.ArrayList;

class CharAnimation {
    private ArrayList<Actions> complete;

    CharAnimation(ArrayList<Actions> anims){
        complete = anims;
    }

    void update(int direction, int delt){
        for (Actions a : complete){
            a.update(a, direction, delt);
        }
    }

    void lastDir(char last){
        for (Actions a : complete) {
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
