package representation;

import java.util.Set;

/**
 * <b>
 * Class representing an implication constraint between two variables (x => y)
 * </b>
 *
 * <p>
 * An implication constraint is satisfied according to the following rule : <br>
 * Let x and y be two variables, subDomainX and subDomainY be the subdomains of x and y respectively. <br>
 * If x is assigned to a value in subDomainX, then y must be assigned to a value in subDomainY.
 * In other words, if x is assigned to a value in subDomainX, then y must be assigned to a value in subDomainY
 * or if x is not assigned to a value in subDomainX, then y can be assigned to any value in subDomainY.
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class Implication extends BinaryConstraint {

    /**
     * The subdomain of the first variable
     */
    private final Set<Object> subDomainVar1;

    /**
     * The subdomain of the second variable
     */
    private final Set<Object> subDomainVar2;

    /**
     * Constructor of the class
     *
     * @param var1          the first variable of the constraint
     * @param subDomainVar1 the subdomain of the first variable
     * @param var2          the second variable of the constraint
     * @param subDomainVar2 the subdomain of the second variable
     */
    public Implication(Variable var1, Set<Object> subDomainVar1, Variable var2, Set<Object> subDomainVar2) {
        super(var1, var2);
        // We check that the subdomains are subsets of the domains of the variables
        if (!var1.getDomain().containsAll(subDomainVar1) || !var2.getDomain().containsAll(subDomainVar2)) {
            throw new IllegalArgumentException("The subdomains must be subsets of the domains of the variables");
        }
        this.subDomainVar1 = subDomainVar1;
        this.subDomainVar2 = subDomainVar2;
    }

    /**
     * <b>Returns the subdomain of the first variable</b>
     *
     * @return the subdomain of the first variable
     */
    public Set<Object> getSubDomainVar1() {
        return this.subDomainVar1;
    }

    /**
     * <b>Returns the subdomain of the second variable</b>
     *
     * @return the subdomain of the second variable
     */
    public Set<Object> getSubDomainVar2() {
        return this.subDomainVar2;
    }

    @Override
    public boolean isSatisfied(Object firstValue, Object secondValue) {
        // We check if the first value is not in the subdomain of the first variable or
        // if the second value is in the subdomain of the second variable
        return !this.subDomainVar1.contains(firstValue) || this.subDomainVar2.contains(secondValue);
    }


    @Override
    public String toString() {
        return "Implication [var1=" + this.getFirstVariable() + ", subDomainVar1=" + this.subDomainVar1 + ", var2=" + this.getSecondVariable() + ", subDomainVar2=" + this.subDomainVar2 + "]";
    }


}

