package datamining;

import java.util.Set;

/**
 * <b>
 *     A Miner is an object that can mine itemsets from a transactional database.
 * </b>
 *
 * <p>
 *     When you talk about mining, you talk about extracting information from a database.
 *     In this case, we want to extract itemsets that satisfy a minimum support from a transactional database.
 * </p>
 *
 * <p>
 *     User can define a minimum support and extract itemsets that satisfy this minimum support.
 *     The minimum support is a percentage of the number of transactions in the transactional database.
 *     For example, if the minimum support is 0.5, it means that we want to extract itemsets that occurs in at least
 *     50% of the transactions in the transactional database.
 *     The minimum support must be between 0 and 1.
 *     If the minimum support is 0, it means that we want to extract all itemsets.
 *     If the minimum support is 1, it means that we want to extract itemsets that occurs in all transactions.
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public interface ItemsetMiner {

    /**
     * Return a boolean database
     *
     * @return a boolean database
     */
    BooleanDatabase getDatabase();

    /**
     * Extract the frequent itemset of the database
     *
     * @param minSupport the minimum support
     * @return the set of itemset that have a frequency greater or equal than the minimum support
     * @throws IllegalArgumentException if the minimum support is not between 0 and 1
     */
    Set<Itemset> extract(float minSupport);



}
