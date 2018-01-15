
import bc.GameController;
import bc.Unit;
import bc.UnitType;
import bc.VecUnit;

public class Player {

    private static final Worker worker = new Worker();
    private static final Knight knight = new Knight();
    private static final Ranger ranger = new Ranger();
    private static final Mage mage = new Mage();
    private static final Healer healer = new Healer();

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

            convertToUnitTemplate(unit).doTurn(gc, unit);
        }

        // Indicate we've finished out turn
        gc.nextTurn();
    }

    private BaseUnit convertToUnitTemplate(Unit unit) {
        UnitType unitType = unit.unitType();

        switch (unitType) {
            case Worker: return worker;
            case Knight: return knight;
            case Ranger: return ranger;
            case Mage: return mage;
            case Healer: return healer;

            default: throw new RuntimeException("Unit type not implemented " + unitType);
        }

    }

}
