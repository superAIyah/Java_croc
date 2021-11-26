package task16;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        if (p1.age != p2.age)
            return Integer.compare(-p1.age, -p2.age); // сортировка возрастов в порядке убывания
        else
            return p1.name.compareTo(p2.name); // сортировка имен
    }
}
