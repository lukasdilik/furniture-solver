package com.example.furnitureSolver.data;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Lukáš on 12.01.2017.
 */
public class SolutionsTest {

    private Room room;
    private Furniture furniture;

    @Before
    public void setUp() throws Exception {
        this.room = new Room();
        this.furniture = new Furniture();
        this.room.loadData(new File("files/room.txt"));
        this.furniture.loadData(new File("files/furniture.txt"));
    }

    @Test
    public void correctSolutionsTest() {
        FurnitureItem A = this.furniture.getFurnitureItems().get(0);
        FurnitureItem B = this.furniture.getFurnitureItems().get(1);

        Solution s = new Solution();
        s.addSolution(new SolutionItem(0,2,A));
        s.addSolution(new SolutionItem(1,3,B));
        assertThat(room.isSolutionCorrect(s)).isTrue();

        s = new Solution();
        s.addSolution(new SolutionItem(0,3,A));
        s.addSolution(new SolutionItem(1,0,B));
        assertThat(room.isSolutionCorrect(s)).isTrue();

        s = new Solution();
        s.addSolution(new SolutionItem(2,1,A));
        s.addSolution(new SolutionItem(2,3,B));
        assertThat(room.isSolutionCorrect(s)).isTrue();

        s = new Solution();
        s.addSolution(new SolutionItem(2,3,A));
        s.addSolution(new SolutionItem(0,1,B));
        assertThat(room.isSolutionCorrect(s)).isTrue();
    }

    @Test
    public void inCorrectSolutionsTest() {
        FurnitureItem A = this.furniture.getFurnitureItems().get(0);
        FurnitureItem B = this.furniture.getFurnitureItems().get(1);

        Solution s = new Solution();
        s.addSolution(new SolutionItem(0,0,A));
        s.addSolution(new SolutionItem(0,0,B));
        assertThat(room.isSolutionCorrect(s)).isFalse();

        s = new Solution();
        s.addSolution(new SolutionItem(0,3,A));
        s.addSolution(new SolutionItem(1,4,B));
        assertThat(room.isSolutionCorrect(s)).isFalse();

        s = new Solution();
        s.addSolution(new SolutionItem(3,2,A));
        s.addSolution(new SolutionItem(1,0,B));
        assertThat(room.isSolutionCorrect(s)).isFalse();

        s = new Solution();
        s.addSolution(new SolutionItem(2,3,A));
        s.addSolution(new SolutionItem(0,2,B));
        assertThat(room.isSolutionCorrect(s)).isFalse();
    }
}