import org.newdawn.slick.TrueTypeFont;
import java.awt.Font;

class Fonte {

    private Font font;
    org.newdawn.slick.Color color;

    Fonte(String fonte, int style, int size, int r, int g, int b, int opacity){
        this.font = new Font(fonte, style, size);
        this.color = new org.newdawn.slick.Color(r, g, b, opacity);
    }

    int getWidth(String whatchars){
        TrueTypeFont ttf = new TrueTypeFont(this.font, true);
        return ttf.getWidth(whatchars);
    }

    int getHeight(String whatchars){
        TrueTypeFont ttf = new TrueTypeFont(this.font, true);
        return ttf.getHeight(whatchars);
    }

    static void ShadowFont(Button b){
        TrueTypeFont ttf = new TrueTypeFont(b.fonte.font, true);
        ttf.drawString(b.x - ttf.getWidth(b.st)/2, b.y - ttf.getHeight()/2 + 2, b.st, b.fonte.color.darker(0.28f));
        ttf.drawString(b.x - ttf.getWidth(b.st)/2 + 1, b.y - ttf.getHeight()/2 + 2 , b.st, b.fonte.color.darker(0.28f));
        ttf.drawString(b.x - ttf.getWidth(b.st)/2 + 2, b.y - ttf.getHeight()/2 + 2, b.st, b.fonte.color.darker(0.28f));
        ttf.drawString(b.x - ttf.getWidth(b.st)/2 + 2, b.y - ttf.getHeight()/2 + 1, b.st, b.fonte.color.darker(0.28f));
        ttf.drawString(b.x - ttf.getWidth(b.st)/2 + 2, b.y - ttf.getHeight()/2 , b.st, b.fonte.color.darker(0.28f));
        ttf.drawString(b.x - ttf.getWidth(b.st)/2 + 1, b.y - ttf.getHeight()/2 + 1, b.st, b.fonte.color.darker(0.18f));
        ttf.drawString(b.x - ttf.getWidth(b.st)/2, b.y - ttf.getHeight()/2 + 1, b.st, b.fonte.color.darker(0.18f));
        ttf.drawString(b.x - ttf.getWidth(b.st)/2 + 1, b.y - ttf.getHeight()/2 , b.st, b.fonte.color.darker(0.18f));
        ttf.drawString(b.x - ttf.getWidth(b.st)/2, b.y - ttf.getHeight()/2 , b.st, b.fonte.color);
    }

}
