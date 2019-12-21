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

    Inv_CommonShip_4(4),
    Inv_CommonShip_3(3),
    Inv_CommonShip_2(2),
    Inv_CommonShip_1(1),
    Inv_Submarine(1),
    Inv_Mine(1),
    Inv_Minesweeper(1),

    Sea(1),
    Selected(1),
    DeadZone(1),

    DeadShip(1),
    Shooted(1);

    private int range;

    CellType(int range) {
        this.range = range;
    }

    public int getRange() {
        return range;
    }

    public static List<CellType> getInvUnitsList() {
        return List.of(CellType.Inv_CommonShip_4, CellType.Inv_CommonShip_3, CellType.Inv_CommonShip_2,
                CellType.Inv_CommonShip_1, CellType.Inv_Submarine, CellType.Inv_Mine, CellType.Inv_Minesweeper);
    }

    public static List<CellType> getAllUnitsList() {
        return List.of(CellType.CommonShip_4, CellType.CommonShip_3, CellType.CommonShip_2,
                CellType.CommonShip_1, CellType.Submarine, CellType.Mine, CellType.Minesweeper,
                CellType.Inv_CommonShip_4, CellType.Inv_CommonShip_3, CellType.Inv_CommonShip_2,
                CellType.Inv_CommonShip_1, CellType.Inv_Submarine, CellType.Inv_Mine, CellType.Inv_Minesweeper);
    }
}
