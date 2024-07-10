package planning;

import representation.Variable;

import java.util.Map;

/**
 * <b>
 * Class representing an heuristic that computes the number of missing variables
 * in a state
 * </b>
 *
 * <p>
 * This heuristic computes the number of missing variables in a state. <br>
 * It can be used to solve a planning problem using an informed search algorithm. <br>
 * It is admissible. It is not optimal, but it is fast, and
 * it is often enough to solve a planning problem. <br>
 * </p>
 *
 * <p>
 * It is not optimal because it does not take into account the cost of the actions to reach
 * a state. <br>
 * It is admissible because it never overestimates the cost of reaching a goal state. <br>
 * It is fast because it does not need to compute the cost of the actions to reach a state. <br>
 * It is often enough to solve a planning problem because it is admissible, and it is fast. <br>
 * It is not monotone because even if the number of missing variables decreases, the cost of
 * reaching a goal state can increase.
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class MissingVariablesHeuristic implements Heuristic {

    /**
     * The goal
     */
    private final Goal goal;

    /**
     * Constructor of the class MissingVariablesHeuristic
     *
     * @param goal the goal
     */
    public MissingVariablesHeuristic(Goal goal) {
        this.goal = goal;
    }

    @Override
    public float estimate(Map<Variable, Object> state) {
        float missingVariables = 0;
        for (Variable variable : goal.getGoal().keySet()) {
            if (!state.containsKey(variable)) {
                missingVariables++;
            }
        }
        return missingVariables;
    }
}
