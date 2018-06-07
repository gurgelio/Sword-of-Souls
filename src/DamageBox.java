class DamageBox {
    private int damage, radius, x, y;

    DamageBox(int x, int y, int radius, int damage){
        this.damage = damage;
        this.radius = radius;
        this.x = x;
        this.y = y;
    }

    boolean isOver(Entity x){
        float[] hitbox = x.hitbox();
        double distance = Math.sqrt(Math.pow(this.x-hitbox[0], 2) + Math.pow(this.y-hitbox[1], 2));
        if(distance <= radius){
            x.hp -= damage;
            return true;
        }
        return false;
    }
}
