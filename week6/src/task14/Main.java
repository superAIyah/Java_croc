package task14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //задаем пути файлам, с которыми работаем
        Scanner scanner = new Scanner(System.in);
        String prefix = "src/task14/";
        String ID = "ID";
        String History = "History";
        String pathID = prefix + ID;
        String pathHistory = prefix + History;

        HashMap<Integer, String> ids = new HashMap<Integer, String>(); // по ID фильма получить его название

        try(BufferedReader r = new BufferedReader((new FileReader(pathID)))) { // считываем соответствия ID -> названия
            String line;
            while ((line = r.readLine()) != null) {
                String[] splits = line.split(",");
                Integer id = Integer.parseInt(splits[0]);
                String name = splits[1];
                ids.put(id, name);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Файл "+pathID+" не найден");
        }

        ArrayList<String> lines = new ArrayList<>(); //считываем в список строк историю пользователей
        try(BufferedReader r = new BufferedReader((new FileReader(pathHistory)))) {
            String line;
            while ((line = r.readLine()) != null) {
                lines.add(line);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Файл "+pathHistory+" не найден");
        }

        int n = lines.size();
        ArrayList<Integer>[] interests = new ArrayList[n]; //массив списков истории пользователей
        for (int i = 0; i < n; i++) { // заполняем двуммерный массив, прееводя фильмы в int
            String[] nums = lines.get(i).split(",");
            interests[i] = new ArrayList<>();
            for (String num : nums)
                interests[i].add(Integer.parseInt(num));
        }

        System.out.println("Введите интересы пользователя через запятую без пробелов");
        String[] inputID = scanner.nextLine().split(",");
        ArrayList<Integer> watched = new ArrayList<Integer>(); // список фильмов, которые пользователь посмотрел
        for (int i = 0; i < inputID.length; i++)
            watched.add(Integer.parseInt(inputID[i]));

        ArrayList<Integer> sameInterest = new ArrayList<>(); // список индексов пользователей с похожими интересами
        HashSet<Integer> myFilms = new HashSet<>(watched); // множество просмотренных фильмов
        for (int i = 0; i < n; i++) {
            HashSet<Integer> films = new HashSet<>(interests[i]);
            films.retainAll(myFilms); // пересечение просмотров для определения схожести интересов
            if (films.size() >= watched.size() / 2.)
                sameInterest.add(i);
        }

        String bestFilm = "0";
        int maxView = 0;
        HashMap<Integer, Integer> timesWatched= new HashMap<>();
        // подсчет кол-ва просмотренных фильмов среди пользователей с похожими интересами
        for (int index : sameInterest) {
            for (int film : interests[index]) {
                Integer count = timesWatched.get(film);
                if (count == null)
                    count = 0;
                count++;
                if (count > maxView && !myFilms.contains(film)) {
                    maxView = count;
                    bestFilm = ids.get(film);
                }
                timesWatched.put(film, count);
            }
        }

        System.out.println(bestFilm);
    }
}
