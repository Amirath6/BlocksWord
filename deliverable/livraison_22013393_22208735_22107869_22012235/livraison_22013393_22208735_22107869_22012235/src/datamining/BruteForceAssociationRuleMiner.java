package datamining;

import representation.BooleanVariable;
import util.Utilities;

import java.util.HashSet;
import java.util.Set;

/**
 * <b>
 *     Bruteforce association rule miner is a miner that can extract association rules from a transaction database
 *     using the bruteforce algorithm
 * </b>
 *
 * <p>
 *     The bruteforce algorithm is a simple algorithm that can extract association rules from a transaction database.
 *     It is based on the fact that if a rule is valid, then all its subsets are valid.
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class BruteForceAssociationRuleMiner extends AbstractAssociationRuleMiner {

    /**
     * Apriori
     */
    private final Apriori apriori;

    /**
     * Construct a bruteforce association rule miner
     *
     * @param database the database of the miner
     */
    public BruteForceAssociationRuleMiner(BooleanDatabase database) {
        super(database);
        this.apriori = new Apriori(database);
    }

    /**
     * Returns all premises of a rule except the empty set and the rule itself
     *
     * @param items the items of the rule
     * @return all premises of the rule
     */
    public static Set<Set<BooleanVariable>> allCandidatePremises(Set<BooleanVariable> items) {

        // We will use a set to store all the premises
        Set<Set<BooleanVariable>> premises = new HashSet<>();

        // All subsets of the items
        Set<Set<BooleanVariable>> subsets = Utilities.subsets(items);

        // We will add all the subsets except the empty set and the items
        for (Set<BooleanVariable> subset : subsets) {
            if (!subset.isEmpty() && !subset.equals(items)) {
                premises.add(subset);
            }
        }

        return premises;
    }

    @Override
    public Set<AssociationRule> extract(float minFrequency, float minConfidence) {

        // We will use a set to store all the association rules
        // Each association rule have an antecedent and a conclusion
        Set<AssociationRule> associationRules = new HashSet<>();

        // Frequent itemsets
        Set<Itemset> frequentItemsets = this.apriori.extract(minFrequency);

        // We will iterate over all the frequent itemsets
        for (Itemset itemset : frequentItemsets) {

            // All possible conclusions of the rule is all the subsets of the itemset
            Set<Set<BooleanVariable>> conclusions = allCandidatePremises(itemset.getItems());

            // The frequency of the rule
            float frequency = frequency(itemset.getItems(), frequentItemsets);

            // We will iterate over all the conclusions
            for (Set<BooleanVariable> conclusion : conclusions) {

                // The premise is the difference between the itemset and the conclusion
                Set<BooleanVariable> premise = new HashSet<>(itemset.getItems());
                premise.removeAll(conclusion);

                // The confidence of the rule
                float confidence = confidence(premise, itemset.getItems(), frequentItemsets);

                // If the confidence is greater than the minimum confidence
                if (confidence >= minConfidence) {

                    // We will add the rule to the set of association rules
                    associationRules.add(new AssociationRule(premise, conclusion, frequency, confidence));
                }

            }

        }
        return associationRules;
    }

}


