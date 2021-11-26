package task15;

import java.util.HashSet;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PredBlackList implements Predicate<String> {
    HashSet<String> blackList;
    public PredBlackList(HashSet<String> blackList) {
        this.blackList = blackList;
    }

    @Override
    public boolean test(String comment) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+"); //паттерн для выделения слов
        Matcher matcher = pattern.matcher(comment); //выделяем из комментарий слова
        while (matcher.find()) {  // идем по всем найденным словам
            String word = matcher.group().toLowerCase(Locale.ROOT); // приводим к нижнему регистру
            if (blackList.contains(word)) { // если слово в черном списке -> удаляем комментарий
                return false;
            }
        }
        return true;
    }
}
