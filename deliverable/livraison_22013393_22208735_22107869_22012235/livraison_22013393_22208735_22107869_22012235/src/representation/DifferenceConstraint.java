package representation;

import java.util.Objects;

/**
 * <b>Class representing a difference constraint between two variables</b>
 *
 * <p>
 * A difference constraint is a constraint that specifies that two variables
 * must have different values (x != y)
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class DifferenceConstraint extends BinaryConstraint {

    /**
     * Constructor of the class
     *
     * @param firstVariable  the first variable of the constraint
     * @param secondVariable the second variable of the constraint
     */
    public DifferenceConstraint(Variable firstVariable, Variable secondVariable) {
        super(firstVariable, secondVariable);
    }

    @Override
    public boolean isSatisfied(Object firstValue, Object secondValue) {
        // We check if the values are different
        return !Objects.equals(firstValue, secondValue);
    }

    @Override
    public String toString() {
        return "DifferenceConstraint [var1=" + this.getFirstVariable() + ", var2=" + this.getSecondVariable() + "]";
    }

}

