package test;

import representationtests.VariableTests;
import representationtests.BooleanVariableTests;
import representationtests.DifferenceConstraintTests;
import representationtests.ImplicationTests;
import representationtests.UnaryConstraintTests;
/**
 * <b>
 *     Class representing a test of the representation package
 * </b>
 *
 */
public class TestRepresentation {

    /**
     * <b>
     *     Main method of the test
     * </b>
     *
     * @param args the arguments of the main method
     */
    public static void main(String[] args) {
        boolean test = true;
        System.out.println("Test of the representation package");

        System.out.println("Test of the Variable class");
        test = test && VariableTests.testEquals();
        test = test && VariableTests.testHashCode();

        System.out.println("Test of the BooleanVariable class");
        test = test && BooleanVariableTests.testConstructor();
        test = test && BooleanVariableTests.testEquals();
        test = test && BooleanVariableTests.testHashCode();


        System.out.println("Test of the Constraint class");

        System.out.println("Test of the UnaryConstraint class");
        test = test && UnaryConstraintTests.testGetScope();
        test = test && UnaryConstraintTests.testIsSatisfiedBy();

        System.out.println("Test of the DifferenceConstraint class");
        test = test && DifferenceConstraintTests.testGetScope();
        test = test && DifferenceConstraintTests.testIsSatisfiedBy();

        System.out.println("Test of the Implication class");
        test = test && ImplicationTests.testGetScope();
        test = test && ImplicationTests.testIsSatisfiedBy();

        System.out.println(test ? " All tests OK " : " At least one test KO ");




    }
}
