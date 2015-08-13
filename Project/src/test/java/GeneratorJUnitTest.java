import Logic.Generator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GeneratorJUnitTest extends Assert{
    private static int exampleWidth;
    private static int exampleLenght;
    private static List<String> exampleLandscapeTypes = new ArrayList<String>();
    private static List<String> exampleObjectTypes = new ArrayList<String>();
    private static int exampleObjectNumber;
    private static List<String> exampleEnemyTypes = new ArrayList<String>();
    private static int exampleEnemyNumber;
    private static Point exampleStartPoint;

    @Before
    public static void setUpExamples() {
        exampleWidth = 4;
        exampleLenght = 5;
        exampleLandscapeTypes.add("Ground");
        exampleLandscapeTypes.add("Rock");
        exampleLandscapeTypes.add("Slime");
        exampleObjectTypes.add("Potion");
        exampleObjectTypes.add("Horn");
        exampleObjectNumber = 3;
        exampleEnemyTypes.add("AradiaBotRemains");
        exampleEnemyTypes.add("EctobiologicalBird");
        exampleEnemyTypes.add("EctobiologicalWorm");
        exampleEnemyNumber = 2;
        exampleStartPoint = new Point(1, 1);
    }

    @Test
    public void testGenerator() {
        Exception actual = new Generator().generateLevel(exampleWidth, exampleLenght, exampleLandscapeTypes,
                exampleObjectTypes, exampleObjectNumber, exampleEnemyTypes, exampleEnemyNumber, exampleStartPoint);
        Exception expected = null;
        assertEquals(expected, actual);
    }

    @After
    public static void tearDownExamples() {
        exampleWidth = 0;
        exampleLenght = 0;
        exampleLandscapeTypes.clear();
        exampleObjectTypes.clear();
        exampleObjectNumber = 0;
        exampleEnemyTypes.clear();
        exampleEnemyNumber = 0;
        exampleStartPoint = null;
    }
}
