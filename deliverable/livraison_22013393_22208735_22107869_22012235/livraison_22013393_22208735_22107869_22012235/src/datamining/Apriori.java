package datamining;

import representation.BooleanVariable;

import java.util.*;

/**
 * <b>
 *     Class representing a miner of itemset based on the Apriori algorithm
 * </b>
 *
 * <p>
 *     The Apriori algorithm is an algorithm that permits to find frequent itemsets in a transactional database.
 *     It is based on the principle of the "bottom-up" approach. It is a two-step algorithm. First, it generates
 *     the frequent itemsets of size 1. Then, it generates the frequent itemsets of size 2. And so on until
 *     the frequent itemsets of size k are generated.
 * </p>
 *
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class Apriori extends AbstractItemsetMiner {

    /**
     * Construct a miner of itemset based on the Apriori algorithm
     *
     * @param database the boolean database
     */
    public Apriori(BooleanDatabase database) {
        super(database);
    }



    /**
     * Return the set of all singletons itemset that have a frequency greater or equal than the minimum support
     *
     * @param minSupport the minimum support
     * @return the set of all singletons itemset
     */
    public Set<Itemset> frequentSingletons(float minSupport) {
        Set<Itemset> singletons = new HashSet<>();
        for (BooleanVariable item : this.getDatabase().getItems()) {
            Set<BooleanVariable> items = new HashSet<>();
            items.add(item);
            float frequency = this.frequency(items);
            if (frequency >= minSupport) {
                singletons.add(new Itemset(items, frequency));
            }
        }
        return singletons;
    }

    /**
     * <b>
     *     Return the combined sorted items of the two given sets of items
     * </b>
     *
     * <p>
     *     We will combine the two sets of items and sorted them if the two sets of items have the same size
     *     and if they have the same k-1 first items and if they have some different items.
     * </p>
     *
     * @param items1 the first set of items
     * @param items2 the second set of items
     * @return the combined sorted items of the two given sets of items
     */
    public static SortedSet<BooleanVariable> combine(SortedSet<BooleanVariable> items1, SortedSet<BooleanVariable> items2) {

        // The two sets have different size, or they have the last item in common, or they have not the same k-1 first items
        if (items1.isEmpty() || items1.size() != items2.size() || items1.last().equals(items2.last()) || !items1.headSet(items1.last()).equals(items2.headSet(items2.last()))) {
            return null;
        }

        // We combine the two sets of items
        SortedSet<BooleanVariable> combinedItems = new TreeSet<>(COMPARATOR);
        combinedItems.addAll(items1);
        combinedItems.add(items2.last());

        return combinedItems;
    }

    /**
     * <b>
     *     All subsets frequent of the given itemset according to Collection of Frequent Itemsets
     * </b>
     *
     * @param items the set of items
     * @param collection the collection of frequent itemsets
     * @return true if all the subsets obtained by deleting exactly one element from the set of items are contained in the collection
     */
    public static boolean allSubsetsFrequent(Set<BooleanVariable> items, Collection<SortedSet<BooleanVariable>> collection) {

        return items.stream().allMatch(item -> {
            SortedSet<BooleanVariable> subset = new TreeSet<>(COMPARATOR);
            subset.addAll(items);
            subset.remove(item);
            return collection.contains(subset);
        });
    }

    @Override
    public Set<Itemset> extract(float minSupport) {

        // The set of singletons itemset that have a frequency greater or equal than the minimum support
        Set<Itemset> singletons = this.frequentSingletons(minSupport);

        // The set of frequent itemsets
        Set<Itemset> itemsets = new HashSet<>(singletons);

        // The list of possible candidates
        List<SortedSet<BooleanVariable>> candidates = new ArrayList<>();

        // Add the singletons itemset to the list of possible candidates
        for (Itemset itemset : singletons) {
            SortedSet<BooleanVariable> items = new TreeSet<>(COMPARATOR);
            items.addAll(itemset.getItems());
            candidates.add(items);
        }

        // While the list of possible candidates is not empty
        while (!candidates.isEmpty()) {
            List<SortedSet<BooleanVariable>> nextCandidates = new ArrayList<>();
            for(int i = 0; i < candidates.size(); i++) {
                for (int j = i + 1; j < candidates.size(); j++) {
                    SortedSet<BooleanVariable> combinedItems = combine(candidates.get(i), candidates.get(j));
                    if (combinedItems != null && allSubsetsFrequent(combinedItems, candidates)) {
                        float frequency = this.frequency(combinedItems);
                        if (frequency >= minSupport) {
                            itemsets.add(new Itemset(combinedItems, frequency));
                            nextCandidates.add(combinedItems);
                        }
                    }
                }
            }
            candidates = nextCandidates;
        }
        return itemsets;
    }



}
