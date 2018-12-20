public class Main {
    public static void main(String[] args) throws Exception {
        final String string = "€ростьижалость"; // строка дл€ шифровки // 14 элементов
        Caesar caesar = new Caesar();
        Analysis analysis = new Analysis();
        String newString = "";

        System.out.println("\n»сходна€ строка / Original string: \n" + string); // исходна€ строка
        System.out.println("\n»спользование шифра ÷езар€ / Using the Caesar cipher: ");
        newString = caesar.caesarEncrypt(caesar.stringEncrypt(string), 2);
        System.out.println(newString); // шифрование строки
        System.out.println("\nƒешифровка шифра ÷езар€ / Caesar cipher decryption: ");
        System.out.println(caesar.caesarDecrypt(caesar.stringEncrypt(newString), 2)); // дешифрование строки
        if (analysis.Analysis()){ // вывод частотных анализов

        }
    }
}

// шифрованный алфавит дл€ n = 2:
//  1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18   19   20   21   22   23   24   25   26   27   28   29   30   31   32   33
// "а", "б", "в", "г", "д", "е", "Є", "ж", "з", "и", "й", "к", "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ъ", "ы", "ь", "э", "ю", "€"
// "в", "г", "д", "е", "Є", "ж", "з", "и", "й", "к", "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ъ", "ы", "ь", "э", "ю", "€", "а", "б"

// после шифра ÷езар€ строка "€ростьижалость" будет зашифрованна как "бтруф€лйготхца" (при блочном шифровании)
// после шифра ÷езар€ строка "галактика" будет зашифрованна как "евнвмхлнг" (при блочном шифровании)
// р€д слов, букв или иных знаков, написанных или напечатанных в одну линию (работает!!!)