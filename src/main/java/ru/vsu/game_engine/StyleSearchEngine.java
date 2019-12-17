package ru.vsu.game_engine;

import ru.vsu.domain.CellType;

/**
 * @author Ivan Rovenskiy
 * 18 December 2019
 */
public class StyleSearchEngine {
    public static String processCellStyle(CellType cellType) {
        switch (cellType) {
            case Sea:
                return "sea-grid-cell";
            case CommonShip_4: case CommonShip_3: case CommonShip_2: case CommonShip_1:
                return "common-ship-grid-cell";
            default:
                return "sea-grid-cell";
        }
    }
}
