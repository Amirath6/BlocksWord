package blocksworld;

import representation.Constraint;
import representation.DifferenceConstraint;
import representation.Implication;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * <b>
 * Class representing a space constraints of the blocksworld
 * </b>
 *
 * <p>
 * A BWConstraintSpace is a space constraints of the blocksworld. <br>
 * It is defined by a number of blocks and a number of piles. <br>
 * </p>
 */
public class BWConstraintSpace {

    /**
     * The number of blocks of the blocksworld
     */
    private final int nbBlocks;

    /**
     * The number of piles of the blocksworld
     */
    private final int nbPiles;

    /**
     * Set of the constraints between blocks and blocks
     */
    private Set<Constraint> blockBlockConstraints;

    /**
     * Set of the constraints between blocks and piles
     */
    private Set<Constraint> blockPileConstraints;

    /**
     * BlockWorld variable space
     */
    private BWVariableSpace variableSpace;


    /**
     * Constructor of the class
     *
     * @param nbBlocks the number of blocks of the blocksworld
     * @param nbPiles  the number of piles of the blocksworld
     */
    public BWConstraintSpace(int nbBlocks, int nbPiles) {
        this.nbBlocks = nbBlocks;
        this.nbPiles = nbPiles;
        this.blockBlockConstraints = null;
        this.blockPileConstraints = null;
        this.variableSpace = new BWVariableSpace(nbBlocks, nbPiles);
    }


    /**
     * Get the number of blocks of the blocksworld
     *
     * @return the number of blocks of the blocksworld
     * @see BWConstraintSpace#nbBlocks
     */
    public int getNbBlocks() {
        return this.nbBlocks;
    }

    /**
     * Get the number of piles of the blocksworld
     *
     * @return the number of piles of the blocksworld
     * @see BWConstraintSpace#nbPiles
     */
    public int getNbPiles() {
        return this.nbPiles;
    }

    /**
     * Get the set of the constraints between blocks and blocks
     *
     * @return the set of the constraints between blocks and blocks
     * @see BWConstraintSpace#blockBlockConstraints
     */
    public Set<Constraint> getBlockBlockConstraints() {
        if (this.blockBlockConstraints == null) {
            this.blockBlockConstraints = new HashSet<>();
            IntStream.range(0, this.getNbBlocks()).forEach(i -> {
                IntStream.range(0, this.getNbBlocks()).forEach(j -> {
                    if (i != j) {
                        this.blockBlockConstraints.add(
                                new DifferenceConstraint(
                                        this.getVariableSpace().getOnVariables().get(i),
                                        this.getVariableSpace().getOnVariables().get(j)));

                        this.blockBlockConstraints.add(
                                new Implication(
                                        this.getVariableSpace().getOnVariables().get(i),
                                        Set.of(j),
                                        this.getVariableSpace().getFixedVariables().get(j),
                                        Set.of(true)));
                    }
                });
            });
            /*for (int i = 0; i < this.getNbBlocks(); i++) {
                for (int j = 0; j < this.getNbBlocks(); j++) {
                    if (i != j) {
                        // on_i != on_j
                        this.blockBlockConstraints.add(
                                new DifferenceConstraint(variableSpace.getOnVariables().get(i),
                                        variableSpace.getOnVariables().get(j)
                                )
                        );

                        // (on_i == j) => (fixed_j= true)
                        this.blockBlockConstraints.add(
                                new Implication(
                                        variableSpace.getOnVariables().get(i),
                                        Set.of(j),
                                        variableSpace.getFixedVariables().get(j),
                                        Set.of(true)
                                )
                        );
                    }

                }
            }*/
        }
        return this.blockBlockConstraints;
    }

    /**
     * Get the set of the constraints between blocks and piles
     *
     * @return the set of the constraints between blocks and piles
     * @see BWConstraintSpace#blockPileConstraints
     */
    public Set<Constraint> getBlockPileConstraints() {
        if (this.blockPileConstraints == null) {
            this.blockPileConstraints = new HashSet<>();
            IntStream.range(0, this.getNbBlocks()).forEach(i -> {
                IntStream.range(0, this.getNbPiles()).forEach(j -> {
                    // (on_i = -(j+1)) => (free_j = true)
                    this.blockPileConstraints.add(
                            new Implication(
                                    variableSpace.getOnVariables().get(i),
                                    Set.of(-(j + 1)),
                                    variableSpace.getFreeVariables().get(j),
                                    Set.of(false)
                            )
                    );
                });
            });
        }
        return this.blockPileConstraints;
    }

    /**
     * Get the set of the constraints between blocks and blocks and between blocks and piles
     *
     * @return the set of the constraints between blocks and blocks and between blocks and piles
     * @see BWConstraintSpace#getBlockBlockConstraints()
     * @see BWConstraintSpace#getBlockPileConstraints()
     */
    public Set<Constraint> getConstraints() {
        Set<Constraint> constraints = new HashSet<>();
        constraints.addAll(this.getBlockBlockConstraints());
        constraints.addAll(this.getBlockPileConstraints());
        return constraints;
    }

    /**
     * Get the variable space of the blocksworld
     *
     * @return the variable space of the blocksworld
     * @see BWConstraintSpace#variableSpace
     */
    public BWVariableSpace getVariableSpace() {
        return this.variableSpace;
    }


}
