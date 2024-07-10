package blocksworld;

import representation.Variable;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * <b>
 * Class representing a variable of the state of the blocksworld that specifies where a block is
 * </b>
 *
 * <p>
 * On_b is a variable of the state of the blocksworld that specifies where a block is. <br> <br>
 * For blocksworld that have nbBlocks blocks and nbPiles piles:
 * </p>
 * <ul>
 *     <li>
 *         There are nbBlocks * nbPiles possible values for On_b. So we can have
 *         (nbBlocks * nbPiles) possible instances of On_b. <br>
 *     </li>
 *     <li>
 *         On_b variables can take value in {-nbPiles, -nbPiles+1, ..., -1, 0, 1, ..., nbBlocs - 2, nbBlocs -1 }\ {b}
 *         where b is the block that is represented by the variable. <br>
 *         So if the b block is on the b' block then the value of the On_b variable is b'. <br>
 *         If the b block is on the table in the pile p then the value of the On_b variable is -(p+1). <br>
 *         For example, if we have 3 blocks and 3 piles, the variable representing where the block 0 is can take value in
 *         {-3, -2, -1, 1, 2, 3} \ {0} = {-3, -2, -1, 1, 2, 3} <br>
 *         If the block 0 is on the pile 0, the variable representing where the block 0 is will take the value -1. <br>
 *         If the block 0 is on the block 1, the variable representing where the block 0 is will take the value 1. <br>
 *     </li>
 *     <li>
 *         On_b variables are represented by the string "On_b" where b is the block that is represented by the variable. <br>
 *         For example, the variable representing the position of the block 0 is represented by the string "On_0". <br>
 *     </li>
 * </ul>
 *
 * @author 22013393
 * @version 1.0
 */
public class On_b extends Variable {


    /**
     * Constructeur de la classe On_b
     *
     * @param numBlock le numero du bloc
     * @param domaine  le domaine des valeurs de la variables
     */
    public On_b(int numBlock, Set<Object> domaine) {
        super("On_" + numBlock, domaine);
        this.getDomain().remove(numBlock);
    }

    /**
     * Renvoie une nouvelle instance de On_b en spécifiant l'intervalle du domaine
     *
     * @param numBlock le numero du bloc
     * @param nbBlocks le nombre de blocks
     * @param nbPiles  le nombre de piles
     */
    public static On_b getInstance(int numBlock, int nbBlocks, int nbPiles) {
        Set<Object> domaine = new HashSet<>();
        IntStream.range(-nbPiles, nbBlocks).forEach(domaine::add);
        domaine.remove(numBlock);
        return new On_b(numBlock, domaine);
    }
}
