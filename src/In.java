import org.lwjgl.input.Mouse;

class In {
    private static int[] getMouse(){
        return new int[] {Mouse.getX(), Game.height -Mouse.getY()};
    }

    static boolean mouseIsOver(int[] area){ //x, y, width, height
        int[] mouse = getMouse();
        if(area[0] >= mouse[0] && mouse[0] >= area[2] && area[1] >= mouse[1] && mouse[1] >= area[3]) return true;
        return false;
    }

    static boolean mouseIsPressed(int button){
        if(true) return true; //testar pressionamento de botão
        return false;

    }
}