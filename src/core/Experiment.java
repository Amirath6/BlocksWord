package core;

import planning.*;


import java.util.*;

/**
 * Classe principal du programme
 * 
 * @author 22013393@etu.unicaen.fr
 * @version 1.0
 */
public class Experiment {

    /**
     * @param args Ligne de commandes
     */
    public static void main(String[] args) {

        // Default number max of variables
        int nbVariables = 30;

        //Read the number of variables from the command line
        if (args.length > 0) {
            try {
                nbVariables = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
        }

        PlanningProblem problem = PlanningProblem.generateRandomPlanningProblem(nbVariables);
        AbstractPlanner planner = new BFSPlanner(problem);
        AbstractPlanner planner2 = new DFSPlanner(problem);
        AbstractPlanner planner3 = new DijkstraPlanner(problem);

        AbstractPlanner planner4 = new AStarPlanner(problem, new MissingAndWrongVariablesHeuristic(problem.getGoal()));

        AbstractPlanner planner5 = new BeamPlanner(problem, new MissingAndWrongVariablesHeuristic(problem.getGoal()), 100);



        // List of all the planners
        List<AbstractPlanner> planners = List.of(planner, planner2, planner3, planner4, planner5);

        System.out.println("The number of actions is " + problem.getActions().size());
        System.out.println("Number of variables: " + nbVariables);
        System.out.println("\n\n");
        for(AbstractPlanner p : planners) {
            System.out.println("Planner: " + p.getClass().getSimpleName());
            System.out.println("Solving...");
            long startTime = System.nanoTime();
            List<Action> actions = p.plan();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("Duration: " + duration + "ns");
            System.out.println("Number of actions: " + (actions == null ? 0 : actions.size()));
            System.out.println("The total cost is: " + (actions == null ? 0 : Action.getCostOfActions(actions)));
            System.out.println("Number of visited nodes: " + p.getProbe());
            System.out.println("\n\n\n");

        }


    }
}