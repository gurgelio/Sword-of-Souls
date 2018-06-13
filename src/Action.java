import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;

class Action {
    private Animation[] walk = {null,null,null,null,null}, cast = {null,null,null,null,null}, shoot = {null,null,null,null,null}, current;
    Animation[] die = {null,null}, slash = {null,null,null,null,null}, thrust = {null,null,null,null,null}, stop = {null,null,null,null,null};

    Action(Image img, int x, int y, int deltaFrame){

        for (Animation[] anim : new Animation[][] {stop, cast, thrust, walk, slash, shoot}){
            for (int j : new int[] {0,0,4,8,12,16})
                for (int i : new int[] {0,1,2,3}){
                    System.out.println(i+j);
                    System.out.print(anim[i]);
                    anim[i] = new Animation(new SpriteSheet(img.getSubImage(0,(i + j) * y, img.getWidth(), y), x, y),deltaFrame);
            }
            anim[4] = anim[0];
        }

        die[0] = new Animation(new SpriteSheet(img.getSubImage(0,20*y, img.getWidth(), y), x, y),deltaFrame);
        die[1] = die[0];

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

    void render(float x, float y){
        this.current[4].draw(x, y);
    }

    boolean isStopped(){
        return this.current[4].isStopped();
    }

    void start(){
        this.current[4].start();
    }

    void setState(String state){
        switch(state){
            case "walk":
                this.current = this.walk;
                break;

            case "slash":
                this.current = this.slash;
                break;

            case "cast":
                this.current = this.cast;
                break;

            case "thrust":
                this.current = this.thrust;
                break;

            case "shoot":
                this.current = this.shoot;
                break;

            case "die":
                this.current = this.die;
                break;

            case "stop":
                this.current = this.stop;
                break;
        }
    }
}