package planning;

import representation.Variable;

import java.util.Map;

/**
 * <b>
 * Interface representing an heuristic
 * </b>
 *
 * <p>
 * An heuristic is a function that estimates the cost of the path from a state to a goal
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public interface Heuristic {

    /**
     * <b>
     * Returns the estimated cost of the path from the state to the goal
     * </b>
     *
     * @param state the state
     * @return the estimated cost of the path from the state to the goal
     */
    float estimate(Map<Variable, Object> state);
}
