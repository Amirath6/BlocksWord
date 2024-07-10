package planning;

import representation.Variable;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>
 * Class representing an basic action that can be performed by an state
 * </b>
 *
 * <p>
 * A basic action is an action whose preconditions and effects are partial assignments of variables. <br>
 * Such an action can be performed by an state if the state assigns to the variables of the preconditions
 * the values specified by the preconditions. <br>
 * Its effects produce a new state equal to the state on which the action is performed, except that
 * the variables of the effects are assigned to the values specified by the effects. <br>
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class BasicAction implements Action {

    /**
     * The preconditions of the action
     */
    private final Map<Variable, Object> precondition;

    /**
     * The effects of the action
     */
    private final Map<Variable, Object> effect;

    /**
     * The cost of the action
     */
    private final int cost;

    /**
     * <b>
     * Constructor of the class
     * </b>
     *
     * @param precondition the preconditions of the action
     * @param effect       the effects of the action
     * @param cost         the cost of the action
     */
    public BasicAction(Map<Variable, Object> precondition, Map<Variable, Object> effect, int cost) {
        this.precondition = precondition;
        this.effect = effect;
        this.cost = cost;
    }


    public Map<Variable, Object> getPrecondition() {
        return this.precondition;
    }

    /**
     * Returns the effects of the action
     *
     * @return the effects of the action
     */
    public Map<Variable, Object> getEffect() {
        return this.effect;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public boolean isApplicable(Map<Variable, Object> state) {
        if (state == null) {
            return this.precondition == null;
        }
        return state.entrySet().containsAll(this.precondition.entrySet());
    }

    @Override
    public Map<Variable, Object> successor(Map<Variable, Object> state) {
        if (state == null) {
            return this.effect;
        }
        Map<Variable, Object> successor = new HashMap<>(state);
        successor.putAll(this.effect);
        return successor;
    }


    @Override
    public String toString() {
        return "BasicAction [precondition=" + precondition + ", effet=" + effect
                + ", cost=" + cost + "]";
    }

}
