package com.battlecode;

import bc.GameController;

public class Player {

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
        // do stuff
        // do stuff

        // Indicate we've finished out turn
        gc.nextTurn();
    }
}
