import model.Student;
import service.GradeCalculator;
import utils.InputValidator;
import report.ReportGenerator;

import java.util.Scanner;

/**
 * Main execution class for the Grade Calculator project.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("        Welcome to Grade Calculator      ");
        System.out.println("=========================================");
        
        Scanner scanner = new Scanner(System.in);
        InputValidator validator = new InputValidator(scanner);
        
        String name = validator.getValidString("Enter Student Name: ");
        int rollNumber = validator.getValidInt("Enter Roll Number: ");
        
        Student student = new Student(name, rollNumber);
        
        int numSubjects = validator.getValidInt("Enter number of subjects: ");
        double maxMarksPerSubject = 100.0; // Assuming standard 100 max marks
        
        for (int i = 1; i <= numSubjects; i++) {
            String subjectName = validator.getValidString("Enter name for Subject " + i + ": ");
            double marks = validator.getValidMark("Enter marks for " + subjectName + " (0-" + maxMarksPerSubject + "): ", maxMarksPerSubject);
            student.addSubjectMark(subjectName, marks);
        }
        
        GradeCalculator calculator = new GradeCalculator();
        ReportGenerator generator = new ReportGenerator();
        
        System.out.println("\nCalculating results...\n");
        generator.displayAndSaveReport(student, calculator, maxMarksPerSubject);
        
        scanner.close();
        System.out.println("Thank you for using the Grade Calculator!");
    }
}
