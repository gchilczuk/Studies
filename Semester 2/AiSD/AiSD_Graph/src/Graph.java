import java.util.*;

/**
 * @author Grzegorz Chilczuk
 *         Created on 12 maj 2016
 */
public class Graph<T> {
    LinkedList<Vertex> vertices;
    ArrayList<LinkedList<Edge>> neighbours;

    Graph(){
        vertices = new LinkedList<>();
        neighbours = new ArrayList<>();
    }

    void addVertex(T value){
        Vertex vertex = new Vertex(value);
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            neighbours.add(new LinkedList<Edge>());
        }
    }

   /* void addVertex(T value, T[] neighbours){
        addVertex(value);
        for (T neig : neighbours) {
            addNeighbour(value, neig);
        }
    }*/

    void addNeighbour(T value, T newNeighbour, int weight){
        Vertex neig = null, n;

        for (int i = 0; neig == null && i < vertices.size(); i++){
            n = vertices.get(i);
            if (n.value.equals(newNeighbour)) neig = n;
        }

        if (neig == null) throw new NullPointerException("No such Neighbour!");

        int index = -1;
        for (int i = 0; index < 0 && i < vertices.size(); i++){
            if (vertices.get(i).value.equals(value)) index = i;
        }

        if (index == -1) throw new NullPointerException("No such Vertex!");

        addNeighbour(index, neig, weight);
    }

    void addNeighbour(Vertex vertex, Vertex newNeighbour, int weight){
        int index = vertices.indexOf(vertex);
        neighbours.get(index).add(new Edge(newNeighbour, weight));
    }

    void addNeighbour(int index, Vertex newNeighbour, int weight){
        neighbours.get(index).add(new Edge(newNeighbour, weight));
    }

    Integer weight (Vertex start, Vertex end){
        Integer w = Integer.MAX_VALUE-50;
        for (Edge edge : neighbours.get(vertices.indexOf(start))) {
            if (edge.b.equals(end)) {
                w = edge.weight;
                break;
            }
        }
        return w;
    }

    // ––––––––––– DIJKSTRA \ start
    void print(HashMap<Vertex, Integer> map){
//        System.out.println("From: "+start+" to:");
        for (Vertex v : map.keySet()){
            Integer len = map.get(v);
            v.distance = len;
            if (len < Integer.MAX_VALUE) {
                printPath(v,1);
                System.out.printf("%10s\n", v.value + " – " + map.get(v));
            }
            else
                System.out.println(v.value+ " – out of range");
        }
    }

    void printPath(Vertex vertex, int stan){
        if (vertex.prev.equals(vertex)) System.out.printf(vertex.value + " → ");
        else {
            printPath(vertex.prev, 0);
            if (stan == 0) System.out.printf(vertex.value + " → ");
        }
    }

    void dijkstra(Vertex start){
        Vertex k, j;
        HashMap<Vertex, Integer> D = new HashMap<>();
        PriorityQueue V = new PriorityQueue();
        PriorityQueue L = new PriorityQueue();
        for (Vertex vertex : vertices){
            if (!vertex.equals(start)) {
                vertex.distance = weight(start, vertex);
                if(vertex.distance < Integer.MAX_VALUE-50){
                    vertex.prev = start;
                }
                else vertex.prev = null;
                V.add(vertex);
                D.put(vertex, vertex.distance);
            }
        }
        start.distance = 0;
        start.prev = start;
        int counter = 0;
        while (counter < V.size){
            k = V.get(counter);
            counter++;
            L.add(k);
            for (int i = 0; i < V.size; i++){
                j = V.get(i);
                Integer w = weight(k,j);
                if(w <= Integer.MAX_VALUE && D.get(k) + w > 0 && (D.get(j) > D.get(k) + w)) {
                    D.remove(j);
                    D.put(j, D.get(k) + w);
                    j.prev = k;
                }
            }
        }
        print(D);
    }

    void dijkstra(T start){
        dijkstra(getVertex(start));
    }
    // ––––––––––– DIJKSTRA \ end

    Vertex getVertex(T value){
        for (Vertex v : vertices){
            if (v.value.equals(value)) {
                return v;
            }
        }
        throw new NullPointerException("No such Vertex!");
    }

    void setAllNotVisited(){
        for(Vertex v : vertices) {
            v.visited = false;
            v.distance = 0;
        }
    }

    void DFS(T value){
        Vertex start = getVertex(value);
        setAllNotVisited();
        DFS_V(start, 0);
    }

    void DFS_V(Vertex vertex, int dist){
        vertex.visited = true;
        for (Edge edge : neighbours.get(vertices.indexOf(vertex))){
            if (!edge.b.visited)
                DFS_V(edge.b, dist+edge.weight);
        }
        vertex.distance = dist;
        System.out.println(vertex);
    }

    void BFS(T value){
        Vertex start = getVertex(value), vertex, v;
        setAllNotVisited();
        start.visited = true;
        LinkedList<Vertex> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()){
            vertex = queue.poll();
            for (Edge edge : neighbours.get(vertices.indexOf(vertex))){
                v = edge.b;
                if (!v.visited){
                    v.visited = true;
                    queue.add(v);
                    v.distance = edge.weight + vertex.distance;
                }
            }
            System.out.println(vertex);
        }
    }

    void showNeighbours(){
        for (int i = 0; i < neighbours.size(); i++){
            System.out.printf(vertices.get(i).value.toString() + ": ");
            System.out.println(neighbours.get(i));
        }
    }

    class Vertex implements Comparable<Vertex> {
        T value;
        boolean visited;
        Integer distance;
        Vertex prev;

        Vertex(T value){
            this.value = value;
            this.distance = Integer.MAX_VALUE;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return value.equals(vertex.value);
        }
        @Override
        public int compareTo(Vertex vertex) {
            return distance - vertex.distance;
        }
        @Override
        public String toString() {
            return value.toString()+" ("+distance+")";
        }

    }

    class Edge{
        int weight;
        Vertex b;
        Edge( Vertex b, int weight){
            this.b = b;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return b.value + " - "+weight;
        }
    }

}
