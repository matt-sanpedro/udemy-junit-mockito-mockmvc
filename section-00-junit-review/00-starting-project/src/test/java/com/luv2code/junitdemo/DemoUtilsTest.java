import com.luv2code.junitdemo.DemoUtils;

import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.boot.test.system.CapturedOutput;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.assertj.core.api.Assertions.assertThat;

import java.beans.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
@ExtendWith(OutputCaptureExtension.class)
public class DemoUtilsTest {
    // define utility class as a field
    DemoUtils demoUtils;

    private static final Logger log = LoggerFactory.getLogger(DemoUtilsTest.class);

    @BeforeEach
    void setupBeforeEach() {
        demoUtils = new DemoUtils();
        System.out.println("@BeforeEach: executes before each test method");
    }

    @Test
    void testInfoLog(CapturedOutput output) {
        log.info("This is an info log message");
        assertThat(output).contains("This is an info log message");
    }
    
    @DisplayName("Equals and Not Equals Test")
    @Test
    void testEqualsAndNotEquals() {
        System.out.println("Running test: testEqualsAndNotEquals");
        log.info("This is an info message");
        // Check if the two integers equal
        assertEquals(6, demoUtils.add(2, 4), "2+4 must be 6");
        assertNotEquals(6, demoUtils.add(1, 9), "1+9 must not be 6");
        
    }
    
    @DisplayName("Null and Not Null Test")
    @Test
    void testNullAndNotNull() {
        System.out.println("Running test: testNullAndNotNull");
        
        String str1 = null;
        String str2 = "Hello";
        
        assertNull(demoUtils.checkNull(str1), "String should be null");
        assertNotNull(demoUtils.checkNull(str2), "String should not be null");
    }

    /*
    @AfterEach
    void tearDownAfterEach() {
        System.out.println("@AfterEach: executes after each test method");
    }
    
    @BeforeAll
    static void setupBeforeEachClass() {
        System.out.println("@BeforeAll: executes only once before all test methods");
    }
    
    @AfterAll
    static void setupAfterEachClass() {
        System.out.println("@AfterAll: executes only once after all test methods");
    }
    */
    
}
