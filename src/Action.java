import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;

class Action {
    Animation up, down, left, right;
    Animation[] walk = {null,null,null,null,null}, cast = {null,null,null,null,null}, shoot = {null,null,null,null,null};
    Animation[] die = {null,null}, slash = {null,null,null,null,null}, thrust = {null,null,null,null,null}, stop = {null,null,null,null,null};
    Animation[][] allActions = {walk, cast, shoot, die, slash, thrust, stop};
    private Animation current_direction;

    Action(Image img, int x, int y, int deltaFrame){

        SpriteSheet upSheet = new SpriteSheet(img.getSubImage(0,0,img.getWidth(), y), x, y);
        SpriteSheet leftSheet = new SpriteSheet(img.getSubImage(0, y,img.getWidth(),y), x, y);
        SpriteSheet downSheet = new SpriteSheet(img.getSubImage(0,2*y,img.getWidth(),y), x, y);
        SpriteSheet rightSheet = new SpriteSheet(img.getSubImage(0,3*y,img.getWidth(),y), x, y);

        up = new Animation(upSheet,deltaFrame);
        left = new Animation(leftSheet,deltaFrame);
        down = new Animation(downSheet,deltaFrame);
        right = new Animation(rightSheet,deltaFrame);
        current_direction = up;
    }

    // Construtor para folha com todas as animações
    Action(Image img, int x, int y, int deltaFrame, int s){

        stop[0] = new Animation(new Image[] {img.getSubImage(0,0,x,y)}, deltaFrame);
        stop[1] = new Animation(new Image[] {img.getSubImage(0,y,x,y)}, deltaFrame);
        stop[2] = new Animation(new Image[] {img.getSubImage(0,2*y,x,y)}, deltaFrame);
        stop[3] = new Animation(new Image[] {img.getSubImage(0,3*y,x,y)}, deltaFrame);
        stop[4] = stop[0];

        cast[0] = new Animation(new SpriteSheet(img.getSubImage(0,0, img.getWidth(), y), x, y),deltaFrame);
        cast[1] = new Animation(new SpriteSheet(img.getSubImage(0, y, img.getWidth(), y), x, y),deltaFrame);
        cast[2] = new Animation(new SpriteSheet(img.getSubImage(0,2*y, img.getWidth(), y), x, y),deltaFrame);
        cast[3] = new Animation(new SpriteSheet(img.getSubImage(0,3*y, img.getWidth(), y), x, y),deltaFrame);
        cast[4] = cast[0];

        thrust[0] = new Animation(new SpriteSheet(img.getSubImage(0,4*y, img.getWidth(), y), x, y),deltaFrame);
        thrust[1] = new Animation(new SpriteSheet(img.getSubImage(0, 5*y, img.getWidth(), y), x, y),deltaFrame);
        thrust[2] = new Animation(new SpriteSheet(img.getSubImage(0,6*y, img.getWidth(), y), x, y),deltaFrame);
        thrust[3] = new Animation(new SpriteSheet(img.getSubImage(0,7*y, img.getWidth(), y), x, y),deltaFrame);
        thrust[4] = thrust[0];

        walk[0] = new Animation(new SpriteSheet(img.getSubImage(0,8*y, img.getWidth(), y), x, y),deltaFrame);
        walk[1] = new Animation(new SpriteSheet(img.getSubImage(0, 9*y, img.getWidth(), y), x, y),deltaFrame);
        walk[2] = new Animation(new SpriteSheet(img.getSubImage(0,10*y, img.getWidth(), y), x, y),deltaFrame);
        walk[3] = new Animation(new SpriteSheet(img.getSubImage(0,11*y, img.getWidth(), y), x, y),deltaFrame);
        walk[4] = walk[0];

        slash[0] = new Animation(new SpriteSheet(img.getSubImage(0,12*y, img.getWidth(), y), x, y),deltaFrame);
        slash[1] = new Animation(new SpriteSheet(img.getSubImage(0, 13*y, img.getWidth(), y), x, y),deltaFrame);
        slash[2] = new Animation(new SpriteSheet(img.getSubImage(0,14*y, img.getWidth(), y), x, y),deltaFrame);
        slash[3] = new Animation(new SpriteSheet(img.getSubImage(0,15*y, img.getWidth(), y), x, y),deltaFrame);
        slash[4] = slash[0];

        shoot[0] = new Animation(new SpriteSheet(img.getSubImage(0,16*y, img.getWidth(), y), x, y),deltaFrame);
        shoot[1] = new Animation(new SpriteSheet(img.getSubImage(0, 17*y, img.getWidth(), y), x, y),deltaFrame);
        shoot[2] = new Animation(new SpriteSheet(img.getSubImage(0,18*y, img.getWidth(), y), x, y),deltaFrame);
        shoot[3] = new Animation(new SpriteSheet(img.getSubImage(0,19*y, img.getWidth(), y), x, y),deltaFrame);
        shoot[4] = shoot[0];

        die[0] = new Animation(new SpriteSheet(img.getSubImage(0,20*y, img.getWidth(), y), x, y),deltaFrame);
        die[1] = die[0];
    }

    Action(Image img, int x, int y, int deltaFrame, String s){
        for (int i : new int[] {0,1,2,3,4}){
            stop[i] = new Animation(new Image[] {img.getSubImage(0,i * y, x, y)}, deltaFrame);
            cast[i] = new Animation(new SpriteSheet(img.getSubImage(0,i * y, img.getWidth(), y), x, y),deltaFrame);
            thrust[i] = new Animation(new SpriteSheet(img.getSubImage(0,(i + 4) * y, img.getWidth(), y), x, y),deltaFrame);
            walk[i] = new Animation(new SpriteSheet(img.getSubImage(0,(i + 8) * y, img.getWidth(), y), x, y),deltaFrame);
            slash[i] = new Animation(new SpriteSheet(img.getSubImage(0,(i + 12) * y, img.getWidth(), y), x, y),deltaFrame);
            shoot[i] = new Animation(new SpriteSheet(img.getSubImage(0,(i + 16) * y, img.getWidth(), y), x, y),deltaFrame);
        }
        die[0] = new Animation(new SpriteSheet(img.getSubImage(0,20*y, img.getWidth(), y), x, y),deltaFrame);

        stop[4] = stop[0];
        cast[4] = cast[0];
        thrust[4] = thrust[0];
        walk[4] = walk[0];
        slash[4] = slash[0];
        shoot[4] = shoot[0];
        die[1] = die[0];
    }

    void update(String direction, int delta){
        if ("up".equals(direction)) {
            this.current_direction = up;

        } else if ("left".equals(direction)) {
            this.current_direction = left;

        } else if ("right".equals(direction)) {
            this.current_direction = right;

        } else {
            this.current_direction = down;

        }
        this.current_direction.update(delta);
    }

    void update(String direction, int delta, Animation[] act){

        if ("up".equals(direction)) act[4] = act[0];
        else if ("left".equals(direction)) act[4] = act[1];
        else if ("down".equals(direction)) act[4] = act[2];
        else if ("right".equals(direction)) act[4] = act[3];

        act[4].update(delta);

    }

    int getFrame(){
        return this.current_direction.getFrame();
    }

    void setFrame(int index){
        current_direction.setCurrentFrame(index);
    }

    void render(float x, float y){
        this.current_direction.draw(x, y);
    }

    void render(float x, float y, Animation[] act){
        act[4].draw(x, y);
    }

    boolean isStopped(){
        return this.current_direction.isStopped();
    }

    void start(){
        this.current_direction.start();
    }
}