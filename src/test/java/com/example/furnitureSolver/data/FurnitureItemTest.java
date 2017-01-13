package com.example.furnitureSolver.data;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Lukáš on 13.01.2017.
 */
public class FurnitureItemTest {
    private Furniture furniture;

    @Before
    public void setUp() throws Exception {
        this.furniture = new Furniture();
    }

    @Test
    public void loadMapFromFileBrokenPath() {
        try {
            furniture.loadData(new File("files/furnitureX.txt"));
            assertThat(false);
        } catch (IOException e) {
            assertThat(true);
        }
    }

    @Test
    public void loadMapFromEmptyFile() {
        try {
            furniture.loadData(new File("files/furnitureE.txt"));
            assertThat(false);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isNotEmpty();
        } catch (IOException e) {
            assertThat(false);
        }
    }

    @Test
    public void loadMapFromFileOneSize() {
        try {
            furniture.loadData(new File("files/furnitureY.txt"));
            assertThat(furniture.getFurnitureItems().size()).isEqualTo(2);
            assertThat(furniture.getFurnitureItems().get(0).getHeight()).isEqualTo(1);
            assertThat(furniture.getFurnitureItems().get(0).getWidth()).isEqualTo(1);
            assertThat(furniture.getFurnitureItems().get(0).getType()).isEqualTo('A');
            assertThat(furniture.getFurnitureItems().get(0).getMap()[0][0]).isEqualTo('#');

            String row = ".#.###.#.";
            char[] toTest = row.toCharArray();
            FurnitureItem second = furniture.getFurnitureItems().get(1);
            assertThat(second.getType()).isEqualTo('B');
            assertThat(second.getWidth()).isEqualTo(1);
            assertThat(second.getHeight()).isEqualTo(row.length());
            for (int i = 0; toTest.length < i; i++) {
                assertThat(toTest[i]).isEqualTo(second.getMap()[i][0]).isEqualTo(toTest[i]);
            }

        } catch (IOException e) {
            assertThat(false);
        }
    }

    @Test
    public void loadMapFromFileBrokenZeroSize() {
        try {
            furniture.loadData(new File("files/furniture4.txt"));
            assertThat(false);
        } catch (Exception e) {
            assertThat(true);
        }
    }

    @Test
    public void loadMapFromFile() {
        try {
            furniture.loadData(new File("files/furniture.txt"));
            assertThat(furniture.getFurnitureItems().size()).isEqualTo(2);
            assertThat(furniture.getFurnitureItems().get(0).getType()).isEqualTo('A');
            assertThat(furniture.getFurnitureItems().get(1).getType()).isEqualTo('B');
            char[][] map1 = new char[2][2];
            map1[0] = "##".toCharArray();
            map1[1] = "##".toCharArray();

            char[][] map2 = new char[3][3];
            map2[0] = ".#.".toCharArray();
            map2[1] = "###".toCharArray();
            map2[2] = ".#.".toCharArray();

            assertThat(map1).isEqualTo(furniture.getFurnitureItems().get(0).getMap());
            assertThat(map2).isEqualTo(furniture.getFurnitureItems().get(1).getMap());
        } catch (IOException e) {
            assertThat(false);
        }
    }

    @Test
    public void loadMapFromString() {
        try {
            String row1 = "C2.###";
            String row2 = "D1#";

            furniture.loadData(row1+ System.lineSeparator() + row2);
            assertThat(furniture.getFurnitureItems().size()).isEqualTo(2);
            assertThat(furniture.getFurnitureItems().get(0).getType()).isEqualTo('C');
            assertThat(furniture.getFurnitureItems().get(1).getType()).isEqualTo('D');
            assertThat(furniture.getFurnitureItems().get(0).getMap()[0]).isEqualTo(".#".toCharArray());
            assertThat(furniture.getFurnitureItems().get(0).getMap()[1]).isEqualTo("##".toCharArray());

            assertThat(furniture.getFurnitureItems().get(1).getHeight()).isEqualTo(1);
            assertThat(furniture.getFurnitureItems().get(1).getWidth()).isEqualTo(1);
            assertThat(furniture.getFurnitureItems().get(1).getMap()[0]).isEqualTo("#".toCharArray());
        } catch (IOException e) {
            assertThat(false);
        }
    }
}