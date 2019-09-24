import java.util.LinkedList;

public class Physics {

    public static boolean Collision(EntityA enta, LinkedList<EntityB> entb) {

        for(int i = 0; i < entb.size(); i++) {
            // if the hit box intersects
            if(enta.getBounds().intersects(entb.get(i).getBounds())) {
                return true;
            }

        }
        return false;
    }

}