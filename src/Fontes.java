import org.newdawn.slick.TrueTypeFont;
import java.awt.Font;

class Fontes {

    public static TrueTypeFont Fonte1 = new TrueTypeFont(new Font("Tahoma",Font.PLAIN,50), true);
    public static TrueTypeFont Fonte2 = new TrueTypeFont(new Font("Arial",Font.PLAIN,35), true);
    public static org.newdawn.slick.Color Vermelho1 = new org.newdawn.slick.Color(204,0,0,255);
    public static org.newdawn.slick.Color Gold1 = new org.newdawn.slick.Color(204,153,0,100);

    public static void ShadowFont(String st, int x, int y, TrueTypeFont f, org.newdawn.slick.Color cor){
        f.drawString(x, y + 2, st, cor.darker(0.28f));
        f.drawString(x + 1, y + 2 , st, cor.darker(0.28f));
        f.drawString(x + 2, y + 2, st, cor.darker(0.28f));
        f.drawString(x + 2, y + 1, st, cor.darker(0.28f));
        f.drawString(x + 2, y , st, cor.darker(0.28f));
        f.drawString(x + 1, y + 1, st, cor.darker(0.18f));
        f.drawString(x, y + 1, st, cor.darker(0.18f));
        f.drawString(x + 1, y , st, cor.darker(0.18f));
        f.drawString(x, y , st, cor);

    }
}
