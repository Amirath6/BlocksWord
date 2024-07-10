package datamining;

import representation.BooleanVariable;

import java.util.HashSet;
import java.util.Set;

/**
 * <b>
 *     Abstract class representing an association rule miner.
 * </b>
 *
 * <p>
 *     An association rule miner is a miner that can extract association rules from a transaction database.
 *     All association rule miners need some common methods to extract association rules from a transaction database.
 *     So, we have defined an abstract class that contains these common methods.
 * </p>
 *
 * <p>
 *     The common methods are:
 * </p>
 * <ul>
 *     <li> {@link #getDatabase()} </li>
 *     <li> {@link #frequency(Set, Set)}
 *     <li> {@link #confidence(Set, Set, Set)}
 * </ul>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0 */
public abstract class AbstractAssociationRuleMiner implements AssociationRuleMiner {

    /**
     * The boolean database
     */
    private final BooleanDatabase database;

    /**
     * Protected constructor
     *
     * @param database the boolean database
     */
    protected AbstractAssociationRuleMiner(BooleanDatabase database) {
        this.database = database;
    }


    @Override
    public BooleanDatabase getDatabase() {
        return this.database;
    }

    /**
     * <b>
     *     Returns the frequency of an item in the itemset
     * </b>
     *
     * @param items the set of items
     * @param itemset the itemset to search in
     * @return the frequency of the item in the itemset
     */
    public static float frequency(Set<BooleanVariable> items, Set<Itemset> itemset) {

        for (Itemset item : itemset) {
            if (item.getItems().equals(items)) {
                return item.getFrequency();
            }
        }

        throw new IllegalArgumentException("The itemset does not contain the given items");

    }

    /**
     * <b>
     *     Returns the confidence of a rule according to the premise and the conclusion
     * </b>
     *
     * @param premise the premise of the rule
     * @param conclusion the conclusion of the rule
     * @param itemset the itemset to search in
     * @return the confidence of the rule
     */
    public static float confidence(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion, Set<Itemset> itemset) {

            Set<BooleanVariable> items = new HashSet<>(premise);
            items.addAll(conclusion);

            return frequency(items, itemset) / frequency(premise, itemset);

    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "database=" + database +
                '}';
    }

}
