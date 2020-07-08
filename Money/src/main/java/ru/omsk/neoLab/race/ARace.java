package ru.omsk.neoLab.race;

import ru.omsk.neoLab.board.Generators.Calls.Call.ACall;

public abstract class ARace {
    protected String nameRace;
    protected int countUnit; // Число юнитов определенной рассы
    protected boolean alive;

    public String getNameRace() {
        return nameRace;
    }

    public int getCountUnit() {
        return countUnit;
    }

    public int getMoney(int money, ACall call) {
        return money;
    }

    public int toDefend(int countUnit) {
        return countUnit;
    } // Сколько нужно для защиты

    public int getRequirementsForCapture(ACall call) {
        return call.getRequirementsForCapture();
    }

    public void toDecline(){
        alive = false;
    }
}
