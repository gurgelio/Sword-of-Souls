import org.newdawn.slick.TrueTypeFont;
import java.awt.Font;

class Fonte {

    TrueTypeFont ttf;
    org.newdawn.slick.Color color;

    Fonte(String fonte, int style, int size, int r, int g, int b, int opacity){
        this.color = new org.newdawn.slick.Color(r, g, b, opacity);
        this.ttf = new TrueTypeFont(new Font(fonte, style, size), true);
    }

    static void ShadowFont(Button b){
        b.fonte.ttf.drawString(b.x, b.y + 2, b.st, b.fonte.color.darker(0.28f));
        b.fonte.ttf.drawString(b.x + 1, b.y + 2 , b.st, b.fonte.color.darker(0.28f));
        b.fonte.ttf.drawString(b.x + 2, b.y + 2, b.st, b.fonte.color.darker(0.28f));
        b.fonte.ttf.drawString(b.x + 2, b.y + 1, b.st, b.fonte.color.darker(0.28f));
        b.fonte.ttf.drawString(b.x + 2, b.y, b.st, b.fonte.color.darker(0.28f));
        b.fonte.ttf.drawString(b.x + 1, b.y + 1, b.st, b.fonte.color.darker(0.18f));
        b.fonte.ttf.drawString(b.x, b.y + 1, b.st, b.fonte.color.darker(0.18f));
        b.fonte.ttf.drawString(b.x + 1, b.y, b.st, b.fonte.color.darker(0.18f));
        b.fonte.ttf.drawString(b.x, b.y, b.st, b.fonte.color);
    }

}
