import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

class Anim {
    Animation up, down, left, right;
    Animation upStill, downStill, leftStill, rightStill, Current;

    Anim(Image img, int x, int y, int AnimSpeed) throws SlickException {

        SpriteSheet upSheet = new SpriteSheet(img.getSubImage(x,0,img.getWidth() - x, y), x, y);
        SpriteSheet leftSheet = new SpriteSheet(img.getSubImage(x, y,img.getWidth() - x,y), x, y);
        SpriteSheet downSheet = new SpriteSheet(img.getSubImage(x,2*y,img.getWidth() - x,y), x, y);
        SpriteSheet rightSheet = new SpriteSheet(img.getSubImage(x,3*y,img.getWidth() - x,y), x, y);
        Image[] upStillImage = {img.getSubImage(0,0, x, y)};
        Image[] leftStillImage = {img.getSubImage(0,y, x, y)};
        Image[] downStillImage = {img.getSubImage(0,2*y, x, y)};
        Image[] rightStillImage = {img.getSubImage(0,3*y, x, y)};

        up = new Animation(upSheet,AnimSpeed);
        left = new Animation(leftSheet,AnimSpeed);
        down = new Animation(downSheet,AnimSpeed);
        right = new Animation(rightSheet,AnimSpeed);
        upStill = new Animation(upStillImage,AnimSpeed);
        leftStill = new Animation(leftStillImage,AnimSpeed);
        downStill = new Animation(downStillImage,AnimSpeed);
        rightStill = new Animation(rightStillImage,AnimSpeed);
        Current = upStill;

    }

    void update(Anim an, int direction, int del){

        /* 1 - Up
           2 - Down
           3 - Left
           4 - Right
         */

        switch (direction){
            case 1:
                an.Current = an.up;
                break;

            case 2:
                an.Current = an.down;
                break;

            case 3:
                an.Current = an.left;
                break;

            case 4:
                an.Current = an.right;
                break;
        }

    an.Current.update(del);

    }

}
