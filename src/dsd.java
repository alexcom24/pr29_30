import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class dsd {
    static void Text(final String input) {
        asList( // используется для возврата списка фиксированного размера, подкрепленного указанным массивом.
                requireNonNull(input, "Строка не может быть пустой")
                        .toLowerCase() // в нижний регистр слова
                        .split("[\\p{Blank}\\p{Punct}]+") // разделить по пробелу, с учётом знаков препинания
        )
                .stream() // создание стрима АПИ
                .collect(groupingBy(s -> s, counting()))// собирание в коллекцию Map, где слово - ключ, значение - количество этого слова в тексте
                // конвертация Map в стрим АПИ
                .entrySet()
                .stream()
                // сортировка отображения по количеству слов в обратном порядке, вывод на экран
                .sorted(
                        (e0, e1) -> {
                            final int res = e1.getValue().compareTo(e0.getValue());
                            return res == 0 ? e0.getKey().compareTo(e1.getKey()) : res;
                        }
                )
                .limit(10) // ограничение в 10 уникальных слов
                // вывод каждого объекта в консоль
                .forEach(System.out::println);
    }
}
