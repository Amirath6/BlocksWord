package representation;

import java.util.Map;
import java.util.Set;

/**
 * <b>
 * Class representing a unary constraint that is related to one variable
 * </b>
 *
 * <p>
 * A unary constraint is satisfied if the value of the variable is in the domain of the constraint
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class UnaryConstraint implements Constraint {

    /**
     * The variable of the constraint
     */
    private final Variable variable;

    /**
     * The domain of the constraint
     */
    private final Set<Object> domain;

    /**
     * Constructor of the class
     *
     * @param variable the variable of the constraint
     * @param domain   the domain of the constraint
     */
    public UnaryConstraint(Variable variable, Set<Object> domain) {
        // We check if the domain is a subset of the domain of the variable
        if (!variable.getDomain().containsAll(domain)) {
            throw new IllegalArgumentException("The domain must be a subset of the domain of the variable");
        }
        this.variable = variable;
        this.domain = domain;
    }

    /**
     * <b>Returns the variable of the constraint</b>
     *
     * @return the variable of the constraint
     */
    public Variable getVariable() {
        return this.variable;
    }

    /**
     * <b>Returns the domain of the constraint</b>
     *
     * @return the domain of the constraint
     */
    public Set<Object> getDomain() {
        return this.domain;
    }

    public boolean isSatisfiedBy(Map<Variable, Object> assignment) {
        // If the variable is not assigned, the constraint is satisfied
        if (!assignment.containsKey(this.variable)) {
            throw new IllegalArgumentException("The variable must be assigned");
        }
        // If the variable is assigned, the constraint is satisfied if the value of the variable is in the domain of the constraint
        return this.domain.contains(assignment.get(this.variable));
    }


    @Override
    public Set<Variable> getScope() {
        return Set.of(this.variable);
    }

    @Override
    public String toString() {
        return "UnaryConstraint[" + "var=" + variable + ", domain=" + domain + ']';
    }

}

