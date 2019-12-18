package ru.vsu.game_engine;

import ru.vsu.domain.CellType;

import java.util.List;

/**
 * @author Ivan Rovenskiy
 * 18 December 2019
 */
public class StyleSearchEngine {
    public static List<String> processCellStyle(CellType cellType) {
        switch (cellType) {
            case Sea:
                return List.of("sea-grid-cell", "grid-cell");
            case Selected:
                return List.of("selected-grid-cell", "grid-cell");
            case CommonShip_4:
            case CommonShip_3:
            case CommonShip_2:
            case CommonShip_1:
                return List.of("common-ship-grid-cell", "grid-cell");
            case Minesweeper:
                return List.of("minesweeper-cells-grid-cell", "grid-cell");
            case Submarine:
                return List.of("submarine-cells-grid-cell", "grid-cell");
            case DeadShip:
                return List.of("dead-ship-cells-grid-cell", "grid-cell");
            case DeadZone:
                return List.of("dead-zone-cells-grid-cell", "grid-cell");
            case Mine:
                return List.of("mine-cells-grid-cell", "grid-cell");
            default:
                return List.of("grid-cell");
        }
    }
}
