package DexsysIT;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class Methods {

    public Map<String, List<Integer>> map = new HashMap<>();
    public List<Integer> listOther = new ArrayList<>();

    @MethodDescription(info = "initArray  - Инициализация массива")
    public void initArray(List<Integer> numbers) {
        map.put("X", numbers.stream().filter(num -> num % 3 == 0).collect(Collectors.toList()));
        map.put("S", numbers.stream().filter(num -> num % 7 == 0).collect(Collectors.toList()));
        map.put("M", numbers.stream().filter(num -> num % 21 == 0).collect(Collectors.toList()));
        listOther = numbers.stream().filter(num -> num % 21 != 0)
                .filter(num -> num % 7 != 0)
                .filter(num -> num % 3 != 0)
                .collect(Collectors.toList());
        System.out.println("Данные проинициализированы \n" +
                "Введите следующую команду.");
    }

    @MethodDescription(info = "anyMore    - выводит на экран были ли значения не вошедшие ни в один список. Возможные значения true, false")
    public void anyMore(List<Integer> listOther) {
        try {
            System.out.println(!listOther.isEmpty());
        } catch (NullPointerException e) {
            System.out.println("false");
        }
    }

    @MethodDescription(info = "print      - печать всех списков \n" +
            "print type - печать конкретного списка, где type принимает значения X,S,M")
    public void print(Map<String, List<Integer>> map, String[] args) {
        for (String str : args) {
            if (map.get(str) == null || map.get(str).isEmpty()) {
                System.out.println("Список " + str + " пуст");
            } else {
                System.out.println(map.get(str));
            }
        }
    }

    @MethodDescription(info = "clear type - чистка списка , где type принимает значения X,S,M")
    public void clear(Map<String, List<Integer>> map, String[] args) {
        for (String str : args) {
            map.put(str, null);
        }
        System.out.println("Соответствующие списки удалены");
    }

    @MethodDescription(info = "merge      - слить все списки в один вывести на экран и очистить все списки")
    public void merge(Map<String, List<Integer>> map, List<Integer> listOther) {
        System.out.println("Все списки разом:");
        for (Map.Entry entry : map.entrySet()) {
            System.out.print(entry.getValue() + " ");
        }
        System.out.println("");
        for (String str : map.keySet()) {
            map.put(str, null);
        }
        listOther.clear();
        System.out.println("Все списки очищены:");
    }

    @MethodDescription(info = "help       - распечатывает доступный список команд с параметрами")
    public void help() {
        Class<Methods> methodsClass = Methods.class;
        Method[] methods = methodsClass.getDeclaredMethods();
        List<Annotation> annotations = Arrays.stream(methods).flatMap(method -> Arrays.stream(method.getAnnotations())).collect(Collectors.toList());
        for (Annotation annotation : annotations) {
            System.out.println(((MethodDescription) annotation).info());
        }
    }
}