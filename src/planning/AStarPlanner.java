package planning;

import representation.Variable;

import java.util.*;

/**
 * <b>
 * Class representing a planner using the A* algorithm
 * </b>
 *
 * <p>
 * An A* algorithm is an algorithm that solves a planning problem using an heuristic. <br>
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class AStarPlanner extends AbstractPlanner {

    /**
     * L'heuristique
     */
    private final Heuristic heuristic;

    /**
     * Constructeur de la classe AstarPlanner avec l'heuristique
     *
     * @param initialState l'état initial
     * @param actions      l'ensemble des actions
     * @param goal         le but
     * @param heuristic    l'heuristique
     */
    public AStarPlanner(Map<Variable, Object> initialState, Set<Action> actions,
                        Goal goal, Heuristic heuristic) {
        super(initialState, actions, goal);
        this.heuristic = heuristic;
    }

    /**
     * Constructeur de la classe AstarPlanner avec l'heuristique
     *
     * @param problem   le problème
     * @param heuristic l'heuristique
     */
    public AStarPlanner(PlanningProblem problem, Heuristic heuristic) {
        super(problem);
        this.heuristic = heuristic;
    }

    /**
     * Returns the heuristic
     *
     * @return the heuristic
     */
    public Heuristic getHeuristic() {
        return this.heuristic;
    }


    @Override
    public List<Action> plan() {

        //Map des heuristiques
        Map<Map<Variable, Object>, Float> heuristics = new HashMap<>();
        heuristics.put(this.getInitialState(), this.heuristic.estimate(this.getInitialState()));

        //Priority queue des états à explorer
        PriorityQueue<Map<Variable, Object>> toExplore = new PriorityQueue<>(Comparator.comparing(heuristics::get));
        toExplore.add(this.getInitialState());

        //Map des distances
        Map<Map<Variable, Object>, Float> distances = new HashMap<>();
        distances.put(this.getInitialState(), 0f);

        return this.astarPlan(new HashMap<>(), new HashMap<>(), distances, heuristics, toExplore);
    }

    protected List<Action> astarPlan(Map<Map<Variable, Object>, Map<Variable, Object>> parents,
                                     Map<Map<Variable, Object>, Action> plan,
                                     Map<Map<Variable, Object>, Float> distance,
                                     Map<Map<Variable, Object>, Float> heuristicDistance,
                                     PriorityQueue<Map<Variable, Object>> open) {
        while (!open.isEmpty()) {
            Map<Variable, Object> currentState = open.poll();
            if (this.getGoal().isSatisfiedBy(currentState)) {
                return BFSPlanner.getBfsPlan(parents, plan, currentState);
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
                        this.incrementProbe();
                        distance.put(newState, newDistance);
                        heuristicDistance.put(newState, newDistance + heuristic.estimate(newState));
                        parents.put(newState, currentState);
                        plan.put(newState, action);
                        open.add(newState);
                    }
                }
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "AstarPlanner [" +
                "\n\tinitialState=" + getInitialState() +
                ",\n\tactions=" + getActions()
                + ",\n\tgoal=" + getGoal() +
                ",\n\theuristic=" + heuristic +
                ",\n\tprobe=" + getProbe() + "\n]";
    }

}