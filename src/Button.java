class Button {
    int x, y;
    String st;
    Fonte fonte;
    boolean held;

    Button(String st, int x, int y, Fonte fonte) {
        this.st = st;
        this.fonte = fonte;
        this.x = x - fonte.ttf.getWidth(st) / 2;
        this.y = y - fonte.ttf.getHeight(st) / 2;
        this.held = false;

    }

    private void setColor(int r, int g, int b, int opacity) {
        this.fonte.color = new org.newdawn.slick.Color(r, g, b, opacity);
    }

    private int[] area() {
        int x = this.x, y = this.y;
        return new int[]{x, y, x + this.fonte.ttf.getWidth(this.st), y + this.fonte.ttf.getHeight(this.st)};
    }

    boolean isClicked() {
        if (In.mouseIsOver(area())) {
            setColor(200, 200, 200, 255);
            if (In.buttonPressed("lmb")) {
                held = true;
            }
            if (In.buttonReleased("lmb") && held) {
                held = false;
                return true;
            }
        } else setColor(59, 66, 60, 255);
        return false;
    }
}