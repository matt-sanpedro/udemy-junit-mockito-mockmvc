import com.luv2code.junitdemo.DemoUtils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.beans.Transient;

public class DemoUtilsTest {
    // define utility class as a field
    DemoUtils demoUtils;

    @BeforeEach
    void setupBeforeEach() {
        demoUtils = new DemoUtils();
        System.out.println("@BeforeEach: executes before each test method");
    }

    @AfterEach
    void tearDownAfterEach() {
        System.out.println("@AfterEach: executes after each test method");
    }

    @Test
    void testEqualsAndNotEquals() {
        System.out.println("Running test: testEqualsAndNotEquals");

        // Check if the two integers equal
        assertEquals(6, demoUtils.add(2, 4), "2+4 must be 6");
        assertNotEquals(6, demoUtils.add(1, 9), "1+9 must not be 6");

    }

    @Test
    void testNullAndNotNull() {
        System.out.println("Running test: testNullAndNotNull");

        String str1 = null;
        String str2 = "Hello";

        assertNull(demoUtils.checkNull(str1), "String should be null");
        assertNotNull(demoUtils.checkNull(str2), "String should not be null");
    }

}
