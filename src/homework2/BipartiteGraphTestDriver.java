package homework2;

import java.util.*;

/**
 * This class implements a testing driver for BipartiteGraph. The driver
 * manages BipartiteGraphs whose nodes and edges are Strings.
 */
public class BipartiteGraphTestDriver {

    private Map<String, BipartiteGraph<String>> graphs;

    /**
     * @modifies this
     * @effects Constructs a new test driver.
     */
    public BipartiteGraphTestDriver () {
    	graphs = new HashMap<String, BipartiteGraph<String>>();
    }

    
    /**
     * @requires graphName != null
     * @modifies this
     * @effects Creates a new graph named graphName. The graph is initially
     * 			empty.
     */
    public void createGraph(String graphName) {
    	BipartiteGraph bGraph = new BipartiteGraph(graphName);        
    	graphs.put(graphName, bGraph);
    }
    
    /**
     * @requires createGraph(graphName)
     *           && nodeName != null
     *           && neither addBlackNode(graphName,nodeName) 
     *                  nor addWhiteNode(graphName,nodeName)
     *                      has already been called on this
     * @modifies graph named graphName
     * @effects Adds a black node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addBlackNode(String graphName, String nodeName) {
    	if(graphName == null || !(graphs.containsKey(graphName))) {
    		System.out.println("No graph by the Name: " + graphName);
    		return;
    	}

    	boolean res = graphs.get(graphName).addBlackNode(nodeName);
    	if (!res)
            System.out.println("Failed to add black node!");
    }

    /**
     * @requires createGraph(graphName)
     *           && nodeName != null
     *           && neither addBlackNode(graphName,nodeName) 
     *                  nor addWhiteNode(graphName,nodeName)
     *                      has already been called on this
     * @modifies graph named graphName
     * @effects Adds a white node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addWhiteNode(String graphName, String nodeName) {
    	if(graphName == null || !(graphs.containsKey(graphName))) {
    		System.out.println("No graph by the Name: " + graphName);
    		return;
    	}
    	boolean res = graphs.get(graphName).addWhiteNode(nodeName);
    	if (!res)
            System.out.println("Failed to add white node: " + nodeName+" to graph: " + graphName);
    }

    /**
     * @requires createGraph(graphName)
     *           && ((addBlackNode(parentName) && addWhiteNode(childName))
     *              || (addWhiteNode(parentName) && addBlackNode(childName)))
     *           && edgeLabel != null
     *           && node named parentName has no other outgoing edge labeled
     * 				edgeLabel
     *           && node named childName has no other incoming edge labeled
     * 				edgeLabel
     * @modifies graph named graphName
     * @effects Adds an edge from the node parentName to the node childName
     * 			in the graph graphName. The new edge's label is the String
     * 			edgeLabel.
     */
    public void addEdge(String graphName,
    					String parentName, String childName, 
                        String edgeLabel) {

        if (edgeLabel == null) {
            System.out.println("edgeLabel is null!");
            return;
        }

        graphs.get(graphName).addEdge(parentName, childName, edgeLabel);
    }

    
    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the black nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listBlackNodes(String graphName) {
    	if (graphName == null || !graphs.containsKey(graphName)) {
            System.out.println("graph name is Null or graph doesn't exist!");
            return null;
        }

        List<Node<String>> blackNodes = graphs.get(graphName).getBlackNodes();
        List<String> blackListString = new ArrayList<String>();
        String blackNodesNames = new String("");

        for (int i = 0; i < blackNodes.size(); i++) {
            blackListString.add(blackNodes.get(i).getLabel());
        }

        Collections.sort(blackListString, String.CASE_INSENSITIVE_ORDER);
        
        for (int i = 0; i < blackListString.size(); i++) {
        	if (i<blackListString.size()-1)
        		blackNodesNames = blackNodesNames.concat(blackListString.get(i)+" ");
        	else
        		blackNodesNames = blackNodesNames.concat(blackListString.get(i));
        }
        return blackNodesNames;
    }

    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the white nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listWhiteNodes(String graphName) {
    	if (graphName == null || !graphs.containsKey(graphName)) {
            System.out.println("graph name is Null or graph doesn't exist!");
            return null;
        }

        List<Node<String>> whiteNode = graphs.get(graphName).getWhiteNodes();
        List<String> whiteListString = new ArrayList<String>();
        String whiteNodesNames = new String("");

        for (int i = 0; i < whiteNode.size(); i++) {
            whiteListString.add(whiteNode.get(i).getLabel());
        }

        Collections.sort(whiteListString, String.CASE_INSENSITIVE_ORDER);

        for (int i = 0; i < whiteListString.size(); i++) {
        	if (i<whiteListString.size()-1)
        		whiteNodesNames = whiteNodesNames.concat(whiteListString.get(i)+" ");
        	else
        		whiteNodesNames = whiteNodesNames.concat(whiteListString.get(i));
        }
            
        return whiteNodesNames;
    }

    
    /**
     * @requires createGraph(graphName) && createNode(parentName)
     * @return a space-separated list of the names of the children of
     * 		   parentName in the graph graphName, in alphabetical order.
     */
    public String listChildren(String graphName, String parentName) {
        if (graphName == null || !graphs.containsKey(graphName)) {
            System.out.println("graph name is Null or graph doesn't exist!");
            return null;
        }

        List<Node<String>> childList = graphs.get(graphName).GetChildList(parentName);
        List<String> childListString = new ArrayList<String>();
        String childNames = new String("");

        for (int i = 0; i < childList.size(); i++) {
            childListString.add(childList.get(i).getLabel());
        }

        Collections.sort(childListString, String.CASE_INSENSITIVE_ORDER);

        for (int i = 0; i < childListString.size(); i++) {
        	if (i<childListString.size()-1)
            	childNames = childNames.concat(childListString.get(i)+" ");
            else
            	childNames = childNames.concat(childListString.get(i));
        }
            
        return childNames;
    }

    
    /**
     * @requires createGraph(graphName) && createNode(childName)
     * @return a space-separated list of the names of the parents of
     * 		   childName in the graph graphName, in alphabetical order.
     */
    public String listParents(String graphName, String childName) {
        if (graphName == null || !graphs.containsKey(graphName)) {
            System.out.println("graph name is Null or graph doesn't exist!");
            return null;
        }

        List<Node<String>> parentList = graphs.get(graphName).GetParentList(childName);
        List<String> parentListString = new ArrayList<String>();
        String parentNames = new String("");

        for (int i = 0; i < parentList.size(); i++) {
            parentListString.add(parentList.get(i).getLabel());
        }

        Collections.sort(parentListString, String.CASE_INSENSITIVE_ORDER);

        for (int i = 0; i < parentListString.size(); i++) {
        	if (i < parentListString.size()-1)
            	parentNames = parentNames.concat(parentListString.get(i)+" ");
            else
            	parentNames = parentNames.concat(parentListString.get(i));
        }
            
        return parentNames;
    }

    
    /**
     * @requires addEdge(graphName, parentName, str, edgeLabel) for some
     * 			 string str
     * @return the name of the child of parentName that is connected by the
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getChildByEdgeLabel(String graphName, String parentName,
    								   String edgeLabel) {
        if (graphName == null || !graphs.containsKey(graphName)){
            System.out.println("graph name is Null or graph doesn't exist!");
            return "";
        }
        if (parentName == null) {
        	System.out.println("parent name is null");
            return "";
        }
        if ((graphs.get(graphName).containsNode(graphs.get(graphName).getBlackNodes(), parentName) == null) && (graphs.get(graphName).containsNode(graphs.get(graphName).getWhiteNodes(), parentName) == null)) {
        	System.out.println("parent: "+parentName +" in graph: "+graphName+" doesn't exist");
            return "";
        }
        if (graphs.get(graphName).GetChildByEdgeLabel(parentName, edgeLabel) == null) {
            System.out.println("child doesn't exist!");
            return "";
        }

        Node child = graphs.get(graphName).GetChildByEdgeLabel(parentName, edgeLabel);
        if (child == null)
            return null;

        String label = (String)(child.getLabel());
    	return label;
    }

    
    /**
     * @requires addEdge(graphName, str, childName, edgeLabel) for some
     * 			 string str
     * @return the name of the parent of childName that is connected by the 
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getParentByEdgeLabel(String graphName, String childName,
    									String edgeLabel) {
        Node childNode  = new Node("");
    	if (graphName == null){
            System.out.println("graph name is Null ");
            return "";
        }
        if (!graphs.containsKey(graphName)) {
        	System.out.println("graph: "+graphName+" doesn't exist!");
        	return "";
        }
        if (childName == null) {
        	System.out.println("child name is null");
        	return "";
        } 
        if ((graphs.get(graphName).containsNode(graphs.get(graphName).getBlackNodes(), childName) == null )&& (graphs.get(graphName).containsNode(graphs.get(graphName).getWhiteNodes(), childName) == null)) {
        	System.out.println("child name: " +childName+" doesn't exist in graph: "+graphName);
        	return "";
        } 
        if (edgeLabel == null){
            System.out.println("edgeLabel is Null ");
            return "";
        }
        childNode = graphs.get(graphName).containsNode(graphs.get(graphName).getBlackNodes(), childName);
        if (childNode == null) {
        	childNode = graphs.get(graphName).containsNode(graphs.get(graphName).getWhiteNodes(), childName);
        	if(childNode == null) {
        		System.out.println("childNode doesn't exist");
        		return "";
        	}
        }
        if(!childNode.containsEdge(childNode.getIncomingEdges(), edgeLabel)) {        	
            System.out.println("node: "+childName+" doesn't have an edge labeled: "+edgeLabel);
            return "";
        }

        Node parent = graphs.get(graphName).GetParentByEdgeLabel(childName, edgeLabel);
        if (parent == null)
            return null;

        String label = (String)(parent.getLabel());

        return label;
    }
}
