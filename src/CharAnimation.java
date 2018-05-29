import java.util.ArrayList;

class CharAnimation {
    private ArrayList<Action> complete;

    CharAnimation(ArrayList<Action> anims){
        complete = anims;
    }

    void update(int direction, int delt){
        for (Action a : complete){
            a.update(a, direction, delt);
        }
    }

    void lastDir(char last){
        for (Action a : complete) {
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
