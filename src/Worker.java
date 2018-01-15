
import bc.GameController;
import bc.MapLocation;
import bc.Unit;

public class Worker extends BaseUnit {

    @Override
    public void doTurn(GameController gc, Unit unit) {

        gc.moveRobot(unit.id(), DirectionUtils.randomDir());

        // TODO
        MapLocation destination = null;

        movementController.moveTowardDestination(gc, unit, destination);
    }
}
