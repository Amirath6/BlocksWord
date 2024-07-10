package representation;

import java.util.Map;
import java.util.Set;

/**
 * <b>
 * Interface represented constraint between variables
 * </b>
 *
 * <p>
 * A constraint is a logical relationship between different variables,
 * each variable having a value in its domain. So a constraint on a set of
 * variables restricts the possible values of these variables can take simultaneously.
 * </p>
 *
 * <p>
 * The constraint must be able to know if the constraint is satisfied.
 * The constraint must also be able to know the variables it is related to.
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public interface Constraint {

    /**
     * <b>
     * Returns true if a state satisfies all constraints
     * </b>
     *
     * <p>
     * A state is a map of variables and their values
     * A constraint is a type of Constraint
     * </p>
     *
     * @param state       the state to test
     *                    (the state must not be null)
     * @param constraints the constraints
     *                    (the constraints must not be null)
     * @return two if the state satisfies all constraints
     */
    static boolean satisfiedAllConstraints(Map<Variable, Object> state, Iterable<Constraint> constraints) {
        if (state == null || constraints == null) {
            throw new NullPointerException();
        }
        for (Constraint constraint : constraints) {
            if (!constraint.isSatisfiedBy(state)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <b>Returns the variables related to the constraint</b>
     *
     * @return the variables related to the constraint
     */
    Set<Variable> getScope();

    /**
     * <b>Returns true if the constraint is satisfied by the assignment</b>
     *
     * @param assignment the assignment to test
     * @return true if the constraint is satisfied by the assignment
     * @throws IllegalArgumentException if the assignment does not contain all the variables of the constraint
     */
    boolean isSatisfiedBy(Map<Variable, Object> assignment);


}
