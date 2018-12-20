import java.util.ArrayList;
import java.util.List;

public class Caesar {

    String[] alphabet = {"а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к", "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ъ", "ы", "ь", "э", "ю", "я"}; // алфавит
    String[] newAlphabet = alphabet.clone(); // шифрованный алфавит
    List<String> string = new ArrayList<>(); // перезапись исходной строки в список
    List<String> newString = new ArrayList<>(); // результат шифровки
    List<String> decryptString = new ArrayList<>(); // результат дешифровки
    List<String> newlist = new ArrayList<>(); // зашифрованный блок из 5 элементов строки
    String acc = ""; // техническая переменная
    int pos = 0; // размер строки
    int n = 2; // ключ
    boolean encryption = false; // флаг шифровки
    boolean decryption = false; // флаг дешифровки

    List<String> stringEncrypt(String str){ // перезапись исходной строки в список
        str = str.replaceAll("\\s+"," "); // удаляем лишние пробелы
        for (int i = 0; i < str.length(); i++) {
            char tmp = str.charAt(i); // дурацкий способ
            acc += tmp; // вызова элемента и запись как String
            string.add(acc);
            acc = ""; // обнуление переменной
        }
        return string;
    }

    String caesarEncrypt(List<String> strenc, int key) { // основной класс шифрования
        List<String> list = new ArrayList<>(); // блок из 5 исходных элементов
        boolean flag = false; // флаг_конец_исходной_строки

        newString = new ArrayList<>(); // обнуление старого результата
        n = key; // устанавливаем значение ключа в переменную ключа
        encryption = true; // определение, что сейчас идет шифровка
        for (pos = 0; pos < strenc.size(); pos++) { // шифрование
            for (String zzz : alphabet) { // сравниваем с алфавитом
                if (strenc.get(pos).equals(zzz)) { // если элемент строки - буква
                                                                                                                        //System.out.println("zzz = " + zzz);
                    list.add(strenc.get(pos)); // то запись
                }
            }
            if (strenc.get(pos).equals(" ")) { // если элемент строки - пробел
                                                                                                                        //System.out.println("zzz = " + zzz);
                list.add(" "); // то запись
            }
            /*if (pos == strenc.size() - 1){ // если мы дошли до последнего элемента (блочная шифровка)
                flag = true; // то flag включен
            }*/
            //if (list.size() == 5 || flag == true) { // когда размер листа становится равен 5 или мы дошли до конца строки (блочная шифровка)
                if(createNewAlphabet(list)){ // шифруем блок / с изменением переделанного алфавита
                }
            //}
                                                                                                                        //System.out.println("list = " + list);
            if (!newlist.isEmpty()) { // если newlist не пустой
                for (String s : newlist){
                    newString.add(s); // добавить к шифрованной строке newlist
                }
                newlist = new ArrayList<>(); // обнуление шифрованного блока
                list = new ArrayList<>(); // обнуление исходного блока
            }
        }
        encryption = false; // шифровка закончена
        string = new ArrayList<>(); // обнуление списка_строки
                                                                                                                        //System.out.println("size = " + newString.size());
        return String.join("", newString); // запись шифрованной строки как String
    }

    String caesarDecrypt(List<String> strenc, int key) { // основной класс дешифровки
        List<String> list = new ArrayList<>(); // блок из 5 исходных элементов
        boolean flag = false; // флаг_конец_исходной_строки

        newString = new ArrayList<>(); // обнуление старого результата
        n = key; // устанавливаем значение ключа в переменную ключа
        decryption = true; // определение, что сейчас идет дешифровка
        for (pos = 0; pos < strenc.size(); pos++) { // дешифровка
            for (String zzz : alphabet) { // сравниваем с алфавитом
                if (strenc.get(pos).equals(zzz)) { // если элемент строки - буква
                    list.add(strenc.get(pos)); // то запись
                }
            }
            if (strenc.get(pos).equals(" ")) { // если элемент строки - пробел
                                                                                                                        //System.out.println("zzz = " + zzz);
                list.add(" "); // то запись
            }
            /*if (pos == strenc.size() - 1){ // если мы дощли до последнего элемента (блочная шифровка)
                flag = true; // то flag включен
            }*/
            //if (list.size() == 5 || flag == true) { // когда размер листа становится равен 5 или мы дошли до конца строки (блочная шифровка)
                if(createNewAlphabet(list)){ // дешифровка блока / с изменением переделанного алфавита
                }
            //}
                                                                                                                        //System.out.println("list = " + list);
            if (!newlist.isEmpty()) { // если newlist не пустой
                for (String s : newlist){
                    decryptString.add(s); // добавить к дешифрованной строке newlist
                }
                newlist = new ArrayList<>(); // обнуление шифрованного блока
                list = new ArrayList<>(); // обнуление исходного блока
            }
        }
        decryption = false; // дешифровка закончена
                                                                                                                        //System.out.println("size = " + newString.size());
        return String.join("", decryptString); // запись дешифрованной строки как String
    }

    boolean createNewAlphabet (List<String> str){ // создание шифрованного алфавита
        int a = 0; // переменная первых букв алфавита переходящих в конец шифрованного алфавита
        int x = 0; // счетчик для записи в шифрованный алфавит

        //if (n == 25) { // создание бесконечного цикла (блочная шифровка)
        //    n = 2;
        //}
        for (int i = 0; i < alphabet.length; i++) {
            if(i + n < alphabet.length) { // если буква_+_ключ не превышают размер алфавита
                newAlphabet[x] = alphabet[i + n]; // то заменяем элемент шифрованного алфавита на этот
            }
            if(i + n >= alphabet.length) { // если буква_+_ключ превышают или равны размеру алфавита
                newAlphabet[x] = alphabet[a]; // // то заменяем элемент шифрованного алфавита 'a' (первые буквы алфавита ставим в конец)
                a++;
            }
            x++;
        }
        if (encryption) { // если сейчас идет шифровка
            for (int y = 0; y < str.size(); y++) { // то шифруем блок строки
                newlist.add(encrypt(str.get(y)));
            }
        }
        if (decryption){ // если сейчас идет дешифровка
            for(int y = 0; y < str.size(); y++) { // то дешифруем блок строки
                newlist.add(decrypt(str.get(y)));
            }
        }
        //n++; // (блочная шифровка)
        return true;
    }

    String encrypt (String str){ // шифрование одного блока
        String lll = "";

        for(int z = 0; z < alphabet.length; z++) {
            if(str.equals(alphabet[z])){ // если находим нужную букву в алфавите
                lll = newAlphabet[z]; // то добавляем эквивалентную букву шифрованного алфавита
            }
        }
        if (str.equals(" ")){ // добавляем пробелы
            lll = " ";
        }
        return lll;
    }

    String decrypt (String str){ // дешифровка одного блока
        String lll = "";

        for(int z = 0; z < newAlphabet.length; z++) {
            if(str.equals(newAlphabet[z])){ // если находим нужную букву в шифрованном алфавите
                lll = alphabet[z]; // то добавляем эквивалентную букву исходного алфавита
            }
        }
        if (str.equals(" ")){ // добавляем пробелы
            lll = " ";
        }
        return lll;
    }
}
