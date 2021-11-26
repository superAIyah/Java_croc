package task15;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        FilterByPredicate filter = new FilterByPredicate() { }; // Анонимный класс с дефолт методом

        Predicate<String> PredWordCheck = new Predicate<String>() { // предикат, отбирающий комментарии без цифр
            @Override
            public boolean test(String comment) {
                Pattern pattern = Pattern.compile("[0-9]+");
                Matcher matcher = pattern.matcher(comment);
                return !matcher.find();
            }
        };

        String com1 = "This is a comment\n" + // создадим комментарии
                "1234 - that has numbers";
        String com2 = "this comment has bad words";
        String com3 = "Normal\n"+
                "comment\n"+
                "correct for all predicates!";

        ArrayList<String> comments = new ArrayList<>(List.of(com1, com2, com3));  // создадим список комментариев
        HashSet<String> badWords = new HashSet<>(List.of("bad", "good", "word")); // создадим черный список слов

        System.out.println("TEST1"); // тестируем
        List<String> res1 = filter.filterComments(comments, PredWordCheck);
        for (String s : res1)
            System.out.println(s);
        System.out.println();
        System.out.println("TEST2");
        List<String> res2 = filter.filterComments(comments, new PredBlackList(badWords));
        for (String s : res2)
            System.out.println(s);
    }
}
