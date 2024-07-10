package planning;

import representation.Variable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <b>
 * Interface representing a solver for planning problems
 * </b>
 *
 * <p>
 * A planner implements a search algorithm to find a solution to a planning problem
 * that is represented by an initial state, a set of actions and a goal. <br>
 * <p>
 * A planner can access to the initial state, the set of actions and the goal through
 * the methods <code>getInitialState()</code>, <code>getActions()</code> and <code>getGoal()</code>. <br>
 * <p>
 * The search algorithm returns a plan that is a sequence of actions
 * that leads from the initial state to a state that satisfies the goal. <br>
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public interface Planner {

    /**
     * Returns the initial state of the planning problem
     *
     * @return the initial state of the planning problem
     */
    Map<Variable, Object> getInitialState();

    /**
     * Returns the set of actions of the planning problem
     *
     * @return the set of actions of the planning problem
     */
    Set<Action> getActions();

    /**
     * Returns the goal of the planning problem
     *
     * @return the goal of the planning problem
     */
    Goal getGoal();

    /**
     * Returns a plan that is a sequence of actions that leads from the initial state
     * to a state that satisfies the goal
     *
     * @return List of actions that leads from the initial state to a state that satisfies the goal
     */
    List<Action> plan();


}
