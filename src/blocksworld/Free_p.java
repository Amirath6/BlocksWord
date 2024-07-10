package blocksworld;

import representation.BooleanVariable;

/**
 * <b>
 * Class representing a variable of the state of the blocksworld that specifies if a block is free
 * </b>
 *
 * <p>
 * Free_b is a variable of the state of the blocksworld that specifies if a block is free. <br> <br>
 * For blocksworld that have nbBlocks blocks and nbPiles piles:
 * </p>
 * <ul>
 *     <li>
 *         There are nbBlocks possible values for Free_b. So we can have nbBlocks possible instances of Free_b. <br>
 *         For example, if we have 3 blocks, we can have 3 possible instances of Free_b. <br>
 *     </li>
 *     <li>
 *         Free_b variables can take value in {true, false} <br>
 *     </li>
 *     <li>
 *         Free_b variables are represented by the string "Free_b" where b is the block that is represented by the variable. <br>
 *         For example, the variable representing if the block 0 is free is represented by the string "Free_0"
 *         and will take the value true if the block 0 is free. <br>
 *     </li>
 * </ul>
 *
 * @author 22013393
 * @version 1.0
 */
public class Free_p extends BooleanVariable {


    /**
     * Constructeur de la classe Free_p
     *
     * @param numPile le numero de la pile
     */
    public Free_p(int numPile) {
        super("Free_" + numPile);
    }


}

