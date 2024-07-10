package blocksworld;


import representation.Variable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <b>
 * This class contains the variables used in the Blocksworld state representation.
 * </b>
 *
 * <p>
 * This class contains the variables using to solve the Blocksworld problem. <br>
 * We have 2 types of variables: <br>
 * </p>
 * <ul>
 *     <li>
 *         Block variables: <br>
 *         <ul>
 *             <li>
 *                 <b>
 *                     On_b : That indicates where the block b is placed.
 *                 </b>
 *             </li>
 *             <li>
 *                 <b>
 *                     Fixed_b : That indicates if the block b is fixed or not.
 *                  </b>
 *             </li>
 *     </li>
 *
 *     <li>
 *         Pile variables: <br>
 *         <ul>
 *             <li>
 *                 <b>
 *                     Free_p : That indicates if the pile p is free or not.
 *                 </b>
 *             </li>
 *         </ul>
 *     </li>
 * </ul>
 *
 * @author 22013393
 * @version 1.0
 */
public class BWVariableSpace {

    /**
     * The number of blocks of the blocksworld
     */
    private final int nbBlocks;

    /**
     * The number of piles of the blocksworld
     */
    private final int nbPiles;

    /**
     * Map that associates a block to its On_b variable
     */
    private Map<Integer, Variable> onVariables;

    /**
     * Map that associates a block to its Fixed_b variable
     */
    private Map<Integer, Variable> fixedVariables;

    /**
     * Map that associates a pile to its Free_p variable
     */
    private Map<Integer, Variable> freeVariables;

    /**
     * Constructor of the class
     *
     * @param nbBlocks the number of blocks of the blocksworld
     * @param nbPiles the number of piles of the blocksworld
     */
    public BWVariableSpace(int nbBlocks, int nbPiles) {
        this.nbBlocks = nbBlocks;
        this.nbPiles = nbPiles;
    }

    /**
     * Getter of the number of blocks of the blocksworld
     *
     * @return the number of blocks of the blocksworld
     */
    public int getNbBlocks() {
        return this.nbBlocks;
    }

    /**
     * Getter of the number of piles of the blocksworld
     *
     * @return the number of piles of the blocksworld
     */
    public int getNbPiles() {
        return this.nbPiles;
    }

    /**
     * Getter of the map that associates a block to its On_b variable
     *
     * @return the map that associates a block to its On_b variable
     */
    public Map<Integer, Variable> getOnVariables() {
        if (this.onVariables == null) {
            this.onVariables = new HashMap<>();
            for (int i = 0; i < this.nbBlocks; i++) {
                this.onVariables.put(i, On_b.getInstance(i, this.nbBlocks, this.nbPiles));
            }
        }
        return this.onVariables;
    }

    /**
     * Getter of the map that associates a block to its Fixed_b variable
     *
     * @return the map that associates a block to its Fixed_b variable
     */
    public Map<Integer, Variable> getFixedVariables() {
        if (this.fixedVariables == null) {
            this.fixedVariables = new HashMap<>();
            for (int i = 0; i < this.nbBlocks; i++) {
                this.fixedVariables.put(i, new Fixed_b(i));
            }
        }
        return this.fixedVariables;
    }

    /**
     * Getter of the map that associates a pile to its Free_p variable
     *
     * @return the map that associates a pile to its Free_p variable
     */
    public Map<Integer, Variable> getFreeVariables() {
        if (this.freeVariables == null) {
            this.freeVariables = new HashMap<>();
            for (int i = 0; i < this.nbPiles; i++) {
                this.freeVariables.put(i, new Free_p(i));
            }
        }
        return this.freeVariables;
    }

    /**
     * Getter of the set of all the variables of the blocksworld
     *
     * @return the set of all the variables of the blocksworld
     */
    public Set<Variable> getVariables() {
        Set<Variable> variables = new HashSet<>();
        variables.addAll(this.getOnVariables().values());
        variables.addAll(this.getFixedVariables().values());
        variables.addAll(this.getFreeVariables().values());
        return variables;
    }

    /**
     * Get the On_b variable of a block
     *
     * @param block the block
     * @return the On_b variable of the block
     */
    public Variable getOn(int block) {
        return this.getOnVariables().get(block);
    }

    /**
     * Get the Fixed_b variable of a block
     *
     * @param block the block
     * @return the Fixed_b variable of the block
     */
    public Variable getFixed(int block) {
        return this.getFixedVariables().get(block);
    }

    /**
     * Get the Free_p variable of a pile
     *
     * @param pile the pile
     * @return the Free_p variable of the pile
     */
    public Variable getFree(int pile) {
        return this.getFreeVariables().get(pile);
    }



}
