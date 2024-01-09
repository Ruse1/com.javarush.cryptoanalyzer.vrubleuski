public class Message {
    public static final String MODE = "Выберите режим, где:\n1 - режим шифрования\n" +
            "2 - режим дешифрования\n3 - режим взлом \"Brute Force\"\nexit - для выхода";
    public static final String ERROR = "Вы ввели не число, попробуйте снова.";
    public static final String MISTAKE = "Такого режима не существует, попробуйте снова.";
    public static final String INPUT_PATH_DECRYPTION = "Введите путь файла формата .txt который требуется расшифровать.";
    public static final String INPUT_PATH_ENCRYPTION = "Введите путь файла формата .txt который требуется зашифровать.";
    public static final String OUTPUT_PATH_DECRYPTION = "Введите путь файла куда расшифровать данные:";
    public static final String OUTPUT_PATH_ENCRYPTION = "Введите путь файла куда зашифровать данные:";
    public static final String WRONG_FORMAT = "Неправильный формат файла %s, требуется txt\n";
    public static final String NOT_EXIST = "Файла не существует или указан неправильный путь!";
    public static final String KEY_ENCRYPTION = "Введите ключ шифрования:";
    public static final String KEY_ERROR = "Вы ввели не число в поле ключа, попробуйте все заново";
    public static final String ENCRYPTION_READY = "Файл зашифрован!";
    public static final String DECRYPTION_READY = "Файл расшифрован!";
    public static final String VOCABULARY = "Выберите язык текста для шифрования, размер букв не имеет значения. Напечатав:\n" +
            "Русский\n" + "Английский";
    public static final String RUS = "Выбран \"Русский язык\"";
    public static final String ENG = "Выбран \"Английский язык\"";
    public static final String DEFAULT = "Выбран язык по умолчанию т.к. указанного словаря не существует";

    public static final String WARN_BRUTE_FORCE = "Убедитесь, что данный файл пустой, иначе файл будет расшифрован в конец " +
            "предыдущего текста.";
}
