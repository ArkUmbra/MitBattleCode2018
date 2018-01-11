package com.battlecode.unit;

import bc.GameController;
import bc.Location;
import bc.Unit;

public class Worker extends BaseUnit {

    @Override
    public void doTurn(GameController gc, Unit unit) {
        // TODO
        Location destination = null;

        movementController.moveTowardDestination(gc, unit, destination);
    }
}
