import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {

    private double x; // x and y position
    private double y;

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
        
    }

    public void render(Graphics g) {
        g.drawImage(player, (int)x, (int)y, null);
    }

    // getters and setter
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

}