import java.util.LinkedList;
import java.awt.Graphics;

public class Controller {

    private LinkedList<Bullet> b = new LinkedList<Bullet>();
    private LinkedList<Enemy> e = new LinkedList<Enemy>();

    Bullet TempBullet;
    Enemy TempEnemy;
    Game game;
    Textures text;

    public Controller(Game game, Textures text) {
        this.game = game;
        this.text = text;

        for(int x = 0; x < Game.WIDTH; x += 256) {
            addEnemy(new Enemy(x, 0, text));
        }
    }
    
    // updates each individual bullet / projectile
    // use a linklist to store all bullets
    public void tick() {
        for(int i = 0; i < b.size(); i++) {
            TempBullet = b.get(i);

            if(TempBullet.getX() > 800) {
                removeBullet(TempBullet);
            }

            TempBullet.tick();
        }
        for(int i = 0; i < e.size(); i++) {
            TempEnemy = e.get(i);

            TempEnemy.tick();
        }
    }
    
    // render each bullet
    public void render(Graphics g) {
        for(int i = 0; i < b.size(); i++) {
            TempBullet = b.get(i);

            TempBullet.render(g);
        }
        for(int i = 0; i < e.size(); i++) {
            TempEnemy = e.get(i);

            TempEnemy.render(g);
        }
    }

    public void addBullet(Bullet block) {
        b.add(block);
    }

    public void removeBullet(Bullet block) {
        b.remove(block);
    }

    public void addEnemy(Enemy block) {
        e.add(block);
    }

    public void removeEnemy(Enemy block) {
        e.remove(block);
    }

}