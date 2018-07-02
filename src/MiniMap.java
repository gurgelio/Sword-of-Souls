/*

 */

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

    void render(Graphics g, Entity larry) {
        g.drawImage(minimap, Camera.getX() + Game.width - 128, Camera.getY() + Game.height - 128);
        g.drawRect(Camera.getX() + Game.width - 129 + toScale(Camera.getX(), 'x'), Camera.getY() + Game.height - 129 + toScale(Camera.getY(), 'y'), toScale(Game.width, 'x'), toScale(Game.height, 'y'));
        g.drawRect(Camera.getX() + Game.width - 127 + toScale(larry.getX(), 'x'), Camera.getY() + Game.height - 126 + toScale(larry.getY(), 'y'), 1, 1);
    }

    private float toScale(float v, char axis){
        if(axis == 'x') return v * scaleX;
        return v * scaleY;
    }

    void setMinimap(Image img, Mapa map){
        this.minimap = img;
        scaleX = (float) minimap.getWidth()/map.getWidth();
        scaleY = (float) minimap.getHeight()/map.getHeight();
    }

}
