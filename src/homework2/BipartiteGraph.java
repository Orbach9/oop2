package homework2;
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
    public void addNode(T parentName, T childName,T edgeLabel){

        Node parentNode, childNode;
        Edge newEdge;
        // check for requires - label edge not null
        if (edgeLabel == null)
            return;

        parentNode = containsNode(blackNodes, parentName) == null ?
                     containsNode(whiteNodes, parentName) : containsNode(blackNodes, parentName);
        childNode = containsNode(blackNodes, childName) == null ?
                    containsNode(whiteNodes, childName) : containsNode(blackNodes, childName);

        // check is parent & child exist
        if(parentNode == null || childNode == null) {
            System.out.println("parent/child does not exist");
            return;
        }

        newEdge = new Edge(edgeLabel, parentNode, childNode);
        parentNode.addOutgoingEdge(newEdge);
        childNode.addIncomingEdge(newEdge);

    }
    
    /**
     * @requires blackNodes.contains(parentName) && whiteNodes.contains(childName)
     * @modifies this
     */    
    public void addEdgeBlackToWhite(T edgeLable, T parentName, T childName) {
    	int parentIndex=0;
    	int childIndex=0;
    	boolean legalParent=false;
    	boolean legalChild=false;
    	//check if lable is legal:
    	for (int i=0; i<blackNodes.size();i++) {
    		if(blackNodes.get(i).getLabel().equals(parentName)) {
    			parentIndex = i;
    			if(!blackNodes.get(i).getOutgoingEdges().contains(edgeLable)) {
    				legalParent = true;
    			}    			
    			break;
    		}
    	}
    	for (int i=0; i<whiteNodes.size();i++) {
    		if(whiteNodes.get(i).getLabel().equals(childName)) {
    			childIndex = i;
    			if(!whiteNodes.get(i).getIncomingEdges().contains(edgeLable)) {
    				legalChild = true;
    			}    			
    			break;
    		}
    	}
    	
    	if (!legalChild ) {
    		System.out.println("Child already has an edge with this lable" );
    		return;
    	}
    	if (!legalParent) {
    		System.out.println("Parent already has an edge with this lable" );
    		return;
    	}
    	//legal, add
    	Edge newEdge = new Edge(edgeLable,blackNodes.get(parentIndex),whiteNodes.get(childIndex));
    	blackNodes.get(parentIndex).addIncomingEdge(newEdge);
    	whiteNodes.get(childIndex).addOutgoingEdge(newEdge);
    }
    
    /**
     * @requires blackNodes.contains(childName) && whiteNodes.contains(parentName)
     * @modifies this
     */    
    public void addEdgeWhiteToBlack(T edgeLable, T parentName, T childName) {
    	int parentIndex=0;
    	int childIndex=0;
    	boolean legalParent=false;
    	boolean legalChild=false;
    	//check if lable is legal:
    	for (int i=0; i<whiteNodes.size();i++) {
    		if(whiteNodes.get(i).getLabel().equals(parentName)) {
    			parentIndex = i;
    			if(!whiteNodes.get(i).getOutgoingEdges().contains(edgeLable)) {
    				legalParent = true;
    			}    			
    			break;
    		}
    	}
    	for (int i=0; i<blackNodes.size();i++) {
    		if(blackNodes.get(i).getLabel().equals(childName)) {
    			childIndex = i;
    			if(!blackNodes.get(i).getIncomingEdges().contains(edgeLable)) {
    				legalChild = true;
    			}    			
    			break;
    		}
    	}
    	
    	if (!legalChild ) {
    		System.out.println("Child already has an edge with this lable" );
    		return;
    	}
    	
    	if (!legalParent) {
    		System.out.println("Parent already has an edge with this lable" );
    		return;
    	}
    	
    	//legal, add
    	Edge newEdge = new Edge(edgeLable,whiteNodes.get(parentIndex),blackNodes.get(childIndex));
    	whiteNodes.get(parentIndex).addIncomingEdge(newEdge);
    	blackNodes.get(childIndex).addOutgoingEdge(newEdge);
    }
    
}
