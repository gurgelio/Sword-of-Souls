import org.newdawn.slick.SlickException;

class SpearSkeleton extends Skeleton{

    SpearSkeleton(float x, float y, int[] stats, String[] equipment) throws SlickException {
        super(x, y, stats, equipment);
    }

    @Override
    void attack(){

        thrust();
    }

}
