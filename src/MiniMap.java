import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;

class MiniMap {
    private Image minimap;
    private float scaleX, scaleY;

    MiniMap(Image minimap, Mapa map){
        this.minimap = minimap;
        scaleX = (float) minimap.getWidth()/map.getWidth();
        scaleY = (float) minimap.getHeight()/map.getHeight();
    }

    void render(Graphics g, Camera camera, Entity larry) {

        g.drawImage(minimap, camera.getX() + Game.width - 128, camera.getY() + Game.height - 128);
        g.drawRect(camera.getX() + Game.width - 128 + toScale(camera.getX(), 'x'), camera.getY() + Game.height - 128 + toScale(camera.getY(), 'y'), toScale(Game.width, 'x'), toScale(Game.height, 'y'));
        g.drawRect(camera.getX() + Game.width - 128 + toScale(larry.getX(), 'x'), camera.getY() + Game.height - 128 + toScale(larry.getY(), 'y'), 1, 1);
    }

    private float toScale(float v, char axis){
        if(axis == 'x') return v * scaleX;
        return v * scaleY;
    }

}
