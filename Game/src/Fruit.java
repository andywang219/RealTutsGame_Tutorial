import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.util.Random;

// For static fruit on the screen
public class Fruit extends GameObject implements EntityA {
    private Textures fruits;
    private ArrayList<BufferedImage> current_fruit;
    private Game game;
    private Controller c;
    private Random r = new Random();
    private int index;

    public Fruit(double x, double y, Textures fruits, Controller c, Game game) {
        super(x, y);
        this.fruits = fruits;
        this.c = c;
        this.game = game;
        current_fruit = new ArrayList<BufferedImage>();
        // The current fruit
        current_fruit.add(fruits.apple);
        current_fruit.add(fruits.orange);
        current_fruit.add(fruits.watermelon);
        current_fruit.add(fruits.grape);
        current_fruit.add(fruits.banana);
    }

    public void tick() {
        // Prevent fruit from spawning off screen in case it does
        if(x <= 0) x = 0;
        if(x >= Game.WIDTH - 32) x = Game.WIDTH - 32;
        if(y <= 0) y = 0;
        if(y >= Game.HEIGHT - 32) y = Game.HEIGHT - 32;
    }

    public void render(Graphics g) {
        index = r.nextInt(5); // random index for current fruit
        g.drawImage(current_fruit.get(index), (int) x, (int) y, null); // randomly spawn specific fruit
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