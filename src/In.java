import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;

class In {
    static final int LMB = 0;
    static final int UP = 200;
    static final int DOWN = 208;
    static final int LEFT = 203;
    static final int RIGHT = 205;
    static final int KEY_W = 17;
    static final int KEY_A = 30;
    static final int KEY_S = 31;
    static final int KEY_D = 32;

    private static int[] getMouse(){
        return new int[] {Mouse.getX(), Mouse.getY()};
    }

    static boolean mouseIsOver(int[] area){ //x, y, width, height
        int[] mouse = getMouse();
        if(area[0] <= mouse[0] && mouse[0] <= area[2] && area[1] <= mouse[1] && mouse[1] <= area[3]) return true;
        return false;
    }

    static boolean mouseIsPressed(int button){
        return Mouse.isButtonDown(button);
    }

    static boolean keyIsPressed(int key){
        return Keyboard.isKeyDown(key);
    }

}