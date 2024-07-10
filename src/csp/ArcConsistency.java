package csp;

import representation.Constraint;
import representation.Variable;

import java.util.*;

/**
 * <b>
 *     Class representing an arc consistency
 * </b>
 *
 * <p>
 *     An arc consistency verifies if a variable is consistent with its neighbors
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0 */
public class ArcConsistency {


    /**
     * Set of Unary constraints
     */
    private final Set<Constraint> unaryConstraints = new HashSet<>();

    /**
     * Set of Binary constraints
     */
    private final Set<Constraint> binaryConstraints = new HashSet<>();

    /**
     * Boolean that if we will use the AC3 algorithm
     */
    private final boolean useAC3;

    /**
     * <b>
     *     Construct a new instance of ArcConsistency
     * </b>
     *
     * @param constraints the set of constraints
     *                   to be used to verify the arc consistency
     * @throws NullPointerException if the set of constraints is null
     * @throws IllegalArgumentException if the set of constraints is not BinaryConstraint or UnaryConstraint
     */
    public ArcConsistency(Set<Constraint> constraints, boolean useAC3) {
        if (constraints == null) {
            throw new NullPointerException("The set of constraints cannot be null");
        }

        // Check if the constraints concern only one variable or two variables
        for (Constraint constraint : constraints) {
            if (constraint.getScope().size() == 2){
                this.binaryConstraints.add(constraint);
            }
            else if (constraint.getScope().size() == 1){
                this.unaryConstraints.add(constraint);
            }
            else {
                throw new IllegalArgumentException("The set of constraints must be BinaryConstraint or UnaryConstraint");
            }
        }
        this.useAC3 = useAC3;
    }

    /**
     * <b>
     *     Construct a new instance of ArcConsistency
     * </b>
     *
     * @param constraints the set of constraints
     *                   to be used to verify the arc consistency
     * @throws NullPointerException if the set of constraints is null
     * @throws IllegalArgumentException if the set of constraints is not BinaryConstraint or UnaryConstraint
     */
    public ArcConsistency(Set<Constraint> constraints) {
        this(constraints, false);
    }




    /**
     * <b>
     *    Renforce the arc consistency of a node and returns true if at least one domain has been emptied
     * </b>
     *
     * @param domains the Map that associates a variable to its domain
     * @return true if at least one domain has been emptied
     */
    public boolean enforceNodeConsistency(Map<Variable, Set<Object>> domains){

        return enforceNodeConsistency(domains.keySet(), domains);
    }

    /**
     *
     * @param variables the set of variables
     * @param domains the Map that associates a variable to its domain
     * @return
     */
    public boolean enforceNodeConsistency(Set<Variable> variables, Map<Variable, Set<Object>> domains){


        for(Constraint constraint : unaryConstraints){
            Variable variable = constraint.getScope().iterator().next();
            if(variables.contains(variable)){
                // We copy the domain of the variable because we will modify it, and we don't want to modify the original domain
                // So we can come back to the original domain if the constraint is not satisfied
                Set<Object> domain = new HashSet<>(domains.get(variable));
                domain.removeIf(value -> !constraint.isSatisfiedBy(Map.of(variable, value)));
                domains.put(variable, domain);
            }
        }

        // If at least one domain has been emptied, we return false
        return domains.values().stream().noneMatch(Set::isEmpty);

    }





    public boolean revise(Variable firstVariable, Set<Object> domain1, Variable secondVariable, Set<Object> domain2){
        boolean revised = false;

        // For each value of the first variable
        for (Iterator<Object> iterator = domain1.iterator(); iterator.hasNext(); ) {

            // Get the value
            Object value = iterator.next();

            // Check if the value is viable
            boolean viable = false;

            // For each value of the second variable
            for (Object value2 : domain2) {

                // Check if the combination of the two values satisfies all the constraints
                boolean allConstraintsSatisfied = true;

                // For each constraint
                for (Constraint constraint : binaryConstraints) {

                    // If the constraint concerns the two variables
                    if (constraint.getScope().contains(firstVariable) && constraint.getScope().contains(secondVariable)) {

                        // Check if the constraint is satisfied
                        if (!constraint.isSatisfiedBy(Map.of(firstVariable, value, secondVariable, value2))) {
                            allConstraintsSatisfied = false;
                            break;
                        }
                    }

                }

                // If the combination of the two values satisfies all the constraints
                if (allConstraintsSatisfied) {
                    // The value is viable
                    viable = true;
                    break;
                }

            }

            // If the value is not viable
            if (!viable) {
                // Remove the value from the domain
                iterator.remove();
                revised = true;
            }

        }

        return revised;
    }


    /**
     * <b>
     *     Filtre tout les domaines
     *  </b>
     *
     * @param domains the Map that associates a variable to its domain
     * @param variables the set of variables
     * @return true if at least one domain has been emptied
     */
    public boolean ac1(Map<Variable, Set<Object>> domains, Set<Variable> variables){

        if(!enforceNodeConsistency(domains)){
            return false;
        }



        boolean revised =false;
        do{
             revised = false;

            for(Variable variable : variables){

                // we create a copy of the domain of the variable because we will modify it
                // So we can come back to the original domain if the constraint is not satisfied
                Set<Object> domain = new HashSet<>(domains.get(variable));
                for(Variable variable2 : variables){
                    if(!variable.equals(variable2)){

                        revised = revise(variable, domain, variable2, domains.get(variable2)) || revised;
                    }
                }
                domains.put(variable, domain);
            }
        }while (revised);

        // If at least one domain has been emptied, we return false
        return domains.values().stream().noneMatch(Set::isEmpty);
    }

    /**
     * <b>
     *     Filtre tout les domaines
     *  </b>
     *
     * @param domains the Map that associates a variable to its domain
     * @return true if at least one domain has been emptied
     */
    public boolean ac1(Map<Variable, Set<Object>> domains) {
        return ac1(domains, domains.keySet());
    }


    /**
     * <b>
     *     Filtre tout les domaines with ac3
     *  </b>
     *
     * @param domains the Map that associates a variable to its domain
     * @param variables the set of variables
     * @return true if at least one domain has been emptied
     */
    public boolean ac3(Map<Variable, Set<Object>> domains, Set<Variable> variables){

        if (!enforceNodeConsistency(domains)) {
            return false;
        }

        // Queue of arcs
        Queue<Map<Variable, Variable>> queue = new LinkedList<>();
        Queue<Map<Variable, Variable>> arcs = new LinkedList<>();
        // Add all the arcs on binary constraints with actiual variables
        for (Constraint constraint : binaryConstraints) {
                Iterator<Variable> iterator = constraint.getScope().iterator();
                Variable variable1 = iterator.next();
                Variable variable2 = iterator.next();
                if (variables.contains(variable1) && variables.contains(variable2)) {
                    queue.add(Map.of(variable1, variable2));
                    arcs.add(Map.of(variable1, variable2));
                }

        }

        // While the queue is not empty
        while (!queue.isEmpty()) {

            // Get the first arc
            Map<Variable, Variable> arc = queue.poll();

            // Get the variables of the arc
            Variable firstVariable = arc.keySet().iterator().next();
            Variable secondVariable = arc.get(firstVariable);

            // Get the domains of the variables
            Set<Object> domain1 = new HashSet<>(domains.get(firstVariable));

            // If the domain of the first variable has been revised
            if (revise(firstVariable, domain1, secondVariable, domains.get(secondVariable))) {

                // If the domain of the first variable is empty
                if (domain1.isEmpty()) {
                    return false;
                }

                // Add all the arcs of the form (K, X) where K is a neighbor of X
                for (Map<Variable, Variable> arc2 : arcs) {
                    if (arc2.containsValue(firstVariable)) {
                        queue.add(arc2);
                    }
                }
            }

                // Update the domain of the first variable
                domains.put(firstVariable, domain1);

        }
        return true;
    }

    /**
     * <b>
     *     Filtre tout les domaines with ac3
     *  </b>
     *
     * @param domains the Map that associates a variable to its domain
     * @return true if at least one domain has been emptied
     */
    public boolean ac3(Map<Variable, Set<Object>> domains) {
        return ac3(domains, domains.keySet());
    }


    /**
     * <b>
     *     Filtre tout les domaines with arc consistency
     *  </b>
     *
     * @param domains the Map that associates a variable to its domain
     * @return true if at least one domain has been emptied
     */
    public boolean arcConsistency(Map<Variable, Set<Object>> domains) {

        return this.arcConsistency(domains, domains.keySet());
    }

    /**
     * <b>
     *     Filtre tout les domaines with arc consistency
     *  </b>
     *
     * @param domains the Map that associates a variable to its domain
     * @param variables the set of variables
     * @return true if at least one domain has been emptied
     */
    public boolean arcConsistency(Map<Variable, Set<Object>> domains, Set<Variable> variables) {

        // If we have only binary constraints
        if (this.binaryConstraints.isEmpty()) {
            return enforceNodeConsistency(domains);
        }

        // If we use ac3
        if (this.useAC3) {
            return ac3(domains, variables);
        }

        return ac1(domains, variables);
    }

}
