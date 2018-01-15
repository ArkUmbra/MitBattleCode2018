import bc.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DirectionUtils {

    private static final List<Direction> DIRECTIONS = new ArrayList<>();
    private static final Random random = new Random(1234);

    static {
        DIRECTIONS.addAll(Arrays.asList(Direction.values()));
    }

    public static Direction randomDir() {
        int nextInt = random.nextInt(DIRECTIONS.size());
        return DIRECTIONS.get(nextInt);
    }
}
