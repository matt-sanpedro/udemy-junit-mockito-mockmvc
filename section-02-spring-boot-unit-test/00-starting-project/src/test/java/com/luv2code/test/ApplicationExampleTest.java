package com.luv2code.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

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

    @Autowired
    ApplicationContext context;

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

    @DisplayName("Is grade greater")
    @Test
    public void isGradeGreaterStudentGrades() {
        assertTrue(studentGrades.isGradeGreater(100.0, 85.0), 
            "The first grade should be greater than the second grade");
    }

    @DisplayName("Is grade greater - false case")
    @Test
    public void isGradeGreaterStudentGradesAssertFalse() {
        assertFalse(studentGrades.isGradeGreater(75.0, 85.0), 
            "The first grade should be less than the second grade");
    }

    @DisplayName("Check Null for student grades")
    @Test
    public void checkNullForStudentGrades() {
        assertNotNull(studentGrades.checkNull(student.getStudentGrades()
            .getMathGradeResults()), "The math grades should not be null");
    }

    @DisplayName("Create student without grade init")
    @Test
    public void createStudentWithoutGradeInit() {
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);
        studentTwo.setFirstname("Lamar");
        studentTwo.setLastname("Jackson");
        studentTwo.setEmailAddress("hailmary@run.com");
        assertNotNull(studentTwo.getFirstname(), "First name should not be null");
        assertNotNull(studentTwo.getLastname(), "First name should not be null");
        assertNotNull(studentTwo.getEmailAddress(), "Email should not be null");
        assertNull(studentGrades.checkNull(studentTwo.getStudentGrades()), 
            "The student grades should be null since it was not initialized");
    }

    @DisplayName("Verify students are prototypes")
    @Test
    public void verifyStudentsArePrototypes() {
        CollegeStudent studentTwo = context.getBean("collegeStudent", CollegeStudent.class);
        CollegeStudent studentThree = context.getBean("collegeStudent", CollegeStudent.class);
        studentTwo.setFirstname("Tebow");
        studentThree.setFirstname("Tom");
        assertNotSame(studentTwo, studentThree, "The two students should not be the same instance");
        assertNotSame(student, studentThree, "The two students should not be the same instance");
        assertNotSame(studentTwo.getFirstname(), studentThree.getFirstname(), 
            "The first names of the two students should not be the same");
    }

    @DisplayName("Find Grade Point Average")
    @Test
    public void findGradePointAverage() {
        assertAll("Testing all assertEquals",
            () -> assertEquals(91.9, studentGrades.findGradePointAverage(student.getStudentGrades().getMathGradeResults()), 
                "The grade point average should be 91.9"),
            () -> assertEquals(367.6, studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults()),
                "The sum of the math grades should be 367.6"),
            () -> assertNotEquals(0, studentGrades.findGradePointAverage(student.getStudentGrades().getMathGradeResults()), 
                "The grade point average should not be 0")
        );
    }

}
