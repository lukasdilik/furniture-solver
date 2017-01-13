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

        if(args.length != 2){
            System.err.println("U must provide exactly 2 arguments");
            System.err.println("First is room map (String or .txt file path)");
            System.err.println("Second is furniture list (String or .txt file path)");
        }

        firstArg = args[0];
        secondArg = args[1];

        if(firstArg.length() < 1){
            System.err.println("Room map cannot be empty");
        }
        if(secondArg.length() < 1){
            System.err.println("Furniture list cannot be empty");
        }

        Room room = new Room();
        Furniture furniture = new Furniture();

        if(firstArg.contains(".txt")){
            room.loadData(new File(firstArg));
        }else {
            room.loadData(firstArg);
        }

        if(secondArg.contains(".txt")){
            furniture.loadData(new File(secondArg));
        }else {
            furniture.loadData(secondArg);
        }

        Solver solver = new Solver(room, furniture);

        List<Solution> solutions = solver.start();

        for(Solution s: solutions){
            System.out.println(s);
        }
    }


}

