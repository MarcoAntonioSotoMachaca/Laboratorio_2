import grades.GradeManager;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        GradeManager gradeManager = new GradeManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add grade");
            System.out.println("2. View average grade");
            System.out.println("3. View number of passing grades");
            System.out.println("4. Exit");
            System.out.println("5. Delete grade");
            System.out.print("Choose an option: ");

            String choiceInput = scanner.nextLine().trim();
            Integer choice;

            try {
                choice = Integer.valueOf(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter a grade: ");
                    String gradeInput = scanner.nextLine().trim();
                    try {
                        Double grade = Double.valueOf(gradeInput);
                        gradeManager.addGrade(grade);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid grade. Please enter a numeric value.");
                    }
                    break;

                case 2:
                    System.out.println("Average grade: " + gradeManager.calculateAverage());
                    break;

                case 3:
                    System.out.println("Number of passing grades: " + gradeManager.countPassingGrades());
                    break;

                case 4:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                    break;

                case 5:
                    ArrayList<Double> currentGrades = gradeManager.getGrades();

                    if (currentGrades.isEmpty()) {
                        System.out.println("There are no grades to delete.");
                        break;
                    }

                    System.out.println("Current grades:");
                    for (Integer i = 0; i < currentGrades.size(); i++) {
                        System.out.println((i + 1) + ". " + currentGrades.get(i));
                    }

                    System.out.println("1. Delete by value");
                    System.out.println("2. Delete by position");
                    System.out.print("Choose delete method: ");
                    String deleteChoiceInput = scanner.nextLine().trim();
                    Integer deleteChoice;

                    try {
                        deleteChoice = Integer.valueOf(deleteChoiceInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid option. Please try again.");
                        break;
                    }

                    if (deleteChoice == 1) {
                        System.out.print("Enter the grade value to delete: ");
                        String valueInput = scanner.nextLine().trim();
                        try {
                            Double value = Double.valueOf(valueInput);
                            if (gradeManager.removeGradeByValue(value)) {
                                System.out.println("Grade deleted successfully.");
                            } else {
                                System.out.println("Grade not found.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid grade. Please enter a numeric value.");
                        }
                    } else if (deleteChoice == 2) {
                        System.out.print("Enter the position to delete: ");
                        String positionInput = scanner.nextLine().trim();
                        try {
                            Integer position = Integer.valueOf(positionInput);
                            if (gradeManager.removeGradeByPosition(position)) {
                                System.out.println("Grade deleted successfully.");
                            } else {
                                System.out.println("Invalid position.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid position. Please enter a whole number.");
                        }
                    } else {
                        System.out.println("Invalid option. Please try again.");
                    }
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
