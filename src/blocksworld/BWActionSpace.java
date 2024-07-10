package blocksworld;

import planning.Action;
import planning.BasicAction;
import representation.Variable;

import java.util.*;

/**
 * <b>
 * Class allowing to obtain all actions of the blocksworld state
 * </b>
 *
 * <p>
 * This class is used to obtain all actions of the blocksworld state. <br>
 * An action is a movement of a block from a pile to another pile
 * provided that the block is movable and that the destination is free. <br>
 * </p>
 *
 * <p>
 * We have 4 types of actions : <br>
 * </p>
 * <ul>
 *     <li>
 *         Move a block b from the top of the block b' to the top of the block b''
 *     </li>
 *     <li>
 *         Move a block b from the top of the block b' to a free pile p
 *     </li>
 *     <li>
 *         Move a block b from the bottom of the pile p to the top of the block b'
 *     </li>
 *     <li>
 *         Move a block b from the bottom of the pile p to a free pile p'
 *     </li>
 * </ul>
 *
 * <p>
 *     All actions is a Set of BasicAction. <br>
 * </p>
 *
 * @author 22013393
 * @version 1.0
 */
public class BWActionSpace {

    /**
     * The number of blocks of the blocksworld
     */
    private final int nbBlocks;

    /**
     * The number of piles of the blocksworld
     */
    private final int nbPiles;


    /**
     * Set of actions that permits to move a block from the top of the block b' to the top of the block b''
     */
    private Set<Action> moveBlockFromTopToTop;

    /**
     * Set of actions that permits to move a block from the top of the block b' to a free pile p
     */
    private Set<Action> moveBlockFromTopToFreePile;

    /**
     * Set of actions that permits to move a block from the bottom of the pile p to the top of the block b'
     */
    private Set<Action> moveBlockFromBottomToTop;

    /**
     * Set of actions that permits to move a block from the bottom of the pile p to a free pile p'
     */
    private Set<Action> moveBlockFromBottomToFreePile;

    /**
     * Set of all actions
     */
    private Set<Action> actions;


    /**
     * Blocksworld variable space
     */
    private BWVariableSpace variableSpace;

    /**
     * Constructor of the class BWActionSpace
     *
     * @param nbBlocks The number of blocks of the blocksworld
     * @param nbPiles  The number of piles of the blocksworld
     */
    public BWActionSpace(int nbBlocks, int nbPiles) {
        this.nbBlocks = nbBlocks;
        this.nbPiles = nbPiles;
        this.variableSpace = new BWVariableSpace(nbBlocks, nbPiles);
    }

    /**
     * Returns the number of blocks of the blocksworld
     *
     * @return The number of blocks of the blocksworld
     */
    public int getNbBlocks() {
        return this.nbBlocks;
    }

    /**
     * Returns the number of piles of the blocksworld
     *
     * @return The number of piles of the blocksworld
     */
    public int getNbPiles() {
        return this.nbPiles;
    }


    /**
     * Create action that move a block b from the top of a block b′ to the top of a block b′′
     *
     * @param b  The block to move
     * @param b1 The block on which the block b is placed
     * @param b2 The block on which the block b will be placed
     * @return The action that move a block b from the top of a block b′ to the top of a block b′′
     */
    private Action createMoveBlockToBlock(int b, int b1, int b2) {

        // The preconditions of the action
        Map<Variable, Object> preconditions = new HashMap<>();

        // The bloc b is not fixed
        preconditions.put(this.variableSpace.getFixed(b), false);

        // The bloc b is on the bloc b1
        preconditions.put(this.variableSpace.getOn(b), b1);

        // The bloc b2 is not fixed
        preconditions.put(this.variableSpace.getFixed(b2), false);

        // The effect of the action
        Map<Variable, Object> effects = new HashMap<>();

        // The bloc b is on the bloc b2
        effects.put(this.variableSpace.getOn(b), b2);

        // The bloc b is not on the bloc b1 so the bloc b1 is not fixed
        effects.put(this.variableSpace.getFixed(b1), false);

        // The bloc b2 is fixed because the bloc b is on it
        effects.put(this.variableSpace.getFixed(b2), true);

        // The cost of the action is 1 because we have the number of blocks that can be moved after the action is the same
        return new BasicAction(preconditions, effects, 1);
    }

    /**
     * Create action that move a block b from above a block b′ to an empty stack p
     *
     * @param b  The block to move
     * @param b1 The block on which the block b is placed
     * @param p  The pile on which the block b will be placed
     * @return The action that move a block b from above a block b′ to an empty stack p
     */
    private Action createMoveBlockToPile(int b, int b1, int p) {

        // The preconditions of the action
        Map<Variable, Object> preconditions = new HashMap<>();

        // The bloc b is not fixed
        preconditions.put(this.variableSpace.getFixed(b), false);

        // The bloc b is on the bloc b1
        preconditions.put(this.variableSpace.getOn(b), b1);

        // The pile p is free
        preconditions.put(this.variableSpace.getFree(p), true);

        // The effect of the action
        Map<Variable, Object> effects = new HashMap<>();

        // The bloc b is on the pile p
        effects.put(this.variableSpace.getOn(b), (-(p + 1)));

        // The bloc b is not on the bloc b1 so the bloc b1 is not fixed
        effects.put(this.variableSpace.getFixed(b1), false);

        // The pile p is not free
        effects.put(this.variableSpace.getFree(p), false);

        // The cost of the action is 0 because we increase the number of blocks that can be moved after the action
        return new BasicAction(preconditions, effects, 0);
    }

    /**
     * Create action that move a block b from the bottom of a stack p to the top of a block b'
     *
     * @param b  The block to move
     * @param p  The pile on which the block b is placed
     * @param b1 The block on which the block b will be placed
     * @return The action that move a block b from the bottom of a stack p to the top of a block b'
     */
    private Action createMovePileToBlock(int b, int p, int b1) {

        // The preconditions of the action
        Map<Variable, Object> preconditions = new HashMap<>();

        // The bloc b is not fixed
        preconditions.put(this.variableSpace.getFixed(b), false);

        // The bloc b is on the pile p
        preconditions.put(this.variableSpace.getOn(b), (-(p + 1)));

        // The bloc b1 is not fixed
        preconditions.put(this.variableSpace.getFixed(b1), false);

        // The effect of the action
        Map<Variable, Object> effects = new HashMap<>();

        // The bloc b is on the bloc b1
        effects.put(this.variableSpace.getOn(b), b1);

        // The bloc b1 is fixed because the bloc b is on it
        effects.put(this.variableSpace.getFixed(b1), true);

        // The pile p is free
        effects.put(this.variableSpace.getFree(p), true);

        // The cost of the action is 2 because it reduces the number of block that can be moved to another block after the action
        return new BasicAction(preconditions, effects, 2);
    }

    /**
     * Create action that move a block b from the bottom of a stack p to an empty stack p'
     *
     * @param b  The block to move
     * @param p  The pile on which the block b is placed
     * @param p1 The pile on which the block b will be placed
     * @return The action that move a block b from the bottom of a stack p to an empty stack p'
     */
    private Action createMovePileToPile(int b, int p, int p1) {

        // The preconditions of the action
        Map<Variable, Object> preconditions = new HashMap<>();

        // The bloc b is not fixed
        preconditions.put(this.variableSpace.getFixed(b), false);

        // The bloc b is on the pile p
        preconditions.put(this.variableSpace.getOn(b), (-(p + 1)));

        // The pile p1 is free
        preconditions.put(this.variableSpace.getFree(p1), true);

        // The effect of the action
        Map<Variable, Object> effects = new HashMap<>();

        // The bloc b is on the pile p1
        effects.put(this.variableSpace.getOn(b), (-(p1 + 1)));

        // The pile p is free
        effects.put(this.variableSpace.getFree(p), true);

        // The pile p1 is not free
        effects.put(this.variableSpace.getFree(p1), false);

        // The action that move a block b from the bottom of a stack p to an empty stack p'
        // The cost of the action is 3 because the actions is unproductive
        return new BasicAction(preconditions, effects, 3);
    }

    public Set<Action> getMoveBlockFromTopToTop() {
        // If the action is not already created
        if (this.moveBlockFromTopToTop == null) {
            // Create the action
            this.moveBlockFromTopToTop = new HashSet<>();
            for (int b = 0; b < this.nbBlocks; b++) {
                for (int b1 = 0; b1 < this.nbBlocks; b1++) {
                    if(b != b1) {
                        for (int b2 = 0; b2 < this.nbBlocks; b2++) {
                            if(b2 != b1) {
                                this.moveBlockFromTopToTop.add(this.createMoveBlockToBlock(b, b1, b2));
                            }
                        }
                    }
                }
            }
        }
        return this.moveBlockFromTopToTop;
    }

    public Set<Action> getMoveBlockFromTopToFreePile() {
        // If the action is not already created
if (this.moveBlockFromTopToFreePile == null) {
            // Create the action
            this.moveBlockFromTopToFreePile = new HashSet<>();
            for (int b = 0; b < this.nbBlocks; b++) {
                for (int b1 = 0; b1 < this.nbBlocks; b1++) {
                    if(b != b1) {
                        for (int p = 0; p < this.nbPiles; p++) {
                            this.moveBlockFromTopToFreePile.add(this.createMoveBlockToPile(b, b1, p));
                        }
                    }
                }
            }
        }
        return this.moveBlockFromTopToFreePile;
    }

    public Set<Action> getMoveBlockFromBottomToTop() {
        // If the action is not already created
        if (this.moveBlockFromBottomToTop == null) {
            // Create the action
            this.moveBlockFromBottomToTop = new HashSet<>();
            for (int b = 0; b < this.nbBlocks; b++) {
                for (int p = 0; p < this.nbPiles; p++) {
                    for (int b1 = 0; b1 < this.nbBlocks; b1++) {
                        if(b != b1) {
                            this.moveBlockFromBottomToTop.add(this.createMovePileToBlock(b, p, b1));
                        }
                    }
                }
            }
        }
        return this.moveBlockFromBottomToTop;
    }

    public Set<Action> getMoveBlockFromBottomToFreePile() {
        // If the action is not already created
        if (this.moveBlockFromBottomToFreePile == null) {
            // Create the action
            this.moveBlockFromBottomToFreePile = new HashSet<>();
            for (int b = 0; b < this.nbBlocks; b++) {
                for (int p = 0; p < this.nbPiles; p++) {
                    for (int p1 = 0; p1 < this.nbPiles; p1++) {
                        if(p != p1) {
                            this.moveBlockFromBottomToFreePile.add(this.createMovePileToPile(b, p, p1));
                        }
                    }
                }
            }
        }
        return this.moveBlockFromBottomToFreePile;
    }

    public Set<Action> getActions() {
        // If the actions are not already created
        if (this.actions == null) {
            // Create the actions
            this.actions = new HashSet<>();
            this.actions.addAll(this.getMoveBlockFromTopToTop());
            this.actions.addAll(this.getMoveBlockFromTopToFreePile());
            this.actions.addAll(this.getMoveBlockFromBottomToTop());
            this.actions.addAll(this.getMoveBlockFromBottomToFreePile());
        }
        return this.actions;
    }

    public BWVariableSpace getVariableSpace() {
        return variableSpace;
    }

    /**
     * Returns a list of all the possible actions that can be applied to the given state
     *
     * @param state The current state
     *
     * @return A list of all the possible actions that can be applied to the given state
     */
    public List<Action> getApplicableActions(Map<Variable, Object> state) {
        List<Action> applicableActions = new ArrayList<>();
        for(Map.Entry<Integer, Variable> entry : this.variableSpace.getFixedVariables().entrySet()) {
            if(state.get(entry.getValue()).equals(false)) {
                applicableActions.addAll(this.getMoveBlockFromTopToTop());
                applicableActions.addAll(this.getMoveBlockFromTopToFreePile());
            }
        }
        return applicableActions;

    }
}
