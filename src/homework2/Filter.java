package homework2;

import java.util.*;

public class Filter<T, R> implements Simulatable<T>{


	private T label;
	private int maxStorageBuff;
	private Node<T> myNode;
	private List<R> opObjs;

	
	public Filter(BipartiteGraph<T> graph, T filterLabel,int maxStorageBuff) {
		if(graph.addWhiteNode(filterLabel) == false) {
			System.out.println("Illeagal filterLabel:" + filterLabel);
			return;
		}
		myNode = graph.containsNode(graph.getWhiteNodes(), filterLabel);
		this.label = filterLabel;
		this.maxStorageBuff = maxStorageBuff;
		opObjs = new ArrayList<R>();
		
	}
	
	
	public void simulate(BipartiteGraph<T> graph) {
		return;
	}
}

