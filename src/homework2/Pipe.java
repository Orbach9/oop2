package homework2;
import java.util.*;

public class Pipe<T, R> implements Simulatable<T>{

	
	private T label;
	private int maxCapacity;
	private boolean hasCapacity;
	private Node<T> myNode;
	private List<R> opObjs;
	
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
		opObjs = new ArrayList<R>();
	}
	
	public void simulate(BipartiteGraph<T> graph) {
		
	}
}
