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
     * 
     */    
    public void addBlackNode(T nodeLable) {
    	Node newNode = new Node(nodeLable);
    	blackNodes.add(newNode);
    }
    
    /**
     * @requires nodes nodeLable!=null, addBlackNode(nodeLable) and 
     * addWhiteNode(nodeLable) have'nt been called before
     * 
     */
    public void addWhiteNode(T nodeLable) {
    	Node newNode = new Node(nodeLable);
    	whiteNodes.add(newNode);
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
        	childNode = containsNode(blackNodes, childName);
        	if (childNode == null) {
        		System.out.println("parent and child are both white");
        		return;
        	}
        } else {
        	isParentWhite = false;
        	parentNode = containsNode(blackNodes, parentName); 
        	childNode = containsNode(whiteNodes,childName);
        	if (childNode == null) {
        		System.out.println("parent and child are both black");
        		return;
        	}
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






	// ********************************************************************************************************
    // TODO: Why do we need this? my func addEdge adds new edge only if its black to white/white to black
    
    /**
     * @requires blackNodes.contains(parentName) && whiteNodes.contains(childName)
     * @modifies this
     */    
    public void addEdgeBlackToWhite(T edgeLabel, T parentName, T childName) {
    	int parentIndex=0;
    	int childIndex=0;
    	boolean legalParent=false;
    	boolean legalChild=false;
    	//check if label is legal:
    	for (int i=0; i<blackNodes.size();i++) {
    		if(blackNodes.get(i).getLabel().equals(parentName)) {
    			parentIndex = i;
    			if(!blackNodes.get(i).getOutgoingEdges().contains(edgeLabel)) {
    				legalParent = true;
    			}    			
    			break;
    		}
    	}
    	for (int i=0; i<whiteNodes.size();i++) {
    		if(whiteNodes.get(i).getLabel().equals(childName)) {
    			childIndex = i;
    			if(!whiteNodes.get(i).getIncomingEdges().contains(edgeLabel)) {
    				legalChild = true;
    			}    			
    			break;
    		}
    	}
    	
    	if (!legalChild ) {
    		System.out.println("Child already has an edge with this label" );
    		return;
    	}
    	if (!legalParent) {
    		System.out.println("Parent already has an edge with this label" );
    		return;
    	}
    	//legal, add
    	Edge newEdge = new Edge(edgeLabel,blackNodes.get(parentIndex),whiteNodes.get(childIndex));
    	blackNodes.get(parentIndex).addIncomingEdge(newEdge);
    	whiteNodes.get(childIndex).addOutgoingEdge(newEdge);
    }
    
    /**
     * @requires blackNodes.contains(childName) && whiteNodes.contains(parentName)
     * @modifies this
     */    
    public void addEdgeWhiteToBlack(T edgeLabel, T parentName, T childName) {
    	int parentIndex=0;
    	int childIndex=0;
    	boolean legalParent=false;
    	boolean legalChild=false;
    	//check if lable is legal:
    	for (int i=0; i<whiteNodes.size();i++) {
    		if(whiteNodes.get(i).getLabel().equals(parentName)) {
    			parentIndex = i;
    			if(!whiteNodes.get(i).getOutgoingEdges().contains(edgeLabel)) {
    				legalParent = true;
    			}    			
    			break;
    		}
    	}
    	for (int i=0; i<blackNodes.size();i++) {
    		if(blackNodes.get(i).getLabel().equals(childName)) {
    			childIndex = i;
    			if(!blackNodes.get(i).getIncomingEdges().contains(edgeLabel)) {
    				legalChild = true;
    			}    			
    			break;
    		}
    	}
    	
    	if (!legalChild ) {
    		System.out.println("Child already has an edge with this label" );
    		return;
    	}
    	
    	if (!legalParent) {
    		System.out.println("Parent already has an edge with this label" );
    		return;
    	}
    	
    	//legal, add
    	Edge newEdge = new Edge(edgeLabel,whiteNodes.get(parentIndex),blackNodes.get(childIndex));
    	whiteNodes.get(parentIndex).addIncomingEdge(newEdge);
    	blackNodes.get(childIndex).addOutgoingEdge(newEdge);
    }
    
}
