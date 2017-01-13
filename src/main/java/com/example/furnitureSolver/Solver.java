package com.example.furnitureSolver;

import com.example.furnitureSolver.data.*;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lukáš on 07.01.2017.
 */
public class Solver {
    private Room room;
    private Furniture furniture;
    private List<Solution> correctSolutions = new ArrayList<>();

    public Room getRoom() {
        return room;
    }

    public List<Solution> getCorrectSolutions() {
        return correctSolutions;
    }

    public Solver(Room room, Furniture furniture) {
        this.room = room;
        this.furniture = furniture;
    }

    public void addCorrectSolutions(Solution solution) {
        getCorrectSolutions().add(solution);
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public List<Solution> start() {
        getCorrectSolutions().clear();
        generateAndTestSolutions();
        return correctSolutions;
    }

    private void generateAndTestSolutions() {
        Solution firstPossibleSolution = new Solution();
        for(FurnitureItem f : getFurniture().getFurnitureItems()){
            firstPossibleSolution.addSolution(new SolutionItem(0,0,f));
        }
        ArrayList<Solution> acumulator = new ArrayList<>();
        acumulator.add(firstPossibleSolution);

        ArrayList<SolutionItem> solutionItems = new ArrayList<>();
        generateAllSolutions(0, solutionItems);
    }

    private void generateAllSolutions(int currentItem, ArrayList<SolutionItem> solutions)
    {
        if (currentItem < getFurniture().getFurnitureItems().size()) {

            for(int i = 0; i < getRoom().getHeight(); i++) {
                for(int j = 0; j < getRoom().getWidth(); j++) {
                    ArrayList<SolutionItem> newAcc = new ArrayList<>(solutions);
                    newAcc.add(new SolutionItem(i,j,getFurniture().getFurnitureItems().get(currentItem)));
                    generateAllSolutions(currentItem + 1, newAcc);
                }
            }
        } else {
            Solution possibleSolution = new Solution();
            for(SolutionItem s : solutions) {
                possibleSolution.addSolution(s);
            }
            if(getRoom().isSolutionCorrect(possibleSolution)){
                addCorrectSolutions(possibleSolution);
            }
        }
    }

}
