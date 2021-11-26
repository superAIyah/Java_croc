package task15;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public interface FilterByPredicate {

    default <T> List<T> filterComments(Iterable<T> collection, Predicate<T> pred) {
        ArrayList<T> correct = new ArrayList<>();

        for (T element : collection) { // отбираем комментарии, соответствующие предикату
            if (pred.test(element))
                correct.add(element);
        }
        return correct;
    }
}