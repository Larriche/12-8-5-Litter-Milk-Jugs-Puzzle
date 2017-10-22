/**
 * This represents a node in our problem formulation
 */
public class Node
{
	// State of this node
	// [ 12-liter jar, 8-liter jar, 5-liter jar]
	public int[] state;

    // Parent of this node
	public Node parent;

    // Action that generated this node
	public Action action;

    /**
     * Create a new node
     * 
     * @param  state  The state for this node
     * @param  action The action that generated this node
     * @param  parent The parent of this node
     * @return Node The new node
     */
	public Node(int[] state, Action action, Node parent)
	{
		this.state = state;
		this.action = action;
		this.parent = parent;
	}
}