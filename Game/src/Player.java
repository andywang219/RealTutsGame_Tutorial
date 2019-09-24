import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject implements EntityA {

    private double velX = 0; // velocity x 
    private double velY = 0; // velocity y

    private Textures text;

    // initialize player (constructor)
    public Player(double x, double y, Textures text) {
        super(x, y);
        this.text = text;
    }

    // update method
    public void tick() {
        x += velX;
        y += velY;

        if(x <= 0) {
            x = 0;
        }
        if(x >= Game.WIDTH - 32) {
            x = Game.WIDTH - 32;
        }
        if(y <= 0) {
            y = 0;
        }
        if(y >= Game.HEIGHT - 32) {
            y = Game.HEIGHT - 32;
        }
    }

    public void render(Graphics g) {
        g.drawImage(text.player, (int)x, (int)y, null);
    }

    // hit box
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    // getters and setters
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