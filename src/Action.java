import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;

class Action {
    private Animation[] walk = {null,null,null,null,null}, cast = {null,null,null,null,null}, shoot = {null,null,null,null,null}, current;
    Animation[] die = {null,null,null,null,null}, slash = {null,null,null,null,null}, thrust = {null,null,null,null,null}, stop = {null,null,null,null,null};

    Action(Image img, int x, int y, int deltaFrame){

        stop[0] = new Animation(new Image[] {img.getSubImage(0,0,x,y)}, deltaFrame);
        stop[1] = new Animation(new Image[] {img.getSubImage(0,y,x,y)}, deltaFrame);
        stop[2] = new Animation(new Image[] {img.getSubImage(0,2*y,x,y)}, deltaFrame);
        stop[3] = new Animation(new Image[] {img.getSubImage(0,3*y,x,y)}, deltaFrame);
        stop[4] = stop[0];

        cast[0] = new Animation(new SpriteSheet(img.getSubImage(0,0, 7*x, y), x, y),2*deltaFrame);
        cast[1] = new Animation(new SpriteSheet(img.getSubImage(0, y, 7*x, y), x, y),2*deltaFrame);
        cast[2] = new Animation(new SpriteSheet(img.getSubImage(0,2*y, 7*x, y), x, y),2*deltaFrame);
        cast[3] = new Animation(new SpriteSheet(img.getSubImage(0,3*y, 7*x, y), x, y),2*deltaFrame);
        cast[4] = cast[0];

        thrust[0] = new Animation(new SpriteSheet(img.getSubImage(0,4*y, 8*x, y), x, y),deltaFrame);
        thrust[1] = new Animation(new SpriteSheet(img.getSubImage(0, 5*y, 8*x, y), x, y),deltaFrame);
        thrust[2] = new Animation(new SpriteSheet(img.getSubImage(0,6*y, 8*x, y), x, y),deltaFrame);
        thrust[3] = new Animation(new SpriteSheet(img.getSubImage(0,7*y, 8*x, y), x, y),deltaFrame);
        thrust[4] = thrust[0];

        walk[0] = new Animation(new SpriteSheet(img.getSubImage(0,8*y, 9*x, y), x, y),deltaFrame);
        walk[1] = new Animation(new SpriteSheet(img.getSubImage(0, 9*y, 9*x, y), x, y),deltaFrame);
        walk[2] = new Animation(new SpriteSheet(img.getSubImage(0,10*y, 9*x, y), x, y),deltaFrame);
        walk[3] = new Animation(new SpriteSheet(img.getSubImage(0,11*y, 9*x, y), x, y),deltaFrame);
        walk[4] = walk[0];

        slash[0] = new Animation(new SpriteSheet(img.getSubImage(0,12*y, 6*x, y), x, y),2*deltaFrame);
        slash[1] = new Animation(new SpriteSheet(img.getSubImage(0, 13*y, 6*x, y), x, y),2*deltaFrame);
        slash[2] = new Animation(new SpriteSheet(img.getSubImage(0,14*y, 6*x, y), x, y),2*deltaFrame);
        slash[3] = new Animation(new SpriteSheet(img.getSubImage(0,15*y, 6*x, y), x, y),2*deltaFrame);
        slash[4] = slash[0];

        shoot[0] = new Animation(new SpriteSheet(img.getSubImage(0,16*y, 13*x, y), x, y),deltaFrame);
        shoot[1] = new Animation(new SpriteSheet(img.getSubImage(0, 17*y, 13*x, y), x, y),deltaFrame);
        shoot[2] = new Animation(new SpriteSheet(img.getSubImage(0,18*y, 13*x, y), x, y),deltaFrame);
        shoot[3] = new Animation(new SpriteSheet(img.getSubImage(0,19*y, 13*x, y), x, y),deltaFrame);
        shoot[4] = shoot[0];

        die[0] = new Animation(new SpriteSheet(img.getSubImage(0,20*y, 6*x, y), x, y),3*deltaFrame);
        die[1] = die[0];
        die[2] = die[0];
        die[3] = die[0];
        die[4] = die[0];

        for (Animation an : slash){
            an.setDuration(0,0);
        }

        for(Animation an : thrust){
            an.setDuration(7, 530);
            an.setLooping(false);
        }

        for (Animation an : walk){
            an.setDuration(0, 0);
        }

        for(Animation an : die) {
            an.setCurrentFrame(0);
            an.setLooping(false);
        }

        for(Animation an : cast){
            an.setLooping(false);
        }

        for(Animation an : stop) {
            an.stop();
        }

        current = stop;

    }

    void render(float x, float y){
        this.current[4].draw(x, y);
    }

    void update(String direction, int delta){

        if ("up".equals(direction)) this.current[4] = this.current[0];
        else if ("left".equals(direction)) this.current[4] = this.current[1];
        else if ("right".equals(direction)) this.current[4] = this.current[3];
        else this.current[4] = this.current[2];

        this.current[4].update(delta);

    }

    int getFrame(){
        return this.current[4].getFrame();
    }

    void setFrame(int index){
        this.current[4].setCurrentFrame(index);
    }



    boolean isStopped(){
        return this.current[4].isStopped();
    }

    void start(){
        this.current[4].start();
    }

    void setItem(Image img, int deltaFrame, int x, int y){
        stop[0] = new Animation(new Image[] {img.getSubImage(0,0,x,y)}, deltaFrame);
        stop[1] = new Animation(new Image[] {img.getSubImage(0,y,x,y)}, deltaFrame);
        stop[2] = new Animation(new Image[] {img.getSubImage(0,2*y,x,y)}, deltaFrame);
        stop[3] = new Animation(new Image[] {img.getSubImage(0,3*y,x,y)}, deltaFrame);
        stop[4] = stop[0];

        cast[0] = new Animation(new SpriteSheet(img.getSubImage(0,0, 7*x, y), x, y),2*deltaFrame);
        cast[1] = new Animation(new SpriteSheet(img.getSubImage(0, y, 7*x, y), x, y),2*deltaFrame);
        cast[2] = new Animation(new SpriteSheet(img.getSubImage(0,2*y, 7*x, y), x, y),2*deltaFrame);
        cast[3] = new Animation(new SpriteSheet(img.getSubImage(0,3*y, 7*x, y), x, y),2*deltaFrame);
        cast[4] = cast[0];

        thrust[0] = new Animation(new SpriteSheet(img.getSubImage(0,4*y, 8*x, y), x, y),deltaFrame);
        thrust[1] = new Animation(new SpriteSheet(img.getSubImage(0, 5*y, 8*x, y), x, y),deltaFrame);
        thrust[2] = new Animation(new SpriteSheet(img.getSubImage(0,6*y, 8*x, y), x, y),deltaFrame);
        thrust[3] = new Animation(new SpriteSheet(img.getSubImage(0,7*y, 8*x, y), x, y),deltaFrame);
        thrust[4] = thrust[0];

        walk[0] = new Animation(new SpriteSheet(img.getSubImage(0,8*y, 9*x, y), x, y),deltaFrame);
        walk[1] = new Animation(new SpriteSheet(img.getSubImage(0, 9*y, 9*x, y), x, y),deltaFrame);
        walk[2] = new Animation(new SpriteSheet(img.getSubImage(0,10*y, 9*x, y), x, y),deltaFrame);
        walk[3] = new Animation(new SpriteSheet(img.getSubImage(0,11*y, 9*x, y), x, y),deltaFrame);
        walk[4] = walk[0];

        slash[0] = new Animation(new SpriteSheet(img.getSubImage(0,12*y, 6*x, y), x, y),2*deltaFrame);
        slash[1] = new Animation(new SpriteSheet(img.getSubImage(0, 13*y, 6*x, y), x, y),2*deltaFrame);
        slash[2] = new Animation(new SpriteSheet(img.getSubImage(0,14*y, 6*x, y), x, y),2*deltaFrame);
        slash[3] = new Animation(new SpriteSheet(img.getSubImage(0,15*y, 6*x, y), x, y),2*deltaFrame);
        slash[4] = slash[0];

        shoot[0] = new Animation(new SpriteSheet(img.getSubImage(0,16*y, 13*x, y), x, y),deltaFrame);
        shoot[1] = new Animation(new SpriteSheet(img.getSubImage(0, 17*y, 13*x, y), x, y),deltaFrame);
        shoot[2] = new Animation(new SpriteSheet(img.getSubImage(0,18*y, 13*x, y), x, y),deltaFrame);
        shoot[3] = new Animation(new SpriteSheet(img.getSubImage(0,19*y, 13*x, y), x, y),deltaFrame);
        shoot[4] = shoot[0];

        die[0] = new Animation(new SpriteSheet(img.getSubImage(0,20*y, 6*x, y), x, y),3*deltaFrame);
        die[1] = die[0];
        die[2] = die[0];
        die[3] = die[0];
        die[4] = die[0];
    }

    void setState(String state, float speed){
        switch(state){
            case "walk":
                this.current = setSpeed(walk, speed*8);
                break;

            case "slash":
                this.current = setSpeed(slash, speed*10);
                break;

            case "cast":
                this.current = setSpeed(cast, 0.75f);
                break;

            case "thrust":
                this.current = setSpeed(thrust, speed*6);
                break;

            case "shoot":
                this.current = setSpeed(shoot, speed*4);
                break;

            case "die":
                this.current = this.die;
                break;

            case "stop":
                this.current = this.stop;
                break;
        }
    }

    private Animation[] setSpeed(Animation[] state, double speed){
        for(Animation an : state){
            an.setSpeed((float) speed);
        }
        return state;
    }
}