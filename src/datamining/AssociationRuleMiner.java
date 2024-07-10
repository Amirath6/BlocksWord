package datamining;

import java.util.Set;

/**
 * <b>
 *     Associate rule miner is a miner that can extract association rules from a transaction database
 * </b>
 *
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public interface AssociationRuleMiner {

    /**
     * <b>
     *     Returns the database of the miner
     * </b>
     *
     * @return the database of the miner
     */
    BooleanDatabase getDatabase();

    /**
     * <b>
     *     extract the association rules from the database
     * </b>
     *
     * @param minFrequency the minimum frequency of the itemset
     * @param minConfidence the minimum confidence of the rule
     * @return the set of association rules
     */
    Set<AssociationRule> extract(float minFrequency, float minConfidence);
}
