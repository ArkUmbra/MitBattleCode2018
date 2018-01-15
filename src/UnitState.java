import bc.MapLocation;
import bc.UnitType;

public class UnitState {

    private final UnitAction unitAction;

    private MapLocation actionLocation;

    private UnitState(UnitAction unitAction) {
        this.unitAction = unitAction;
    }

    public static UnitState roamingState(MapLocation roamingLocation) {
        UnitState unitState = new UnitState(UnitAction.ROAMING);
        unitState.actionLocation = roamingLocation;
        return unitState;
    }

    public static UnitState buildingState(MapLocation locationToBuild) {
        UnitState unitState = new UnitState(UnitAction.BUILDING_FACTORY);
        unitState.actionLocation = locationToBuild;
        return unitState;
    }

    public UnitAction getUnitAction() {
        return unitAction;
    }

    public MapLocation getActionLocation() {
        return actionLocation;
    }

}
