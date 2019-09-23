import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet {

    private double x;
    private double y;

    BufferedImage image;

    // projectile
    public Bullet(double x, double y, Game game) {
        this.x = x;
        this.y = y;

        SpriteSheet ss = new SpriteSheet(game.getOrange());

        image = ss.grabImage(1, 1, 256, 256);
    }

    // update
    public void tick() {
        x += 5;
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }

    public double getX() {
        return x;
    }

}