package datamining;

import representation.BooleanVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <b>
 *     Class representing a transactional database of boolean variables.
 * </b>
 *
 * <p>
 *     A transactional database is a set of transactions. Each transaction is a set of items.
 *     Each item is a boolean variable.
 *     It permits to store transactions data and to perform some operations on them.
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class BooleanDatabase {

    /**
     * The set of item of the transaction database
     */
    private final Set<BooleanVariable> items;

    /**
     * The list of transactions of the transaction database
     */
    private final List<Set<BooleanVariable>> transactions;

    /**
     * Construct a transaction database
     *
     * @param items the set of item of the transaction database
     */
    public BooleanDatabase(Set<BooleanVariable> items) {
        this.items = items;
        this.transactions = new ArrayList<>();
    }

    /**
     * Get the set of item of the transaction database
     *
     * @return the set of item of the transaction database
     */
    public Set<BooleanVariable> getItems() {
        return this.items;
    }

    /**
     * Get the list of transactions of the transaction database
     *
     * @return the list of transactions of the transaction database
     */
    public List<Set<BooleanVariable>> getTransactions() {
        return this.transactions;
    }

    /**
     * Add a transaction to the transaction database
     *
     * @param transaction the transaction to add
     */
    public void addTransaction(Set<BooleanVariable> transaction) {
        this.transactions.add(transaction);
    }

    /**
     * Add a transaction to the transaction database
     * @param transaction the transaction to add
     */
    public void add(Set<BooleanVariable> transaction) {
        this.addTransaction(transaction);
    }

    @Override
    public String toString() {
        return "BooleanDatabase [items=" + items + ", transactions=" + transactions + "]";
    }





}