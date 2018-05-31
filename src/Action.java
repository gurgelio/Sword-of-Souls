import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;

class Action {
    Animation up, down, left, right;
    Animation Current;

    Action(Image img, int x, int y, int deltaFrame){

        SpriteSheet upSheet = new SpriteSheet(img.getSubImage(0,0,img.getWidth() - x, y), x, y);
        SpriteSheet leftSheet = new SpriteSheet(img.getSubImage(0, y,img.getWidth() - x,y), x, y);
        SpriteSheet downSheet = new SpriteSheet(img.getSubImage(0,2*y,img.getWidth() - x,y), x, y);
        SpriteSheet rightSheet = new SpriteSheet(img.getSubImage(0,3*y,img.getWidth() - x,y), x, y);

        up = new Animation(upSheet,deltaFrame);
        left = new Animation(leftSheet,deltaFrame);
        down = new Animation(downSheet,deltaFrame);
        right = new Animation(rightSheet,deltaFrame);
        Current = up;
    }

    void update(String direction, int delta){
        if ("down".equals(direction)) {
            Current = down;

        } else if ("left".equals(direction)) {
            Current = left;

        } else if ("right".equals(direction)) {
            Current = right;

        } else {
            Current = up;

        }
        Current.update(delta);

    }

}