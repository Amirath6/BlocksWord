package planning;

import representation.Variable;

import java.util.Map;

/**
 * <b>
 * Class representing a basic goal
 * </b>
 *
 * <p>
 * A basic goal is a goal whose specified by a partial assignment of variables. <br>
 * A basic goal is reached if all the variables of the goal are assigned to the right value
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class BasicGoal implements Goal {

    /**
     * The goal
     */
    private final Map<Variable, Object> goal;

    /**
     * <b>
     * Constructor of the class
     * </b>
     *
     * @param goal the goal
     */
    public BasicGoal(Map<Variable, Object> goal) {
        this.goal = goal;
    }

    /**
     * Returns a boolean indicating if the goal is reached by the assignment
     *
     * @param assignment the assignment to test
     * @return true if the goal is reached by the assignment
     */
    public boolean isSatisfiedBy(Map<Variable, Object> assignment) {
        if (assignment == null) {
            return this.goal == null;
        }
        return assignment.entrySet().containsAll(this.goal.entrySet());
    }

    /**
     * Returns the goal
     *
     * @return the goal
     */
    public Map<Variable, Object> getGoal() {
        return this.goal;
    }

    @Override
    public String toString() {
        return "BasicGoal [goal=" + goal + "]";
    }
}