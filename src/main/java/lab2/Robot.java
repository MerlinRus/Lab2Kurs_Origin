package lab2;

public class Robot implements Contestants{
    private String name;
    private int max_distance;
    private int max_height;

    public Robot(String name, int max_distance, int max_height){
        this.name = name;
        this.max_distance = max_distance;
        this.max_height = max_height;
    }

    @Override
    public boolean run(Track.Tracks t) {
        if (t.distance <= max_distance) {
            System.out.printf("Робот %s успешно пробежал%n \n", name);
            return true;
        }
        else {
            System.out.printf("Роботу %s не удалось пробежать%n", name);
            return false;
        }
    }

    @Override
    public boolean jump(Wall.Walls w) {
        if (w.height <= max_height) {
            System.out.printf("Робот %s успешно пропрыгал%n", name);
            return true;
        }
        else {
            System.out.printf("Роботу %s не удалось пропрыгать%n", name);
            return false;
        }
    }
}