package planning;

import representation.Variable;

import java.util.Map;
import java.util.Set;

/**
 * <b>
 * Abstract class representing a solver for planning problems
 * </b>
 *
 * <p>
 * A solver for planning problems is specified by an initial state, a set of actions and a goal. <br>
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public abstract class AbstractPlanner implements Planner {

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
     * The probe that is used to count the number of states that are visited
     */
    private int probe;


    /**
     * Protected constructor of the class
     *
     * @param initialState the initial state
     * @param actions      the set of actions
     * @param goal         the goal
     */
    protected AbstractPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
        this.probe = 0;
    }

    /**
     * Protected Constructor of the class that build a planner from PlanningProblem
     *
     * @param problem the planning problem
     */
    protected AbstractPlanner(PlanningProblem problem) {
        this(problem.getInitialState(), problem.getActions(), problem.getGoal());
    }

    @Override
    public Map<Variable, Object> getInitialState() {
        return this.initialState;
    }

    @Override
    public Set<Action> getActions() {
        return this.actions;
    }

    @Override
    public Goal getGoal() {
        return this.goal;
    }

    /**
     * Returns the number of states that are visited
     *
     * @return the number of states that are visited
     */
    public int getProbe() {
        return this.probe;
    }

    /**
     * Sets the probe
     *
     * @param probe the probe
     */
    public void setProbe(int probe) {
        this.probe = probe;
    }

    /**
     * Resets the probe
     */
    public void resetProbe() {
        this.probe = 0;
    }

    /**
     * Increments the probe
     */
    public void incrementProbe() {
        this.probe++;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" +
                "\n\tinitialState=" + initialState +
                ",\n\tactions=" + actions +
                ",\n\tgoal=" + goal +
                ",\n\tprobe=" + probe + "\n]";
    }


}
