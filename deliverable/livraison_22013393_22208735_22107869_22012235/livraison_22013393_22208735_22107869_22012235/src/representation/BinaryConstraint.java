package representation;

import java.util.Map;
import java.util.Set;

/**
 * <b>Class representing a binary constraint between two variables</b>
 *
 * <p>
 * A binary constraint is a constraint that involves exactly two variables.
 * A binary constraint is satisfied if the two variables satisfy the constraint.
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public abstract class BinaryConstraint implements Constraint {

    /**
     * The first variable of the constraint
     */
    private final Variable firstVariable;

    /**
     * The second variable of the constraint
     */
    private final Variable secondVariable;

    /**
     * <b>
     * Protected constructor of the class (only subclasses can instantiate it)
     * </b>
     *
     * @param firstVariable  the first variable of the constraint
     * @param secondVariable the second variable of the constraint
     */
    protected BinaryConstraint(Variable firstVariable, Variable secondVariable) {
        this.firstVariable = firstVariable;
        this.secondVariable = secondVariable;
    }

    /**
     * <b>Returns the first variable of the constraint</b>
     *
     * @return the first variable of the constraint
     */
    public Variable getFirstVariable() {
        return this.firstVariable;
    }

    /**
     * <b>Returns the second variable of the constraint</b>
     *
     * @return the second variable of the constraint
     */
    public Variable getSecondVariable() {
        return this.secondVariable;
    }

    @Override
    public Set<Variable> getScope() {
        if (this.firstVariable.equals(this.secondVariable)) {
            return Set.of(this.firstVariable);
        }
        return Set.of(this.firstVariable, this.secondVariable);
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, Object> assignment) {

        if (!assignment.keySet().containsAll(this.getScope())) {
            throw new IllegalArgumentException("All variables of a constraint must be instantiated");
        }
        return this.isSatisfied(assignment.get(this.firstVariable), assignment.get(this.secondVariable));
    }


    /**
     * <b>
     * Returns a boolean indicating if the two objects satisfy the constraint
     * </b>
     *
     * <p>
     * The two objects are the values of the two variables of the constraint.<br>
     * The two objects are considered as satisfying the constraint if they satisfy the constraint.
     * </p>
     *
     * @param firstValue  the value of the first variable
     * @param secondValue the value of the second variable
     * @return true if the constraint is satisfied by the assignment
     */
    protected abstract boolean isSatisfied(Object firstValue, Object secondValue);

}
