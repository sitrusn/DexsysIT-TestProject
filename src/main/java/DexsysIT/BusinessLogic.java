package DexsysIT;

public class BusinessLogic {

    public void businessLogic() {

        System.out.println("Введите команду (для помощи наберите help):");
        Reader reader = new Reader();
        String command = reader.getCommand();
        String[] keys = reader.getKeys();
        Methods methods = new Methods();

        while (!command.equals("exit")) {
            switch (command) {
                case ("anyMore"):
                    methods.anyMore(methods.listOther);
                    break;
                case ("print"):
                    methods.print(methods.map, keys);
                    break;
                case ("clear"):
                    methods.clear(methods.map, keys);
                    break;
                case ("merge"):
                    methods.merge(methods.map, methods.listOther);
                    break;
                case ("help"):
                    methods.help();
                    break;
                case ("initArray"):
                    methods.initArray(reader.getNumbers());
                    break;
                default:
                    System.out.println("Введите, пожалуйста, корректную команду:");
                    break;
            }
            command = reader.getCommand();
            keys = reader.getKeys();
        }
        System.out.println("Работа программы закончена.");
    }
}