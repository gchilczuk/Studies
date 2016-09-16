/**
 * @author Grzegorz Chilczuk
 *         Created on 08 maj 2016
 */
public class PriorityQueue {
    class Element{
        Node value;
//        Element prev;
        Element next;
        Element(Node node){
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

    void add(Node node){
        Element element = root;
        Element newElement = new Element(node);
        while (element.next != null){
            if (element.next.value.compareTo(node) <= 0)
                element = element.next;
            else break;
        }
        newElement.next = element.next;
        element.next = newElement;
        size++;
    }

    void addf(Node node){
        Element element = root;
        Element newElement = new Element(node);
        while (element.next != null){
            if (element.next.value.compareTo(node) < 0)
                element = element.next;
            else break;
        }
        newElement.next = element.next;
        element.next = newElement;
        size++;
    }

    Node poll(){
        Node n = root.next.value;
        root.next = root.next.next;
        size--;
        return n;
    }

    Node get(int index){
        Element n = root.next;
        for (int i = 1; i <= index; i++){
            n = n.next;
        }
        return n.value;
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
