package blocksworld;

import representation.Constraint;
import representation.Implication;
import representation.Variable;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * <b>
 * Class representing a state of the blocksworld from a number of blocks and a number of piles
 * and that have constraints and that is regular
 * </b>
 *
 * <p>
 * A BWRegularyState is a state of the blocksworld that have constraints and that is regular. <br>
 * A BWRegularyState is a BWStateConstrained. <br>
 * </p>
 * <p>
 * A BWState is regular if in the state, within each pile the gap between two consecutive blocks is the same. <br> <br>
 * In other words, a BWState is regular if within each pile, there is a relative integer x such that
 * on_k = j => on_(2k-j) = k, i ∈ [0, nbPiles-1], j ∈ [0, nbBlocks-1], k ∈ [0, nbBlocks-1]
 * </p>
 *
 * @author 22013393
 * @version 1.0
 */
public class BWRegularConstraintSpace extends BWConstraintSpace {

    /**
     * The set of regularity constraints
     */
    private Set<Constraint> regularityConstraints;


    /**
     * Constructor of the class BWRegularState
     *
     * @param nbBlocks the number of blocks of the blocksworld
     * @param nbPiles  the number of piles of the blocksworld
     */
    public BWRegularConstraintSpace(int nbBlocks, int nbPiles) {
        super(nbBlocks, nbPiles);
    }

    /**
     * Getter of the set of regularity constraints
     *
     * @return the set of regularity constraints
     */
    public Set<Constraint> getRegularityConstraints() {
        if (this.regularityConstraints == null) {
            this.regularityConstraints = new HashSet<>();
            //Set of indices of Piles
            Set<Integer> pileIndices = new HashSet<>();
            IntStream.range(- this.getNbPiles(), 0).forEach(pileIndices::add);

            IntStream.range(0, this.getNbBlocks()).forEach(i -> {
                IntStream.range(0, this.getNbBlocks()).forEach(j -> {

                    if (i != j) {
                        // The index k of the block on which the block j must be placed to be regular with the block i
                        int k = 2 * j - i;

                        // possible values for the variable on_j
                        Set<Object> possibleValues = new HashSet<>(pileIndices);

                        // If k is positive, it is a possible value for the variable on_j
                        if (k >= 0 && k < this.getNbBlocks()) {
                            possibleValues.add(k);
                        }

                        // Add the regularity constraint
                        this.regularityConstraints.add(new Implication(
                                this.getVariableSpace().getOnVariables().get(i),
                                Set.of(j),
                                this.getVariableSpace().getOnVariables().get(j),
                                possibleValues
                        ));
                    }
                });
            });

            /*for (int i = 0; i < this.getNbBlocks(); i++){
                for (int j = 0; j < this.getNbBlocks(); j++) {
                    if (i != j) {

                        // The index k of the block on which the block j must be placed to be regular with the block i
                        int k = 2 * j - i;

                        // possible values for the variable on_j
                        Set<Object> possibleValues = new HashSet<>(pileIndices);

                        // If k is positive, it is a possible value for the variable on_j
                        if (k >= 0 && k < this.getNbBlocks()) {
                            possibleValues.add(k);
                        }

                        // Add the regularity constraint
                        this.regularityConstraints.add(new Implication(
                                this.getVariableSpace().getOnVariables().get(i),
                                Set.of(j),
                                this.getVariableSpace().getOnVariables().get(j),
                                possibleValues
                        ));
                    }
                }
            }*/
        }
        return this.regularityConstraints;
    }

    @Override
    public Set<Constraint> getConstraints() {
        Set<Constraint> constraints = super.getConstraints();
        constraints.addAll(this.getRegularityConstraints());
        return constraints;
    }


}
