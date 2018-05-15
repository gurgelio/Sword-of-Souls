import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class Mouse {
    int getX(GameContainer gc){
        Input input = gc.getInput();
        return input.getMouseX();
    }

    int getY(GameContainer gc){
        Input input = gc.getInput();
        return input.getMouseY();
    }

    boolean isPressed(GameContainer gc){
        Input input = gc.getInput();
        return input.isMousePressed(0);

    }
}