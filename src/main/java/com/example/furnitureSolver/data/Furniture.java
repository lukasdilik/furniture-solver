package com.example.furnitureSolver.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lukáš on 20.12.2016.
 */
public class Furniture {
    private List<FurnitureItem> furnitureItems;

    public List<FurnitureItem> getFurnitureItems() {
        return furnitureItems;
    }

    public void loadData(File file) throws IOException {
        loadFurniture(new BufferedReader(new FileReader(file)));
    }

    public void loadData(String string) throws IOException {
        loadFurniture(new BufferedReader(new StringReader(string)));
    }

    private void loadFurniture(BufferedReader reader) throws IOException {
        this.furnitureItems = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null){
            furnitureItems.add(new FurnitureItem(line));
        }
    }

    @Override
    public String toString() {
        String ouput = "";
        for(FurnitureItem i : getFurnitureItems()){
            ouput += i.toString();
        }
        return ouput;
    }
}
