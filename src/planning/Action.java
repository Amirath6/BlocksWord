package planning;

import representation.Variable;

import java.util.Map;

/**
 * <b>
 * An action is
 * </b>
 *
 * <p>
 * An action is a set of variables that must be assigned to a specific value. <br>
 * An action can be performed by an state if the state satisfies the preconditions. <br>
 * The postconditions of an action are the variables that must be assigned to a specific value. <br>
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public interface Action {

    /**
     * Returns the total cost of List of actions
     *
     * @param actions the list of actions
     *                (the actions must be applicable to the state)
     * @return the total cost of the actions
     * @throws NullPointerException if the list of actions is null
     */
    static int getCostOfActions(Iterable<Action> actions) {
        if (actions == null) {
            throw new NullPointerException();
        }
        int cost = 0;
        for (Action action : actions) {
            cost += action.getCost();
        }
        return cost;
    }

    /**
     * Returns a boolean indicating if the action can be performed by the state
     *
     * @param state the state to test
     * @return true if the action can be performed by the state
     */
    boolean isApplicable(Map<Variable, Object> state);

    /**
     * Returns the new state after performing the action
     *
     * @param state the state on which the action is performed
     *              (the state must satisfy the preconditions)
     * @return the new state after performing the action
     */
    Map<Variable, Object> successor(Map<Variable, Object> state);

    /**
     * Returns the cost of the action
     *
     * @return the cost of the action
     */
    int getCost();


}
