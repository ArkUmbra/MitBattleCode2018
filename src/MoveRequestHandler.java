
import bc.GameController;
import bc.Location;
import bc.MapLocation;
import bc.Unit;

public interface MoveRequestHandler {

    void moveTowardDestination(GameController gc, Unit unitToMove, MapLocation destination);
}
