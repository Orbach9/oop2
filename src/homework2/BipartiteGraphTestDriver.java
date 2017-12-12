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
            System.out.println("Failed to add white node!");
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
        String blackNodesNames = null;

        for (int i = 0; i < blackNodes.size(); i++) {
            blackNodesNames = blackNodesNames.concat(blackNodes.get(i).getLabel()+ " ");
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
        String whiteNodesNames = null;

        for (int i = 0; i < whiteNode.size(); i++) {
            whiteNodesNames = whiteNodesNames.concat(whiteNode.get(i).getLabel()+ " ");
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
        String childNames = null;

        for (int i = 0; i < childList.size(); i++) {
            childNames = childNames.concat(childList.get(i).getLabel()+ " ");
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
        String parentNames = null;

        for (int i = 0; i < parentList.size(); i++) {
            parentNames = parentNames.concat(parentList.get(i).getLabel()+ " ");
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
            return null;
        }

    	return graphs.get(graphName).GetChildByEdgeLabel(parentName, edgeLabel).getLabel();
    }

    
    /**
     * @requires addEdge(graphName, str, childName, edgeLabel) for some
     * 			 string str
     * @return the name of the parent of childName that is connected by the 
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getParentByEdgeLabel(String graphName, String childName,
    									String edgeLabel) {
        if (graphName == null || !graphs.containsKey(graphName)){
            System.out.println("graph name is Null or graph doesn't exist!");
            return null;
        }

        return graphs.get(graphName).GetParentByEdgeLabel(childName, edgeLabel).getLabel();
    }
}
