import bc.GameController;
import bc.MapLocation;
import bc.Planet;

import java.util.concurrent.ThreadLocalRandom;

public class LocationUtils {

    public static MapLocation getRandomLocation(GameController gameController, Planet planet) {
        int y = getRandom(gameController.startingMap(planet).getHeight());
        int x = getRandom(gameController.startingMap(planet).getWidth());
        return new MapLocation(planet, x, y);
    }

    private static int getRandom(long upperBound) {
        return ThreadLocalRandom.current().nextInt(0, (int) upperBound);
    }


}
