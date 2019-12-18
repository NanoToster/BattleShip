package ru.vsu.domain;

import java.util.List;

/**
 * @author Ivan Rovenskiy
 * 18 December 2019
 */
public enum CellType {
    CommonShip_4(4),
    CommonShip_3(3),
    CommonShip_2(2),
    CommonShip_1(1),
    Submarine(1),
    Mine(1),
    Minesweeper(1),

    Sea(1),
    Selected(1),
    DeadZone(1),

    DeadShip(1);

    private int range;

    CellType(int range) {
        this.range = range;
    }

    public int getRange() {
        return range;
    }

    public static List<CellType> getCommonShipsList() {
        return List.of(CellType.CommonShip_4, CellType.CommonShip_3, CellType.CommonShip_2, CellType.CommonShip_1);
    }

    public static List<CellType> getAllUnitsList() {
        return List.of(CellType.CommonShip_4, CellType.CommonShip_3, CellType.CommonShip_2, CellType.CommonShip_1,
                CellType.Submarine, CellType.Mine, CellType.Minesweeper);
    }
}
