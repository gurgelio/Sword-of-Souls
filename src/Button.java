
class Button{
    int x, y;
    String st;
    Fonte fonte;
    Button(String st, int x, int y, Fonte fonte){
        this.st = st;

        this.x = x;
        this.y = y;

        this.fonte = fonte;
    }

    void setColor(int r, int g, int b, int opacity){
        fonte.color = new org.newdawn.slick.Color(r, g, b, opacity);
    }

    int[] area(){
        return new int[] {this.x, this.y, this.getWidth(), this.getHeight()};
    }

    int getWidth(){
        return fonte.getWidth(st);
    }

    int getHeight(){
        return fonte.getHeight(st);
    }
}
