import java.util.Objects;

/**
 * Define some useful methods
 *
 * @author Grzegorz Chilczuk
 */
public abstract class AbstractList<E> implements List<E>  {

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append('[');
        if (!isEmpty()) {
            Iterator iterator = iterator();
            int c =0;
            for (iterator.first(); !iterator.isDone(); iterator.next())
                buffer.append(iterator.current()).append(", ");
            buffer.setLength(buffer.length() - 2);
        }
        buffer.append(']');
        return buffer.toString();
    }
    /*public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append('[');
        if (!isEmpty()) {
            Iterator<E> iterator = iterator();
            System.out.println("test");
            for (iterator.first(); !iterator.isDone(); iterator.next()) {
                buffer.append(iterator.current()).append(", ");
                System.out.println("test");
            }
                buffer.setLength(buffer.length() - 2);
        }
        buffer.append(']');
        return buffer.toString();
    }*/

    @Override
    public int hashCode() {
        int hashCode = 0;
        Iterator i = iterator();
        for (i.first(); !i.isDone(); i.next())
            hashCode ^= i.current().hashCode();
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractList<E> abstractList = ((AbstractList)o);
        if(abstractList.size() != this.size()) return false;
        Iterator i = iterator();
        Iterator j = abstractList.iterator();
        for(i.first(), j.first();
            !i.isDone() && !j.isDone() && i.current().equals(j.current());
            i.next(), j.next());
        return i.isDone() && j.isDone();
    }


}
