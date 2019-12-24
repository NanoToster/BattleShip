package ru.vsu.game_engine.players;

import ru.vsu.components.GameFieldButton;
import ru.vsu.domain.CellType;

/**
 * @author Ivan Rovenskiy
 * 22 December 2019
 */
public interface Player {
    CellType checkAndProcessShoot(GameFieldButton gameFieldButton);
}
