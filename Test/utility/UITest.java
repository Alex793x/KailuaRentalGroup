package utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class UITest {
    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;


    @Test
    @DisplayName("Test only valid integer type input")
    void TestReadInteger() {
        testIn = new ByteArrayInputStream("1\n3\n5\n0\n".getBytes());
        System.setIn(testIn);
        UI ui = new UI();

        assertEquals(1, ui.readInteger());
        assertEquals(3, ui.readInteger());
        assertEquals(5, ui.readInteger());
        assertEquals(0, ui.readInteger());
    }

    @Test
    @DisplayName("Test only valid letters in line input")
    void TestReadLine() {
        testIn = new ByteArrayInputStream("Hello World\nJames Is A Mover\nChilling\nZero\n".getBytes());
        System.setIn(testIn);
        UI ui = new UI();

        assertEquals("Hello World", ui.readLine());
        assertEquals("James Is A Mover", ui.readLine());
        assertEquals("Chilling", ui.readLine());
        assertEquals("Zero", ui.readLine());
    }

    @Test
    @DisplayName("Test only valid letters in word input")
    void TestReadNext() {
        testIn = new ByteArrayInputStream("Hello     \nJames     \nChilling   \nZero       \n".getBytes());
        System.setIn(testIn);
        UI ui = new UI();

        assertEquals("Hello", ui.readNext());
        assertEquals("James", ui.readNext());
        assertEquals("Chilling", ui.readNext());
        assertEquals("Zero", ui.readNext());
    }

    @Test
    @DisplayName("Test only valid boolean type input")
    void TestReadBoolean() {
        testIn = new ByteArrayInputStream("true\nfalse\n".getBytes());
        System.setIn(testIn);
        UI ui = new UI();

        assertTrue(ui.readBoolean());
        assertFalse(ui.readBoolean());
    }

    @Test
    @DisplayName("Test only valid double type input")
    void TestReadDouble() {
        testIn = new ByteArrayInputStream("1\n3\n5\n0\n".getBytes());
        System.setIn(testIn);
        UI ui = new UI();
    }

    @Test
    @DisplayName("Test only valid Date input")
    void TestReadDate() {
        testIn = new ByteArrayInputStream("1\n3\n5\n0\n".getBytes());
        System.setIn(testIn);
        UI ui = new UI();
    }

    @Test
    @DisplayName("Test returned value of InvalidNumberInput")
    void TestInvalidNumberInput() {

    }
}