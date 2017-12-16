package homework2;

public class Filter<T> implements Simulatable<T>{

	private T label;
	
	public Filter(BipartiteGraph<T> graph, T filterLabel) {
		graph.addBlackNode(filterLabel);
		label = filterLabel;
		
	}
	
	public void simulate(BipartiteGraph<T> graph) {
		
	}
}
