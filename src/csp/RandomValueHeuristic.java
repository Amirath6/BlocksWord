package csp;

import representation.Variable;

import java.util.*;

/**
 * <b>
 *     A class representing a random value heuristic for CSP solver (used to choose the next value to assign to a variable)
 *     based on the random order of the values
 * </b>
 *
 * <p>
 *     This heuristic is based on the random order of the values. <br>
 *     The values are ordered randomly. <br>
 *     It is useful when we want to assign a value randomly. <br>
 *     The order of the values is not important can depend on the random generator. <br>
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class RandomValueHeuristic implements ValueHeuristic{


    /**
     * The random generator
     */
    private final Random randomGenerator;

    /**
     * <b>
     *     Constructor of the class
     * </b>
     * @param randomGenerator the random generator
     */
    public RandomValueHeuristic(Random randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    /**
     * <b>
     *     Returns a list of values ordered by the heuristic
     * </b>
     *
     * <p>
     *     The values are ordered randomly. <br>
     *     The order of the values is not important can depend on the random generator. <br>
     * </p>
     *
     * @param variable the variable to choose the value from
     * @param domain   the domain of the variable
     * @return a list of values ordered by the heuristic
     */
    @Override
    public List<Object> ordering(Variable variable, Set<Object> domain) {
        List<Object> values = new ArrayList<>(domain);
        Collections.shuffle(values, randomGenerator);
        return values;
    }
}
