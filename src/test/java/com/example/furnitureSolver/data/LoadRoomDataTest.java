package com.example.furnitureSolver.data;

import org.junit.*;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by Lukáš on 12.01.2017.
 */
public class LoadRoomDataTest {

    private Room room;

    @Before
    public void setUp() throws Exception {
        this.room = new Room();
    }

    @Test
    public void loadMapFromFileBrokenPath() {
        try {
            room.loadData(new File("files/roomX.txt"));
            assertThat(false);
        } catch (IOException e) {
            assertThat(true);
        }
    }

    @Test
    public void loadMapFromEmptyFile() {
        try {
            room.loadData(new File("files/roomE.txt"));
            assertThat(false);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isNotEmpty();
        } catch (IOException e) {
            assertThat(false);
        }
    }

    @Test
    public void loadMapFromFileBrokenSize() {
        try {
            room.loadData(new File("files/roomY.txt"));
            assertThat(false);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isNotEmpty();
        } catch (IOException e) {
            assertThat(false);
        }
    }

    @Test
    public void loadMapFromFileBrokenZeroSize() {
        try {
            room.loadData(new File("files/room4.txt"));
            assertThat(room.getHeight()).isEqualTo(0);
            assertThat(room.getWidth()).isEqualTo(0);
            assertThat(room.getMap().length).isEqualTo(0);
        } catch (IOException e) {
            assertThat(false);
        }
    }

    @Test
    public void loadMapFromFile() {
        try {
            room.loadData(new File("files/room.txt"));
            assertThat(room.getHeight()).isEqualTo(5);
            assertThat(room.getWidth()).isEqualTo(6);
            char[] firstRow = {'.', '.', '#', '#', '#', '.'};
            assertThat(room.getMap()[0]).isEqualTo(firstRow);
            assertThat(room.getMap()[room.getHeight() - 1][room.getWidth() - 1]).isEqualTo('#');
        } catch (IOException e) {
            assertThat(false);
        }
    }

    @Test
    public void loadMapFromFileSmaller() {
        try {
            room.loadData(new File("files/room3.txt"));
            assertThat(room.getHeight()).isEqualTo(2);
            assertThat(room.getWidth()).isEqualTo(2);
            char[] firstRow = {'.', '.'};
            assertThat(room.getMap()[0]).isEqualTo(firstRow);
            assertThat(room.getMap()[room.getHeight() - 1][room.getWidth() - 1]).isEqualTo('#');
        } catch (IOException e) {
            assertThat(false);
        }
    }

    @Test
    public void loadMapFromString() {
        try {
            String map = ".#" + "\r\n" + "##";
            room.loadData("2,2" + System.lineSeparator() + map);
            assertThat(room.getHeight()).isEqualTo(2);
            assertThat(room.getWidth()).isEqualTo(2);
            char[] firstRow = {'.', '#'};
            char[] secondRow = {'#', '#'};
            assertThat(room.getMap()[0]).isEqualTo(firstRow);
            assertThat(room.getMap()[1]).isEqualTo(secondRow);
        } catch (IOException e) {
            assertThat(false);
        }
    }


}