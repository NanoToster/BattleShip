package ru.vsu.game_engine.game;

import ru.vsu.components.GameFieldButton;
import ru.vsu.domain.CellType;
import ru.vsu.game_engine.players.AiPlayer;
import ru.vsu.game_engine.players.HumanPlayer;
import ru.vsu.game_engine.players.Player;
import ru.vsu.utils.StaticGameInfoAccessor;

import static ru.vsu.config.GameSettings.getGameSettings;

/**
 * @author Ivan Rovenskiy
 * 21 December 2019
 */
public class GameBattleEngine implements GameEngine {
    private GameStatus currentStatus;
    private Player humanPlayer;
    private Player aiPlayer;

    public GameBattleEngine() {
        this.currentStatus = GameStatus.PlayersTurn;
        this.humanPlayer = new HumanPlayer();
        this.aiPlayer = new AiPlayer();
    }

    @Override
    public void processGameFieldButtonClick(GameFieldButton gameFieldButton) {
        if (currentStatus.equals(GameStatus.PlayerWin)) {
            StaticGameInfoAccessor.appendGameLog("Player are already wins!");
            return;
        }
        if (currentStatus.equals(GameStatus.AiWin)) {
            StaticGameInfoAccessor.appendGameLog("Ai are already wins!");
            return;
        }
        if (gameFieldButton.getFieldType().equals(GameFieldButton.FieldType.Player)) {
            StaticGameInfoAccessor.appendGameLog("You can't shoot yourself!");
            return;
        }
        checkAndProcessShootOfPlayer(gameFieldButton);
    }

    @Override
    public GameStatus getGameStatus() {
        return currentStatus;
    }

    private void checkAndProcessShootOfPlayer(GameFieldButton gameFieldButton) {
        final CellType killedCellType = humanPlayer.checkAndProcessShoot(gameFieldButton);
        if (killedCellType != null) {
            processNegativeActions(killedCellType, PlayerType.Player, gameFieldButton);
            if (checkPlayerWins()) {
                StaticGameInfoAccessor.appendGameLog("Player Win!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                this.currentStatus = GameStatus.PlayerWin;
            }
            return;
        } else {
            checkAndProcessShootOfAi();
        }
    }

    private void checkAndProcessShootOfAi() {
        while (true) {
            final GameFieldButton gameFieldButton = AiPlayer.aimForAShoot();
            final CellType killedCellType = aiPlayer.checkAndProcessShoot(gameFieldButton);
            if (killedCellType == null) {
                return;
            } else {
                processNegativeActions(killedCellType, PlayerType.AI, gameFieldButton);
                if (checkAiWins()) {
                    StaticGameInfoAccessor.appendGameLog("AI Win!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    this.currentStatus = GameStatus.AiWin;
                }
            }
        }
    }

    private static void processNegativeActions(CellType killedCellType, PlayerType playerType, GameFieldButton gameFieldButton) {
        if (playerType.equals(PlayerType.Player)) {
            if (killedCellType.equals(CellType.Inv_Submarine)) {
                final GameFieldButton cellInPlayersGrid = StaticGameInfoAccessor.getCellInPlayersGrid(gameFieldButton.getRow(), gameFieldButton.getColumn());
                if (CellType.getAllCommonUnitsList().contains(cellInPlayersGrid.getCellType())) {
                    cellInPlayersGrid.setCellType(CellType.DeadShip);
                    return;
                }
                if (cellInPlayersGrid.getCellType().equals(CellType.Sea)) {
                    cellInPlayersGrid.setCellType(CellType.Shooted);
                    return;
                }
            }
            if (killedCellType.equals(CellType.Inv_Mine)) {
                // TODO бессмысленно в отсутвии умного ИИ
                return;
            }
            if (killedCellType.equals(CellType.Inv_Minesweeper)) {
                // TODO бессмысленно в отсутвии умного ИИ
                return;
            }
        }
        if (playerType.equals(PlayerType.AI)) {
            if (killedCellType.equals(CellType.Submarine)) {

            }
            if (killedCellType.equals(CellType.Mine)) {

            }
            if (killedCellType.equals(CellType.Minesweeper)) {

            }
        }
        // TODO дописать остальную уникальную логику
    }

    private static boolean checkPlayerWins() {
        final int gameFieldSize = getGameSettings().getGameFieldSize();

        for (int row = 1; row <= gameFieldSize; row++) {
            for (int column = 1; column <= gameFieldSize; column++) {
                if (CellType.getAllCommonUnitsList().contains(StaticGameInfoAccessor.getCellInOpponentsGrid(row, column).getCellType())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkAiWins() {
        final int gameFieldSize = getGameSettings().getGameFieldSize();

        for (int row = 1; row <= gameFieldSize; row++) {
            for (int column = 1; column <= gameFieldSize; column++) {
                if (CellType.getAllCommonUnitsList().contains(StaticGameInfoAccessor.getCellInPlayersGrid(row, column).getCellType())) {
                    return false;
                }
            }
        }
        return true;
    }

    private enum PlayerType {
        AI,
        Player
    }
}
