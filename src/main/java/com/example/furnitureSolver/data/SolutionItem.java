package com.example.furnitureSolver.data;

/**
 * Created by Lukáš on 07.01.2017.
 */
public class SolutionItem {
    int row, col;
    FurnitureItem furnitureItem;

    public SolutionItem(int row, int col, FurnitureItem furnitureItem) {
        this.row = row;
        this.col = col;
        this.furnitureItem = furnitureItem;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public FurnitureItem getFurnitureItem() {
        return furnitureItem;
    }

    @Override
    public String toString() {
        return String.format("%s(%s,%s)", furnitureItem.getType(), getRow(), getCol());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SolutionItem that = (SolutionItem) o;

        if (row != that.getRow()) return false;
        if (col != that.getCol()) return false;
        return this.getFurnitureItem().getType() == that.getFurnitureItem().getType();
    }

    @Override
    public int hashCode() {
        int result = getRow();
        result = 31 * result + getCol();
        result = 31 * result + (int) this.getFurnitureItem().getType();
        return result;
    }
}
