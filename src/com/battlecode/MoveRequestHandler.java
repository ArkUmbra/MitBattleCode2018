package com.battlecode;

import bc.GameController;
import bc.Location;
import bc.Unit;

public interface MoveRequestHandler {

    void moveTowardDestination(GameController gc, Unit unitToMove, Location destination);
}
