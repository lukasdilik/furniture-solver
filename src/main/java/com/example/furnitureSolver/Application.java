package com.example.furnitureSolver;

import com.example.furnitureSolver.data.Furniture;
import com.example.furnitureSolver.data.Room;
import com.example.furnitureSolver.data.Solution;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Lukáš on 19.12.2016.
 */
public class Application {
    public static void main(String[] args) throws IOException {
        runApplication(args);
    }

    private static void runApplication(String[] args) throws IOException {
        String firstArg;
        String secondArg;

        if (args.length != 2) {
            String error = "U must provide exactly 2 arguments"+System.lineSeparator();
            error += "First is room map (.txt file path)"+System.lineSeparator();
            error += "Second is furniture list ( .txt file path)";
            throw new IllegalArgumentException(error);
        }

        firstArg = args[0];
        secondArg = args[1];

        if (firstArg.length() < 1) {
            throw new IllegalArgumentException("Room map cannot be empty");
        }
        if (secondArg.length() < 1) {
            throw new IllegalArgumentException("Furniture list cannot be empty");
        }

        Room room = new Room();
        Furniture furniture = new Furniture();
        File roomFile = new File(firstArg);
        File furnitureFile = new File(secondArg);
        if (!roomFile.exists()) {
            throw new IOException("Room file on path " + firstArg + " not exists");
        }
        if (!furnitureFile.exists()) {
            throw new IOException("Furniture file on path " + secondArg + " not exists");
        }

        room.loadData(roomFile);
        furniture.loadData(furnitureFile);

        Solver solver = new Solver(room, furniture);

        List<Solution> solutions = solver.start();

        for (Solution s : solutions) {
            System.out.println(s);
        }
    }


}

