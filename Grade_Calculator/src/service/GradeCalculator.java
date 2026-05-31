package service;

import model.Student;

/**
 * Service class responsible for calculating grades, percentages, and averages.
 */
public class GradeCalculator {
    public double calculateTotalMarks(Student student) {
        return student.getSubjectMarks().values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public double calculateAverage(Student student) {
        int numberOfSubjects = student.getSubjectMarks().size();
        if (numberOfSubjects == 0) return 0;
        return calculateTotalMarks(student) / numberOfSubjects;
    }

    public double calculatePercentage(Student student, double maxMarksPerSubject) {
        int numberOfSubjects = student.getSubjectMarks().size();
        if (numberOfSubjects == 0) return 0;
        double totalMaxMarks = numberOfSubjects * maxMarksPerSubject;
        return (calculateTotalMarks(student) / totalMaxMarks) * 100;
    }

    public String calculateGrade(double percentage) {
        if (percentage >= 90) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 70) return "B";
        if (percentage >= 60) return "C";
        if (percentage >= 50) return "D";
        return "F";
    }

    public boolean isPass(String grade) {
        return !grade.equals("F");
    }
}
