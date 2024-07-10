package representation;

import java.util.Objects;
import java.util.Set;

/**
 * <b>
 * Class representing a variable from its name and its domain
 * </b>
 *
 * <p>
 * A variable is a name and a set of possible values (domain) that it can take.<br>
 * Variables are very useful to represent for solving a problem. In fact, a variable
 * permits to represent a value that can be changed or that can be unknown. <br>
 * A variable is immutable (its name and its domain cannot be changed after its creation). <br>
 * A variable is instantiated if it has a value (not null).<br>
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class Variable {

    /**
     * The name of the variable
     */
    private final String name;

    /**
     * The domain of the variable
     */
    private final Set<Object> domain;

    /**
     * Constructor of the class
     *
     * @param name   the name of the variable
     * @param domain the domain of the variable
     */
    public Variable(String name, Set<Object> domain) {
        this.name = name;
        this.domain = domain;
    }

    /**
     * Returns the name of the variable
     *
     * @return the name of the variable
     */
    public String getName() {
        return name;
    }

    /**
     * <b>Returns the domain of the variable</b>
     *
     * @return the domain of the variable
     */
    public Set<Object> getDomain() {
        return domain;
    }


    /**
     * <b>Returns the hashcode of the variable</b>
     *
     * <p>The hashcode of the variable depends only on the name of the variable </p>
     *
     * @return the hashcode of the variable
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /**
     * <b>
     * Checks if the variable is equal to another object
     * </b>
     *
     * <p>
     * The variable is equal to another object if and only if the other object is a variable
     * and has the same name
     * </p>
     *
     * @param obj the object to compare
     * @return true if the variable is equal to the object, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Variable) {
            Variable other = (Variable) obj;
            return Objects.equals(name, other.name);
        }
        return false;
    }


    /**
     * <b>Returns a string representation of the variable</b>
     *
     * <p>
     * The string representation of the variable follows the following format :<br>
     * <code>
     * NameOfTheClass [name=NameOfTheVariable, domain=DomainOfTheVariable]
     * </code>
     * </p>
     *
     * @return a string representation of the variable
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [name=" + name + ", domain=" + domain + "]";
    }
}
