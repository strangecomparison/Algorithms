package dijkstra;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Run {

    public static void main(String[] args) {

        // инициализируем узлы и граф - вносим информацию о них в систему
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);

        Graph graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        // после выполнения этого метода у каждого узла графа будет установлено кратчайшее
        // расстояние до него от начала и кратчайший путь по узлам
        graph = dijkstra(graph, nodeA);

        // должно быть 23
        Node f = graph.getNodes().stream()
                .filter(e -> e.getName().equals("F"))
                .findFirst()
                .orElse(null);

        System.out.println(f.getName() + " " + f.getDistance());

    }

    public static Graph dijkstra(Graph graph, Node source) {

        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>(); // список проверенных узлов
        Set<Node> unsettledNodes = new HashSet<>(); // список узлов на проверку

        unsettledNodes.add(source); // добавляем первый узел на проверку

        while(unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes); // находим самый ближний к текущему узел
            unsettledNodes.remove(currentNode); // убираем его из очереди на проверку

            // проходим по его соседям
            for(Map.Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey(); // соседний узел B
                Integer edgeWeight = adjacencyPair.getValue(); // расстояние A-B

                // если сосед ближайшего узла еще не проверен, то
                if(!settledNodes.contains(adjacentNode)) {
                    // edgeWeight - расстояние A-B
                    // adjacentNode - C или D
                    // currentNode - B
                    calculateMininumDistance(adjacentNode, edgeWeight, currentNode);

                    unsettledNodes.add(adjacentNode); // добавляем соседний узел С в список для проверки непроверенных
                }
            }
            settledNodes.add(currentNode); // добавляем самый ближний к текущему узел в список проверенных
        }

        return graph;
    }

    // метод возвращает самый ближний к текущему узел
    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null; // заготовки для значений
        int lowestDistance = Integer.MAX_VALUE;

        for(Node node : unsettledNodes) {   // проходимся по нетронутым узлам и находим тот, который ближе всего к текущему
            int nodeDistance = node.getDistance();

            if(nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    // метод обновляет наименьшее расстояние от начала до узла
    private static void calculateMininumDistance(Node adjacentNode, Integer edgeWeight,
                                                 Node sourceNode) {

        // sourceNode - точка B
        // adjacentNode - точка C
        // edgeWeight - расстояние A-B

        Integer sourceDistance = sourceNode.getDistance(); // получаем расстояние A-B

        // если A-B-C меньше, чем A-C
        // adjacentNote изначально хранит в себе расстояние не B-C, а A-C, т.к. ялвяется соседом A
        if(sourceDistance + edgeWeight < adjacentNode.getDistance()) {
            adjacentNode.setDistance(sourceDistance + edgeWeight);  // самое короткое расстояние от начала до C получается A-B-C

            // получаем самый короткий путь из узлов до B
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            // добавляем туда узел C
            shortestPath.add(sourceNode);
            // устанавливает для C самый короткий путь из узлов
            adjacentNode.setShortestPath(shortestPath);
        }
    }
}
