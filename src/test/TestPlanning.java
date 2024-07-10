package test;

import planning.*;
import planningtests.*;
import representation.Variable;

import java.util.Map;
import java.util.Set;

/**
 * <b>
 *     Class representing a test of the planning package
 * </b>
 *
 * <p>
 *     This class is used to test the planning package. It depends on the
 *     success of the tests of the representation package.
 * </p>
 */
public class TestPlanning {

    /**
     * <b>
     * Main method of the test
     * </b>
     *
     * @param args the arguments of the main method
     */
    public static void main(String[] args) {
        boolean test = true;
        System.out.println("Test of the planning package");

        System.out.println("Test of BasicAction class");
        test = test && BasicActionTests.testIsApplicable();
        test = test && BasicActionTests.testSuccessor();
        test = test && BasicActionTests.testGetCost();

        System.out.println("Test of BasicGoal class");
        test = test && BasicGoalTests.testIsSatisfiedBy();

        System.out.println("Test of the Planner class");

        System.out.println("Test of the BFSPlanner class");
        test = test && BFSPlannerTests.testPlan();

        System.out.println("Test of the DFSPlanner class");
        test = test && DFSPlannerTests.testPlan();

        System.out.println("Test of the DijkstraPlanner class");
        test = test && DijkstraPlannerTests.testPlan();

        System.out.println("Test of the AStarPlanner class");
        test = test && AStarPlannerTests.testPlan();


        System.out.println("Test of the BeamPlanner class");
        test = test && new PlannerTests(
                (Map<Variable, Object> state, Set<Action> actions, Goal goal) ->
                        new BeamPlanner(state, actions, goal, new Heuristic() {
                            @Override
                            public float estimate(Map<Variable, Object> state) {
                                return 0;
                            }
                        }, 1000)
        ).testPlan();



        System.out.println(test ? " All tests OK " : " At least one test KO ");

    }
}
