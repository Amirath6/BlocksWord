package planning;

import representation.Variable;

import java.util.*;

/**
 * <b>
 * Class representing a planner that uses a depth-first search algorithm
 * </b>
 *
 * <p>
 * A depth-first search algorithm is a search algorithm that explores the deepest
 * nodes of the search tree first before exploring the shallower nodes
 * (i.e. the nodes closer to the root of the tree)
 * <br>
 * The depth-first search algorithm is implemented by the method <code>plan()</code>
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class DFSPlanner extends AbstractPlanner {


    public DFSPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        super(initialState, actions, goal);
    }

    public DFSPlanner(PlanningProblem problem) {
        super(problem);
    }

    @Override
    public List<Action> plan() {
        this.resetProbe();
        return this.dfsRec(this.getInitialState(), new ArrayList<>(), new HashSet<>());
    }

    /**
     * Recursive method that implements the depth-first search algorithm
     *
     * @param currentState the current state
     * @param plan         the plan
     * @param visited      the set of visited states
     * @return the plan that is a sequence of actions that leads from the initial state to a state that satisfies the goal
     */
    private List<Action> dfsRec(Map<Variable, Object> currentState, List<Action> plan,
                                Set<Map<Variable, Object>> visited) {
        if (this.getGoal().isSatisfiedBy(currentState)) {
            return plan;
        }
        visited.add(currentState);
        this.incrementProbe();
        for (Action action : this.getActions()) {
            if (action.isApplicable(currentState)) {
                Map<Variable, Object> nextState = action.successor(currentState);
                if (!visited.contains(nextState)) {
                    visited.add(nextState);
                    plan.add(action);
                    List<Action> result = this.dfsRec(nextState, plan, visited);
                    if (result != null) {
                        return result;
                    }
                    plan.remove(action);
                }
            }
        }
        return null;
    }

}
