package task16;

import com.sun.source.tree.Tree;

import java.util.*;

public class Main {

    public static int getGroup(int age, ArrayList<Integer> bounds) {
        int group = 0;
        for (int bound : bounds)
            if (age <= bound)
                return group;
            else
                group++;
        return group;
    }

    public static void clearOutput(TreeMap<Integer, ArrayList<Person> > match, ArrayList<Integer> bounds) {
        for (int key : match.keySet()) {
            int group = -key;
            // предельные случаи
            if (group == bounds.size()) { // если человек превосходит все границы
                int ageLimit = bounds.get(bounds.size() - 1) + 1;
                String people = match.get(key).toString().replaceAll("[\\[\\]]", "");
                System.out.printf("%d+: %s%n", ageLimit, people);
                continue;
            }
            if (group == 0) { // если человек меньше всех границ
                int ageLimit = bounds.get(0);
                String people = match.get(key).toString().replaceAll("[\\[\\]]", "");
                System.out.printf("0-%d: %s%n", ageLimit, people);
                continue;
            }
            // общий случай вывода
            int ageLimit1 = bounds.get(group - 1) + 1;
            int ageLimit2 = bounds.get(group);
            String people = match.get(key).toString().replaceAll("[\\[\\]]", "");
            System.out.printf("%d-%d: %s%n", ageLimit1, ageLimit2, people);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        String[] strBounds = line.split(" ");

        ArrayList<Integer> bounds = new ArrayList<Integer>(); // считываем массив разделителей возрастов
        for (String bound : strBounds) {
            System.out.println(bound);
            bounds.add(Integer.parseInt(bound));
        }

        ArrayList<Person> people = new ArrayList<>();
        while (true) { // считываем людей в массив
            line = scanner.nextLine();
            if (line.equals("END"))
                break;
            String[] fioAge = line.split(", ");
            people.add(new Person(fioAge[0], Integer.parseInt(fioAge[1])));
        }

        TreeMap<Integer, ArrayList<Person> > match = new TreeMap<>(); // TreeMap упорядочивает ключи
        for (Person p : people) {
            int group = -getGroup(p.age, bounds); // упорядочивание нужно в порядке убивания, поэтому со знаком "-"
            try {
                match.get(group).add(p);
            }
            catch (Exception e) {
                match.put(group, new ArrayList<>(List.of(p)));
            }
        }

        for (int key : match.keySet())  // сортируем людей в словаре
            match.get(key).sort(new PersonComparator());

        clearOutput(match, bounds); // выводим итоговые категории возрастов

    }


}
