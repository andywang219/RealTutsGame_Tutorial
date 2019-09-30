import java.awt.Graphics;
import java.awt.Rectangle;
//import java.awt.image.BufferedImage;

public class Player extends GameObject implements EntityA {
    private double velX = 0;
    private double velY = 0;
    private Textures tex;
    Animation anim;

    public Player(double x, double y, Textures tex) {
        // intialize player's location in the game
        super(x, y); // from game object class
        this.tex = tex;
        anim = new Animation(tex.player, 3, 6, 1, 3); // frames, speed, 1 column by 3 rows (last 2 parameters)
        // format: frames, speed, col, row
    }

    public void tick() {
        x += velX;
        y += velY;

        if (x <= 0) x = 0;
        if (x >= 600) x = 600;
        if (y <= 0) y = 0;
        if (y >= 480 - 32) y = 480 - 32;
        anim.runAnimation();
    }

    public void render(Graphics g) {
        // g.drawImage(tex.player, (int)x, (int)y, null);
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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }
}