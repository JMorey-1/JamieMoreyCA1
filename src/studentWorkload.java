import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 *
 * @author jamie
 */
public class studentWorkload {

    public static void main(String[] args) {
           Scanner scanner = new Scanner(System.in);
         
           
               System.out.println("Please choose an option to continue:");
               System.out.println("Please press 1 to output data to file");
               System.out.println("Please press 2 to add data to file");
               System.out.println("Please press 3 to exit program");
               
               int option;
               option = scanner.nextInt();
               
               switch (option){
                       case 1: 
                            System.out.println("You choose option 1");
                            System.out.println("Do you want to return to the main menu? (y/n)");
                             String answer = scanner.next();
                             if (answer.equals("y"))
                            break;
                       
                       case 2:
                            System.out.println("You choose option 2");
                            System.out.println("Please enter the data you would like to add to the Student file?");
                            System.out.println("");
                            break;
                       
                       case 3:
                            System.out.println("Exiting the program");
                            break;
                            
                       default:
                            System.out.println("Invalid choice please enter 1, 2 or 3.");
                            break;
                              
                          }
                        
    
   }      
 }