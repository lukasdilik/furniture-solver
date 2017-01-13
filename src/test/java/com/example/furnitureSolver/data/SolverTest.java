package com.example.furnitureSolver.data;

import com.example.furnitureSolver.Solver;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by Lukáš on 12.01.2017.
 */
public class SolverTest {

    private Furniture furniture;
    private Room room;

    @Before
    public void setUp() throws Exception {
        this.furniture = new Furniture();
        this.room = new Room();
    }

    @Test
    public void correctSolutions() throws IOException {
        room.loadData(new File("files/room5.txt"));
        furniture.loadData(new File("files/furniture5.txt"));
        FurnitureItem A = this.furniture.getFurnitureItems().get(0);
        FurnitureItem B = this.furniture.getFurnitureItems().get(1);
        FurnitureItem C = this.furniture.getFurnitureItems().get(2);

        Solver solver = new Solver(room, furniture);
        solver.start();
        List<Solution> correctSolutions = solver.getCorrectSolutions();
        assertThat(correctSolutions.size()).isNotZero();

        Solution correct1 = new Solution();
        correct1.addSolution(new SolutionItem(1, 0, A));
        correct1.addSolution(new SolutionItem(0, 2, B));
        correct1.addSolution(new SolutionItem(2, 1, C));

        assertThat(correctSolutions).contains(correct1);

        Solution correct2 = new Solution();
        correct2.addSolution(new SolutionItem(1, 0, A));
        correct2.addSolution(new SolutionItem(2, 2, B));
        correct2.addSolution(new SolutionItem(2, 0, C));

        assertThat(correctSolutions).contains(correct2);

        Solution inCorrect = new Solution();
        inCorrect.addSolution(new SolutionItem(1, 0, A));
        inCorrect.addSolution(new SolutionItem(2, 2, B));
        inCorrect.addSolution(new SolutionItem(2, 1, C));
        assertThat(correctSolutions).doesNotContain(inCorrect);

        for (Solution s : correctSolutions) {
            assertThat(s.getSolutionItems().size()).isEqualTo(3);
            assertThat(s.getSolutionItems()).doesNotContain(new SolutionItem(0, 0, A));
            assertThat(s.getSolutionItems()).doesNotContain(new SolutionItem(0, 0, B));
            assertThat(s.getSolutionItems()).doesNotContain(new SolutionItem(0, 0, C));
            assertThat(s.getSolutionItems()).doesNotContain(new SolutionItem(0, 1, A));
            assertThat(s.getSolutionItems()).doesNotContain(new SolutionItem(0, 1, B));
            assertThat(s.getSolutionItems()).doesNotContain(new SolutionItem(0, 1, C));
        }

    }

    @Test
    public void correctSolutionsStringOneSize() throws IOException {
        room.loadData("1,1" + System.lineSeparator() + "#");
        furniture.loadData("A1#");
        FurnitureItem A = this.furniture.getFurnitureItems().get(0);
        Solver solver = new Solver(room, furniture);
        solver.start();
        List<Solution> correctSolutions = solver.getCorrectSolutions();
        assertThat(correctSolutions.size()).isEqualTo(1);
        Solution correct = new Solution();
        correct.addSolution(new SolutionItem(0, 0, A));
        assertThat(correctSolutions.get(0)).isEqualTo(correct);
    }

    @Test
    public void correctSolutionsStringSquareSize() throws IOException {
        room.loadData("2,2" + System.lineSeparator() + "##" + System.lineSeparator() + "#.");
        furniture.loadData("A1#");
        FurnitureItem A = this.furniture.getFurnitureItems().get(0);
        Solver solver = new Solver(room, furniture);
        solver.start();
        List<Solution> correctSolutions = solver.getCorrectSolutions();
        assertThat(correctSolutions.size()).isEqualTo(3);

        List<Solution> all = new ArrayList<>();
        Solution s;
        s = new Solution();
        s.addSolution(new SolutionItem(0, 0, A));
        all.add(s);
        s = new Solution();
        s.addSolution(new SolutionItem(0, 1, A));
        all.add(s);
        s = new Solution();
        s.addSolution(new SolutionItem(1, 0, A));
        all.add(s);

        assertThat(correctSolutions).isEqualTo(all);
    }
}