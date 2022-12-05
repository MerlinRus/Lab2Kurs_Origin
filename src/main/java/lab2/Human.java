package lab2;

public class Human implements Contestants{
    private String name;
    private int max_distance;
    private int max_height;

    public Human(String name, int max_distance, int max_height){
        this.name = name;
        this.max_distance = max_distance;
        this.max_height = max_height;
    }

    @Override
    public boolean run(Track.Tracks t) {
        if (t.distance <= max_distance) {
            System.out.printf("Человек %s успешно пробежал%n", name);
            return true;
        }
        else {
            System.out.printf("Человеку %s не удалось пробежать%n \n", name);
            return false;
        }
    }

    @Override
    public boolean jump(Wall.Walls w) {
        if (w.height <= max_height) {
            System.out.printf("Человек %s успешно пропрыгал%n", name);
            return true;
        }
        else {
            System.out.printf("Человеку %s не удалось пропрыгать%n \n", name);
            return false;
        }
    }
}
