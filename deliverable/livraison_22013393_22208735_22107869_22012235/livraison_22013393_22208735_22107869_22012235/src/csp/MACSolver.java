package csp;


import representation.Constraint;
import representation.Variable;

import java.util.*;

/**
 * <b>
 *     Class representing a MAC solver
 * </b>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 * */
public class MACSolver extends AbstractSolver{

    private final ArcConsistency arcConsistency;

    /**
     * <b>
     *     Construct a new instance of MACSolver
     * </b>
     *
     * @param constraints the set of constraints
     *                   to be used to verify the arc consistency
     */
    public MACSolver(Set<Variable> variables, Set<Constraint> constraints, boolean useAC3) {
        super(variables, constraints);
        this.arcConsistency = new ArcConsistency(constraints, useAC3);
    }

    public MACSolver(Set<Variable> variables, Set<Constraint> constraints) {
        this(variables, constraints, false);
    }

    public ArcConsistency getArcConsistency() {
        return arcConsistency;
    }

    /**
     * Boolean that represents if the solver is using AC3 or not
     * @return true if the solver is using AC3, false otherwise
     */

    @Override
    public Map<Variable, Object> solve() {
        Map<Variable, Set<Object>> domains = new HashMap<>();
        for (Variable variable : this.getVariables()) {
            domains.put(variable, new HashSet<>(variable.getDomain()));
        }

        return solve(new HashMap<>(), new HashSet<>(this.getVariables()), domains);
    }


    /**
     * Recursive method to solve the CSP
     *
     * @param partialSolution the partial solution
     * @param variables the set of variables
     * @param domains the domains of the variables
     * @return the solution
     */
    protected Map<Variable, Object> solve(Map<Variable, Object> partialSolution, Set<Variable> variables, Map<Variable, Set<Object>> domains) {

        // If there is no more variables to assign
        if (variables.isEmpty()) {
            return partialSolution;
        }

        // If the domains are not arc consistent
        if (!getArcConsistency().arcConsistency(domains)) {
            return null;
        }

        // Choose a variable
        Variable variable = variables.iterator().next();
        // Remove the variable from the set of variables
        variables.remove(variable);

        Set<Object> domain = domains.get(variable);
        return addVariableToSolution(partialSolution, variables, domains, variable, domain);
    }

    /**
     * Try to find a solution by assigning a value to the variable
     * @param partialSolution the partial solution
     * @param variables the set of variables
     * @param domains the domains of the variables
     * @param variable the variable to assign
     * @param domain the domain of the variable
     * @return
     */
    protected Map<Variable, Object> addVariableToSolution(Map<Variable, Object> partialSolution, Set<Variable> variables, Map<Variable, Set<Object>> domains, Variable variable, Iterable<Object> domain) {
        // Assign a value to the variable
        for(Object value : domain) {

            // Create a new partial solution
            Map<Variable, Object> newPartialSolution = new HashMap<>(partialSolution);
            newPartialSolution.put(variable, value);

            // Test the consistency of the new partial solution
            if (isConsistent(newPartialSolution)) {

                // We copy the domains to avoid modifying the original domains during the recursive call to solve
                Map<Variable, Set<Object>> newDomains = new HashMap<>(domains);

                // We restrict the domain of the variable to the value
                newDomains.put(variable, new HashSet<>(Collections.singletonList(value)));

                // Recursive call
                Map<Variable, Object> solution = solve(newPartialSolution, variables, newDomains);
                if (solution != null) {
                    return solution;
                }
            }
        }

        // No solution found, so we add the variable to the set of variables and return null
        variables.add(variable);
        return null;
    }
}
