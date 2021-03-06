package ru.omsk.neoLab.race;


import ru.omsk.neoLab.board.Сell.Cell;
import ru.omsk.neoLab.board.Сell.TypeCell;

public final class Orcs extends ARace {

    public Orcs() {
        nameRace = "Orcs";
        countTokens = 5;
    }

    @Override
    public int getAdvantageCaptureCell(final Cell cell) {
        if (cell.getType() == TypeCell.Water) {
            return cell.getTokensForCapture();
        }
        return cell.getTokensForCapture() - 1;
    }
}