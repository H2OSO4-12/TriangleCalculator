import com.example.triangle.SideException;
import com.example.triangle.TriangleCalculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TrianglesTests {
    @Test
    public void testGetArea() throws SideException {
        TriangleCalculator t = new TriangleCalculator(1, 1, 1);
        double actual = t.getArea();
        double expected = 0.43;
        Assertions.assertEquals(expected, actual);
    }

    static Stream <Arguments> provider() {

        return Stream.of(
            Arguments.of(1,1,1,3), Arguments.of(2,3,4,9), Arguments.of(5,5,5,15));
    }
    @ParameterizedTest
    @MethodSource("provider")
    public void testPerimeter(double a, double b, double c, double expectedP) {
        TriangleCalculator t = new TriangleCalculator(a, b, c);
        double actual = t.getPerimetr();
        Assertions.assertEquals(actual, expectedP);
    }

    @ParameterizedTest
    @CsvSource({"1,1,1,3","5,5,5,15"})
    public void testPerimeter2(double a, double b, double c, double expectedP) {
        TriangleCalculator t = new TriangleCalculator(a, b, c);
        double actual = t.getPerimetr();
        Assertions.assertEquals(actual, expectedP);
    }
    @Disabled
    @Test
    @DisplayName("Type Triangle")
    public void testType() {
        TriangleCalculator t = new TriangleCalculator(1,1,1);
        String actual = t.calculate();
        String expected = "равносторонний треугольник";
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void testException() {
        TriangleCalculator t = new TriangleCalculator(-1, 1, 1);
        assertThrows(SideException.class, t::getArea);
    }
}
