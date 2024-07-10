package planning;

import representation.BooleanVariable;
import representation.Variable;
import util.Utilities;

import java.util.*;

/**
 * <b>
 * Class representing a planning problem
 * </b>
 *
 * <p>
 * A planning problem is specified by an initial state, a set of actions and a goal. <br>
 * A planning problem is solved by a planner
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class PlanningProblem {

    private static final Random random = new Random();
    /**
     * The initial state
     */
    private final Map<Variable, Object> initialState;
    /**
     * The set of actions
     */
    private final Set<Action> actions;
    /**
     * The goal
     */
    private final Goal goal;

    /**
     * Constructor of the class
     *
     * @param initialState the initial state
     * @param actions      the set of actions
     * @param goal         the goal
     */
    public PlanningProblem(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
    }

    /**
     * Generates a default solvable planning problem
     *
     * @return a default solvable planning problem
     */
    public static PlanningProblem generateDefaultSolvablePlanningProblem() {
        return new PlanningProblem(
                Map.of(
                        new Variable("A", Set.of("a", "b", "c")), "a",
                        new Variable("B", Set.of("a", "b", "c")), "b",
                        new Variable("C", Set.of("a", "b", "c")), "c"
                ),
                Set.of(
                        new BasicAction(
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "a",
                                        new Variable("B", Set.of("a", "b", "c")), "b"
                                ),
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "b",
                                        new Variable("B", Set.of("a", "b", "c")), "c"
                                ),
                                random.nextInt(10)
                        ),
                        new BasicAction(
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "b",
                                        new Variable("B", Set.of("a", "b", "c")), "c"
                                ),
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "c",
                                        new Variable("B", Set.of("a", "b", "c")), "a"
                                ),
                                random.nextInt(10)
                        ),
                        new BasicAction(
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "c",
                                        new Variable("B", Set.of("a", "b", "c")), "a"
                                ),
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "a",
                                        new Variable("B", Set.of("a", "b", "c")), "b"
                                ),
                                random.nextInt(10)
                        ),
                        new BasicAction(
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "a",
                                        new Variable("B", Set.of("a", "b", "c")), "c"
                                ),
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "b",
                                        new Variable("B", Set.of("a", "b", "c")), "a"
                                ),
                                random.nextInt(10)
                        ),
                        new BasicAction(
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "b",
                                        new Variable("B", Set.of("a", "b", "c")), "a"
                                ),
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "c",
                                        new Variable("B", Set.of("a", "b", "c")), "b"
                                ),
                                random.nextInt(10)
                        ),
                        new BasicAction(
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "c",
                                        new Variable("B", Set.of("a", "b", "c")), "b"
                                ),
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "a",
                                        new Variable("B", Set.of("a", "b", "c")), "c"
                                ),
                                random.nextInt(10)
                        ),
                        new BasicAction(
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "a",
                                        new Variable("B", Set.of("a", "b", "c")), "a"
                                ),
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "b",
                                        new Variable("B", Set.of("a", "b", "c")), "b"
                                ),
                                random.nextInt(10)
                        ),
                        new BasicAction(
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "b",
                                        new Variable("B", Set.of("a", "b", "c")), "b"
                                ),
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "c",
                                        new Variable("B", Set.of("a", "b", "c")), "c"
                                ),
                                random.nextInt(10)
                        ),
                        new BasicAction(
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "c",
                                        new Variable("B", Set.of("a", "b", "c")), "c"
                                ),
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "a",
                                        new Variable("B", Set.of("a", "b", "c")), "a"
                                ),
                                random.nextInt(10)
                        ),
                        new BasicAction(
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "a",
                                        new Variable("B", Set.of("a", "b", "c")), "b"
                                ),
                                Map.of(
                                        new Variable("A", Set.of("a", "b", "c")), "a",
                                        new Variable("B", Set.of("a", "b", "c")), "c"
                                ),
                                random.nextInt(10)
                        )
                ),
                new BasicGoal(
                        Map.of(
                                new Variable("A", Set.of("a", "b", "c")), "b",
                                new Variable("B", Set.of("a", "b", "c")), "a",
                                new Variable("C", Set.of("a", "b", "c")), "c"
                        )
                )
        );
    }

    /**
     * Generates a random action applicable to the given state.
     *
     * @param state The state to generate an action for.
     *              The action will be applicable to this state.
     *              The state is the map of Boolean values for each variable.
     * @return A random action applicable to the given state.
     * @throws IllegalArgumentException If the given state is null.
     */
    public static BasicAction generateRandomAction(Map<Variable, Object> state) {
        if (state == null) {
            throw new IllegalArgumentException("The given state is null.");
        }

        Map<Variable, Object> preconditions = new HashMap<>();
        Map<Variable, Object> effects = new HashMap<>();

        for (Variable variable : state.keySet()) {
            if (random.nextBoolean()) {
                preconditions.put(variable, state.get(variable));
            }
            if (random.nextBoolean()) {
                effects.put(variable, Utilities.randomElement(variable.getDomain()));
            }
        }

        return new BasicAction(preconditions, effects, random.nextInt(10));
    }

    /**
     * Generates a random planning problem with the given number of variables at most.
     *
     * @param maxVariables The maximum number of variables in the planning problem.
     *                     The number of variables will be a random number between 1 and this number.
     * @return A random planning problem with the given number of variables at most.
     */
    public static PlanningProblem generateRandomPlanningProblem(int maxVariables) {
        Set<Variable> variables = new HashSet<>();
        for (int i = 0; i < maxVariables; i++) {
            variables.add(new BooleanVariable("X" + i));
        }
        // Initial state
        Map<Variable, Object> initialState = new HashMap<>();
        for (Variable variable : variables) {
            if (random.nextBoolean()) {
                initialState.put(variable, random.nextBoolean());
            }
        }
        //Number of actions
        int numberOfActions = 500;
        Set<Action> actions = new HashSet<>();
        Map<Variable, Object> goalState = new HashMap<>(initialState);
        for (int i = 0; i < numberOfActions; i++) {
            Action action = generateRandomAction(goalState);
            actions.add(action);

            if (Math.random() < 0.33) {
                goalState = action.successor(goalState);
            }
        }





        return new PlanningProblem(initialState, actions, new BasicGoal(goalState));
    }

    /**
     * Returns the initial state
     *
     * @return the initial state
     */
    public Map<Variable, Object> getInitialState() {
        return this.initialState;
    }

    /**
     * Returns the set of actions
     *
     * @return the set of actions
     */
    public Set<Action> getActions() {
        return this.actions;
    }

    /**
     * Returns the goal
     *
     * @return the goal
     */
    public Goal getGoal() {
        return this.goal;
    }

    /**
     * Returns a string representation of the planning problem
     *
     * @return a string representation of the planning problem
     */
    @Override
    public String toString() {
        return "PlanningProblem{" + "initialState=" + initialState + ", actions=" + actions + ", goal=" + goal + '}';
    }

    /**
     * Returns a boolean indicating if the planning problem is valid
     *
     * @return true if the planning problem is valid
     */
    public boolean isValid() {
        return this.initialState != null && this.actions != null && this.goal != null;
    }

    /**
     * Returns a boolean indicating if the planning problem is solved
     *
     * @return true if the planning problem is solved
     */
    public boolean isSolved() {
        return this.goal.isSatisfiedBy(this.initialState);
    }

    /**
     * Returns a boolean indicating if the planning problem is solvable
     *
     * @return true if the planning problem is solvable
     * @throws Exception if the planning problem is not valid
     */
    public boolean isSolvable() throws Exception {
        if (!this.isValid()) {
            throw new Exception("Planning problem is not valid");
        }
        return this.isSolved() || this.isSolvable(this.initialState);
    }

    /**
     * Returns a boolean indicating if the planning problem is solvable
     *
     * @param state the state to test
     * @return true if the planning problem is solvable
     */
    private boolean isSolvable(Map<Variable, Object> state) {
        for (Action action : this.actions) {
            if (action.isApplicable(state)) {
                Map<Variable, Object> newState = action.successor(state);
                if (this.goal.isSatisfiedBy(newState)) {
                    return true;
                }
                if (this.isSolvable(newState)) {
                    return true;
                }
            }
        }
        return false;
    }


}
