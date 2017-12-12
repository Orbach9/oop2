package homework2;
import com.sun.deploy.util.BlackList;

import java.util.*;

/**
 * This class implements a bipartite graph.
 * Each graph has a name and contain list of all his nodes.
 */

public class BipartiteGraph<T> {

    private String graphName;
    private List<Node<T>> blackNodes;
    private List<Node<T>> whiteNodes;

    /**
     * @requires graphName != null
     * @modifies this
     * @effects Constructs a new graph.
     */
    public BipartiteGraph(String graphName){
        this.graphName = graphName;
        this.blackNodes = new ArrayList<Node<T>>();
        this.whiteNodes = new ArrayList<Node<T>>();
    }

    /**
     * @return the black nodes list.
     */
    public List<Node<T>> getBlackNodes(){
        return blackNodes;
    }

    /**
     * @return the white nodes list.
     */
    public List<Node<T>> getWhiteNodes(){
        return whiteNodes;
    }
    
    /**
     * @requires nodes nodeLable!=null, addBlackNode(nodeLable) and 
     * addWhiteNode(nodeLable) have'nt been called before
     * @return true if succeeded, false if failed
     */    
    public boolean addBlackNode(T nodeLable) {
    	Node newNode = new Node(nodeLable);
    	if(blackNodes.contains(nodeLable) || whiteNodes.contains(nodeLable)) {
    		System.out.println("Node label " + nodeLable+" Already exists");
    		return false;
    	}
    	blackNodes.add(newNode);
    	return true;
    }
    
    /**
     * @requires nodes nodeLable!=null, addBlackNode(nodeLable) and 
     * addWhiteNode(nodeLable) have'nt been called before
     * @return true if succeeded, false if failed
     */
    public boolean addWhiteNode(T nodeLable) {
    	Node newNode = new Node(nodeLable);
    	if(blackNodes.contains(nodeLable) || whiteNodes.contains(nodeLable)) {
    		System.out.println("Node label " + nodeLable+" Already exists");
    		return false;
    	}
    	whiteNodes.add(newNode);
    	return true;
    }

    /**
     * @requires nodes != null, label != null
     * @return the node with the label- label, null in case there is
     * no such node.
     */
    public Node<T> containsNode(List<Node<T>> nodes, T label) {
        for (Node node : nodes) {
            if (node.getLabel() == label) {
                return node;
            }
        }
        return null;
    }

    // TODO: add check of the requires in check rep or check in the func- check if write  to black
    /**
     * @requires (blackNodes.contain(parentName) && whiteNode.contain(childName)) ||
     *           (whiteNode.contain(parentName) && blackNodes.contain(childName))
     *           && edgeLabel != null
     *           && node named parentName has no other outgoing edge labeled
     * 				edgeLabel
     *           && node named childName has no other incoming edge labeled
     * 				edgeLabel
     * @modifies this
     * @effects Adds an edge from the node parentName to the node childName
     * 			in the graph. The new edge's label is the String
     * 			edgeLabel.
     */
    public void addEdge(T parentName, T childName,T edgeLabel){

        Node parentNode, childNode;
        Edge newEdge;
        boolean isParentWhite = true;
        // check for requires - label edge not null
        if (edgeLabel == null)
            return;

        if(containsNode(blackNodes, parentName) == null) {
        	parentNode = containsNode(whiteNodes, parentName);
        	if (parentNode == null) {
        		System.out.println("Parent node doesn't exist");
        		return;
        	}
        	childNode = containsNode(blackNodes, childName);
        	if (childNode == null) {
        		System.out.println("Elegal Parameters");
        		return;
        	}
        } else {
        	isParentWhite = false;
        	parentNode = containsNode(blackNodes, parentName); 
        	childNode = containsNode(whiteNodes,childName);
        	if (childNode == null) {
        		System.out.println("Elegal Parameters");
        		return;
        	}
        }         
        boolean isLegal = childNode.checkIfLegalEdge(edgeLabel,false/*not an outgoing edge*/);
        if (isLegal == false) {
        	System.out.println("child already has an incoming edge labled" + edgeLabel);
        	return;
        }
        isLegal = parentNode.checkIfLegalEdge(edgeLabel,true/*outgoing edge*/);       
        if (isLegal == false) {
        	System.out.println("parent already has an outgoing edge labled" + edgeLabel);
        	return;
        }
        newEdge = new Edge(edgeLabel, parentNode, childNode);
        parentNode.addOutgoingEdge(newEdge);
        childNode.addIncomingEdge(newEdge);

    }

	/**
	 * @requires parentName != NULL
	 * @return list of child nodes of the parent
	 */
	public List<Node<T>> GetChildList (T parentName) {

		List <Node<T>> childList = new LinkedList<Node<T>>();
		List <Edge<T>> outgoingEdge = new LinkedList<Edge<T>>();

		if (parentName == null)
			return null;

		Node parent = containsNode(blackNodes, parentName) == null ?
				      containsNode(whiteNodes, parentName) : containsNode(blackNodes, parentName);
		if (parent == null)
			return null;

		outgoingEdge = parent.getOutgoingEdges();
		for (int i =0; i < outgoingEdge.size(); i++){
			Edge tmpEdge = outgoingEdge.get(i);
			childList.add(tmpEdge.getEndNode());
		}

		return childList;
	}

	/**
	 * @requires childNode != NULL
	 * @return list of parent nodes of the parent
	 */
	public List<Node<T>> GetParentList (T childName) {

		List <Node<T>> parentList = new LinkedList<Node<T>>();
		List <Edge<T>> incomingEdge = new LinkedList<Edge<T>>();

		if (childName == null)
			return null;

		Node child = containsNode(blackNodes, childName) == null ?
					 containsNode(whiteNodes, childName) : containsNode(blackNodes, childName);
		if (child == null)
			return null;

		incomingEdge = child.getIncomingEdges();
		for (int i =0; i < incomingEdge.size(); i++){
			Edge tmpEdge = incomingEdge.get(i);
			parentList.add(tmpEdge.getStartNode());
		}

		return parentList;
	}

	/**
	 * @requires node labled parentName has an outgoing edge labeled edgeLabel,
	 *  parent node labeled parentName exists
	 * @return child node of parent node labeled parentName with edge labeled edgeLabel between them
	 */
	public Node<T> GetChildByEdgeLabel (T parentName , T edgeLabel) {
		Node parentNode = containsNode(whiteNodes,parentName);
		if (parentNode == null) {
			parentNode = containsNode(blackNodes,parentName);
			if (parentNode  == null) {
				System.out.println("Parent doesn't exist");
				return null;
			}
		}
		
		if(!parentNode.getOutgoingEdges().contains(edgeLabel)) {
			System.out.println("Parent doesn't have an outgoing edge labeled: "+edgeLabel);
			return null;
		}
		Edge edge = null;
		for (int i = 0; i<parentNode.getOutgoingEdges().size();i++) {
			Edge cur = (Edge)parentNode.getOutgoingEdges().get(i);
			if(cur.getLabel().equals(edgeLabel)) {
				edge = cur;
			}
		}
		if (edge == null) {
			System.out.println("Node labeled: "+parentName + "doesn't have an edge labeled: " + edgeLabel);
			return null;
		}
		return edge.getEndNode();		
	}
	
	/**
	 * @requires node labeled childName has an incoming edge labeled edgeLabel,
	 *  node labeled childName exists
	 * @return parent node of child node labeled childName with edge labeled edgeLabel between them
	 */
	public Node<T> GetParentByEdgeLabel (T childName , T edgeLabel) {
		Node childNode = containsNode(whiteNodes,childName);
		if (childNode == null) {
			childNode = containsNode(blackNodes,childName);
			if (childNode  == null) {
				System.out.println("Child doesn't exist");
				return null;
			}
		}
		
		if(!childNode.getIncomingEdges().contains(edgeLabel)) {
			System.out.println("Child doesn't have an incoming edge labeled: "+edgeLabel);
			return null;
		}
		Edge edge = null;
		for (int i = 0; i<childNode.getIncomingEdges().size();i++) {
			Edge cur = (Edge)childNode.getIncomingEdges().get(i);
			if(cur.getLabel().equals(edgeLabel)) {
				edge = cur;
			}
		}
		if (edge == null) {
			System.out.println("Node labeled: "+childName + "doesn't have an edge labeled: " + edgeLabel);
			return null;
		}
		return edge.getStartNode();		
	}
    
}
