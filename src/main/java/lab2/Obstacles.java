package lab2;

public interface Obstacles {
    // Препятствия
    Obstacles[] obstacles = new Obstacles[] {
            new Wall(Wall.Walls.HIGH),
            new Track(Track.Tracks.NORMAL),
            new Wall(Wall.Walls.HIGH),
            new Track(Track.Tracks.FAST),
            new Wall(Wall.Walls.SMALL),
            new Track(Track.Tracks.SLOW),
            new Wall(Wall.Walls.HIGH),
            new Track(Track.Tracks.FAST)
    };
    boolean hindrance(Contestants c);
}