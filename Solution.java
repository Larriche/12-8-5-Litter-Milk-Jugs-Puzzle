public class Solution
{
	public static void main(String[] args)
	{
		WaterJugs jugs = new WaterJugs();
		Node solution_node = jugs.solve();

		if (solution_node == null) {
			System.out.println("No solution found");
		} else {
			jugs.printSolution(solution_node);
		}
	}
}