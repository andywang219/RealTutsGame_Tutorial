import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {

    private double x; // x and y position
    private double y;

    private double velX = 0; // velocity x 
    private double velY = 0; // velocity y

    private BufferedImage player;

    // initialize player (constructor)
    public Player(double x, double y, Game game) {
        this.x = x;
        this.y = y;

        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet()); // get the sprite sheet from Game class
        player = ss.grabImage(1, 1, 256, 256);
    }

    // update method
    public void tick() {
        x += velX;
        y += velY;

        if(x <= -110) {
            x = -110;
        }
        if(x >= 800) {
            x = 800;
        }
        if(y <= -100) {
            y = -100;
        }
        if(y >= 440) {
            y = 440;
        }
    }

    public void render(Graphics g) {
        g.drawImage(player, (int)x, (int)y, null);
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