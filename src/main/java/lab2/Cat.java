package lab2;

public class Cat implements Contestants {
    private String name;
    private int max_distance;
    private int max_height;
    public int count = 2;

    public Cat(String name, int max_distance, int max_height) {
        this.name = name;
        this.max_distance = max_distance;
        this.max_height = max_height;
    }

    @Override
    public boolean run(Track.Tracks t) {
        if (t.distance <= max_distance) {
            System.out.printf("Кот %s успешно пробежал%n", name);
            return true;
        }
        else {
            System.out.printf("Коту %s удалось пробежать%n \n", name);
        }
        return false;
    }

    @Override
    public boolean jump(Wall.Walls w) {
        if (w.height <= max_height) {
            System.out.printf("Кот %s успешно пропрыгал%n", name);
            return true;
        }
        else if (count != 0)
        {
            System.out.printf("Кот " + name + " смог перепрыгнуть препятствие высотой " + max_height + " метров, использовав суперсилу%n");
            count = count - 1;
            return true;
        }
        else {
            System.out.printf("Коту %s не удалось пропрыгать%n \n", name);
            return false;
        }
    }
}