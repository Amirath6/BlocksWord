package planning;


import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * <b>
 * A class representing a limited size queue
 * </b>
 *
 * <p>
 * A limited size queue is a queue that can only contain a limited number of elements.
 * Each element is smaller than the previous one.
 * </p>
 *
 * @author <a href="mailto:22013393@etu.unicaen.fr">KITSOUKOU Manne Emile</a>
 * @author <a href="mailto:22208735@etu.unicaen.fr">ZEKZEKHNINI Cheyma</a>
 * @author <a href="mailto:22107869@etu.unicaen.fr">KISSAMI SAFAE</a>
 * @author <a href="mailto:22012235@etu.unicaen.fr">OROU-GUIDOU Amirath Farah</a>
 * @version 1.0
 */
public class LimitedSizeQueue<T> extends PriorityQueue<T> {

    /**
     * The maximum size of the queue
     */
    private final int maxSize;

    /**
     * Constructor of the class
     *
     * @param maxSize the maximum size of the queue
     */
    public LimitedSizeQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public LimitedSizeQueue(int maxSize, Comparator<? super T> comparator) {
        super(comparator);
        this.maxSize = maxSize;
    }

    /**
     * Add an element to the queue if it is smaller than the biggest element of the queue
     * and if the queue is not full yet (if the queue is full, the biggest element is removed)
     *
     * @param element the element to add
     * @return true if the element has been added, false otherwise
     */
    @Override
    public boolean add(T element) {
        if (size() < maxSize) {
            return super.add(element);
        } else {
            boolean added = super.add(element);
            // If the element has been added, Search the last element and remove it
            if (added) {
                T lastElement = null;
                for (T e : this) {
                    lastElement = e;
                }
                super.remove(lastElement);
                added = !Objects.equals(lastElement, element);
            }
            return added;
        }
    }
}



