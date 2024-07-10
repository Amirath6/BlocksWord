package csp;

import representation.Variable;

import java.util.Map;
import java.util.Set;

/**
 * <b>
 *     Interface representing a variable heuristic for CSP solver (used to choose the next variable to assign)
 * </b>
 *
 * <p>
 *     A variable heuristic is used to choose the next variable to assign. <br>
 *     The choice of the next variable is based on a predefined strategy. <br>
 *     Different strategies can be implemented by implementing this interface. <br>
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public interface VariableHeuristic {

    /**
     * Returns the best next variable to assign
     *
     * @param variables the set of variables to choose from
     * @param domains   the domains of the variables
     * @return the best next variable to assign
     */
    Variable best(Set<Variable> variables, Map<Variable, Set<Object>> domains);

}
