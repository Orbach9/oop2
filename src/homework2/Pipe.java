package homework2;

public class Pipe<T> implements Simulatable<T>{
	
	private T label;
	
	public Pipe(BipartiteGraph<T> graph, T pipeLabel) {
		graph.addBlackNode(pipeLabel);
		label = pipeLabel;
	}
	
	public void simulate(BipartiteGraph<T> graph) {
		return;
	}
}
