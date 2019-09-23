import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet {

    private double x;
    private double y;

    private Textures text;

    // projectile
    public Bullet(double x, double y, Textures text) {
        this.x = x;
        this.y = y;
        this.text = text;
    }

    // update
    public void tick() {
        x += 5;
    }

    public void render(Graphics g) {
        g.drawImage(text.orange, (int) x, (int) y, null);
    }

    public double getX() {
        return x;
    }

}