package planning;

import representation.Variable;

import java.util.*;

/**
 * Classe utilisant l'algorithme de Dijkstra pour trouver le chemin le plus
 * court pour aboutir à un but.
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class DijkstraPlanner extends AbstractPlanner {

    public DijkstraPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        super(initialState, actions, goal);
    }

    public DijkstraPlanner(PlanningProblem problem) {
        super(problem);
    }

    /**
     * Returns plan using Dijkstra algorithm
     *
     * @param father a map of the father of each state
     * @param plan   a map of the action to do to reach each state
     * @param goals
     */
    private static List<Action> getDijkstraPlan(Map<Map<Variable, Object>, Map<Variable, Object>> father,
                                                Map<Map<Variable, Object>, Action> plan, Queue<Map<Variable, Object>> goals) {
        LinkedList<Action> actions = new LinkedList<>();
        Map<Variable, Object> current = goals.poll();
        while (father.get(current) != null) {
            actions.addFirst(plan.get(current));
            current = father.get(current);
        }
        return actions;
    }

    @Override
    public List<Action> plan() {
        Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<>();
        Map<Map<Variable, Object>, Action> plan = new HashMap<>();
        Map<Map<Variable, Object>, Float> distance = new HashMap<>();
        PriorityQueue<Map<Variable, Object>> open = new PriorityQueue<>(Comparator.comparing(distance::get));
        PriorityQueue<Map<Variable, Object>> goals = new PriorityQueue<>(Comparator.comparing(distance::get));
        this.resetProbe();
        father.put(this.getInitialState(), null);
        distance.put(this.getInitialState(), 0f);
        open.add(this.getInitialState());
        Map<Variable, Object> currentState;
        while (!open.isEmpty()) {
            currentState = open.poll();
            if (this.getGoal().isSatisfiedBy(currentState)) {
                goals.add(currentState);
            }
            for (Action action : this.getActions()) {
                if (action.isApplicable(currentState)) {
                    Map<Variable, Object> newState = action.successor(currentState);
                    distance.computeIfAbsent(newState, (k) -> {
                        this.incrementProbe();
                        return Float.POSITIVE_INFINITY;
                    });
                    Float newDistance = distance.get(currentState) + action.getCost();
                    if (distance.get(newState) > newDistance) {
                        distance.put(newState, newDistance);
                        father.put(newState, currentState);
                        plan.put(newState, action);
                        open.add(newState);
                    }
                }
            }
        }
        if (goals.isEmpty()) {
            return null;
        }
        return DijkstraPlanner.getDijkstraPlan(father, plan, goals);

    }

}
