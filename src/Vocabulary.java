import java.util.Arrays;
import java.util.List;

public class Vocabulary {
    private static final List<Character> ALPHABET_RUS = Arrays.asList('а', 'б', 'в',
            'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
            'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»',
            ':', '!', '?', ' ');
    private static final List<Character> ALPHABET_ENG = Arrays.asList('a', 'b', 'c',
            'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z', '.', ',', '«', '»',
            ':', '!', '?', ' ');

    public static List<Character> getVocabulary(Language language) {
        switch (language) {
            case RUS:
                return ALPHABET_RUS;
            case ENG:
                return ALPHABET_ENG;
            default:
                return ALPHABET_RUS;
        }
    }
}
