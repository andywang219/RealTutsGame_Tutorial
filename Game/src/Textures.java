import java.awt.image.BufferedImage;

public class Textures {
    private SpriteSheet ss = null;
    public BufferedImage player;
    public BufferedImage missile;
    public BufferedImage enemy;

    public Textures(Game game) {
        ss = new SpriteSheet(game.getSpriteSheet());
        getTextures();
    }

    private void getTextures() { 
        player = ss.grabImage(1, 1, 32, 96); // 96 for animation
        missile = ss.grabImage(2, 1, 32, 96);
        enemy = ss.grabImage(3, 1, 32, 96);
    }
}