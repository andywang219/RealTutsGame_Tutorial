import java.util.LinkedList;
import java.util.Random;
import java.awt.Graphics;

public class Controller {

    private LinkedList<EntityA> ea = new LinkedList<EntityA>();
    private LinkedList<EntityB> eb = new LinkedList<EntityB>();

    EntityA enta;
    EntityB entb;
    Random r = new Random();
    Textures text;

    public Controller(Textures text) {
        this.text = text;
    }

    // create more enemies based on enemy_count from Game
    public void createEnemy(int enemy_count) {
        for(int i = 0; i < enemy_count; i++) {
            addEntity(new Enemy(r.nextInt(Game.WIDTH - 32), -10, text)); // will automatically know its a EntityB b/c enemy is EntityB
        }
    }
    
    // updates each individual bullet / projectile
    public void tick() {
        // Entity A
        for(int i = 0; i < ea.size(); i++) {
            enta = ea.get(i);
            enta.tick();
        }

        // Entity B
        for(int i = 0; i < eb.size(); i++) {
            entb = eb.get(i);
            entb.tick();
        }
    }
    
    // render each bullet
    public void render(Graphics g) {
        // Entity A
        for(int i = 0; i < ea.size(); i++) {
            enta = ea.get(i);
            enta.render(g);
        }

        // Entity B
        for(int i = 0; i < eb.size(); i++) {
            entb = eb.get(i);
            entb.render(g);
        }
    }
    
    // Entity A
    public void addEntity(EntityA block) {
        ea.add(block);
    }
    
    public void removeEntity(EntityA block) {
        ea.remove(block);
    }

    // Entity B
    public void addEntity(EntityB block) {
        eb.add(block);
    }
    
    public void removeEntity(EntityB block) {
        eb.remove(block);
    }

    public LinkedList<EntityA> getEntityA() {
        return ea;
    }

    public LinkedList<EntityB> getEntityB() {
        return eb;
    }

}