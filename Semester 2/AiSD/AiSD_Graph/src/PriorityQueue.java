/**
 * @author Grzegorz Chilczuk
 *         Created on 08 maj 2016
 *         Updated on 21 maj 2016
 */
public class PriorityQueue {
    class Element{
        Graph.Vertex value;
        Element prev;
        Element next;
        Element(Graph.Vertex node){
            value = node;
            next = null;
        }
    }

    Element root;
    int size;

    PriorityQueue (){
        root = new Element(null);
        size = 0;
    }

    void add(Graph.Vertex node){
        Element element = root;
        Element newElement = new Element(node);
        while (element.next != null){
            if (element.next.value.compareTo(node) <= 0)
                element = element.next;
            else break;
        }
        newElement.next = element.next;
        newElement.prev = element;
        if (element.next != null) element.next.prev = newElement;
        element.next = newElement;
        size++;
    }

    Graph.Vertex poll(){
        Graph.Vertex n = root.next.value;
        root.next = root.next.next;
        size--;
        return n;
    }

    Element getElem(int index){
        Element n = root.next;
        for (int i = 0; i < index; i++){
            n = n.next;
        }
        return n;
    }

    Graph.Vertex get(int index){
        Element n = root.next;
        for (int i = 0; i < index; i++){
            n = n.next;
        }
        return n.value;
    }

    Graph.Vertex remove (int index){
        Element el = getElem(index);
        el.prev.next = el.next;
        el.next.prev = el.prev;
        return el.value;
    }

    void sort(){
        for (int i = 0; i < size; i++){
            if (i > 0){
                if (get(i).compareTo(get(i-1)) < 0){
                    Graph.Vertex v = remove(i);
                    add(v);
                }
            }
            if (i < size-1){
                if (get(i).compareTo(get(i+1)) > 0){
                    Graph.Vertex v = remove(i);
                    add(v);
                }
            }
        }
    }

    boolean contains(Graph.Vertex v){
        for (int i = 0; i < size; i++){
            if (get(i).equals(v))
                return true;
        }
        return false;
    }

    boolean isEmpty(){
        return size <= 0;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("[ ");
        Element el = root.next;
        while (el != null){
            s.append(el.value.toString()+", ");
            el = el.next;
        }
        s.deleteCharAt(s.length()-1);
        s.deleteCharAt(s.length()-1);
        s.append(" ]");
        return s.toString();
    }
}
