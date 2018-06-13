import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;

class Action {
    Animation up, down, left, right;
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
    // 0: Cast | 1: Thrust | 2: Walk | 3: Slash | 4: Bow | 5: Die
    Action(Image img, int x, int y, int deltaFrame, int num){

        SpriteSheet upSheet = new SpriteSheet(img.getSubImage(0,4*num*y, img.getWidth(), y), x, y);
        SpriteSheet leftSheet = new SpriteSheet(img.getSubImage(0, y + 4*num*y, img.getWidth(), y), x, y);
        SpriteSheet downSheet = new SpriteSheet(img.getSubImage(0,2*y + 4*num*y, img.getWidth(), y), x, y);
        SpriteSheet rightSheet = new SpriteSheet(img.getSubImage(0,3*y + 4*num*y, img.getWidth(), y), x, y);

        up = new Animation(upSheet,deltaFrame);
        left = new Animation(leftSheet,deltaFrame);
        down = new Animation(downSheet,deltaFrame);
        right = new Animation(rightSheet,deltaFrame);
        current_direction = up;
    }

    void update(String direction, int delta){
        if ("down".equals(direction)) {
            this.current_direction = down;

        } else if ("left".equals(direction)) {
            this.current_direction = left;

        } else if ("right".equals(direction)) {
            this.current_direction = right;

        } else {
            this.current_direction = up;

        }
        this.current_direction.update(delta);
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

    boolean isStopped(){
        return this.current_direction.isStopped();
    }

    void start(){
        this.current_direction.start();
    }
}