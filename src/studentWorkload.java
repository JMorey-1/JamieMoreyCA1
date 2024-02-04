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
                    readStudentData();                   
                    System.out.println("Valid data from the file Student.txt has been output to the Status.txt file.");
                    break;
        
                case 2:
                    addStudentData();
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
                   System.out.println("Exiting the program, goodbye!");
                   System.exit(0);
                  }
                 }
               }
         
         
         
 //METHODS    
         
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
     
        
   //READING IN THE STUDENT    
    //Method for reading lines from student text file
    //Reads 3 lines in and continues doing so till reaching the end of the file
        public static void readStudentData() {
        try (Scanner myScan = new Scanner(new FileReader("Students.txt"))) {
            while (myScan.hasNextLine()) {
                String[] studentData = new String[3];

                // Read 3 lines
                for (int i = 0; i < 3 && myScan.hasNextLine(); i++) {
                    studentData[i] = myScan.nextLine();
                }

              // Call my validation method to check the data
            if (isValidData(studentData)) {
              // If valid, call my writeToFile method to output the lines to the "Status.txt" file
                writeToFile(studentData, "Status.txt");
            }
                
                   }
                    } catch (Exception e) {
                      System.out.println("Error reading lines from the file.");
                    }
                   }

       
  
//WRITE TO FILE METHOD
private static void writeToFile(String[] data, String filename) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
        // Extract the last name from the first line
        String[] nameParts = data[0].split("\\s+");
        String lastName = (nameParts.length > 1) ? nameParts[1] : "";

        // Output the formatted data to the file
        writer.write(data[2] + " - " + lastName);  // Line 1: Student Number - Last Name
        writer.newLine();

        // Convert the class number to workload description
        String workloadDescription = changeToWorkload(data[1]);

        writer.write(workloadDescription);  // Line 2: Workload
        writer.newLine();

        writer.newLine();  // Add an extra newline for separation between entries
    } catch (Exception e) {
        System.out.println("Error writing to the file.");
    }
}
 
   //ADD STUDENT DATA MANUALLY
    public static void addStudentData() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the student data (Name Lastname ClassNumber StudentNumber):");
        String inputLine = scanner.nextLine();
         String[] studentData = inputLine.split(" ");

        // Validate and process the input
        if (studentData.length == 4) {
            // Concatenate the first two components (Name and Lastname) to form the first line
            String firstLine = studentData[0] + " " + studentData[1];

            // Validate and process the lines
            if (isValidData(new String[]{firstLine, studentData[2], studentData[3]})) {
                writeToFile(new String[]{firstLine, studentData[2], studentData[3]}, "Status.txt");
                System.out.println("Student data has been added to Status file.");
            } else {
                System.out.println("Invalid data. Please enter data in the correct format.");
            }
        } else {
            System.out.println("Invalid data. Please enter data in the correct format.");
        }
    }


//Method to change number of classes into a workload    
private static String changeToWorkload(String classNumber) {
    try {
        int numberOfClasses = Integer.parseInt(classNumber);

        switch (numberOfClasses) {
            case 1:
                return "Very Light";
            case 2:
                return "Light";
            case 3:
            case 4:
            case 5:
                return "Part Time";
            default:
                return "Full Time";
        }
    } catch (NumberFormatException e) {
        return "Invalid";
    }
}
    

//VALIDATION METHODS 
    
// Validation method based on specified criteria
 private static boolean isValidData(String[] studentData) {
    String[] nameData = studentData[0].split("\\s+");

    return isNameDataValid(nameData) &&
           isClassNumberValid(studentData[1]) &&
           isStudentNumberValid(studentData[2]);
}

//Checking first name is only letters, and that last name can be either letters or numbers.
private static boolean isNameDataValid(String[] nameData) {
    return nameData.length == 2 &&
           nameData[0].matches("[A-Za-z]+") &&
           nameData[1].matches("[A-Za-z0-9]+");
}

//Checking that student class number is an integer between 1 and 8, inclusive.
private static boolean isClassNumberValid(String classNumber) {
    try {
        int number = Integer.parseInt(classNumber);
        return number >= 1 && number <= 8;
    } catch (NumberFormatException e) {
        return false;
    }
}

private static boolean isStudentNumberValid(String studentNumber) {
    return studentNumber.length() >= 6 &&
           isYearValid(studentNumber) &&
           isFifthCharValid(studentNumber) &&
           areThirdAndFourthCharactersLetters(studentNumber) &&
           areRemainingDigitsValid(studentNumber);
    
}

private static boolean isYearValid(String studentNumber) {
    return Character.isDigit(studentNumber.charAt(0)) &&
           Character.isDigit(studentNumber.charAt(1)) &&
           Integer.parseInt(studentNumber.substring(0, 2)) >= 20;
    
}

//Checking that the 3rd and 4th characters of the Student Number are letters
private static boolean areThirdAndFourthCharactersLetters(String studentNumber) {
    return Character.isLetter(studentNumber.charAt(2)) && Character.isLetter(studentNumber.charAt(3));
}


//Checking that the 5th character of the student number is a letter or integer
private static boolean isFifthCharValid(String studentNumber) {
    return Character.isLetterOrDigit(studentNumber.charAt(4));
}

//Checking that the number that follows the last letter is between 1 and 200 inclusive.
//Since the 5th character can be either a letter or number we check that first to see where to begin reading our number.
private static boolean areRemainingDigitsValid(String studentNumber) {
    char fifthCharacter = studentNumber.charAt(4);

    // Check if the 5th character is a letter
    if (Character.isLetter(fifthCharacter)) {
        int startIndex = 5;

        // Check if the remaining characters represent a number between 1 and 200 using regex
        String remainingDigits = studentNumber.substring(startIndex);
        return remainingDigits.matches("[0-9]{1,3}") && Integer.parseInt(remainingDigits) <= 200;
    }

    // If the 5th character is not a letter, it should be a digit
    if (Character.isDigit(fifthCharacter)) {
        int startIndex = 6;

        // Check if the remaining characters represent a number between 1 and 200 using regex
        String remainingDigits = studentNumber.substring(startIndex);
        return remainingDigits.matches("[0-9]{1,3}") && Integer.parseInt(remainingDigits) <= 200;
    }

    // If the 5th character is neither a letter nor a digit, consider it invalid
    return false;
}

}

