package Beer;
import Beer.EmptyQueueException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Queue;


public class PQueue<T extends Comparable> implements Iterable<T>{
    private final ArrayList<T> Queue;
    private Comparator<T> Comparable;

    public PQueue(){
        Queue=new ArrayList<T>();
    }
    public void push(T t){
        Queue.add(t);
        Queue.sort(Comparable);
    }
    public T pop() throws EmptyQueueException{
        return Queue.remove(Queue.size()-1);
    }
    public T top() throws EmptyQueueException{
        return Queue.get(Queue.size()-1);
    }
    public int size(){
        return Queue.size();
    }
    public void clear(){
        Queue.clear();
    }

    public Iterator<T> iterator(){
        return Queue.iterator();
    }

}
