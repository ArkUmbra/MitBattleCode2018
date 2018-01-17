
import bc.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Worker extends BaseUnit {

    private static final Map<Integer, UnitState> WORKER_STATES = new HashMap<>();

    @Override
    public void doTurn(GameController gc, Unit unit) {

        int unitId = unit.id();
        UnitState unitState = WORKER_STATES.get(unitId);
        if (unitState == null) {
            unitState = createNewRandomUnitState(gc, unit);
            WORKER_STATES.put(unitId, unitState);
        }
        MapLocation actionLocation = unitState.getActionLocation();
        switch (unitState.getUnitAction()) {
            case ROAMING:
                movementController.moveTowardDestination(gc, unit, actionLocation);
                if (unit.location().mapLocation().distanceSquaredTo(actionLocation) == 0) {
                    WORKER_STATES.remove(unitId);
                    doTurn(gc, unit);
                } else {
                    WORKER_STATES.put(unitId, unitState);
                }
                break;
            case BUILDING_FACTORY:
                if (gc.hasUnitAtLocation(actionLocation) &&
                        gc.canBuild(unitId, gc.senseUnitAtLocation(actionLocation).id())) {
                    gc.build(unitId, gc.senseUnitAtLocation(actionLocation).id());
                } else {
                    //Cannot build. Either finished building, or no unit there anymore.
                    WORKER_STATES.remove(unitId);
                    doTurn(gc, unit);
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

                } else {
                    unitState = createNewRandomUnitState(gameController, unit);
                }
                break;
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
