package com.example.furnitureSolver.data;

import java.io.*;

/**
 * Created by Lukáš on 19.12.2016.
 */
public class Room {

    public static final char RESERVED = '?';
    public static final char NONE = '.';
    public static final char FREE = '#';
    private char[][] map;
    private char[][] originalMap;
    private int height, width;

    public void loadData(File file) throws IOException {
        loadRoomMap(new BufferedReader(new FileReader(file)));
    }

    public void loadData(String string) throws IOException {
        loadRoomMap(new BufferedReader(new StringReader(string)));
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

    private void loadRoomMap(BufferedReader reader) throws IOException {
        String firstLine = reader.readLine();
        initializeMap(firstLine);
        fillMapData(reader);
    }

    private void initializeMap(String firstLine) {
        if(firstLine == null || firstLine.length() == 0){
            throw new RuntimeException("Dimensions are empty");
        }
        String[] dimensions = firstLine.split(",");
        if(dimensions.length != 2){
            throw new RuntimeException("u must provide at least 2 dimensions");
        }
        this.height = Integer.parseInt(dimensions[0]);
        this.width = Integer.parseInt(dimensions[1]);
        this.map = new char[this.height][this.width];
        this.originalMap = new char[this.height][this.width];
    }

    private void fillMapData(BufferedReader reader) throws IOException {
        String line;
        for(int row = 0; row < getHeight(); row++){
            line = reader.readLine();
            char[] lineChar = line.toCharArray();
            for(int col = 0; col < getWidth(); col++){
                this.map[row][col] = lineChar[col];
                this.originalMap[row][col] = lineChar[col];
            }
        }
    }

    private void clearRoom(){
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                this.map[row][col] = this.originalMap[row][col];
            }
        }
    }

    public boolean isSolutionCorrect(Solution solution){
        clearRoom();
        for(SolutionItem solutionItem: solution.getSolutionItems()){
            if(isCorrectSolutionItem(solutionItem)){
                placeItem(solutionItem);
            }else {
                return false;
            }
        }
        return true;
    }

    private boolean isCorrectSolutionItem(SolutionItem solutionItem){
        if(solutionItem.getRow() + solutionItem.getFurnitureItem().getHeight() > getHeight()){
            return false;
        }
        if(solutionItem.getCol() + solutionItem.getFurnitureItem().getWidth() > getWidth()){
            return false;
        }

        for(int row = 0; row < solutionItem.getFurnitureItem().getHeight(); row++){
            for(int col = 0; col <  solutionItem.getFurnitureItem().getWidth(); col++){
                char roomUnit = getMap()[row+solutionItem.getRow()][col+solutionItem.getCol()];
                char itemUnit = solutionItem.getFurnitureItem().getMap()[row][col];
                //negative cases, when furnitureItem certainly does not fit
                if(itemUnit == NONE){
                    continue;
                }
                if(roomUnit == RESERVED){
                    return false;
                }
                if(roomUnit == NONE && itemUnit == FREE){
                    return false;
                }
            }
        }
        return true;
    }

    private void placeItem(SolutionItem solutionItem){
        for(int row = 0; row < solutionItem.getFurnitureItem().getHeight(); row++) {
            for (int col = 0; col < solutionItem.getFurnitureItem().getWidth(); col++) {
                if(solutionItem.getFurnitureItem().getMap()[row][col] == NONE){
                    continue;
                }
                this.map[row+solutionItem.getRow()][col+solutionItem.getCol()] = RESERVED;
            }
        }
    }

    @Override
    public String toString() {
        String output = "";
        for(int row = 0; row < this.map.length; row++){
            for(int col = 0; col < this.map[row].length; col++){
                output += this.map[row][col];
            }
            output += System.lineSeparator();
        }
        return output;
    }
}
