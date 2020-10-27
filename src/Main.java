import java.util.*;

public class Main {
    public static void main(String[] args) {

    }


    public static Graph dijkstra(Graph graph, Node node) {
        node.setDistance(0);

        Set<Node> areSet = new HashSet<>();
        Set<Node> unSet = new HashSet<>();

        unSet.add(node);

        while (unSet.size() != 0) {
            Node currentNode = getLowestDistanceNode(unSet);
            unSet.remove(currentNode);
            for (Map.Entry<Node, Integer> adjacentPair : currentNode.getAdjacentNodes().entrySet()) {
                Node adjNode = adjacentPair.getKey();
                Integer edgeWeight = adjacentPair.getValue();
                if(!areSet.contains(adjNode)){
                    calculateMinimumDistance(adjNode, edgeWeight, currentNode);
                    unSet.add(adjNode);
                }
            }
            areSet.add(currentNode);
        }
        return graph;
    }

    private static void calculateMinimumDistance(Node adjNode, Integer edgeWeight, Node currentNode) {
        Integer sourceDistance = currentNode.getDistance();
        if (sourceDistance + edgeWeight < adjNode.getDistance()){
            adjNode.setDistance(sourceDistance + edgeWeight);
            LinkedList<Node> shortestPath = new LinkedList<>(currentNode.getShortestPath());
            shortestPath.add(adjNode);
            adjNode.setShortestPath(shortestPath);
        }
    }

    private static Node getLowestDistanceNode(Set<Node> unSet) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;

        for(Node node: unSet){
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance){
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

}


class Graph {

    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }
}

class Node {

    Map<Node, Integer> adjacentNodes = new HashMap<>();

    private String name;

    private List<Node> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    public Node(String name) {
        this.name = name;
    }

    public void addDis(Node node, Integer distance) {
        adjacentNodes.put(node, distance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }
}