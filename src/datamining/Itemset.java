package datamining;

import representation.BooleanVariable;

import java.util.Set;

/**
 * <b>
 *     Class representing an itemset.
 * </b>
 *
 * <p>
 *     An itemset is a set of items that occurs together in a transactional database.
 *     So, we can get the support(frequency) of an itemset in a transactional database that represents the number of
 *     transactions that contains the itemset.
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class Itemset {

    /**
     * The set of item that compose the itemset
     */
    private final Set<BooleanVariable> items;

    /**
     * The frequency of the set of item
     */
    private final float frequency;

    /**
     * Construct a set of item with a frequency
     *
     * @param items the set of item
     * @param frequency the frequency of the set of item
     */
    public Itemset(Set<BooleanVariable> items, float frequency) {
        this.items = items;
        this.frequency = frequency;
    }

    /**
     * Get the set of item
     *
     * @return the set of item
     */
    public Set<BooleanVariable> getItems() {
        return this.items;
    }

    /**
     * Get the frequency of the set of item
     *
     * @return the frequency of the set of item
     */
    public float getFrequency() {
        return this.frequency;
    }


    @Override
    public String toString() {
        return "Itemset{" +
                "items=" + items +
                ", frequency=" + frequency +
                '}';
    }


}
