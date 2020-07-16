package ru.omsk.neoLab;

import ru.omsk.neoLab.board.Generators.Cells.Сell.ACell;
import ru.omsk.neoLab.race.ARace;

import java.util.ArrayList;

public final class Validator {

    public static boolean isCheckingBelongsCell(Player player, ACell cell) {
        return cell.getBelongs().equals(player);
    }

    public static boolean isCheckingOutputOverBoard(int x, int y, int height, int width) {
        return x >= 0 && y >= 0 && x < height && y < width;
    }

    public static boolean isCheckRaceInGame(ARace race, ArrayList<ARace> raceList) {
        return raceList.contains(race);
    }

}
