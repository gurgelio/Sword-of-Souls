import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.util.ArrayList;
import java.lang.reflect.*;

class Play extends BasicGameState {
    static Larry larry;
    private Camera camera;
    private int stateid;
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
        String[] larryEquip = {"Larry", "Brown Shoes", "Blue Pants", "White Shirt", "Blonde Hair" ,"Short Sword Male", "Spear", "Bow"}; // Usando NPC Lara
        //String[] kk = new String[] {"NPC/Joe","Axe", "Spear", "Recurve Bow"};
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
        larry = new Larry(100,128, larryEquip, new int[]{5, 5, 5});
        entities.add(larry);
        entities.add(new SpearSkeleton(1300, 760, new int[]{2, 2, 2}, new String[]{"Skeleton", "Leather Hood", "Spear"}));
        inventoryHud = new Image("img/inventoryHud.png");

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        //camera.render(g, larry, currentmap);
        try {
            Method met = camera.getClass().getDeclaredMethod("render", Graphics.class, Entity.class, Mapa.class);
            met.invoke(camera, g, larry, currentmap);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        currentmap.renderWithEntities(entities, g);
        minimap.render(g, camera, larry);
        larry.getInventory().render(camera.getX(), camera.getY());
        DamageBox.render(g);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        In.update();
        Entity.deleteDeadEntities(entities);
        DamageBox.update(entities, delta);
        Entity.update(entities, delta, currentmap);

        if (In.keyPressed("escape")) sbg.enterState(0);

        if (In.keyPressed("h")) larry.getInventory().setItem(7, "Recurve Bow", larry);


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