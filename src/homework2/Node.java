package homework2;
import java.util.*;

/**
 * This class implements a node object for graph.
 * Each node is point to a list of edges object and has a label.
 */

public class Node<T> {
    private T label;
    private List<Edge<T>> outgoingEdges;
    private List<Edge<T>> incomingEdges;
    /**
     * @modifies this
     * @effects Constructs a new node.
     */
    public Node (T label) {
        this.label = label;
        this.outgoingEdges = new LinkedList<Edge<T>>();
        this.incomingEdges = new LinkedList<Edge<T>>();
    }

    /**
     * @return the label of this
     */
    public T getLabel() {
        return label;
    }

    /**
     * @return the list of the Incoming edges of the node
     */
    public List<Edge<T>> getIncomingEdges() {
        return incomingEdges;
    }

    /**
     * @return the list of the edges leaving the node
     */
    public List<Edge<T>> getOutgoingEdges() {
        return outgoingEdges;
    }

    /**
     * @requires label != null
     * @modifies this
     * @effects set this label
     */
    public void setLabel(T label) {
        this.label = label;
    }

    /**
     * @requires newEdge != null
     * @modifies this
     * @effects add new edge leaving the node
     */
    public void addOutgoingEdge(Edge<T> newEdge){
        for (int i=0;i<outgoingEdges.size();i++) {
        	if(outgoingEdges.get(i).getLabel().equals(newEdge.getLabel())) {
        		System.out.println("Outgoing edge with lable: "+newEdge.getLabel()+"Already exist");
        		return;
        	}        		
        }
    	this.outgoingEdges.add(newEdge);
    }

    /**
     * @requires newEdge != null
     * @modifies this
     * @effects add new edge incoming edge to the node
     */
    public void addIncomingEdge(Edge<T> newEdge){
    	for (int i=0;i<incomingEdges.size();i++) {
        	if(incomingEdges.get(i).getLabel().equals(newEdge.getLabel())) {
        		System.out.println("Incoming edge with lable: "+newEdge.getLabel()+"Already exist");
        		return;
        	}        		
        }
    	this.incomingEdges.add(newEdge);
    }

}
