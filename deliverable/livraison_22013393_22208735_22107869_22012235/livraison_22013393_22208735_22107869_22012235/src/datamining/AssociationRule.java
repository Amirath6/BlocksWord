package datamining;

import representation.BooleanVariable;

import java.util.Set;

/**
 * <b>
 *     Association rule is a rule that can be used to predict the presence of an item in a transaction
 * </b>
 *
 * <p>
 *     An association rule is a probability that particular items will be purchased together.
 *     It is composed of an antecedent and a consequent. The antecedent is a set of items
 *     that must be present in a transaction to predict the presence of the consequent in the transaction.
 *     The support of the rule is the frequency of the antecedent in the transaction database. The confidence
 *     of the rule is the frequency of the consequent in the transaction database given the antecedent.
 *     The lift of the rule is the confidence of the rule divided by the frequency of the consequent in the
 *     transaction database.
 * </p>
 *
 * <p>
 *     The rule is valid if the support and the confidence are greater than the minimum support and the
 *     minimum confidence.
 *     The rule is strong if the lift is greater than 1.
 *     The rule is interesting if the lift is greater than the minimum lift.
 *     The rule is relevant if the rule is valid and interesting.
 *     The rule is redundant if the rule is valid and the antecedent is a subset of another rule. * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class AssociationRule {

    /**
     * The premise of the rule
     */
    private final Set<BooleanVariable> premise;

    /**
     * The conclusion of the rule
     */
    private final Set<BooleanVariable> conclusion;

    /**
     * The frequency of the antecedent in the transaction database
     */
    private final float frequency;

    /**
     * The confidence of the rule
     */
    private final float confidence;

    /**
     * The lift of the rule
     */
    private final float lift;

    /**
     * Build an association rule
     *
     * @param premise the premise of the rule
     * @param conclusion the conclusion of the rule
     * @param frequency the frequency of the antecedent in the transaction database
     * @param confidence the confidence of the rule
     */
    public AssociationRule(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion, float frequency, float confidence) {
        this.premise = premise;
        this.conclusion = conclusion;
        this.frequency = frequency;
        this.confidence = confidence;
        this.lift = this.confidence / this.frequency;
    }

    /**
     * Get the premise of the rule
     *
     * @return the premise of the rule
     */
    public Set<BooleanVariable> getPremise() {
        return this.premise;
    }

    /**
     * Get the conclusion of the rule
     *
     * @return the conclusion of the rule
     */
    public Set<BooleanVariable> getConclusion() {
        return this.conclusion;
    }

    /**
     * Get the frequency of the antecedent in the transaction database
     *
     * @return the frequency of the antecedent in the transaction database
     */
    public float getFrequency() {
        return this.frequency;
    }

    /**
     * Get the confidence of the rule
     *
     * @return the confidence of the rule
     */
    public float getConfidence() {
        return this.confidence;
    }

    /**
     * Get the lift of the rule
     *
     * @return the lift of the rule
     */
    public float getLift() {
        return this.lift;
    }


    @Override
    public String toString() {
        return "AssociationRule{" +
                "antecedent=" + premise +
                ", conclusion=" + conclusion +
                ", frequency=" + frequency +
                ", confidence=" + confidence +
                ", lift=" + lift +
                '}';
    }




}
