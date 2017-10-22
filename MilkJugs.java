/**
 * A milkman carries a full 12 liter container of milk.
 * He needs to deliver exactly 6 liters to a customer
 * who only has 8-liter and a 5-liter container.
 *
 * Can he do this? No milk should be wasted
 */

import java.util.ArrayList;

public class MilkJugs
{
	public ArrayList<Node> frontier;
	public ArrayList<int[]> explored;

    // X is 12 liter container
    // Y is 8 liter container
    // Z is 5-liter container
	public Node solve()
	{
		// Initial problem state
		int[] initial_state = {12, 0, 0};

		// Current node under consideration
		Node curr_node = new Node(initial_state, null, null);

		// List of frontier nodes at each iteration
		// Manipulated as a queue
		this.frontier = new ArrayList<Node>();


		// List of nodes already explored
		// They are excluded from expanded frontier nodes at each iteration
		this.explored = new ArrayList<int[]>();

		frontier.add(curr_node);

		while (true) {
			// If we have exhausted our frontier 
			// we have no solution
			if (frontier.size() == 0) {
				return null;
			}
            
            // Pop new node from frontier to consider
			curr_node = frontier.remove(0);
            
		    // Add chosen node to explored list if not already there
			if(!this.inExplored(curr_node.state)) {
				explored.add(curr_node.state);				
			}

            // List of actions that can be applied based on the state
            // and the description of the problem
			Action[] actions = this.getFeasibleActions(curr_node.state);

            // Apply our transition model to generate child nodes 
            // from current node
			for (Action action : actions) {
				Node child = this.getChildNode(curr_node, action);		
				
				if (!this.inFrontier(child) && !this.inExplored(child.state)) {
					if (this.isGoal(child.state)) {
						return child;
					}
					frontier.add(child);
				}
			}
		}
	}

	/**
     * Get the list of actions that should be applied 
     * when given a state
     * 
     * @param  state A problem state
     * @return actions Array of actions 
     */
	public Action[] getFeasibleActions(int[] state)
	{
		ArrayList<Action> actionsList = new ArrayList<Action>();
		String[] labels = {'x', 'y', 'z'};
		int[] max = {12, 8, 5};

		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state.length; j++) {
				if (i != j) {
					int needed = max[j] - state[j];

					if ((needed > 0) && (state[i] > 0)) {
						actionsList.add(new Action(labels[i], labels[j], Math.min(needed, state[i])));
					}
				}
			}
		}

        Action[] actionsArray = new Action[actionsList.size()];
		return actionsList.toArray(actionsArray);
	}

	 /**
     * Get a child node when given a node and action
     * 
     * @param  node  The node which becomes the parent
     * @param  action The action to apply
     * @return  child Child node
     */
	public Node getChildNode(Node node, Action action) {
		int[] new_state = new int[3];
		String[] labels = {'x', 'y', 'z'};

        for (int i = 0; i < new_state.length; i++) {
        	new_state[i] = node.state[i];
        }

		if (action != null) {
			// Implementing the transition model		
			for (int i = 0;i < labels.length; i++) {
				if (action.from == labels[i]) {
					new_state[i] -= action.quantity;
				}

				if (action.to == labels[i]) {
					new_state[i] += action.quantity;
				}
			}
		}

		Node new_node = new Node(new_state, action, node);

		return new_node;
	}

	/**
     * Check whether a node is in frontier
     * 
     * @param  node The node
     * @return  Truth value of test
     */
	public boolean inFrontier(Node node)
	{
		for (Node curr : this.frontier) {
			if ((curr.state[0] == node.state[0]) && (curr.state[1] == node.state[1])) {
				return true;
			}
		}

		return false;
	}

    /**
     * Check whether a state is in explored state
     * 
     * @param  state State to consider
     * @return  Truth value of test
     */
	public boolean inExplored(int[] state)
	{
		for (int i = 0; i < this.explored.size(); i++) {
			int[] curr = this.explored.get(i);

			if ((curr[0] == state[0]) && (curr[1] == state[1])) {
				return true;
			}
		}

		return false;
	}
}
