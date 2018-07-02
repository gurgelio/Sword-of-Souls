/*
Cria um botão para o menu principal a partir de uma String
 */

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.Color;
import java.awt.Font;

class Button {
    private int x, y;
    private String st;
    private TrueTypeFont ttf;
    private boolean held;
    private Color color, defaultColor, hoverColor;

    Button(String st, int x, int y, Font font, Color defaultColor, Color colorOver){
        this.st = st;
        this.ttf = new TrueTypeFont(font, true);
        this.x = x - ttf.getWidth(st) / 2;
        this.y = y - ttf.getHeight(st) / 2;
        this.held = false;
        this.defaultColor = defaultColor;
        this.color = defaultColor;
        this.hoverColor = colorOver;
    }

    //retorna true em caso de clique no botão, muda a cor dele caso o mouse esteja posicionado acima dele
    boolean isClicked() {
        if (In.mouseIsOver(area())) {
            color = hoverColor;
            if (In.buttonPressed("lmb")) {
                held = true;
            }
            if (In.buttonReleased("lmb") && held) {
                held = false;
                return true;
            }
        } else color = defaultColor;
        return false;
    }

    //desenha o botão na tela, criando um pequeno efeito 3D
    void render(){
        for(int shiftY = 2; shiftY >= 0; shiftY--){
            for(int shiftX = 2; shiftX >= 0; shiftX--){
                ttf.drawString(x + shiftX, y + shiftY, st, color.darker((shiftX+shiftY)*0.15f));
            }
        }
    }

    private int[] area() {
        int x = this.x, y = this.y;
        return new int[]{x, y, this.ttf.getWidth(this.st), this.ttf.getHeight(this.st)};
    }
}