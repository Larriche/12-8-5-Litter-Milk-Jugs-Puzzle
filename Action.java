public class Action
{
	// Source container for a pour action
	public String from;

    // Destination container for a pour action
	public String to;

    // Quantity of milk poured
	public int quantity;

    public Action(String from, String to, int quantity)
    {
    	this.from = from;
    	this.to = to;
    	this.quantity = quantity;
    }
}