package com.luv2code.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ApplicationExampleTest {

    private static int count = 0;

    @Value("${info.app.name}")
    private String appInfo;

    @Value("${info.app.description}")
    private String appDescription;
    
    @Value("${info.app.version}")
    private String appVersion;

    @Value("${info.school.name}")
    private String schoolName;

    // inject spring beans
    @Autowired
    CollegeStudent student;

    @Autowired
    StudentGrades studentGrades;

    @BeforeEach
    public void beforeEach() {
        count++;
        System.out.println("Testing: " + appInfo + "; Desciption: " + appDescription + 
            "; Version: " + appVersion + "\nTest Number: " + count);
        student.setFirstname("Kendrick");
        student.setLastname("Lamar");
        student.setEmailAddress("luv.whenu@count.me.out");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0, 85.0, 90.0, 92.6)));
        student.setStudentGrades(studentGrades);
    }
    
    @Test
    void basicTest() {
        
    }

    @DisplayName("Add grade results for student grades")
    @Test
    public void addGradeResultsForStudentGrades() {
        assertEquals(367.6, studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults()), 
            "The sum of the math grades should be 367.6");
    }

    @DisplayName("Add grade results for student grades not equal")
    @Test
    public void addGradeResultsForStudentGradesNotEquals() {
        assertNotEquals(0, studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults()), 
            "The sum of the math grades should not be 0");
    }
}
