import org.newdawn.slick.SlickException;

class SpearSkeleton extends Skeleton{

    SpearSkeleton(float x, float y, int[] stats, String[] equipment) throws SlickException {
        super(x, y, stats, equipment);
    }

    @Override
    void attack(){
        float[] vector = new float[]{Play.larry.getX() - getX(), Play.larry.getY() - getY()};
        if(Math.abs(vector[0]) > Math.abs(vector[1])){
            if(vector[0] < 0){
                direction = "left";
            } else direction = "right";
        } else if(vector[1] < 0){
            direction = "up";
        } else direction = "down";

        thrust();
    }

}
