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
            
            while (true) {
            int option = menu();

            switch (option) {
                case 1:
                    readLines();
                    System.out.println("Student data has been output to Status file.");
                    break;

                case 2:
                    System.out.println("Please enter the data you would like to add to the Student file?");
                    break;

                case 3:
                    System.out.println("Exiting the program");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice please enter 1, 2, or 3.");
                    break;
                   }
            
                   System.out.println("Do you want to return to the main menu? (y/n)");
                   String answer = scanner.next();
            
                   if (!answer.equals("y")) {
                   System.out.println("Exiting the program");
                   System.exit(0);
                   }
     }
   }
    //MENU METHOD
    public static int menu() {

        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("Please choose an option to continue:");
        System.out.println("------------------------------------");
        System.out.println("Please press 1 to output data to file");
        System.out.println("Please press 2 to add data to file");
        System.out.println("Please press 3 to exit program");

        selection = input.nextInt();
        return selection;    
    
    }
    //Method for reading lines from student text file
    //Reads 3 lines in and continues doing so till reaching the end of the file
    public static void readLines() {
       try (Scanner myScan = new Scanner(new FileReader("Students.txt"))) { 
        int linesToRead = 3;
        String[] lines = new String[linesToRead];

        while (myScan.hasNextLine()) {
            for (int i = 0; i < linesToRead && myScan.hasNextLine(); i++) {
                lines[i] = myScan.nextLine();
                System.out.println(lines[i]);
            }

            processLines(lines);
        }
    } catch (Exception e) {
        System.out.println("Error reading lines from the file.");
    }
}
      
     //Method for Processing my lines in the student text file
     public static void processLines(String[] lines){
     
          
        //ADD VALIDATION PER LINE 
             String[] words2 = lines[0].split(" ");
             String word1 = words2[0];
             String word2 = words2[1];
             String line2 = lines[1];
             String line3 = lines[2];
             System.out.println(word1);
             System.out.println(word2);
             System.out.println(line2);
             System.out.println(line3);
             
     }
          
      } 
    


