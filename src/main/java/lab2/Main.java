package lab2;

public class Main {
    public static void main(String[] args) {
        // Препятствия
        Obstacles[] obstacles = new Obstacles[] {
                new Wall(Wall.Walls.SMALL),
                new Wall(Wall.Walls.SMALL),
                new Wall(Wall.Walls.HIGH),
                new Wall(Wall.Walls.HIGH),
                new Wall(Wall.Walls.HIGH),
                new Track(Track.Tracks.FAST),
                new Track(Track.Tracks.FAST),
                new Track(Track.Tracks.FAST),
                new Wall(Wall.Walls.SMALL),
                new Track(Track.Tracks.SLOW),
                new Wall(Wall.Walls.HIGH),
                new Track(Track.Tracks.FAST)
        };

        Contestants[] contestants = new Contestants[]{
                new Cat("Снежок", 30, 25),
                new Human("Лёша", 700, 10),
                new Robot("Валли", 4000, 60),
                new Human("Виталий", 1000, 15)
        };

        for (Contestants c : contestants) {
            for (Obstacles o: obstacles) {
                if(!o.hindrance(c)) break;
            }
        }
    }
}