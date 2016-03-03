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
