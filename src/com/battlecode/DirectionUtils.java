package com.battlecode;

import bc.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DirectionUtils {

    private static final List<Direction> DIRECTIONS = new ArrayList<>();
    private static final Random random = new Random(1234);

    static {
        for (Direction dir : Direction.values()) {
            DIRECTIONS.add(dir);
        }

    }

    public static Direction randomDir() {
        int nextInt = random.nextInt(DIRECTIONS.size());
        return DIRECTIONS.get(nextInt);
    }
}
