import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Алгоритм поиска в ширину
 *
 * Граф, по которому делаю, в книге "Грокаем алгоритмы"
 *
 * Ищем продавца манго. Пусть продавцом манго считается тот,
 * у кого имя заканчивается на определенную букву.
 */

public class Task3 {

    public static void main(String[] args) {

        Map<String, String[]> friends = new LinkedHashMap<>();
        friends.put("Me", new String[] { "Bob", "Clair", "Alice" });
        friends.put("Bob", new String[] { "Anuj", "Peggie"});
        friends.put("Clair", new String[] { "Tom", "Johnny"});
        friends.put("Alice", new String[] { "Peggie"});
        friends.put("Peggie", new String[] {});
        friends.put("Anuj", new String[] {});
        friends.put("Johnny", new String[] {});
        friends.put("Tom", new String[] {});

        String letter = "nny";

        System.out.println(findSeller(friends, letter));
    }

    /*
    метод принимает реализацию направленного графа - хэш-таблицу,
    и букву, которая идентифицирует продавца манго
     */
    private static String findSeller(Map<String, String[]> graph, String letter) {

        // получаем узлы графа и преобразуем в ArrayList,
        // чтобы последовательно пройтись по всем узлам
        List<String> people = new ArrayList<>(graph.keySet());

        Queue<String> queue = new LinkedList<>(); // очередь для проверки на продавца
        Set<String> checkedPeople = new HashSet<>(); // здесь хранятся люди, которых мы уже проверили

        // добавляем в очередь знакомых первого участника
        // берем здесь первый элемент из keySet и используем
        // как ключ для мапы graph. этот результат помещаем в очередь,
        // преобразовав в коллекцию
        int personNumber = 0;
        queue.addAll(Arrays.asList(graph.get(people.get(personNumber))));

        // пока очередь не опустеет и пока счетчик людей не превышен
        while(!queue.isEmpty() & personNumber < people.size()) {
            String currentPerson = queue.poll();
            if(checkedPeople.contains(currentPerson)) continue;     // если такого человека уже проверяли, пропускаем его

            if(currentPerson.endsWith(letter)) { // если имя этого человека оканчивается на указанную букву
                return currentPerson;
            } else {
                personNumber++; // будем добавлять соседей следующего человека
                checkedPeople.add(currentPerson);
                queue.addAll(Arrays.asList(graph.get(people.get(personNumber))));
            }

        }

        return "there is no such person";
    }




}
