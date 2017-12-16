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

    private void checkRep() {
    	
    	Node tmpNode,curNode;
    	Edge curEdge,tmpEdge;
    	boolean isBlack;
    	boolean isLegalEdge = false;
    	T tmpLabel;
    	//loop for each node
    	for (int i=0; i<(blackNodes.size()+whiteNodes.size());i++) {
    		//check if black of white
    		if (i<blackNodes.size()) {
    			curNode = blackNodes.get(i);
    			isBlack = true;
    		} else {
    			curNode = whiteNodes.get(i-blackNodes.size());
    			isBlack = false;
    		}
    		
    		//check each node has a unique label - white-black and black-black combinations
    		
    		//black nodes
    		for(int j=0;j<blackNodes.size();j++) {
    			if(isBlack) {
    				//make sure it's not the same node
    				if(j != i) {
    					//check they have different labels
        				assert(!blackNodes.get(j).getLabel().equals(curNode.getLabel()));
        			}
    			} else {
    				//they have different colors
    				assert(!blackNodes.get(j).getLabel().equals(curNode.getLabel()));
    			}
    			
    		}
    		
    		//white nodes
    		for(int j=0;j<whiteNodes.size();j++) {
    			if(!isBlack) {
    				//make sure it's not the same node
    				if(j != i-blackNodes.size()) {
    					//check they have different labels
        				assert(!whiteNodes.get(j).getLabel().equals(curNode.getLabel()));
        			}	
    			} else { /*they have different colors*/
    				assert(!whiteNodes.get(j).getLabel().equals(curNode.getLabel()));   				
    			}    			
    		}
    		
    		//check each edge is unique    		
    		for (int j=0; j<curNode.getIncomingEdges().size(); j++) {
    			curEdge = (Edge) curNode.getIncomingEdges().get(j);
    			for (int k=0; k<curNode.getIncomingEdges().size(); k++) {
    				tmpEdge = (Edge) curNode.getIncomingEdges().get(k);
    				if (j!=k) {/*make sure it's different edges */
    					
    					//check each Edge label is uniqe
    					assert(!curEdge.getLabel().equals(tmpEdge.getLabel()));
    					
    					//check each parent has child has 1 or 0 edges in the same direction
    					assert(!curEdge.getStartNode().equals(tmpEdge.getStartNode()));
    				}
    			}
    			
    			//check each edge has 2 different colors of nodes in it's ends -
    			//no need to check for outgoing and incoming - only one (that's all of the edges)
    			if (isBlack) {
    				for(int k=0; k<whiteNodes.size(); k++) {
    					if (curEdge.getStartNode().getLabel().equals(whiteNodes.get(k).getLabel()))
    						isLegalEdge = true;
    				}
    			} else {
    				for(int k=0; k<blackNodes.size(); k++) {
    					if (curEdge.getStartNode().getLabel().equals(blackNodes.get(k).getLabel()))
    						isLegalEdge = true;
    				}
    			}
    			assert(isLegalEdge);
    			isLegalEdge = false;
    		}
    		
    		for (int j=0; j<curNode.getOutgoingEdges().size(); j++) {
    			curEdge = (Edge) curNode.getOutgoingEdges().get(j);
    			for (int k=0; k<curNode.getOutgoingEdges().size(); k++) {
    				tmpEdge = (Edge) curNode.getOutgoingEdges().get(k);
    				if (j!=k) {
    					//check each label is uniqe
    					assert(!curEdge.getLabel().equals(tmpEdge.getLabel()));
    					//check each parent has child has 1 or 0 edges in the same direaction
    					assert(!curEdge.getEndNode().equals(tmpEdge.getEndNode()));
    				}
    			}
    		}
    	}
    }
    
    /**
     * @requires graphName != null
     * @modifies this
     * @effects Constructs a new graph.
     */
    public BipartiteGraph(String graphName){
    
    	this.graphName = graphName;
        this.blackNodes = new ArrayList<Node<T>>();
        this.whiteNodes = new ArrayList<Node<T>>();
        checkRep();
    }

    /**
     * @return the black nodes list.
     */
    public List<Node<T>> getBlackNodes(){
    	
    	checkRep();
    	return blackNodes;
    }
    
    
    
    /**
     * @return the white nodes list.
     */
    public List<Node<T>> getWhiteNodes(){
    	
    	checkRep();
    	return whiteNodes;
    }
    
    /**
     * @requires nodes nodeLable!=null, addBlackNode(nodeLable) and 
     * addWhiteNode(nodeLable) have'nt been called before
     * @return true if succeeded, false if failed
     */    
    public boolean addBlackNode(T nodeLable) {
    	
    	checkRep();
    	Node newNode = new Node(nodeLable);
    	if(this.containsNode(blackNodes, nodeLable)!=null || this.containsNode(whiteNodes, nodeLable)!=null) {
    		System.out.println("Node label " + nodeLable+" Already exists");
    		checkRep();
    		return false;
    	}
    	
    	blackNodes.add(newNode);
    	checkRep();
    	return true;
    }
    
    /**
     * @requires nodes nodeLable!=null, addBlackNode(nodeLable) and 
     * addWhiteNode(nodeLable) have'nt been called before
     * @return true if succeeded, false if failed
     */
    public boolean addWhiteNode(T nodeLable) {
    	
    	checkRep();
    	Node newNode = new Node(nodeLable);
    	if(this.containsNode(blackNodes, nodeLable)!=null|| this.containsNode(whiteNodes, nodeLable)!=null) {
    		System.out.println("Node label " + nodeLable+" Already exists");
    		checkRep();
    		return false;
    	}
    	whiteNodes.add(newNode);
    	checkRep();
    	return true;
    }

    /**
     * @requires nodes != null, label != null
     * @return the node with the label- label, null in case there is
     * no such node.
     */
    public Node<T> containsNode(List<Node<T>> nodes, T label) {
        
    	for (Node node : nodes) {
            if (node.getLabel().equals(label)) {
            	checkRep();
            	return node;
            }
        }
    	checkRep();
        return null;
    }

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
        checkRep();
        
        // check for requires - label edge not null
        if (edgeLabel == null) {
        	checkRep();
        	return;
        }
        	
        if(containsNode(blackNodes, parentName) == null) {
        	parentNode = containsNode(whiteNodes, parentName);
        	if (parentNode == null) {
        		System.out.println("Parent node: "+parentName+" doesn't exist");
        		checkRep();	
        		return;
        	}
        	childNode = containsNode(blackNodes, childName);
        	if (childNode == null) {
        		System.out.println("illegal Parameters");
        		checkRep();
        		return;
        	}
        } else {
        	isParentWhite = false;
        	parentNode = containsNode(blackNodes, parentName); 
        	childNode = containsNode(whiteNodes,childName);
        	if (childNode == null) {
        		System.out.println("Illegal Parameters");
        		checkRep();
        		return;
        	}
        }         
        boolean isLegal = childNode.checkIfLegalEdge(edgeLabel,false/*not an outgoing edge*/);
        if (isLegal == false) {
        	System.out.println("Child: " +childName+ " already has an incoming edge labled" + edgeLabel);
        	checkRep();
        	return;
        }
        Edge tmpEdge;
    	for(int i=0;i<parentNode.getOutgoingEdges().size();i++) {
    		tmpEdge = (Edge) parentNode.getOutgoingEdges().get(i);
    		if (tmpEdge.getEndNode().equals(childNode)) {
    			System.out.println("Illegal Edge - already has an edge from: " +parentName+" to: "+childName);
    			checkRep();
    			return;
    		}
    	}
    	isLegal = parentNode.checkIfLegalEdge(edgeLabel,true/*outgoing edge*/);       
        if (isLegal == false) {
        	System.out.println("Parent: " +parentName+ " already has an outgoing edge labled" + edgeLabel);
        	checkRep();
        	return;
        }
        newEdge = new Edge(edgeLabel, parentNode, childNode);
        parentNode.addOutgoingEdge(newEdge);
        childNode.addIncomingEdge(newEdge);
        checkRep();
    }

	/**
	 * @requires parentName != NULL
	 * @return list of child nodes of the parent
	 */
	public List<Node<T>> GetChildList (T parentName) {

		checkRep();
		List <Node<T>> childList = new LinkedList<Node<T>>();
		List <Edge<T>> outgoingEdge = new LinkedList<Edge<T>>();

		if (parentName == null) {
			checkRep();
			return null;
		}
			

		Node parent = containsNode(blackNodes, parentName) == null ?
				      containsNode(whiteNodes, parentName) : containsNode(blackNodes, parentName);
		if (parent == null) {
			checkRep();
			return null;
		}
			

		outgoingEdge = parent.getOutgoingEdges();
		for (int i =0; i < outgoingEdge.size(); i++){
			Edge tmpEdge = outgoingEdge.get(i);
			childList.add(tmpEdge.getEndNode());
		}
		checkRep();
		return childList;
	}

	/**
	 * @requires childNode != NULL
	 * @return list of parent nodes of the parent
	 */
	public List<Node<T>> GetParentList (T childName) {

		checkRep();
		List <Node<T>> parentList = new LinkedList<Node<T>>();
		List <Edge<T>> incomingEdge = new LinkedList<Edge<T>>();

		if (childName == null) {
			checkRep();
			return null;
		}
			

		Node child = containsNode(blackNodes, childName) == null ?
					 containsNode(whiteNodes, childName) : containsNode(blackNodes, childName);
		if (child == null) {
			checkRep();
			return null;
		}
			
		incomingEdge = child.getIncomingEdges();
		for (int i =0; i < incomingEdge.size(); i++){
			Edge tmpEdge = incomingEdge.get(i);
			parentList.add(tmpEdge.getStartNode());
		}
		checkRep();
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
				System.out.println("Parent: " +parentName+ " in graph: "+graphName+" doesn't exist");
				checkRep();
				return null;
			}
		}
		
		if(!parentNode.containsEdge(parentNode.getOutgoingEdges(),edgeLabel)) {
			System.out.println("node: " +parentName+ " doesn't have an outgoing edge labeled: "+edgeLabel);
			checkRep();
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
			checkRep();
			return null;
		}
		checkRep();
		return edge.getEndNode();		
	}
	
	/**
	 * @requires node labeled childName has an incoming edge labeled edgeLabel,
	 *  node labeled childName exists
	 * @return parent node of child node labeled childName with edge labeled edgeLabel between them
	 */
	public Node<T> GetParentByEdgeLabel (T childName , T edgeLabel) {
		
		checkRep();
		Node childNode = containsNode(whiteNodes,childName);
		if (childNode == null) {
			childNode = containsNode(blackNodes,childName);
			if (childNode  == null) {
				System.out.println("Child: "+childName+" doesn't exist");
				checkRep();
				return null;
			}
		}
		
		if(!childNode.containsEdge(childNode.getIncomingEdges(),edgeLabel)) {
			System.out.println("Child: "+childName+" doesn't have an incoming edge labeled: "+edgeLabel);
			checkRep();
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
			checkRep();
			return null;
		}
		checkRep();
		return edge.getStartNode();		
	}
    
}
