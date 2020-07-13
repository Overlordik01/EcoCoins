package ru.omsk.neoLab;

import ru.omsk.neoLab.board.Generators.Cells.ListCell;
import ru.omsk.neoLab.board.Generators.Cells.Сell.ACell;
import ru.omsk.neoLab.board.Generators.Cells.Сell.Earth;
import ru.omsk.neoLab.board.Generators.Cells.Сell.Mounted;
import ru.omsk.neoLab.board.Generators.Cells.Сell.Mushrooms;
import ru.omsk.neoLab.race.ARace;

public class Player {

    private final String nickName;
    private int countCoin;
    private int countTokens;

    private ARace race;
    private ListCell location = new ListCell();

    private ARace raceDecline;
    private ListCell locationDecline;

    public Player(String nickName) {
        this.nickName = nickName;
        this.countCoin = 0;
    }

    public void takeRaceFromPool(ARace race) {
        this.race = race;
        countTokens = race.getCountTokens();
    }

    public void addTerritory(ACell cell) {
        if (cell.getAbilityCapture()) {
            countTokens -= cell.getCountUnits() + getCountTokens();
            location.add(cell);
        }
        if (cell.getBelongs() != this) {
            countTokens -= cell.getCountUnits() + 1;
        }
    }

    public void pickUpUnits() {
        for (ACell cell : location.getCells()) {
            this.countTokens += cell.getCountUnits() - 1;
        }
    }

    public void makeCoinCount() {
        // Сбор монет с действующих рас
        if (race.getNameRace().equals("Elfs")) {
            if (location.getCells().contains(new Earth())) {
                countCoin += 1;
            }
            if (location.getCells().contains(new Mounted())) {
                countCoin += 1;
            }
            if (location.getCells().contains(new Mushrooms())) {
                countCoin += 1;
            }
        }
        for (ACell cell : location.getCells()) {
            countCoin += cell.getCoin();
        }

        // Сбор монет с упадших рас
        if (locationDecline != null && raceDecline != null) {
            if (raceDecline.getNameRace().equals("Elfs")) {
                if (locationDecline.getCells().contains(new Earth())) {
                    countCoin += 1;
                }
                if (locationDecline.getCells().contains(new Mounted())) {
                    countCoin += 1;
                }
                if (locationDecline.getCells().contains(new Mushrooms())) {
                    countCoin += 1;
                }
            }
            for (ACell cell : locationDecline.getCells()) {
                countCoin += cell.getCoin();
            }
        }
    }

    public String getNickName() {
        return nickName;
    }

    public int getCountCoin() {
        return countCoin;
    }

    public ARace getRace() {
        return race;
    }

    public ListCell getLocation() {
        return location;
    }

    public int getCountTokens() {
        return countTokens;
    }

    public void setRaceDecline() {
        raceDecline = race;
        race = null;
        countTokens = 0;
    }

    public void setLocation(ListCell location) {
        this.location = location;
    }
}
