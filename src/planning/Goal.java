package planning;

import representation.Variable;

import java.util.Map;

/**
 * <b>
 * A goal that can be achieved by an state
 * </b>
 *
 * <p>
 * A goal is a set of variables where each variable must be assigned to a specific value. <br>
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public interface Goal {

    /**
     * <b>
     * Return a boolean indicating if the goal is reached by the assignment
     * </b>
     *
     * <p>
     * A goal is reached if all the variables of the goal are assigned to the value
     * specified by the condition
     * </p>
     *
     * @param assignment the assignment to test
     * @return true if the goal is reached by the assignment
     */
    boolean isSatisfiedBy(Map<Variable, Object> assignment);

    /**
     * <b>
     * Returns the goal
     * </b>
     *
     * @return the goal
     */
    Map<Variable, Object> getGoal();
}
