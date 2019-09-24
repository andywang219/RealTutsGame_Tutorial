import java.awt.Graphics;
import java.awt.Rectangle;

// Entity A's do not collide with one another
public interface EntityA {

    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();

    public double getX();
    public double getY();

}