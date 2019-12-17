package ru.vsu.components;

import javafx.scene.control.Button;
import ru.vsu.domain.CellType;

/**
 * @author Ivan Rovenskiy
 * 17 December 2019
 */
public class GameFieldButton extends Button {
    private int column;
    private int row;
    private CellType cellType;
    private FieldType fieldType;

    public GameFieldButton(int column, int row, FieldType fieldType) {
        super();
        this.column = column;
        this.row = row;
        this.fieldType = fieldType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public CellType getCellType() {
        return cellType;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public enum FieldType {
        Player,
        Enemy
    }
}
