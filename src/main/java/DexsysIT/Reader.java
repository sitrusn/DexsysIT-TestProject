package DexsysIT;

import java.util.*;

public class Reader {

    private Scanner in = new Scanner(System.in);
    private String str;
    private String command;
    private String[] keys;
    private List<Integer> numbers = new ArrayList<>();

    public void checkNumbersForValidVulues() {
        System.out.println("Введите целые числа через пробел:");
        String str = in.nextLine();
        boolean isInputOK = false;
        while (!isInputOK) {
            if (str.matches("(\\d+\\s{1})*\\d+")) {
                isInputOK = true;
            } else {
                System.out.println("Вводите, пожалуйста, числа по шаблону. \n" +
                        "Num Num Num и т.д.");
                str = in.nextLine();
            }
        }
        String[] num = str.split(" ");
        numbers.clear();
        for (String strNum : num){
            numbers.add(Integer.valueOf(strNum));
        }
        numbers.sort(Comparator.naturalOrder());
    }

    public void distillCommandFromInputString() {
        str = in.nextLine();
        if (str.contains(" ")) {
            command = str.split(" ")[0];
        } else {
            command = str;
        }
    }

    public void checkKeysForValidValues() {
        str = str.trim();
        if (str.contains(" ")) {
            boolean isKeysOK = false;
            while (!isKeysOK) {

                String commandParameters;
                try {
                    commandParameters = str.substring(str.indexOf(" ")).trim();
                } catch (StringIndexOutOfBoundsException e){
                    commandParameters = "X S M";
                }

                if (commandParameters.matches("([MXS]\\s*)+")) {
                    keys = commandParameters.split(" ");
                    isKeysOK = true;
                } else {
                    System.out.println("Вводите, пожалуйста, ключи по следующему шаблону: \n" +
                            "команда Key Key Key \n" +
                            "где Key - одна из букв X, S или M");
                    isKeysOK = false;
                    str = in.nextLine();
                }
            }
        } else {
            keys = new String[]{
                    "X", "S", "M"
            };
        }
    }

    public List<Integer> getNumbers() {
        checkNumbersForValidVulues();
        return numbers;
    }

    public String getCommand() {
        distillCommandFromInputString();
        return command;
    }

    public String[] getKeys() {
        checkKeysForValidValues();
        return keys;
    }
}