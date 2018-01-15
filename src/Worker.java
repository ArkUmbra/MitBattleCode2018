
import bc.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Worker extends BaseUnit {

    private static final Map<Integer, UnitState> WORKER_STATES = new HashMap<>();

    @Override
    public void doTurn(GameController gc, Unit unit) {

        UnitState unitState = WORKER_STATES.get(unit.id());
        if (unitState == null) {
            unitState = createNewRandomUnitState(gc, unit);
            WORKER_STATES.put(unit.id(), unitState);
        }
        switch (unitState.getUnitAction()) {
            case ROAMING:
                movementController.moveTowardDestination(gc, unit, unitState.getActionLocation());
                if (unit.location().mapLocation().distanceSquaredTo(unitState.getActionLocation()) == 0) {
                    WORKER_STATES.remove(unit.id());
                } else {
                    WORKER_STATES.put(unit.id(), unitState);
                }
                break;
            case BUILDING_FACTORY:
                if (gc.canBuild(unit.id(), UnitType.Factory.swigValue())) {
                    gc.build(unit.id(), UnitType.Factory.swigValue());
                    WORKER_STATES.put(unit.id(), unitState);
                }
                break;
            default:
                break;
        }
    }

    private UnitState createNewRandomUnitState(GameController gameController, Unit unit) {
        int randomNumber = ThreadLocalRandom.current().nextInt(0, 2);
        final UnitState unitState;
        switch (randomNumber) {
            case 0:
                unitState = buildRoamingState(gameController);
                break;
            case 1:
                Direction direction = DirectionUtils.randomDir();
                if (gameController.canBlueprint(unit.id(), UnitType.Factory, direction)) {
                    gameController.blueprint(unit.id(), UnitType.Factory, direction);
                    unitState = UnitState.buildingState(unit.location().mapLocation().add(direction));
                    break;
                }
            default:
                unitState = buildRoamingState(gameController);
                break;
        }
        return unitState;
    }

    private UnitState buildRoamingState(GameController gameController) {
        UnitState unitState;
        MapLocation randomLocation = LocationUtils.getRandomLocation(gameController, gameController.planet());
        unitState = UnitState.roamingState(randomLocation);
        return unitState;
    }
}
