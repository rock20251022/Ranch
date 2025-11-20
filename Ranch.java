/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ranch;
import java.util.Scanner;

public class Ranch {
    public static final int gMAX_BARNS = 15; 
    public static final int gMAX_OCCUPANCY = 50;
    public static int[] gBarns = new int[gMAX_BARNS];
    public static int gBarnNum;
    public static final Scanner gSCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Fall 2024 - UTSA - CS1083 - Section 003 - Project 2 - Ranch - written by Andrew Teran");
        
        // Prompt user to enter the number of barns
        System.out.print("Please enter the number of barns (Max 15): ");
        gBarnNum = gSCANNER.nextInt();
        while (gBarnNum < 0 || gBarnNum > gMAX_BARNS) {
            System.out.println("ERROR, Number of barns must be between 0 and 15.");
            System.out.print("Please enter the number of barns (Max 15): ");
            gBarnNum = gSCANNER.nextInt();
        }
        
        int option = -1; 
        do {
    // Main menu
        System.out.print("\nMAIN MENU\n0 - Empty barn, 1 - Add/Remove cattle, 2 - List barns occupancy, 3 - Add new barn, 4 - Report, 5 - Exit\n");
        System.out.print("Select an option: ");
    
    
        if (gSCANNER.hasNextInt()) {
        option = gSCANNER.nextInt(); 
        
        
        if (option < 0 || option > 5) {
            System.out.println("ERROR, You need to enter a number between 0 and 5 (inclusive).");
        } else {
            if (option == 0) {
                emptyBarn();
            } else if (option == 1) {
                addRemoveCattle();
            } else if (option == 2) {
                listBarns();
            } else if (option == 3) {
                addNewBarn();
            } else if (option == 4) {
                report();
            } else if (option == 5) {
                System.out.println("Exiting program.");
            }
        }
    } else {
        
        System.out.println("ERROR, You need to enter a number between 0 and 5 (inclusive).");
        gSCANNER.next(); 
        option = -1; 
    }
} while (option != 5); 
    }

    // Method to empty a barn
    public static void emptyBarn() {
        System.out.print("Enter barn index to empty: ");
        int index = gSCANNER.nextInt();
        
        if (index >= 0 && index < gBarnNum) {
            gBarns[index] = 0;
            System.out.println("Barn " + index + " is now empty.");
        } else {
            System.out.println("ERROR, Invalid barn index.");
        }
    }

    // Method to add or remove cattle from a barn
    public static void addRemoveCattle() {
    System.out.print("Enter barn index to modify (0 to " + (gBarnNum - 1) + "): ");
    int index = gSCANNER.nextInt();
    
    if (index >= 0 && index < gBarnNum) {
        System.out.println("Current occupancy of the barn at index " + index + " is: " + gBarns[index]);
        
        int quantity;
        do {
            System.out.print("Enter quantity of cattle to add/remove (0-" + gMAX_OCCUPANCY + "): ");
            quantity = gSCANNER.nextInt();
            
            if (quantity < 0 || quantity > gMAX_OCCUPANCY) {
                System.out.println("ERROR, you need to enter a number between 0 and " + gMAX_OCCUPANCY + " (inclusive).");
            }
        } while (quantity < 0 || quantity > gMAX_OCCUPANCY); 
        
        System.out.print("Do you want to add cattle (Y/N)? ");
        char addRemove = gSCANNER.next().charAt(0);
        
        if (addRemove == 'Y' || addRemove == 'y') {
            if (gBarns[index] + quantity > gMAX_OCCUPANCY) {
                System.out.println("ERROR, Cannot add more cattle than the maximum occupancy (" + gMAX_OCCUPANCY + ").");
            } else {
                gBarns[index] += quantity;
                System.out.println("Added " + quantity + " cattle to barn " + index + ".");
            }
        } else if (addRemove == 'N' || addRemove == 'n') {
            if (gBarns[index] - quantity < 0) {
                System.out.println("ERROR, You can't remove more cattle than the ones in the barn at index " + index + ". Try again\nThe current occupancy is " + quantity);
            } else {
                gBarns[index] -= quantity;
                System.out.println("Removed " + quantity + " cattle from barn " + index + ". The new occupancy is: " + quantity);
            }
        } else {
            System.out.println("ERROR, Invalid input. Use Y to add or N to remove.");
        }
    } else {
        System.out.println("ERROR, Invalid barn index.");
    }
}


    // Method to list the barns and their occupancy
    public static void listBarns() {
        System.out.println("\nBarn Occupancy List:");
        for (int i = 0; i < gBarnNum; i++) {
            System.out.println("Barn " + i + ": " + gBarns[i] + " cattle");
        }
    }

    // Report method
public static void report() {
    System.out.println("\nBarn Occupancy Classification Summary:");
    
    // Displaying barns that are under capacity
    System.out.println("Under capacity: " + getBarnsByOccupancy(0, gMAX_OCCUPANCY - 1));
    
    // Displaying barns that are at exact capacity
    System.out.println("Exact capacity: " + getBarnsByOccupancy(gMAX_OCCUPANCY, gMAX_OCCUPANCY));
    
    // Displaying barns that are over capacity
    System.out.println("Over capacity: " + getBarnsByOccupancy(gMAX_OCCUPANCY + 1, Integer.MAX_VALUE));
}


public static int getBarnsByOccupancy(int lowerLimit, int upperLimit) {
    int count = 0;
    for (int i = 0; i < gBarnNum; i++) {
        if (gBarns[i] >= lowerLimit && gBarns[i] <= upperLimit) {
            count++;
        }
    }
    return count;
}

    // Method to add a new barn
    public static void addNewBarn() {
        if (gBarnNum < gMAX_BARNS) {
            System.out.println("Ok, Adding a new barn.");
            gBarns[gBarnNum] = 0;
            gBarnNum++;
            System.out.println("New barn added. Current number of barns: " + gBarnNum);
        } else {
            System.out.println("ERROR, The database is full, no more barns can be added.");
        }
    }
}






