import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Grzegorz Chilczuk
 *         Created on 25 maj 2016
 */
public class Graph {
    ArrayList<Integer> vertices;
    LinkedList<Edge> edges;
    int n, m;

    Graph(String filename) throws FileNotFoundException{
        vertices = new ArrayList<>();
        edges = new LinkedList<>();
        int a,b,dist;
        Scanner scanner = new Scanner(new File(filename));
        n = scanner.nextInt();
        m = scanner.nextInt();
        for (Integer i = 1; i <= n; i++)
            vertices.add(i);
        for (int i = 0; i < m; i++) {
            a = scanner.nextInt();
            b = scanner.nextInt();
            dist = scanner.nextInt();
            edges.add(new Edge(a,b,dist));
        }
    }

    ArrayList<Edge> prior(){
        ArrayList<Edge> pr = new ArrayList<>();
        int j;
        for (int i = 0; i < m; i++){
            Edge edge = edges.get(i);
            for (j = 0; j < pr.size() && pr.get(j).distance < edge.distance; j++);
            pr.add(j, edge);
        }
        return pr;
    }

    LinkedList<Edge> kruskalAlg(){
//        INIT
        Edge edge = null;
        LinkedList<Integer> newList;
        LinkedList<Edge> E = new LinkedList<>();
        ArrayList<Edge> EG = prior();
        LinkedList<LinkedList<Integer>> forest = new LinkedList<>();
        for(Integer integer:this.vertices){
            newList = new LinkedList<>();
            newList.add(integer);
            forest.add(newList);
        }
//        System.out.println("FOREST: "+forest);
        for(int i = 0; i < EG.size() && forest.size() > 1; i++){
            edge = EG.get(i);
            if (!isCycle(edge, forest)){
                E.add(edge);
                System.out.println(edge);
                forest = union(edge, forest);
            }
//            System.out.println("FOREST: "+forest);

        }
        return E;
    }

    boolean isCycle(Edge edge, LinkedList<LinkedList<Integer>> forest){
        for (LinkedList<Integer> f : forest) {
            if (f.contains(edge.a) && f.contains(edge.b)) {
//                System.out.println(f);
                return true;
            }
        }
        return false;
    }

    LinkedList<LinkedList<Integer>> union(Edge edge, LinkedList<LinkedList<Integer>> forest){
//        System.out.println("Edge: "+edge.a+", "+edge.b);
//        System.out.println(forest);
        LinkedList<Integer> list1 = null, list2 = null;
        for (int i = 0; i < forest.size(); i++){
            LinkedList<Integer> f = forest.get(i);
//            System.out.println("Sprawdzam czy: "+f + " zawiera: ");
            if (f.contains(edge.a)) {
                list1 = forest.remove(i);
//                System.out.println("Zawiera a");
                i--;
            }
            if (f.contains(edge.b)){
                list2 = forest.remove(i);
//                System.out.println("Zawiera b");
                i--;
            }
        }
        if (list1 == null || list2 == null){
            System.out.println("NULL");
            return forest;
        }
//        System.out.println("L1: "+list1);
        list1.addAll(list2);
        forest.add(list1);
        return forest;
    }


    
    LinkedList<Edge> primAlg(){
//        INIT
        int index, found;
        Edge edge = null;
        LinkedList<Edge> E = new LinkedList<>();
        ArrayList<Edge> EG = prior();
        ArrayList<Integer> used = new ArrayList<>();
        ArrayList<Integer> not_used = (ArrayList<Integer>)vertices.clone();
        used.add(not_used.remove(0));
//        INIT \
        while (!not_used.isEmpty()){
            found = 0;
            for (int i = 0; i < m && found == 0; i++){
                edge = EG.get(i);
                if (used.contains(edge.a) && not_used.contains(edge.b) )
                    found = 1;
                else if (used.contains(edge.b) && not_used.contains(edge.a))
                    found = 2;
            }
            if (found != 0) {
                System.out.println(edge);
                E.add(edge);
                if(found == 1)
                    index = not_used.indexOf(edge.b);
                else
                    index = not_used.indexOf(edge.a);

                used.add(not_used.remove(index));
            }
        }

        return E;
    }

    int answerPrim(){
        int w = 0;
        LinkedList<Edge> edges = primAlg();
        for (Edge e : edges){
            w += e.distance;
        }
        return w;
    }

    int answerKruskal(){
        int w = 0;
        LinkedList<Edge> edges = kruskalAlg();
        for (Edge e : edges){
            w += e.distance;
        }
        return w;
    }

    class Edge{
        Integer a;
        Integer b;
        Integer distance;
        Edge(int a, int b, int distance){
            this.a = a;
            this.b = b;
            this.distance = distance;
        }
        @Override
        public String toString() {
            return a+" – "+b+": "+distance;
        }
    }

}
