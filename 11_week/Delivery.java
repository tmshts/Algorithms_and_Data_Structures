import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Delivery {

    public static void main(String[] args) {

        File file = new File("./deliveries.csv");

        int number = 100;
    
        String[] addresses = new String[number];
        int[][] distances = new int[number][number];

        try {
            Scanner scan = new Scanner(file);

            for(int i = 0; i < number; i++) {
                String line = scan.nextLine();
                // Split the line by commas
                String[] parts = line.split(",");
                //get the address
                addresses[i] = parts[0];
                //get the distances
                for (int j = 0; j < number; j++) {  
                    distances[i][j] = Integer.parseInt(parts[j+1].trim());
                }
            }
            scan.close();
        } catch(Exception e) {
            System.err.println(e);
        }
        
        /*
        for(int i = 0; i < 100; i++) {
            System.out.println(addresses[i]);
        }
        */
        
        /*
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 99; j++) {
                System.out.print(distances[i][j]+",");
            }
            System.out.println(distances[i][99]);
            System.out.println("**********");
        }
        */

        // create array for (un)visited cities - fill with false
        boolean[] visited = new boolean[number];
        for(int i = 0; i < number; i++) {
            visited[i] = false;
        }

        int total_distance = 0;

        // Start at Pizza Base
        int current_city = 0;

        ArrayList<Integer> track = new ArrayList<Integer>();

        // add Pizza base to track
        track.add(current_city);
        // mark Pizza base as visited
        visited[current_city] = true;

        // set up for the for loop
        int nearest_city = 0;
        int nearest_distance = 1000000;
        int calculated_distance = 0;

        // we should iterate only over 98 cities in first for loop
        // as second for loop iterates over 99 cities
        for(int i = 0; i < number - 1; i++) {
            nearest_city = 0;
            nearest_distance = 10000;

            // find the nearest unvisited city
            for(int j = 0; j < number; j++) {
                if(visited[j] == false) {
                    calculated_distance = distances[current_city][j];
                    if(calculated_distance < nearest_distance) {
                        nearest_city = j;
                        nearest_distance = calculated_distance;
                    }
                }
            }

            // move to the nearest city
            current_city = nearest_city;
            track.add(current_city);
            visited[current_city] = true;
            total_distance += nearest_distance;
        }
        
        // return to the Pizza base
        track.add(0);
        total_distance += distances[current_city][0];

        System.out.println("Total distance");
        System.out.println(total_distance);

        // System.out.println(addresses[track.get(99)]);

        
        for(int i = 0; i < track.size(); i++) {
            System.out.println(addresses[track.get(i)]);
        }
        

    }


}