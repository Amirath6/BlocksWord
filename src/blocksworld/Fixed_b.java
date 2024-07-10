package blocksworld;

import representation.BooleanVariable;

public class Fixed_b extends BooleanVariable {


    /**
     * Constructeur de la classe Fixed_b
     *
     * @param numBlock le numero du bloc
     */
    public Fixed_b(int numBlock) {
        super("Fixed_" + numBlock);
    }

}
