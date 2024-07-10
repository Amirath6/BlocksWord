package csp;

import representation.Constraint;
import representation.Variable;

import java.util.*;

/**
 * <b>
 *     A class representing a CSP solver that uses backtracking to solve a CSP problem and return a solution
 * </b>
 *
 * <p>
 *     A BacktrackSolver uses backtracking to solve a CSP problem and return a solution. <br>
 *     Backtracking is a general algorithm for finding all (or some) solutions to some computational problems,
 *     notably constraint satisfaction problems, that incrementally builds candidates to the solutions, and
 *     abandons each partial candidate ("backtracks") as soon as it determines that the candidate cannot possibly
 *     be completed to a valid solution.
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class BacktrackSolver extends AbstractSolver {

    /**
     * <b>
     *     Builds a BacktrackSolver with a set of variables and a set of constraints
     * </b>
     *
     * @param variables   the set of variables of the CSP problem
     * @param constraints the set of constraints of the CSP problem
     */
    public BacktrackSolver(Set<Variable> variables, Set<Constraint> constraints) {
        super(variables, constraints);
    }

    /**
     * <b>
     *     Returns a solution of a CSP problem or null if there is no solution
     * </b>
     *
     * <p>
     *     An recursive method that uses backtracking to solve a CSP problem and return a solution. <br>
     *     From a partial solution of a CSP problem, that is a map of variables and their values, the method
     *     checks if the partial solution is a solution of the CSP problem and if it is not, it tries to extend
     *     the partial solution by adding one of not yet assigned variables and its value. <br>
     * </p>
     *
     * @param partialSolution the partial solution of the CSP problem
     *                        (a map of variables and their values)
     * @param variables      the set of variables of the CSP problem that are not yet assigned
     * @return a solution of a CSP problem or null if there is no solution
     */
    private Map<Variable, Object> solve(Map<Variable, Object> partialSolution, Set<Variable> variables) {
        if (variables.isEmpty()) {
            return partialSolution;
        }

        // Get and remove the first variable of the set of variables
        Variable variable = variables.iterator().next();
        variables.remove(variable);
        Set<Object> domain = variable.getDomain();
        for (Object value : domain) {
            // The new partial solution is the old partial solution with the new variable and its value
            Map<Variable, Object> newPartialSolution = new HashMap<>(partialSolution);
            newPartialSolution.put(variable, value);
            // Check if the new partial solution is consistent with the constraints
            if (this.isConsistent(newPartialSolution)) {
                // Try to solve the CSP problem with the new partial solution
                Map<Variable, Object> solution = solve(newPartialSolution, variables);
                if (solution != null) {
                    return solution;
                }
            }
        }

        // We have to backtrack
        variables.add(variable);
        return null;
    }

    @Override
    public Map<Variable, Object> solve() {
        return solve(new HashMap<>(), new HashSet<>(this.getVariables()));
    }




}
