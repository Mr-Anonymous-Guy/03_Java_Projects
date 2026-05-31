package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a student with a name, roll number, and subject marks.
 */
public class Student {
    private String name;
    private int rollNumber;
    private Map<String, Double> subjectMarks;
    
    public Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.subjectMarks = new HashMap<>();
    }

    public void addSubjectMark(String subject, double mark) {
        this.subjectMarks.put(subject, mark);
    }

    public String getName() { return name; }
    public int getRollNumber() { return rollNumber; }
    public Map<String, Double> getSubjectMarks() { return subjectMarks; }
}
