package homework2;

public class Pipe<T> implements Simulatable<T>{
	
	private T label;
	private int maxCapacity;
	private boolean hasCapacity;
	private Node<T> myNode;
	
	public Pipe(BipartiteGraph<T> graph, T pipeLabel) {
		if(graph.addBlackNode(pipeLabel) == false) {
			System.out.println("illegal pipe label" + pipeLabel);
			return;
		}
		myNode = graph.containsNode(graph.getBlackNodes(), pipeLabel);
		this.label = pipeLabel;
		this.hasCapacity = false;
	}
	
	public Pipe(BipartiteGraph<T> graph, T pipeLabel,int maxCapacity) {
		graph.addBlackNode(pipeLabel);
		this.label = pipeLabel;
		this.maxCapacity = maxCapacity;
		this.hasCapacity = true;
	}
	
	public void simulate(BipartiteGraph<T> graph) {
		return;
	}
}
