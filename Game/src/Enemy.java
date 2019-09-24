import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject implements EntityB {
    
    private Textures text;

    Random r = new Random();
    private int speed = (r.nextInt(3) + 1);

    public Enemy(double x, double y, Textures text) {
        super(x, y);
        this.text = text;
    }

    public void tick() {
        y += speed;

        if(y > Game.HEIGHT - 32) {
            speed = (r.nextInt(3) + 1);
            y = -10;
            x = r.nextInt(Game.WIDTH) - 32;
        }
    }

    public void render(Graphics g) {
        g.drawImage(text.enemy, (int) x, (int) y, null); 
    }

    // hit box
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

}