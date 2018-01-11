package com.battlecode.unit;

import bc.GameController;
import bc.Unit;
import com.battlecode.MovementController;

public abstract class BaseUnit {

    protected MovementController movementController = new MovementController();


    public abstract void doTurn(GameController gc, Unit unit);

}
