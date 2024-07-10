package csp;

import representation.Variable;

import java.util.Map;

/**
 * <b>
 *     An interface representing a CSP solver that can solve a CSP problem and return a solution
 * </b>
 *
 * <p>
 *     A CSP solver permits to solve a CSP problem and return a solution. <br>
 *     A CSP problem is specified by a set of variables, a set of constraints and a set of domains. <br>
 *     A CSP solution is a map of variables and their values. <br>
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public interface Solver {

    /**
     * <b>
     *     Returns a solution of a CSP problem or null if there is no solution
     * </b>
     *
     * <p>
     *     A CSP Solution is a map of variables and their values. <br>
     *     A CSP Solution is a solution of a CSP problem if it satisfies all constraints. <br>
     * </p>
     * @return a solution of a CSP problem or null if there is no solution
     */
    Map<Variable, Object> solve();
}
