package csp;

import representation.Constraint;
import representation.Variable;

import java.util.*;

/**
 * <b>
 *     A class representing a CSP solver that can solve a CSP problem and return a solution
 *     using an heuristic variable selection and an heuristic value selection
 * </b>
 *
 * <p>
 *     A CSP solver permits to solve a CSP problem and return a solution. <br>
 *     A CSP problem is specified by a set of variables, a set of constraints. <br>
 *     The choice of the next variable to assign is based on a predefined strategy. <br>
 *     The choice of the next value to assign to a variable is based on a predefined strategy. <br>
 *     The solver will use a MAC algorithm to check the consistency of the solution. <br>
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class HeuristicMACSolver extends MACSolver {

    private final VariableHeuristic variableSelectionHeuristic;
    private final ValueHeuristic valueSelectionHeuristic;

    /**
     * <b>
     *     Construct a new instance of HeuristicMACSolver
     * </b>
     *
     * @param constraints the set of constraints
     *                   to be used to verify the arc consistency
     * @param variableSelectionHeuristic the variable selection heuristic
     * @param valueSelectionHeuristic the value selection heuristic
     */
    public HeuristicMACSolver(Set<Variable> variables, Set<Constraint> constraints, VariableHeuristic variableSelectionHeuristic, ValueHeuristic valueSelectionHeuristic, boolean useAC3) {
        super(variables, constraints, useAC3);
        this.variableSelectionHeuristic = variableSelectionHeuristic;
        this.valueSelectionHeuristic = valueSelectionHeuristic;
    }

    public HeuristicMACSolver(Set<Variable> variables, Set<Constraint> constraints, VariableHeuristic variableSelectionHeuristic, ValueHeuristic valueSelectionHeuristic) {
        this(variables, constraints, variableSelectionHeuristic, valueSelectionHeuristic, false);
    }


    @Override
    protected Map<Variable, Object> solve(Map<Variable, Object> partialSolution, Set<Variable> variables, Map<Variable, Set<Object>> domains) {
        if (variables.isEmpty()) {
            return partialSolution;
        }

        if (!getArcConsistency().arcConsistency(domains)) {
            return null;
        }

        // Pop the next variable to assign
        Variable variable = variableSelectionHeuristic.best(variables, domains);
        variables.remove(variable);

        // Order the values
        List<Object> values = valueSelectionHeuristic.ordering(variable, domains.get(variable));

        return addVariableToSolution(partialSolution, variables, domains, variable, values);
    }





}
