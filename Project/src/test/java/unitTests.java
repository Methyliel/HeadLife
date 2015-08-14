
public class unitTests {
    public static void main(String argc[]) {
        GeneratorJUnitTest generatorJUnitTest = new GeneratorJUnitTest();

        //before
        generatorJUnitTest.setUpExamples();

        //test
        generatorJUnitTest.testGenerator();

        //after
        generatorJUnitTest.tearDownExamples();
    }
}
