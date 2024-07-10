package test;

import csptests.*;

/**
 * <b>
 *     Class representing a test of the csp package
 * </b>
 *
 * <p>
 *     This class is used to test the csp package. It depends on the
 *     success of the tests of the representation package.
 * </p>
 */
public class TestCSP {

    /**
     * <b>
     * Main method of the test
     * </b>
     *
     * @param args the arguments of the main method
     */
    public static void main(String[] args) {
        boolean test = true;
        System.out.println("Test of the csp package");

        System.out.println("Test of the Solver class");

        System.out.println("Test of the AbstractSolver class");
        test = test && AbstractSolverTests.testIsConsistent();

        System.out.println("Test of the BacktrackSolver class");
        test = test && BacktrackSolverTests.testSolve();

        System.out.println("Test of the ArcConsistency class");
        test = test && ArcConsistencyTests.testEnforceNodeConsistency();
        test = test && ArcConsistencyTests.testRevise();
        test = test && ArcConsistencyTests.testAC1();

        System.out.println("Test of the MACSolver class");
        test = test && MACSolverTests.testSolve();

        System.out.println("Test of NbConstraintsHeuristic class");
        test = test && NbConstraintsVariableHeuristicTests.testBest();

        System.out.println("Test of DomainSizeHeuristic class");
        test = test && DomainSizeVariableHeuristicTests.testBest();

        System.out.println("Test of the RandomValueHeuristic class");
        test = test && RandomValueHeuristicTests.testOrdering();

        System.out.println("Test of the HeuristicMACSolver class");
        //test = test && HeuristicMACSolverTests.testSolve();

        System.out.println(test ? " All tests OK " : " At least one test KO ");

    }
}
