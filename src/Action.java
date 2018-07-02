/*
Recebe uma imagem de um item do diretório anim e a transforma em uma animação
 */

import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;

class Action {
    Animation[] walk = {null,null,null,null,null}, cast = {null,null,null,null,null}, shoot = {null,null,null,null,null}, current;
    Animation[] die = {null,null,null,null,null}, slash = {null,null,null,null,null}, thrust = {null,null,null,null,null}, stop = {null,null,null,null,null};

    Action(Image img, int subImageWidth, int subImageHeight, int deltaFrame){

        //instancia as animções do item. Animação de morte recebe tratamento especial devido a falta de 4 direções
        die[0] = new Animation(new SpriteSheet(img.getSubImage(0,20*subImageHeight, 6*subImageWidth, subImageHeight), subImageWidth, subImageHeight),3*deltaFrame);
        for(int i = 0; i < 4; i++){
            cast[i] = new Animation(new SpriteSheet(img.getSubImage( 0, i*subImageHeight, 7*subImageWidth, subImageHeight),subImageWidth, subImageHeight), 2*deltaFrame);
            thrust[i] = new Animation( new SpriteSheet(img.getSubImage( 0, (4+i)*subImageHeight, 8*subImageWidth, subImageHeight), subImageWidth, subImageHeight), deltaFrame);
            walk[i] = new Animation(new SpriteSheet(img.getSubImage( 0, (8+i)*subImageHeight, 9*subImageWidth, subImageHeight),subImageWidth, subImageHeight), deltaFrame);
            slash[i] = new Animation(new SpriteSheet(img.getSubImage( 0, (12+i)*subImageHeight, 6*subImageWidth, subImageHeight),subImageWidth, subImageHeight), 2*deltaFrame);
            shoot[i] = new Animation(new SpriteSheet(img.getSubImage( 0, (16+i)*subImageHeight, 13*subImageWidth, subImageHeight),subImageWidth, subImageHeight), deltaFrame);
            stop[i] = new Animation(new SpriteSheet(img.getSubImage( 0, i*subImageHeight, subImageWidth, subImageHeight),subImageWidth, subImageHeight), deltaFrame);
            die[i] = die[0];
        }

        //define uma direção inicial para a última posição do vetor, que representa a direção atual do item
        cast[4] = cast[0];
        thrust[4] = thrust[0];
        walk[4] = walk[0];
        slash[4] = slash[0];
        shoot[4] = shoot[0];
        stop[4] = stop[0];
        die[4] = die[0];

        //define o comportamento especial de cada animação
        for (Animation an : slash){
            an.setDuration(0,0);
        }

        for(Animation an : thrust){
            an.setDuration(7, 530);
            an.setLooping(false);
        }

        for (Animation an : walk){
            an.setDuration(0, 0);
        }

        for(Animation an : die) {
            an.setLooping(false);
        }

        for(Animation an : cast){
            an.setLooping(false);
        }

        for(Animation an : stop) {
            an.stop();
        }


        current = stop;
    }

    //desenha na tela o item em sua animação e direção atual
    void render(float x, float y){
        this.current[4].draw(x, y);
    }

    //atualiza a direção do item seguindo a direção do personagem
    void update(String direction, int delta){

        if ("up".equals(direction)) this.current[4] = this.current[0];
        else if ("left".equals(direction)) this.current[4] = this.current[1];
        else if ("right".equals(direction)) this.current[4] = this.current[3];
        else this.current[4] = this.current[2];

        this.current[4].update(delta);

    }

    //realiza a troca da animação do item
    void setState(String state, float speed){
        switch(state){
            case "walk":
                this.current = setSpeed(walk, speed*8);
                break;

            case "slash":
                this.current = setSpeed(slash, speed*10);
                break;

            case "cast":
                this.current = setSpeed(cast, 0.75f);
                break;

            case "thrust":
                this.current = setSpeed(thrust, speed*6);
                break;

            case "shoot":
                this.current = setSpeed(shoot, speed*4);
                break;

            case "die":
                this.current = this.die;
                break;

            case "stop":
                this.current = this.stop;
                break;
        }
    }

    //mantem a animação com a velocidade adequada em relação ao atributo de destreza do personagem
    private Animation[] setSpeed(Animation[] state, double speed){
        for(Animation an : state){
            an.setSpeed((float) speed);
        }
        return state;
    }

    int getFrame(){
        return this.current[4].getFrame();
    }

    void setFrame(int index){
        this.current[4].setCurrentFrame(index);
    }

    boolean isStopped(){
        return this.current[4].isStopped();
    }

    void start(){
        this.current[4].start();
    }
}