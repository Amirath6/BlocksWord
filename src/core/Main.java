package core;

import blocksworld.*;
import bwmodel.BWState;
import bwmodel.BWStateBuilder;
import bwui.BWComponent;
import bwui.BWIntegerGUI;
import csp.*;
import planning.*;
import planning.Action;
import representation.BooleanVariable;
import representation.Constraint;
import representation.Variable;

import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

//        Map<Object, Set<Object>> map = new HashMap<>();
//        map.put("a", new HashSet<>(Set.of(1, 2, 3)));
//        map.put("b", new HashSet<>(Set.of(4, 5, 6)));
//        Map<Object, Set<Object>> map2 = new HashMap<>(map);
//        map2.put("a", new HashSet<>(Set.of(1, 2)));
//        System.out.println(map);
//        System.out.println(map2);
//        map2.get("a").remove(1);
//        System.out.println(map);
//        System.out.println(map2);

        int numBlocks = 15;
        int numPiles = 5;

        BWVariableSpace variableSpace = new BWVariableSpace(numBlocks, numPiles);
        BWRegularConstraintSpace constraintSpace = new BWRegularConstraintSpace(numBlocks, numPiles);
        BWActionSpace actionSpace = new BWActionSpace(numBlocks, numPiles);

        Solver solver = new MACSolver(variableSpace.getVariables(), constraintSpace.getConstraints());
        Solver solver6 = new MACSolver(variableSpace.getVariables(), constraintSpace.getConstraints(), true);
        Solver solver2 = new HeuristicMACSolver(variableSpace.getVariables(), constraintSpace.getConstraints(), new HeuresticVariable1(), new HeuresticValue1(new Random(250701)));
        Solver solver5 = new HeuristicMACSolver(variableSpace.getVariables(), constraintSpace.getConstraints(), new HeuresticVariable1(), new HeuresticValue1(new Random(250701)), true);
        Solver solver3 = new HeuristicMACSolver(variableSpace.getVariables(), constraintSpace.getConstraints(), new NbConstraintsVariableHeuristic(constraintSpace.getConstraints(), true), new HeuresticValue1());
        Solver solver4 = new BacktrackSolver(variableSpace.getVariables(), constraintSpace.getConstraints());
        List<Map<Variable, Object>> solutions = new ArrayList<>();
        List<Solver> solvers = new ArrayList<>();
        solvers.add(solver2);

        solvers.add(solver5);

        //solvers.add(solver4);
        for (Solver s : solvers) {


            System.out.println("Begin The search with " + s.getClass().getSimpleName());
            // Begin time
            long startTime = System.nanoTime();
            Map<Variable, Object> solution = s.solve();
            // End time
            long endTime = System.nanoTime();
            solutions.add(solution);

            if (solution != null) {
                System.out.println("Solution found with " + s.getClass().getSimpleName());
                System.out.println("Time : " + (endTime - startTime) / 1000000 + " ms");
                System.out.println(Constraint.satisfiedAllConstraints(solution, constraintSpace.getConstraints()));
//                // Print On variables
//                for (int i = 0; i < numBlocks; i++) {
//                    System.out.println("On_" + i + " = " + solution.get(variableSpace.getOnVariables().get(i)));
//                }
//                System.out.println();
//                // Print Free variables
//                for (int i = 0; i < numPiles; i++) {
//                    System.out.println("Free_" + i + " = " + solution.get(variableSpace.getFreeVariables().get(i)));
//                }
//                System.out.println();
//                // Print Fixed variables
//                for (int i = 0; i < numBlocks; i++) {
//                    System.out.println("Fixed_" + i + " = " + solution.get(variableSpace.getFixedVariables().get(i)));
//                }
//                System.out.println();
                // Building state
                BWStateBuilder<Integer> builder = BWStateBuilder.makeBuilder(numBlocks);
                for (int b = 0; b < numBlocks; b++) {
                    Variable onB = variableSpace.getOnVariables().get(b); // get instance of Variable for " on_b "
                    int under = (int) solution.get(onB);
                    if (under >= 0) { // if the value is a block ( as opposed to a stack )
                        builder.setOn(b, under);
                    }
                }
                // Displaying
                BWIntegerGUI gui = new BWIntegerGUI(numBlocks);
                JFrame frame = new JFrame("Monde de " + numBlocks + " blocs" + " et " + numPiles + " piles avec " + s.getClass().getSimpleName());
                frame.add(gui.getComponent(builder.getState()));
                frame.pack();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            } else {
                System.out.println("No solution found");
            }
        }

//        if(solutions.size() >=2){
//            Map<Variable, Object> initialSolution = solutions.get(0);
//            Map<Variable, Object> solution = solutions.get(1);
//
//            Planner planner = new AStarPlanner(initialSolution, actionSpace.getActions(), new BasicGoal(solution), new MissingAndWrongVariablesHeuristic(new BasicGoal(solution)));
//
//            System.out.println("Begin The search with " + planner.getClass().getSimpleName());
//            List<Action> actions = planner.plan();
//
//            if(actions != null) {
//                System.out.println("Plan found");
//                BWStateBuilder<Integer> builder = BWStateBuilder.makeBuilder(numBlocks);
//                for (int b = 0; b < numBlocks; b++) {
//                    Variable onB = variableSpace.getOnVariables().get(b); // get instance of Variable for " on_b "
//                    int under = (int) solution.get(onB);
//                    if (under >= 0) { // if the value is a block ( as opposed to a stack )
//                        builder.setOn(b, under);
//                    }
//                }
//                BWIntegerGUI gui = new BWIntegerGUI(numBlocks);
//                JFrame frame = new JFrame("Plan d'actions");
//                BWComponent<Integer> component = gui.getComponent(builder.getState());
//                frame.add(component);
//                frame.pack();
//                frame.setVisible(true);
//                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//                for(Action a : actions){
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    initialSolution = a.successor(initialSolution);
//                    builder = BWStateBuilder.makeBuilder(numBlocks);
//                    for (int b = 0; b < numBlocks; b++) {
//                        Variable onB = variableSpace.getOnVariables().get(b); // get instance of Variable for " on_b "
//                        int under = (int) solution.get(onB);
//                        if (under >= 0) { // if the value is a block ( as opposed to a stack )
//                            builder.setOn(b, under);
//                        }
//                    }
//                    component.setState(builder.getState());
//                }
//
//            }else{
//                System.out.println("No plan found");
//            }
//
//        }


    }




}

