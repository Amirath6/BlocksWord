/**
 * <b>
 * This package contains the classes that are used to solve planning problems.
 * </b>
 *
 * <p>
 * In this package, we can find the classes that are used to solve planning problems.<br>
 * For solving the problem, we need to represent actions that can be applied to a state,
 * and a goal that is a state that we want to reach. <br>
 * Solving a problem involves finding a sequence of actions that leads from the initial state. To do so,
 * from a predefined set of actions, the search of a solution is done by exploring the state space. Thus
 * we can make an analogy between the problem and a graph. Indeed, the nodes of the graph are the states
 * and the edges are the actions. So the problem is to find, in this graph, a path from the initial state
 * to the goal state. <br>
 * </p>
 *
 * <p>
 * So through this package, through a set of actions, an initial state and a goal state, we can represent
 * our problem as a graph and solve it by exploring the graph with different algorithms. <br>
 * Each algorithm has its own characteristics and its own advantages and disadvantages. We wrote
 * different algorithms in each different class.
 * </p>
 */
package planning;