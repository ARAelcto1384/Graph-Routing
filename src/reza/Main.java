package reza;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("<<<<<router>>>>>");

        Graph graph = new Graph();
        System.out.println("We want to find the best and shortest path between the origin and destination of graph. The algorithm we use is Dijkstra.");
        System.out.print("Please enter the name of the origin: ");
        String origin = input.next();
        Tail interfacee;
        System.out.print("Please enter the name of the destination: ");
        String destination = input.next();
        int totalDistance = 0;
        System.out.print(origin);
        interfacee = graph.minTail(origin);
        while(true){
            totalDistance += interfacee.distance;
            if(interfacee.destination.name.equals(destination)){
                System.out.println("\nThe way found! Total distance is: " + totalDistance);
                break;
            }
            if(interfacee == null) break;
            interfacee = graph.minTail(interfacee.destination.name);

        }


    }
}