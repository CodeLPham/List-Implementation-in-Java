/*
 CMPS 101 FALL 2017
 Student: Leon Pham
 ID: 1595392
 Assignment: pa1
 */


import java.io.*;
import java.util.Scanner;

public class Lex {
    public Lex() {
    }

    public static void main(String[] args) throws IOException {
        Scanner inFile = null;
        PrintWriter outFile = null;
        String[] lineArray = null;
        int lines = 0;
        new List();
        
        inFile = new Scanner(new File(args[0]));
        outFile = new PrintWriter(new FileWriter(args[1]));
        
        if (args.length != 2) {
            System.err.println("Invalid number of arguements");
            System.exit(1);
        }

        while(inFile.hasNextLine()) {
            ++lines;
            inFile.nextLine();
        }
        inFile.close();
        outFile.close();
        
        
        lineArray = new String[lines];
        inFile = new Scanner(new File(args[0]));
        outFile = new PrintWriter(new FileWriter(args[1]));
        
        int i;
        for(i = 0; i < lines; ++i) {
            lineArray[i] = inFile.nextLine();
        }

        List listOfLines = insertionSort(lineArray);
        
        for(i = 0; i < lines; ++i) {
            outFile.println(lineArray[listOfLines.get()]);
            
        }

        outFile.close();
        inFile.close();
    }

    public static List insertionSort(String[] myArray) {
        List L = new List();
        L.append(0);
        String tail = null;
        String head = null;
        String current = null;
        int myLength = myArray.length;

        for(int k = 1; k < myLength; ++k) {
            current = myArray[k];
            head = myArray[L.front()];
            tail = myArray[L.back()];


            if (current.compareTo(head) < 0) {
                L.prepend(k);
            } else if (current.compareTo(tail) > 0) {
                L.append(k);
            } else {
                L.moveFront();

                while(L.index() != -1) {

                    if (current.compareTo(myArray[L.get()]) >= 0) {
                        L.insertBefore(k);
                        break;
                    }
                    L.moveNext();
                }
            }
        }
        return L;
    }
}
