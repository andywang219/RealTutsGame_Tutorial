import java.awt.Graphics;

public class Enemy {
    
    private double x, y;

    private Textures text;

    public Enemy(double x, double y, Textures text) {
        this.x = x;
        this.y = y;
        this.text = text;
    }

    public void tick() {
        x += 3;
    }

    public void render(Graphics g) {
        g.drawImage(text.enemy, (int) x, (int) y, null); 
    }

}