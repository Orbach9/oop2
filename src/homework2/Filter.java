package homework2;

public class Filter<T> implements Simulatable<T>{

	private T label;
	private int maxStorageBuff;
	private Node<T> myNode;
	
	public Filter(BipartiteGraph<T> graph, T filterLabel,int maxStorageBuff) {
		if(graph.addWhiteNode(filterLabel) == false) {
			System.out.println("Illeagal filterLabel:" + filterLabel);
			return;
		}
		myNode = graph.containsNode(graph.getWhiteNodes(), filterLabel);
		this.label = filterLabel;
		this.maxStorageBuff = maxStorageBuff;
		
	}
	
	public void simulate(BipartiteGraph<T> graph) {
		Node myNode = graph.getWhiteNodes()
	}
}
