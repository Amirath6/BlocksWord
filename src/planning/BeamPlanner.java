package planning;

import representation.Variable;

import java.util.*;

/**
 * <b>
 * Class representing a planner using the beam search algorithm to solve a planning problem
 * </b>
 *
 * <p>
 * A beam search algorithm is an algorithm that solves a planning problem using an heuristic. <br>
 * It is a variant of the A* algorithm. <br>
 * The beam search algorithm uses a beam width to limit the number of states that are explored. <br>
 * Its particularity is that it does not use a priority queue to store the states to explore. <br>
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class BeamPlanner extends AStarPlanner {

    /**
     * The beam width
     */
    private final int beamWidth;

    /**
     * Constructor of the class BeamPlanner
     *
     * @param initialState the initial state
     * @param actions      the set of actions
     * @param goal         the goal
     * @param heuristic    the heuristic
     * @param beamWidth    the beam width
     */
    public BeamPlanner(Map<Variable, Object> initialState, Set<Action> actions,
                       Goal goal, Heuristic heuristic, int beamWidth) {
        super(initialState, actions, goal, heuristic);
        this.beamWidth = beamWidth;
    }

    /**
     * Constructor of the class BeamPlanner
     *
     * @param problem   the problem
     * @param heuristic the heuristic
     * @param beamWidth the beam width
     */
    public BeamPlanner(PlanningProblem problem, Heuristic heuristic, int beamWidth) {
        super(problem, heuristic);
        this.beamWidth = beamWidth;
    }

    @Override
    public List<Action> plan() {
        //Map des heuristiques
        Map<Map<Variable, Object>, Float> heuristics = new HashMap<>();
        heuristics.put(this.getInitialState(), this.getHeuristic().estimate(this.getInitialState()));

        //Priority queue des états à explorer
        PriorityQueue<Map<Variable, Object>> toExplore = new LimitedSizeQueue<>(this.beamWidth, Comparator.comparing(heuristics::get));
        toExplore.add(this.getInitialState());
        //Map des distances
        Map<Map<Variable, Object>, Float> distances = new HashMap<>();
        distances.put(this.getInitialState(), 0f);

        return this.astarPlan(new HashMap<>(), new HashMap<>(), distances, heuristics, toExplore);
    }
}

