import java.util.ArrayList;
import java.util.List;

public class Controls {
    static List<String> intendedActions() {
        List<String> actions = new ArrayList<>();

        // Directionals
        if ((In.keyHeld("a") || In.keyHeld("left")) &&
                !(In.keyHeld("d") || In.keyHeld("right"))) actions.add("moveLeft");
        else if ((In.keyHeld("d") || In.keyHeld("right")) &&
                !(In.keyHeld("a") || In.keyHeld("left"))) actions.add("moveRight");
        if ((In.keyHeld("w") || In.keyHeld("up")) &&
                !(In.keyHeld("s") || In.keyHeld("down"))) actions.add("moveUp");
        else if ((In.keyHeld("s") || In.keyHeld("down")) &&
                !(In.keyHeld("w") || In.keyHeld("up"))) actions.add("moveDown");

        if (In.keyHeld("lshift")) actions.add("run");

        return actions;
    }

    static boolean isMoving() {
        return intendedActions().contains("moveLeft") || intendedActions().contains("moveRight") ||
                intendedActions().contains("moveUp") || intendedActions().contains("moveDown");
    }

    static String getMovingDirection() {
        if (intendedActions().contains("moveLeft")) return "left";
        else if (intendedActions().contains("moveRight")) return "right";
        else if (intendedActions().contains("moveUp")) return "up";
        else if (intendedActions().contains("moveDown")) return "down";
        else return "";
    }
}
