package blocksworld;

import csp.VariableHeuristic;
import representation.Variable;

import java.util.Map;
import java.util.Set;

public class HeuresticVariable1 implements VariableHeuristic{


    @Override
    public Variable best(Set<Variable> variables, Map<Variable, Set<Object>> domains) {
        // We choose first On_b variables with the lowest domain size and then the other variables with the lowest domain size

        // The best On_b variable
        Variable bestOn_b = null;

        // The best variable
        Variable best = null;

        // The size of the best On_b variable
        int bestOn_bSize = Integer.MAX_VALUE;

        // The size of the best variable
        int bestSize = Integer.MAX_VALUE;

        // For each variable
        for (Variable variable : variables) {
            // If the variable is an instance of On_b
            if (variable instanceof On_b) {
                // If the size of the domain of the variable is lower than the size of the best On_b variable
                if (domains.get(variable).size() < bestOn_bSize) {
                    // We update the best On_b variable
                    bestOn_b = variable;
                    bestOn_bSize = domains.get(variable).size();
                }
            } else {
                // If the size of the domain of the variable is lower than the size of the best variable
                if (domains.get(variable).size() < bestSize) {
                    // We update the best variable
                    best = variable;
                    bestSize = domains.get(variable).size();
                }
            }
        }
        return bestOn_b != null ? bestOn_b : best;
    }
}
