package com.battlecode;

import bc.GameController;
import bc.Unit;
import bc.VecUnit;

public class Player {

    private MovementController movementController = new MovementController();

    public static void main(String... args) {
        new Player().startMatch();
    }

    private void startMatch() {
        while (true) {

            GameController gc = new GameController();

            try {
                doTurn(gc);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void doTurn(GameController gc) {
        // Seems like VecUnit is not iterable so we can't use a proper for loop (groan)
        VecUnit myUnits = gc.myUnits();

        for (int i = 0; i < myUnits.size(); i++) {
            Unit unit = myUnits.get(i);
            gc.moveRobot(unit.id(), DirectionUtils.randomDir());

            //movementController.moveTowardDestination(gc, unit);
        }

        // Indicate we've finished out turn
        gc.nextTurn();
    }
}
