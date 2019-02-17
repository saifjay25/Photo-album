package model;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * @author Saif Jame
 * @author Philip Aquilina
 */
public class asisster {
    public static <x> boolean findOne(Collection<x> tCollection, Predicate<x> predicate) {
        return tCollection.stream().anyMatch(predicate);
    }
    public static <x extends Comparable<x>> boolean addOrRetrieveOrderedList(List<x> l, x element) {
        ListIterator<x> repeat = l.listIterator();
        while (true) {
            if (!repeat.hasNext()) {
                repeat.add(element);
                return true;
            }
            x elementInList = repeat.next();
            if (elementInList.compareTo(element) > 0) {
                repeat.previous();
                repeat.add(element);
                return true;
            }
            if (elementInList.compareTo(element) == 0) {
                return false;
            }
        }
    }
    public static <x,w> List<x> filter(Collection<x> tCollection, w u, BiPredicate<x,w> biPredicate) {
        return tCollection.stream().filter(t -> biPredicate.test(t, u)).collect(Collectors.toList());
    }
    public static <x,w> List<w> map(Collection<x> tCollection, Function<x,w> f) {
        return tCollection.stream().map(f).collect(Collectors.toList());
    }
    public static <x extends Comparable<x>, K> x delete(List<x> m, K k, BiPredicate<x,K> p) {
        ListIterator<x> repeat = m.listIterator();
        while (repeat.hasNext()) {
            x y = repeat.next();
            if (p.test(y, k)) {
                repeat.remove();
                return y;
            }
        }
        return null;
    }
    public static <x extends Comparable<x>, h> x get(List<x> q, h k, BiPredicate<x,h> i) {
        return q.stream().filter(t -> i.test(t, k)).findFirst().orElse(null);
    }
    public static <x extends Comparable<x>> boolean search(List<x> source, List<x> rule, BiPredicate<x,x> bp) {
        boolean equal = true;
        for (x c : rule) {
            boolean thumbs= source.stream().anyMatch(s -> bp.test(s, c));
            if (!thumbs) {
                equal = false;
                break;
            }
        }
        return equal;
    }
}