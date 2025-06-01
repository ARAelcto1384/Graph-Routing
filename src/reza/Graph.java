package reza;

import java.util.LinkedList;
import java.util.Scanner;

public class Graph {
    //attribute
    private LinkedList<Head> heads;
    private LinkedList<Tail> tails;

    Scanner input = new Scanner(System.in);

    //Constructors
    public Graph() {
        int i = 1,distanceTail;
        String nameHead;

        this.heads = new LinkedList<>();
        this.tails = new LinkedList<>();

        System.out.print("Please enter head (" + i + ") :(When finished, type 'quit'.) ");
        while(!(nameHead = input.next()).equals("quit")) {
            i++;
            System.out.print("Please enter head (" + i + ") :(When finished, type 'quit'.) ");
            this.heads.add(new Head(nameHead));
        }

        if(!this.heads.isEmpty()){
            i = 1;
            String originTail, destinationTail;

            do {
                System.out.println("Please enter The beginning and end of the tail(" + i + ") :(When finished, type 'quit'.) ");
                System.out.print("beginning(" + i + "): ");
                originTail = input.next();
                if(originTail.equals("quit")) break;
                System.out.print("end(" + i + "): ");
                destinationTail = input.next();
                System.out.print("distance(" + i + "): ");
                try {
                    distanceTail = input.nextInt();
                } catch (Exception e) {
                    System.out.print("\nInvalid distance entered! Please enter a valid distance: ");
                    distanceTail = input.nextInt();
                }
                try {
                    this.tails.add(new Tail(this.search(originTail), this.search(destinationTail), distanceTail));
                } catch (HeadNotFound e) {
                    System.out.println(e.getMessage());
                    i--;
                }
                i++;
            } while(true);
        }

        System.out.println("---------Graph was created!");
    }
    public Graph(LinkedList<Head> heads,LinkedList<Tail> tails){
        this.heads = heads;
        this.tails = tails;
    }

    //Methods
    public Head search(String name){
        for(Head head : this.heads){
           if(head.name.equals(name)){
               return head;
           }
        }
        throw new HeadNotFound();
    }
    public Tail minTail(String origin){
        Tail minInterface = this.tails.getFirst();

        for(Tail tail : this.tails){
            if(tail.origin.name.equals(origin)){

                //Ring
                if(tail.origin.name.equals(tail.destination.name)) continue;

                //Wheel
                if(origin.equals(tail.destination.name)) continue;

                if(tail.distance > minInterface.distance){
                    minInterface = tail;
                }
            }
        }
        try{
            this.search(origin);
            System.out.print(" --> " + minInterface.destination.name);
            return minInterface;
        }
        catch (HeadNotFound e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
