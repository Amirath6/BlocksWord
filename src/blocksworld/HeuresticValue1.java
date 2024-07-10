package blocksworld;

import csp.HeuristicMACSolver;
import csp.ValueHeuristic;
import representation.Variable;

import java.util.*;

/**
 */
public class HeuresticValue1 implements ValueHeuristic {

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
    public HeuresticValue1(Random randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public HeuresticValue1() {
        this(new Random());
    }

    @Override
    public List<Object> ordering(Variable variable, Set<Object> domain) {
        // We order the values so that the stack values are assigned first
        // This is done by putting the stack values at the beginning of the list
        // and the block values at the end of the list
        List<Object> domainList = new ArrayList<>(domain);
        Collections.shuffle(domainList, randomGenerator);
        // The list of values ordered by the heuristic
        List<Object> values = new ArrayList<>();

        // If variable is not an instance of On_b
        if (!(variable instanceof On_b)) {
            // We add all the values of the domain to the list
            values.addAll(domain);
        } else {
            // We add the stack values to the list
            for (Object value : domainList) {
                Integer i = (Integer) value;
                if (i < 0) {
                    values.add(0, value);
                }else{
                    values.add(value);
                }
            }

        }
        return values;
    }

}
