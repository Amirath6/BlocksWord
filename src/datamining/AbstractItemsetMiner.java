package datamining;

import representation.BooleanVariable;

import java.util.Comparator;
import java.util.Set;

/**
 * <b>
 *     An abstract class representing an itemset miner.
 * </b>
 *
 * <p>
 *     An itemset miner is an algorithm that permits to find itemsets in a transactional database.
 *     It permits to find frequent itemsets in a transactional database. So, we use this abstract class to
 *     define the common methods that help a miner to perform its task.
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public abstract class AbstractItemsetMiner implements ItemsetMiner {

    /**
     * The boolean database
     */
    private final BooleanDatabase database;

    /**
     * The comparator used to compare itemsets. It is based on the lexicographic order.
     */
    public static final Comparator<BooleanVariable> COMPARATOR = (firstVariable, secondVariable) -> {
        if (firstVariable == null) {
            return -1;
        } else if (secondVariable == null) {
            return 1;
        } else {
            return firstVariable.getName().compareTo(secondVariable.getName());
        }
    };

    /**
     * Protected constructor
     *
     * @param database the boolean database
     */
    protected AbstractItemsetMiner(BooleanDatabase database) {
        this.database = database;
    }

    /**
     * Return the frequency of a set of item
     *
     * @param items the set of item
     */
    public float frequency(Set<BooleanVariable> items) {

        // frequency is the number of transactions containing the items divided by the number of transactions
        return (float) this.database.getTransactions().stream().filter(transaction -> transaction.containsAll(items)).count() / this.database.getTransactions().size();

    }

    @Override
    public BooleanDatabase getDatabase() {
        return this.database;
    }




}
