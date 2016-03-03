import java.util.ArrayList;
import java.util.List;


//use of enumator for positioning of the boat with values as RIGHT or LEFT//
enum Position {RIGHT, LEFT}

public class State
{
	private int canLeft;
	private int missLeft;
	private int canRight;
	private int missRight;
	private Position boat;

	private State parentState;

	public State(int canLeft, int missLeft, Position boat,
			int canRight, int missRight)
	{
		this.canLeft = canLeft;
		this.missLeft = missLeft;
		this.boat = boat;
		this.canRight = canRight;
		this.missRight = missRight;
	}

	//this small method is for checking whether the current state is a goal state or not by checking canLEFT & missLEFT == 0.//
	public boolean isGoal()
	{
		return canLeft == 0 && missLeft == 0;
	}
	//checking if the new state added each time is valid or not by passing conditions//
	//if the conditions are valid then, it is added to the searchlist stack, else return false or as invalid state//
	public boolean isValid()
	{
		if (missLeft >= 0 && missRight >= 0 && canLeft >= 0 && canRight >= 0
				&& (missLeft == 0 || missLeft >= canLeft)
				&& (missRight == 0 || missRight >= canRight)) {
			return true;
		}
		return false;
	}

	//this is the method for generating new states or successor states starting from the root node.//
	public List<State> generateSuccessors()
	{
		List<State> successors = new ArrayList<State>();
		
		//testing and adding successor states , missionaries and cannibals w.r.t to the position of the boat 
		//& how many are going to LEFT or else RIGHT position//
		if (boat == Position.LEFT)
		{
			testAndAdd(successors, new State(canLeft, missLeft - 2, Position.RIGHT,
					canRight, missRight + 2)); 
			testAndAdd(successors, new State(canLeft - 2, missLeft, Position.RIGHT,
					canRight + 2, missRight)); 
			testAndAdd(successors, new State(canLeft - 1, missLeft - 1, Position.RIGHT,
					canRight + 1, missRight + 1));
			testAndAdd(successors, new State(canLeft, missLeft - 1, Position.RIGHT,
					canRight, missRight + 1)); 
			testAndAdd(successors, new State(canLeft - 1, missLeft, Position.RIGHT,
					canRight + 1, missRight)); 
		}
		else
		{
			testAndAdd(successors, new State(canLeft, missLeft + 2, Position.LEFT,
					canRight, missRight - 2)); 
			testAndAdd(successors, new State(canLeft + 2, missLeft, Position.LEFT,
					canRight - 2, missRight)); 
			testAndAdd(successors, new State(canLeft + 1, missLeft + 1, Position.LEFT,
					canRight - 1, missRight - 1));
			testAndAdd(successors, new State(canLeft, missLeft + 1, Position.LEFT,
					canRight, missRight - 1)); 
			testAndAdd(successors, new State(canLeft + 1, missLeft, Position.LEFT,
					canRight - 1, missRight));
		}
		return successors;
	}
	
	//Isvalid method is called from the state class to check for validity of the state//
	private void testAndAdd(List<State> successors, State newState)
	{
		if (newState.isValid())
		{
			newState.setParentState(this);
			successors.add(newState);
		}
	}


	public State getParentState()
	{
		return parentState;
	}

	public void setParentState(State parentState)
	{
		this.parentState = parentState;
	}

	@Override
	public String toString()
	{
		//displaying the outputs of number of missionaries and cannibals on each side of the boat.// 
		if (boat == Position.LEFT)
		{
			return "(" + canLeft +"C"+ "," + missLeft +"M"+ ")" + "<---BOAT LEFT---<"
				+ "(" + canRight +"C"+ "," + missRight +"M"+ ")";
		}
		else
		{
			return "(" + canLeft +"C"+ "," + missLeft +"M"+ ")" + ">---Boat RIGHT--->"
					+ "(" + canRight +"C"+ "," + missRight +"M"+ ")";
		}
	}
}
