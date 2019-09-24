import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject implements EntityA {

    private Textures text;
    private Game game;

    // projectile
    public Bullet(double x, double y, Textures text, Game game) {
        super(x, y);
        this.text = text;
        this.game = game;
    }

    // update
    public void tick() {
        x += 5;

        if(Physics.Collision(this, game.eb)) {
            System.out.println("COLLISION DETECTED");
        }
    }

    public void render(Graphics g) {
        g.drawImage(text.orange, (int) x, (int) y, null);
    }

    // hit box
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 26, 26);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}