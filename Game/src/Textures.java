import java.awt.image.BufferedImage;

// loads all of the sprite sheets just once
public class Textures {

    public BufferedImage player, orange, enemy;

    private SpriteSheet ss;
    private SpriteSheet orange_ss;
    private SpriteSheet enemy_ss;

    public Textures(Game game) {
        ss = new SpriteSheet(game.getSpriteSheet());
        orange_ss = new SpriteSheet(game.getOrange());
        enemy_ss = new SpriteSheet(game.getEnemy());

        getTextures();
    }

    private void getTextures() {
        player = ss.grabImage(2, 1, 32, 32);
        orange = orange_ss.grabImage(1, 1, 32, 32);
        enemy = enemy_ss.grabImage(2, 1, 32, 32);
    }

}