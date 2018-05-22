import org.lwjgl.input.Mouse;
import org.newdawn.slick.Input;

class In {
    static final int LMB = 0;
    private static int[] getMouse(){
        return new int[] {Mouse.getX(), Mouse.getY()};
    }

    static boolean mouseIsOver(int[] area){ //x, y, width, height
        int[] mouse = getMouse();
        if(area[0] <= mouse[0] && mouse[0] <= area[2] && area[1] <= mouse[1] && mouse[1] <= area[3]) return true;
        return false;
    }

    static boolean mouseIsPressed(int button){
        if(Mouse.isButtonDown(button)) return true;
        return false;

    }
}