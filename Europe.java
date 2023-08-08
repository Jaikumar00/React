import java.util.*;

class Graph {
    private Map<String, List<String>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addEdge(String source, String destination) {
        adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
    }

    public Map<String, List<String>> getAdjacencyList() {
        return adjacencyList;
    }
}

public class EuropeanVacation {
    private static List<String> findRoute(Graph graph, String currentCity, List<String> visitedCities) {
        visitedCities.add(currentCity);
        if (!graph.getAdjacencyList().containsKey(currentCity)) {
            return visitedCities;
        }

        for (String neighbor : graph.getAdjacencyList().get(currentCity)) {
            if (!visitedCities.contains(neighbor)) {
                return findRoute(graph, neighbor, visitedCities);
            }
        }

        return visitedCities;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        List<String[]> tickets = new ArrayList<>(Arrays.asList(
            new String[]{"Kiev", "Prague"},
            new String[]{"Prague", "Zurich"},
            new String[]{"Zurich", "Amsterdam"},
            new String[]{"Amsterdam", "Barcelona"},
            new String[]{"Barcelona", "Berlin"},
            new String[]{"Berlin", "Kiev"}
        ));

        for (String[] ticket : tickets) {
            graph.addEdge(ticket[0], ticket[1]);
        }

      
        String startCity = "Kiev";

    
        List<String> route = findRoute(graph, startCity, new ArrayList<>());
        System.out.println("Route traveled by your son: " + String.join(" -> ", route));
    }
}
