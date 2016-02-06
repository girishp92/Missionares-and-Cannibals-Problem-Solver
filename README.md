# Missionares-and-Cannibals-Problem-Solver
Using the concepts of Artificial Intelligence. Solving the Missionares and Cannibals puzzle using DFS, BFS, Depth limited.

No of files : 5

1 Breathfirstsearch.java



import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;

import javax.swing.JTextArea;


public class Breathfirstsearch
{
	//this is the implementation of Breathfirstsearch algorithm//

	protected String label;                  
	protected Object state;                  
	protected Object oper;                   
	protected Vector links;                  
	protected int depth;                     
	protected boolean expanded;              
	protected boolean tested;                
	protected float cost = 0;                
	private static JTextArea traceTextArea;  
	public static final int FRONT = 0;
	public static final int BACK = 1;
	public static final int INSERT = 2;

	public boolean leaf()
	{
		return (links.size() == 0);
	}

	public void setDepth(int depth)
	{
		this.depth = depth;
	}

	public void setOperator(Object oper)
	{
		this.oper = oper;
	}

	public void setExpanded()
	{
		expanded = true;
	}

	public void setExpanded(boolean state)
	{
		expanded = state;
	}

	public boolean isExpanded()
	{
		return expanded;
	}

	public void setTested()
	{
		tested = true;
	}

	public void setTested(boolean state)
	{
		tested = state;
	}

	public boolean isTested()
	{
		return tested;
	}

	static public void setDisplay(JTextArea textArea)
	{
		traceTextArea = textArea;
	}

	public Object getState()
	{
		return state;
	}

	public void reset()
	{
		depth = 0;
		expanded = false;
		tested = false;
	}

	public void trace()
	{
		String indent = new String();

		for (int i = 0; i < depth; i++)
		{
			indent += "  ";
		}
		traceTextArea.append(indent + "Searching " + depth + ": " + label + " with state = " + state + "\n");
	}

	//expanding the tree//
	public void expand(Vector queue, int position)
	{
		setExpanded();
		for (int j = 0; j < links.size(); j++)
		{
			Breathfirstsearch nextNode = null;
			if (!nextNode.tested)
			{
				//incrementing the depth of the tree after every sucessuful checking of the states//
				nextNode.setTested(true);
				nextNode.setDepth(depth + 1);
				switch (position)
				{
				case FRONT :
					queue.insertElementAt(nextNode, 0);
					break;
				case BACK :
					queue.addElement(nextNode);
					break;
				case INSERT :
					boolean inserted = false;
					float nextCost = nextNode.cost;

					for (int k = 0; k < queue.size(); k++)
					{
						
					}
					if (!inserted)
					{
						queue.addElement(nextNode);
					}
					break;
				}
			}
		}
	}

	//here is the main implementation of the BFS Algorithm//
	public State execBFS(State initialState)
	{
		if (initialState.isGoal()) {
			return initialState;
		}
		Queue<State> searchlist = new LinkedList<State>();	// FIFO queue
		Set<State> visited = new HashSet<State>();
		searchlist.add(initialState);
		while (true) 
		{
			if (searchlist.isEmpty()) 
			{
				return null;	// failure
			}
			State state = searchlist.remove();
			visited.add(state);
			List<State> successors = state.generateSuccessors();
			for (State child : successors) 
			{
				if (!visited.contains(child) || !searchlist.contains(child)) 
				{
					if (child.isGoal()) 
					{
						return child;
					}
					searchlist.add(child);
				}
			}
		}
	}
}






2 Depthfirstsearch.java



import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Scanner;
import java.util.Set;


//this is the implementation of Depth first search algorithm//
public class Depthfirstsearch {

	
	public State execDFS(State initialState)
	{		//Checks for initial state being the goal state or not, 
			//if yes then it will return the initial state and print solution//
			if (initialState.isGoal()) {
				return initialState;
			}
			//Use of stack for the depth first search// 
			Stack<State> searchlist = new Stack<State>();
			Set<State> visited = new HashSet<State>();
			
			//adding the initial state to the stack//
			searchlist.add(initialState);
			while (true) 
			{
				if (searchlist.isEmpty()) 
				{
					return null;	// failure
				}
				State state = searchlist.pop();
				visited.add(state);
				
				//new sucessor state is generated//
				List<State> successors = state.generateSuccessors();
				for (State child : successors) 
				{
					if (!visited.contains(child) || !searchlist.contains(child)) 
					{
						if (child.isGoal()) 
						{
							return child;
						}
						//adding new child state to the searchlist stack//
						searchlist.add(child);
					}
				}
			}
		}
	}


3. Depthlimitedsearch.java


import java.util.List;


public class Depthlimitedsearch
{
	//Recursive Depth limited Search algorithm//
	public State execDLS(State initialState)
	{
		//Initially setting the limit of depth for the tree//
		int limit = 20;
		return recursiveDLS(initialState, limit);
	}

	private State recursiveDLS(State state, int limit)
	{
		//Initially checks if initial state is goal, if not then checks the limit is zero,
		//if yes then,returns null value.
		//else new state is generated with calling the generatesucessor method.
		if (state.isGoal())
		{
			return state;
		}
		else if (limit == 0)
		{
			return null;
		}
		else {
			List<State> successors = state.generateSuccessors();
			for (State child : successors)
			{
				//again calling the recursive method using enchanced for loop//
				State result = recursiveDLS(child, limit - 1);
				if (null != result)
				{
					return result;
				}
			}
			return null;
		}
	}
}


4. main.java


import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class main {
	
	//this is the main method in the Missionaries and cannibals problem using various Search Strategies//

	public static void main(String[] args) {
		System.out.println("----- Missionaries and Cannibals Problem -----");
		System.out.println("Choose the search method: ");
		System.out.println("\t 1. Breadth-first search algorithm");
		System.out.println("\t 2. Depth-limited search algorithm");
		System.out.println("\t 3. Depth-First search algorithm");
		System.out.println("\t 4. Iterative Deepening search algorithm");
		System.out.print("\nType your choice and press ENTER: ");

		
		Scanner in = new Scanner(System.in);
		int casein = in.nextInt();
		State initialState = new State (3, 3, Position.LEFT, 0, 0);
		switch(casein)
		{
		case 1:
			BFS(initialState);
			break;
		case 2:
			DLS(initialState);
			break;
		case 3: 
			DFS(initialState);
			break;
		case 4:
			break;
		default:
			System.out.println("Invalid - Please select correct option");
		}
	}
	
	//Calling the BFS method class by creating an object of it//
	private static void BFS(State initialState)
	{ 
		Breathfirstsearch s1 = null;
		
		if(s1 == null)
		{
			s1 = new Breathfirstsearch();	
		}
		State solution = s1.execBFS(initialState);
		printSolution(solution);
	}
	//Calling the DLS method class by creating an object of it//
	private static void DLS(State initialState)
	{
		Depthlimitedsearch s2 = null; 
		
		if(s2 == null)
		{
			s2 = new Depthlimitedsearch();
		}
		State solution = s2.execDLS(initialState);
		printSolution(solution);
	}
	//Calling the DFS method class by creating an object of it//
	private static void DFS(State initialState)
	{
		Depthfirstsearch d1 = null;
		
		if(d1==null)
		{
			d1=new Depthfirstsearch();
		}
		
		State solution = d1.execDFS(initialState);
		printSolution(solution);
	}

	//Printing the solutions//
	private static void printSolution(State solution) 
	{ 
		if (null == solution)
		{
			System.out.print("\n No solution found.");
		}
		else
		{
			List<State> path = new ArrayList<State>();
			State state = solution;
			while(null!=state)
			{
				path.add(state);
				state = state.getParentState();
			}

			int depth = path.size() - 1;
			for (int i = depth; i >= 0; i--)
			{
				state = path.get(i);
				if (state.isGoal())
				{
					System.out.print("\n" + state.toString());
				}
				else
				{
					System.out.print("\n" + state.toString());
				}
			}
			System.out.println();
			System.out.println("\nSolution found with depth: "+ depth);
		}
	}
}


5. state.java


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

