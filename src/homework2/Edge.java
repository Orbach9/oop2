package homework2;
import java.util.*;

/**
 * This class implements a edge object for graph.
 * Each edge has start node and end node and has a label.
 */

public class Edge<T> {

    private T label;
    private Node<T> startNode;
    private Node<T> endNode;

    /**
     * @modifies this
     * @effects Constructs a new edge.
     */
    public Edge (T label, Node startNode, Node endNode) {
        this.label = label;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    /**
     * @return this.label
     */
    public T getLabel() {
        return label;
    }

    /**
     * @return this.endNode
     */
    public Node<T> getEndNode() {
        return endNode;
    }

    /**
     * @return this.startNode
     */
    public Node<T> getStartNode() {
        return startNode;
    }

    /**
     * @modifies this
     * @effects set edge label.
     */
    public void setLabel(T label) {
        this.label = label;
    }

    /**
     * @modifies this
     * @effects set edge start node.
     */
    public void setStartNode(Node<T> startNode) {
        this.startNode = startNode;
    }

    /**
     * @modifies this
     * @effects Set edge end node.
     */
    public void setEndNode(Node<T> endNode) {
        this.endNode = endNode;
    }
}
