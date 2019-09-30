import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject implements EntityA {
    private Textures tex;
    private Game game;
    Animation anim;

    public Bullet(double x, double y, Textures tex, Game game) {
        super(x, y);
        this.tex = tex;
        this.game = game;
        anim = new Animation(tex.missile, 3, 6, 1, 3);
    }

    public void tick() {
        y -= 10;
        if (Physics.Collision(this, game.eb)) {
            System.out.println("COLLISION DETECTED");
        }
        anim.runAnimation();
    }

    public void render(Graphics g) {
        // g.drawImage(tex.missile, (int) x, (int) y, null);
        anim.drawAnimation(g, x, y, 0);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32); // width and height to establish the proper hitbox around a sprite
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}