/**
 * @author Grzegorz Chilczuk
 *         Created on 12 maj 2016
 */
public class Main {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        graph.addVertex("Wrocław");
        graph.addVertex("Oława");
        graph.addVertex("Nysa");
        graph.addVertex("Brzeg");
        graph.addVertex("Opole");
        graph.addNeighbour("Wrocław", "Oława", 10);
        graph.addNeighbour("Oława", "Brzeg", 50);
        graph.addNeighbour("Wrocław", "Nysa", 30);
        graph.addNeighbour("Nysa", "Brzeg", 20);
        graph.addNeighbour("Nysa", "Opole", 60);
        graph.addNeighbour("Brzeg", "Opole", 10);
        graph.addNeighbour("Wrocław", "Opole", 100);

        String start = "Wrocław";

        System.out.println("——————— Dijkstra ");
        graph.dijkstra(start);
        System.out.println();
        System.out.println("——————— DFS from "+start);
        graph.DFS(start);
        System.out.println();
        System.out.println("——————— BFS from "+start);
        graph.BFS(start);

        System.out.println();
        System.out.println("——————— Lista sąsiedztwa");
        graph.showNeighbours();


    }
}
