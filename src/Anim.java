import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

public class Anim {
    public int AnimSpeed = 120;
    public Animation up, down, left, right, upStill, downStill, leftStill, rightStill;

    public Anim(Image img, int x, int y) throws SlickException {

        SpriteSheet upSheet = new SpriteSheet(img.getSubImage(x,0,img.getWidth() - x, y), x, y);
        SpriteSheet downSheet = new SpriteSheet(img.getSubImage(x,0,img.getWidth() - x,2*y), x, y);
        SpriteSheet leftSheet = new SpriteSheet(img.getSubImage(x,0,img.getWidth() - x,3*y), x, y);
        SpriteSheet rightSheet = new SpriteSheet(img.getSubImage(x,0,img.getWidth() - x,4*y), x, y);
        Image[] upStillImage = {img.getSubImage(0,0, x, y)};
        Image[] leftStillImage = {img.getSubImage(0,y, x, 2*y)};
        Image[] downStillImage = {img.getSubImage(0,2*y, x, 3*y)};
        Image[] rightStillImage = {img.getSubImage(0,3*y, x, 4*y)};

        up = new Animation(upSheet,AnimSpeed);
        left = new Animation(leftSheet,AnimSpeed);
        down = new Animation(downSheet,AnimSpeed);
        right = new Animation(rightSheet,AnimSpeed);
        upStill = new Animation(upStillImage,AnimSpeed);
        leftStill = new Animation(leftStillImage,AnimSpeed);
        downStill = new Animation(downStillImage,AnimSpeed);
        rightStill = new Animation(rightStillImage,AnimSpeed);

    }
}
