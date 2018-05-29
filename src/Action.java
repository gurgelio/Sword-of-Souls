import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;

class Action {
    Animation up, down, left, right;
    Animation Current;

    Action(Image img, int x, int y, int AnimSpeed){

        SpriteSheet upSheet = new SpriteSheet(img.getSubImage(0,0,img.getWidth() - x, y), x, y);
        SpriteSheet leftSheet = new SpriteSheet(img.getSubImage(0, y,img.getWidth() - x,y), x, y);
        SpriteSheet downSheet = new SpriteSheet(img.getSubImage(0,2*y,img.getWidth() - x,y), x, y);
        SpriteSheet rightSheet = new SpriteSheet(img.getSubImage(0,3*y,img.getWidth() - x,y), x, y);

        up = new Animation(upSheet,AnimSpeed);
        left = new Animation(leftSheet,AnimSpeed);
        down = new Animation(downSheet,AnimSpeed);
        right = new Animation(rightSheet,AnimSpeed);
        Current = up;
    }

    void update(Action an, int direction, int del){

        /*
        1 - Up
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