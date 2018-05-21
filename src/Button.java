
class Button{
    int x, y, width, height;
    String st;
    Fonte fonte;
    Button(String st, int x, int y, Fonte fonte){
        this.st = st;

        this.x = x;
        this.y = y;

        this.width = fonte.ttf.getWidth(st);
        this.height = fonte.ttf.getHeight(st);

        this.fonte = fonte;
    }

    void setColor(int r, int g, int b, int opacity){
        fonte.color = new org.newdawn.slick.Color(r, g, b, opacity);
    }

    int[] area(){
        return new int[] {this.x, this.y, this.width, this.height};
    }
}
