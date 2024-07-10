package csp;

import representation.Constraint;
import representation.Variable;

import java.util.*;

/**
 * <b>
 *     Class representing a variable heuristic based on the number of constraints
 *     a variable is involved in
 * </b>
 *
 * <p>
 *     This heuristic is based on the number of constraints a variable is involved in. <br>
 *     We can choose to minimize or maximize the number of constraints. <br>
 *     If we choose to minimize the number of constraints, the variable with the lowest number of constraints
 *     will be chosen. It is useful when we want to assign a variable that is involved in the least number
 *     of constraints. It is likely to lead to a solution faster. <br>
 *     If we choose to maximize the number of constraints, the variable with the highest number of constraints
 *     will be chosen. It is useful when we want to assign a variable that is involved in the most number
 *     of constraints. It is likely to lead to a failure. <br>
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class NbConstraintsVariableHeuristic implements VariableHeuristic {

    /**
     * The boolean indicating if we want to maximize or minimize the number of constraints
     */
    private final boolean maximize;

    /**
     * The set of constraints
     */
    private final Set<Constraint> constraints;

    /**
     * <b>
     *     Constructor of the class
     * </b>
     *
     * @param maximize    the boolean indicating if we want to maximize or minimize the number of constraints
     * @param constraints the set of constraints
     */
    public NbConstraintsVariableHeuristic(Set<Constraint> constraints, boolean maximize) {
        this.maximize = maximize;
        this.constraints = constraints;
    }

    /**
     * <b>
     *     Returns the best next variable to assign
     * </b>
     *
     * <p>
     *     The variable with the lowest or highest number of constraints will be chosen. <br>
     * </p>
     *
     * @param variables the set of variables to choose from
     * @param domains   the domains of the variables
     * @return the best next variable to assign
     */
    @Override
    public Variable best(Set<Variable> variables, Map<Variable, Set<Object>> domains) {
        // Map that associates a variable to the number of constraints it is involved in
        Map<Variable, Integer> nbConstraints = new HashMap<>();
        // list of sorted variables
        List<Variable> sortedVariables = new ArrayList<>(variables);

        // For each variable, we count the number of constraints it is involved in
        for (Variable variable : variables) {
            int nb = 0;
            for (Constraint constraint : constraints) {
                if (constraint.getScope().contains(variable)) {
                    nb++;
                }
            }
            nbConstraints.put(variable, nb);
        }

        // We sort the variables according to the number of constraints they are involved in
        sortedVariables.sort((o1, o2) -> {
            if (maximize) {
                return nbConstraints.get(o2) - nbConstraints.get(o1);
            } else {
                return nbConstraints.get(o1) - nbConstraints.get(o2);
            }
        });

        // We return the first variable of the sorted list
        return sortedVariables.get(0);


    }

}
