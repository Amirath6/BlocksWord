package csp;

import representation.Constraint;
import representation.Variable;

import java.util.*;

/**
 * <b>
 *     An abstract class representing a CSP solver that can solve a CSP problem and return a solution
 * </b>
 *
 * <p>
 *     A CSP solver permits to solve a CSP problem and return a solution. <br>
 *     A CSP problem is specified by a set of variables, a set of constraints. <br>
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public abstract  class AbstractSolver implements  Solver{

    /**
     * The set of variables of the CSP problem
     */
    private final Set<Variable> variables;

    /**
     * The set of constraints of the CSP problem
     */
    private final Set<Constraint> constraints;

    /**
     * <b>
     *     Protected constructor of the class (only subclasses can instantiate it)
     * </b>
     *
     * @param variables   the set of variables of the CSP problem
     * @param constraints the set of constraints of the CSP problem
     */
    protected AbstractSolver(Set<Variable> variables, Set<Constraint> constraints) {
        this.variables = variables;
        this.constraints = constraints;
    }

    /**
     * <b>
     *     Returns the set of variables of the CSP problem
     * </b>
     * @return the set of variables of the CSP problem
     */
    public Set<Variable> getVariables() {
        return this.variables;
    }

    /**
     * <b>
     *     Returns the set of constraints of the CSP problem
     * </b>
     * @return the set of constraints of the CSP problem
     */
    public Set<Constraint> getConstraints() {
        return this.constraints;
    }

    /**
     * <b>
     *     Returns a boolean indicating if a CSP solution is consistent
     * </b>
     *
     * <p>
     *     A CSP Solution is consistent if it completely assigns a value to each variable of each constraint
     *     and if it satisfies all constraints. <br>
     * </p>
     *
     * @param solution the CSP solution to check
     *                 (a CSP solution is a map of variables and their values)
     * @return a boolean indicating if a CSP solution is consistent
     * @throws IllegalArgumentException if the CSP solution is null
     */
    public boolean isConsistent(Map<Variable, Object> solution) throws IllegalArgumentException {
        if (solution == null) {
            throw new IllegalArgumentException("The CSP solution cannot be null");
        }

        for (Constraint constraint : this.constraints) {
            if (solution.keySet().containsAll(constraint.getScope()) && !constraint.isSatisfiedBy(solution)) {
                return false;
            }
        }
        return true;
    }

}
