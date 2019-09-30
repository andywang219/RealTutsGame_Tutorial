import java.awt.image.BufferedImage;

public class Textures {
    private SpriteSheet ss = null;
    private SpriteSheet fruits = null;
    public BufferedImage player;
    public BufferedImage missile;
    public BufferedImage enemy;

    public Textures(Game game) {
        ss = new SpriteSheet(game.getSpriteSheet());
        fruits = new SpriteSheet(game.getFruitsSheet());
        getTextures();
    }

    private void getTextures() { 
        player = ss.grabImage(3, 1, 32, 192); // 96 for animation
        missile = fruits.grabImage(3, 1, 32, 128);
        enemy = ss.grabImage(2, 1, 32, 128);
    }
}