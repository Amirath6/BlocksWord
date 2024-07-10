package csp;

import representation.Variable;

import java.util.List;
import java.util.Set;

/**
 * <b>
 *     Interface representing a value heuristic for CSP solver (used to choose the next value to assign
 *     to a variable)
 * </b>
 *
 * <p>
 *     A value heuristic is used to choose the next value to assign to a variable. <br>
 *     The choice of the next value is based on a predefined strategy. <br>
 *     Different strategies can be implemented by implementing this interface. <br>
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public interface ValueHeuristic {

    /**
     * Returns a list of values ordered by the heuristic
     *
     * @param variable the variable to choose the value from
     * @param domain   the domain of the variable
     * @return a list of values ordered by the heuristic
     */
    List<Object> ordering(Variable variable, Set<Object> domain);
}
