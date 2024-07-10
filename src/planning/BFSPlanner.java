package planning;

import representation.Variable;

import java.util.*;

/**
 * <b>
 * Class representing a planner using the Breadth-First Search algorithm
 * </b>
 *
 * <p>
 * A breadth-first search planner is a search algorithm that explores the shallowest
 * nodes of the search tree first before exploring the deeper nodes
 * (i.e. the nodes closer to the leaves of the tree)
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class BFSPlanner extends AbstractPlanner {

    public BFSPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        super(initialState, actions, goal);
    }

    public BFSPlanner(PlanningProblem problem) {
        super(problem);
    }

    public static List<Action> getBfsPlan(Map<Map<Variable, Object>, Map<Variable, Object>> parents,
                                          Map<Map<Variable, Object>, Action> actions, Map<Variable, Object> goal) {
        List<Action> plan = new LinkedList<>();
        Map<Variable, Object> current = goal;
        while (parents.get(current) != null) {
            plan.add(actions.get(current));
            current = parents.get(current);
        }
        Collections.reverse(plan);
        return plan;
    }

    @Override
    public List<Action> plan() {

        if (this.getGoal().isSatisfiedBy(this.getInitialState())) {
            return Collections.emptyList();
        }
        this.resetProbe();
        Set<Map<Variable, Object>> visited = new HashSet<>();
        ArrayDeque<Map<Variable, Object>> open = new ArrayDeque<>();
        open.add(this.getInitialState());
        Map<Map<Variable, Object>, Map<Variable, Object>> parents = new HashMap<>();
        Map<Map<Variable, Object>, Action> plan = new HashMap<>();
        parents.put(this.getInitialState(), null);
        Map<Variable, Object> currentState;
        while (!open.isEmpty()) {
            currentState = open.poll();
            this.incrementProbe();
            visited.add(currentState);
            for (Action action : this.getActions()) {
                if (action.isApplicable(currentState)) {
                    Map<Variable, Object> successor = action.successor(currentState);
                    if (!visited.contains(successor) && !open.contains(successor)) {
                        this.incrementProbe();
                        parents.put(successor, currentState);
                        plan.put(successor, action);
                        if (this.getGoal().isSatisfiedBy(successor)) {
                            return BFSPlanner.getBfsPlan(parents, plan, successor);
                        }
                        open.add(successor);

                    }
                }
            }
        }
        return null;
    }


}
