package representation;

import java.util.Set;

/**
 * <b>
 * Class representing a boolean variable from its name
 * </b>
 *
 * <p>
 * A boolean variable is a variable with a domain of two elements : true and false. <br>
 * So a boolean variable can take only two values when it is instantiated. <br>
 * </p>
 *
 * <p>
 * BooleanVariable is a subclass of Variable. In fact, a boolean variable is a variable
 * that we specify with a domain of two elements : true and false. <br>
 * In this way, we can use a boolean variable as a variable. <br>
 * It inherits all the methods and attributes of Variable. <br>
 * </p>
 *
 * <p>
 * <strong>
 * Note : <br>
 * We don't need to override equals and hashCode methods because we use the same
 * methods of Variable (because a boolean variable is a variable). Like that, we
 * can compare BooleanVariable objects with Variable objects and vice versa. The
 * hashcode method of a boolean variable is the same as the hashcode of a variable to
 * permit that two any objects (boolean variable or variable) with the same name
 * have the same hashcode.<br>
 * We don't need to override toString method because the method of Variable is
 * enough and appropriate for a boolean variable. <br>
 * </strong>
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class BooleanVariable extends Variable {

    /**
     * Constructor of the class
     *
     * @param name the name of the variable
     */
    public BooleanVariable(String name) {
        super(name, Set.of(true, false));
    }

}
