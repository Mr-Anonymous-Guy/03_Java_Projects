package report;

import model.Student;
import service.GradeCalculator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Generates and saves the final student report card.
 */
public class ReportGenerator {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public void displayAndSaveReport(Student student, GradeCalculator calculator, double maxMarksPerSubject) {
        double totalMarks = calculator.calculateTotalMarks(student);
        double average = calculator.calculateAverage(student);
        double percentage = calculator.calculatePercentage(student, maxMarksPerSubject);
        String grade = calculator.calculateGrade(percentage);
        boolean isPass = calculator.isPass(grade);
        
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("=========================================\n");
        reportBuilder.append("           STUDENT REPORT CARD           \n");
        reportBuilder.append("=========================================\n");
        reportBuilder.append("Name: ").append(student.getName()).append("\n");
        reportBuilder.append("Roll Number: ").append(student.getRollNumber()).append("\n");
        reportBuilder.append("-----------------------------------------\n");
        reportBuilder.append(String.format("%-20s %10s\n", "Subject", "Marks"));
        reportBuilder.append("-----------------------------------------\n");
        
        for (Map.Entry<String, Double> entry : student.getSubjectMarks().entrySet()) {
            reportBuilder.append(String.format("%-20s %10.2f\n", entry.getKey(), entry.getValue()));
        }
        
        reportBuilder.append("-----------------------------------------\n");
        reportBuilder.append(String.format("%-20s %10.2f\n", "Total Marks", totalMarks));
        reportBuilder.append(String.format("%-20s %10s\n", "Average", df.format(average)));
        reportBuilder.append(String.format("%-20s %10s%%\n", "Percentage", df.format(percentage)));
        reportBuilder.append(String.format("%-20s %10s\n", "Grade", grade));
        reportBuilder.append(String.format("%-20s %10s\n", "Status", isPass ? "PASS" : "FAIL"));
        reportBuilder.append("=========================================\n");
        
        String report = reportBuilder.toString();
        System.out.println(report);
        
        saveReportToFile(student, report);
    }

    private void saveReportToFile(Student student, String report) {
        try {
            File dir = new File("reports");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = dtf.format(LocalDateTime.now());
            String fileName = "reports/Report_" + student.getRollNumber() + "_" + timestamp + ".txt";
            
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(report);
            }
            System.out.println("Report successfully saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving report: " + e.getMessage());
        }
    }
}
