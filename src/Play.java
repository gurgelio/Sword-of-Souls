import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.lang.reflect.Method;
import java.util.ArrayList;

class Play extends BasicGameState {
    private Camera camera;
    private int stateid;
    private Larry larry;
    private ArrayList<Entity> entities;
    private MiniMap minimap;
    Mapa map, cave, currentmap;
    private Hud hud;
    private Image inventoryHud;

    Play(int id){
        stateid = id;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //String[] larryEquip = {"Larry", "Brown Shoes", "Blue Pants", "White Shirt", "Blonde Hair" ,"Short Sword Male", "Spear", "Bow"}; // Usando NPC Lara
        In.init();
        map = new Mapa("map/mapa.tmx", "map/mapa2.tmx");
        map.init();
        cave = new Mapa("map/cave.tmx", "map/cave2.tmx");
        cave.init();
        currentmap = map;

        hud = new Hud("img/lifeHud.png");
        camera = new Camera();
        minimap = new MiniMap(new Image("map/mapa128.png"), map);
        entities = new ArrayList<>();
        //declarar na ordem BEHIND, BODY, FEET, LEGS, TORSO, BELT, HEAD, HANDS, WEAPONS
        larry = new Larry(3,4, new String[] {"NPC/Joe","Axe", "Spear", "Recurve Bow", "Kite Shield"});
        entities.add(larry);
        for (String st : larry.getInventory().getEquipped()) System.out.println(st);
        inventoryHud = new Image("img/inventoryHud.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        camera.render(g, larry, currentmap);
        currentmap.renderWithEntities(entities, g);
        minimap.render(g, camera, larry);
        hud.renderInventory();
        //hud.render(camera.getX(), camera.getY(), larry.hp, larry.mana);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        In.update();
        Entity.update(entities, delta, currentmap);

        if (In.keyPressed("escape")) {
            sbg.enterState(0);
        }

        if (In.keyPressed("h")) larry.getInventory().setItem(3, "Bow", larry);


        if (larry.getX() > 41*32 & larry.getX() < 43*32){
            if (larry.getY() < 4*32 & currentmap == map){
                currentmap = cave;
                minimap.setMinimap(new Image("map/cave128.png"), cave);
                larry.setpos(47,40);
            }
        }

        if (In.buttonReleased("rmb")) larry.setpos((int) (camera.getX() + In.getMouse()[0] - 16)/32,(int) (camera.getY() + In.getMouse()[1] - 32)/32);
    }


    @Override
    public int getID() {
        return stateid;
    }

}