class CharAnimation {
    private Anim complete;

    CharAnimation(Anim an){
        complete = an;
        complete.current = an.getAnimList();
    }

    void update(int direction, int delt){
        for (Action a : complete.current){
            a.update(a, direction, delt);
        }
    }

    void lastDir(char last){
        for (Action a : complete.current) {
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
