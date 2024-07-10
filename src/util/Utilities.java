package util;

import java.util.*;

/**
 * <b>
 *     Class representing a collection of utility methods
 * </b>
 *
 * <p>
 *     This class contains utility methods for collections(sets, lists, maps, etc.)
 *     and arrays <br>

 *     This class is immutable <br>
 *     This class is final <br>
 * </p>
 *
 * @author  22013393
 * @version 1.0
 */
public final class Utilities {

    /**
     * <b>
     *     Constructor of the class
     * </b>
     *
     * <p>
     *     This constructor is private because this class is final and immutable
     * </p>
     */
    private Utilities() {}

    /**
     * Returns a boolean indicating if the array contains the element
     * @param array the array
     * @param element the element
     * @return true if the array contains the element
     */
    public static boolean contains(Object[] array, Object element) {
        if (array == null) {
            return false;
        }
        for (Object object : array) {
            if (object.equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a boolean indicating if the array contains the element
     * @param array the array
     * @param element the element
     * @return true if the array contains the element
     */
    public static boolean contains(int[] array, int element) {
        if (array == null) {
            return false;
        }
        for (int object : array) {
            if (object == element) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a boolean indicating if the array contains the element
     * @param array the array
     * @param element the element
     * @return true if the array contains the element
     */
    public static boolean contains(float[] array, float element) {
        if (array == null) {
            return false;
        }
        for (float object : array) {
            if (object == element) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a boolean indicating if the array contains the element
     * @param array the array
     * @param element the element
     * @return true if the array contains the element
     */
    public static boolean contains(double[] array, double element) {
        if (array == null) {
            return false;
        }
        for (double object : array) {
            if (object == element) {
                return true;
            }
        }
        return false;
    }



    /**
     * Return all sublists of a list
     *
     * @param <T> the type of the list elements
     * @param list the list to get the sublists from
     *             (the list must not be null)
     * @return all sublists of the list
     *        (the returned list is not null)
     *        (the returned list is not empty)
     * @throw IllegalArgumentException if the list is null
     */
    public static <T> List<List<T>> subLists(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("The list must not be null");
        }
        List<List<T>> subLists = new ArrayList<List<T>>();
        subLists.add(new ArrayList<T>());
        for (T t : list) {
            List<List<T>> newSubLists = new ArrayList<List<T>>();
            for (List<T> subList : subLists) {
                List<T> newSubList = new ArrayList<T>(subList);
                newSubList.add(t);
                newSubLists.add(newSubList);
            }
            subLists.addAll(newSubLists);
        }
        return subLists;
    }

    /**
     * Return all subsets of a set
     *
     * @param <T> the type of the set elements
     * @param set the set to get the subsets from
     *            (the set must not be null)
     * @return all subsets of the set
     *       (the returned set is not null)
     *       (the returned set is not empty)
     * @throw IllegalArgumentException if the set is null
     */
    public static <T> Set<Set<T>> subsets(Set<T> set) {
        if (set == null) {
            throw new IllegalArgumentException("The set must not be null");
        }
        Set<Set<T>> subsets = new HashSet<Set<T>>();
        subsets.add(new HashSet<>());
        for (T t : set) {
            Set<Set<T>> newSubsets = new HashSet<Set<T>>();
            for (Set<T> subset : subsets) {
                Set<T> newSubset = new HashSet<T>(subset);
                newSubset.add(t);
                newSubsets.add(newSubset);
            }
            subsets.addAll(newSubsets);
        }
        return subsets;
    }

    /**
     * Return a random element of a collection
     *
     * @param <T> the type of the elements
     * @param collection the collection to get the random element from
     *                   (the collection must not be null)
     * @return a random element of the iterable
     * @throw IllegalArgumentException if the collection is null
     */
    public static <T> T randomElement(Collection<T> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("The collection must not be null");
        }
        int index = (int) (Math.random() * collection.size());
        List<T> list = new ArrayList<>(collection);
        return list.get(index);
    }


    /**
     * Retourne un booléen indiquant si un nombre appartient à un intervalle
     *
     * @param number le nombre
     * @param min le minimum de l'intervalle
     * @param max le maximum de l'intervalle
     * @return true si le nombre appartient à l'intervalle
     */
    public static boolean isBetween(int number, int min, int max) {
        return number >= min && number <= max;
    }

}
