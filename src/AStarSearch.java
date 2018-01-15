import bc.*;

public class AStarSearch implements MoveRequestHandler {

    @Override
    public void moveTowardDestination(GameController gc, Unit unitToMove, MapLocation destination) {
        MapLocation unitLocation = unitToMove.location().mapLocation();

        long directDistSquaredFromUnitToDest = calculateDistance(unitLocation, destination);
        VecMapLocation surroundingMapLocs = gc.allLocationsWithin(unitLocation, directDistSquaredFromUnitToDest);


    }


    private long calculateDistance(MapLocation origin, MapLocation destination) {
        return origin.distanceSquaredTo(destination);
    }

}
