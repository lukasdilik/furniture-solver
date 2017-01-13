package com.example.furnitureSolver.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lukáš on 07.01.2017.
 */
public class Solution {
    private List<SolutionItem> solutionItems;

    public void clear() {
        this.solutionItems.clear();
    }

    public Solution() {
        this.solutionItems = new ArrayList<>();
    }

    public List<SolutionItem> getSolutionItems() {
        return solutionItems;
    }

    public void addSolution(SolutionItem solutionItem) {
        this.solutionItems.add(solutionItem);
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < solutionItems.size(); i++) {
            result += solutionItems.get(i).toString();
            result += (i == (solutionItems.size() - 1)) ? "" : " ";
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        Solution s = (Solution) o;
        if(s == null && this == null){
            return true;
        }
        if(s.getSolutionItems().size() !=  this.getSolutionItems().size()){
            return false;
        }

        for (int i = 0; i < this.solutionItems.size(); i++){
            if(!this.solutionItems.get(i).equals(s.getSolutionItems().get(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return solutionItems != null ? solutionItems.hashCode() : 0;
    }
}
