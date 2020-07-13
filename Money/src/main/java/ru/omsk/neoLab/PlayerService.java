package ru.omsk.neoLab;

import ru.omsk.neoLab.board.Board;
import ru.omsk.neoLab.board.Generators.Cells.Сell.ACell;
import ru.omsk.neoLab.race.ARace;

import java.util.ArrayList;
import java.util.HashSet;

public class PlayerService {

    // Массивы, для вычисления допустимых ходов
    private final int[] calci = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
    private final int[] calcj = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};

    private final ArrayList<ACell> possibleCellsCapture = new ArrayList<ACell>();


    public void raceChoice(HashSet<ARace> racesPool, ARace race, Player player) {
        if (Validator.isCheckRaceInGame(race, racesPool)) {
            player.takeRaceFromPool(race);
            racesPool.remove(race);
            LoggerGame.logChooseRaceTrue(player);
        }
        LoggerGame.logChooseRaceFalse();
    }

    public ArrayList<ACell> findOutWherePlayerCanGo(Board board, Player player) {
        if (player.getLocation() == null || player.getLocation().getCells().size() == 0) {
            possibleCellsCapture.clear();
            for (int i = 0; i < board.getBoard()[0].length; i++) {
                possibleCellsCapture.add(board.getBoard()[0][i]);
                possibleCellsCapture.add(board.getBoard()[board.getBoard().length - 1][i]);
            }
            for (int i = 1; i < board.getBoard().length - 1; i++) {
                possibleCellsCapture.add(board.getBoard()[i][0]);
                possibleCellsCapture.add(board.getBoard()[i][board.getBoard().length - 1]);
            }
        } else {
            for (ACell cell : player.getLocation().getCells()) {
                for (int i = 0; i < 8; i++) {
                    int x = cell.getX() + calci[i];
                    int y = cell.getY() + calcj[i];
                    if (Validator.isCheckingOutputOverBoard(x, y, board.getHeight(), board.getWidth())) {
                        if (!Validator.isCheckingBelongsCell(player, board.getBoard()[x][y])) {
                            possibleCellsCapture.add(board.getBoard()[x][y]);
                        }
                    }
                }
            }
        }
        return possibleCellsCapture;
    }

    public void regionCapture(Player player, ACell cell) {
        if (possibleCellsCapture.contains(cell)) {
            player.addTerritory(cell);
        }
    }

    public void unitDistribution(Player player) {
        player.pickUpUnits();
    }

    public void countMoney(Player player) {
        player.makeCoinCount();
    }

    public void raceInDecline(Player player) {
        player.setRaceDecline();
    }
}
