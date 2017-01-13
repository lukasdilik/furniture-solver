package com.example.furnitureSolver.data;

/**
 * Created by Lukáš on 20.12.2016.
 */
public class FurnitureItem {
    private char type;
    private char[][] map;
    private int height, width;

    public char getType() {
        return type;
    }

    public char[][] getMap() {
        return map;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public FurnitureItem(String inputData) {
        if (inputData == null || inputData.length() < 3) {
            throw new RuntimeException("height must be greater than 3");
        }
        this.type = inputData.charAt(0);
        int maxWidth = Integer.parseInt(String.valueOf(inputData.charAt(1)));
        char[] dataArray = new char[inputData.length() - 2];
        inputData.getChars(2, inputData.length(), dataArray, 0);
        this.height = (inputData.length()-2) / maxWidth;
        this.width = maxWidth;
        this.map = new char[this.height][this.width];
        for (int i = 0; i < dataArray.length; i++) {
            int j = i % maxWidth;
            int k = i / maxWidth;
            this.map[k][j] = dataArray[i];
        }
    }

    @Override
    public String toString() {
        String output = getType()+":"+System.lineSeparator();
        for(int i = 0; i < getMap().length; i++){
            for(int j = 0; j < getMap()[i].length; j++){
                output += getMap()[i][j];
            }
            boolean islastLine = (i+1) == getMap().length;
            if(!islastLine){
                output += System.lineSeparator();
            }
        }
        return output;
    }
}