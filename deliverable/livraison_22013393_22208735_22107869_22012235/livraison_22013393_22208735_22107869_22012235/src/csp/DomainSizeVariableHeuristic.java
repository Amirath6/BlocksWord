package csp;

import representation.Variable;

import java.util.*;

/**
 * <b>
 *     A class representing a domain size variable heuristic for CSP solver (used to choose the next variable to assign)
 *     based on the size of the domain of the variable
 * </b>
 *
 * <p>
 *     This heuristic is based on the size of the domain of the variable. <br>
 *     We can choose to minimize or maximize the size of the domain. <br>
 *     If we choose to minimize the size of the domain, the variable with the lowest size of domain
 *     will be chosen. It is useful when we want to assign a variable that has the smallest domain.
 *     It is likely to lead to a solution faster. <br>
 *     If we choose to maximize the size of the domain, the variable with the highest size of domain
 *     will be chosen. It is useful when we want to assign a variable that has the largest domain.
 *     It is likely to lead to a failure. <br>
 * </p>
 *
 * @author 22013393
 * @version 1.0
 */
public class DomainSizeVariableHeuristic implements VariableHeuristic{

    /**
     * The boolean indicating if we want to maximize or minimize the size of the domain
     */
    private final boolean maximize;

    /**
     * <b>
     *     Constructor of the class
     * </b>
     *
     * @param maximize the boolean indicating if we want to maximize or minimize the size of the domain
     */
    public DomainSizeVariableHeuristic(boolean maximize) {
        this.maximize = maximize;
    }

    /**
     * <b>
     *     Returns the best next variable to assign
     * </b>
     *
     * <p>
     *     The variable with the lowest or highest size of domain will be chosen. <br>
     *     The size of the domain is the number of values in the domain. <br>
     * </p>
     *
     * @param variables the set of variables
     * @param domains   the domains of the variables
     * @return the best next variable to assign
     */
    @Override
    public Variable best(Set<Variable> variables, Map<Variable, Set<Object>> domains) {
        List<Variable> variablesList = new ArrayList<>(variables);

        // We sort the variables based on the size of their domains
        variablesList.sort((v1, v2) -> {
            int size1 = domains.get(v1).size();
            int size2 = domains.get(v2).size();
            return size1 - size2;
        });

        if (maximize) {
            return variablesList.get(variablesList.size() - 1);
        } else {
            return variablesList.get(0);
        }
    }
}

