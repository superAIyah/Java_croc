package task13;

import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        // создаем комментарии для теста
        String comment1 = "This is comment" +
                "\nThis is a string" +
                "\nThis is a row";
        String comment2 = "cow goes moo";
        String comment3 = "I like" +
                "\nprogramming" +
                "\non Java";
        String comment4 = "I am a bad Student";

       HashSet<String> badWords = new HashSet<>();
        ArrayList<String> comments = new ArrayList<String>();
        //зададим слова в черный список, обязательно в нижнем регистре
        badWords.add("bad");
        badWords.add("moo");
        comments.add(comment1);
        comments.add(comment2);
        comments.add(comment3);
        comments.add(comment4);
        Filter filter = new Filter();
        filter.filterComments(comments, badWords);
        for (int i = 0; i < comments.size(); i++) { // выводим отфильтрованные комментарии
            System.out.println("--------------- " + (i+1));
            System.out.println(comments.get(i));
        }
    }
}
