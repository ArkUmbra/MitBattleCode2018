

import bc.GameController;
import bc.Unit;

public abstract class BaseUnit {

    protected MovementController movementController = new MovementController();


    public abstract void doTurn(GameController gc, Unit unit);

}
