import java.util.*;
import java.io.FileReader;
import java.io.FileWriter;


public class Analysis {
    Map<String, Integer> letters = new HashMap<>(); // результат анализа источника
    String[] alphabet = {"а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к", "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ъ", "ы", "ь", "э", "ю", "я"}; // алфавит
    List <String> basestrings = new ArrayList<>(); // список для хранения букв частотного анализа исходного текста
    List <String> basebigramme = new ArrayList<>(); // список для хранения биграмм исходного текста
    List <String> strings = new ArrayList<>(); // список для хранения букв частотного анализа
    List <Integer> integers = new ArrayList<>();  // список для хранения значений частотного анализа

    public boolean Analysis() throws Exception {
        FileReader fr = new FileReader("Л.Н. ТОЛСТОЙ - ВОЙНА И МИР.txt"); // чтение источника
        FileReader strfr = new FileReader("Часть Л.Н. ТОЛСТОЙ - ВОЙНА И МИР.txt"); // чтение куска источника
        FileReader textfr = new FileReader("Произвольный текст.txt"); // чтение произвольного текста
        FileWriter fw = new FileWriter("Частотный анализ Л.Н. ТОЛСТОЙ - ВОЙНА И МИР.txt"); // запись в файл кол-во букв источника
        FileWriter partfw = new FileWriter("Частотный анализ части Л.Н. ТОЛСТОЙ - ВОЙНА И МИР.txt"); // запись в файл кол-во букв куска источника
        FileWriter strfw = new FileWriter("Зашифрованная часть Л.Н. ТОЛСТОЙ - ВОЙНА И МИР.txt"); // запись в файл зашифрованного куска источника
        FileWriter testfw = new FileWriter("Дешифрованная часть Л.Н. ТОЛСТОЙ - ВОЙНА И МИР.txt"); // запись в файл дешифрованного куска источника
        FileWriter textfw = new FileWriter("Зашифрованный произвольный текст.txt"); // запись в файл зашифрованного произвольного текста
        FileWriter textpartfw = new FileWriter("Частотный анализ произвольного текста.txt"); // запись в файл кол-во букв произвольного текста
        FileWriter bfw = new FileWriter("Таблица биграмм произвольного текста.txt"); // запись в файл биграмм произвольного текста
        FileWriter textbfw = new FileWriter("Дешифровка биграммами части Л.Н. ТОЛСТОЙ - ВОЙНА И МИР.txt"); // запись в файл дешифрованного биграммами куска источника
        FileWriter bbfw = new FileWriter("Таблица биграмм Л.Н. ТОЛСТОЙ - ВОЙНА И МИР.txt"); // запись в файл биграмм источника
        FileWriter partbfw = new FileWriter("Таблица биграмм части Л.Н. ТОЛСТОЙ - ВОЙНА И МИР.txt"); // запись в файл биграмм куска источника
        Scanner in = new Scanner(fr);
        Scanner strin = new Scanner(strfr);
        Scanner textin = new Scanner(textfr);
        Caesar caesar = new Caesar();
        List <String> textList = new ArrayList<>(); // список элементов текста
        String text = ""; // запись текста из файла в строку
        String acc = ""; // допол. переменная



/////////////////////////РАБОТА С ИСТОЧНИКОМ/////////////////////////
        while (in.hasNextLine()) { // чтение из файла
            text += in.nextLine();
        }
        text = text.toLowerCase(); // все буквы становятся строчными
        text = text.replaceAll("\\s+"," "); // удаляем лишние пробелы
        for (int m = 0; m < text.length(); m++) { // запись в список
            char a = text.charAt(m);
            acc += a;
            for (String st : alphabet){ // только буквы из алфавита
                if (acc.equals(st)){
                    textList.add(acc);
                }
            }
            acc = "";
        }
                                                                                                                        //System.out.println("textList = " + textList);
        for (int p = 0; p < textList.size(); p++) { // запись в map буквы исходного текста
            if (!letters.containsKey(textList.get(p))) { // если данная буква еще не записанна
                                                                                                                        //System.out.println("acc = " + textList.get(p));
                letters.put(textList.get(p), 1); // то добавляем в map и указываем значение "1"
            }
            else { // если данная буква уже есть в map
                int n = letters.get(textList.get(p));
                letters.put(textList.get(p), n+1); // инкрементируем значение данной буквы
            }
        }
        for (Map.Entry<String, Integer> l : letters.entrySet()) { // сортировка частотного анализа источника
            String key = l.getKey();
            int value = l.getValue();
            boolean isAdded = false; // флаг добавления в конец списка
            for (int i = 0; i < integers.size(); i++) {
                if (value > integers.get(i)) {
                    integers.add(i, value);
                    basestrings.add(i, key);
                    isAdded = true;
                    break;
                }
            }
            if (!isAdded) {
                integers.add(value);
                basestrings.add(key);
            }
        }
        for (int z = 0; z < basestrings.size(); z++){
            String lineSeparator = System.getProperty("line.separator"); // перенос строки
            fw.write(z + " " + basestrings.get(z) + " = " + integers.get(z) + lineSeparator); // вывод частотного анализа источника в файл
        }
        fr.close();
        fw.close();
        letters.clear();
        integers = new ArrayList<>();

        for (int p = 0; p < textList.size(); p++) { // запись в map биграммы исходного текста
            acc += textList.get(p);
            if (acc.length() == 2) {
                if (!letters.containsKey(acc)) { // если данная биграмма еще не записанна
                                                                                                                        //System.out.println("acc = " + acc);
                    letters.put(acc, 1); // то добавляем в map и указываем значение "1"
                    acc = "";
                } else { // если данная биграмма уже есть в map
                    int n = letters.get(acc);
                    letters.put(acc, n + 1); // инкрементируем значение данной биграммы
                    acc = "";
                }
            }
        }
        for (Map.Entry<String, Integer> l : letters.entrySet()) { // сортировка биграмм источника
            String key = l.getKey();
            int value = l.getValue();
            boolean isAdded = false; // флаг добавления в конец списка
            for (int i = 0; i < integers.size(); i++) {
                if (value > integers.get(i)) {
                    integers.add(i, value);
                    basebigramme.add(i, key);
                    isAdded = true;
                    break;
                }
            }
            if (!isAdded) {
                integers.add(value);
                basebigramme.add(key);
            }
        }
        for (int z = 0; z < basebigramme.size(); z++){
            String lineSeparator = System.getProperty("line.separator"); // перенос строки
            bbfw.write(z + " " +  basebigramme.get(z) + " = " + integers.get(z) + lineSeparator); // вывод частотного анализа куска источника в файл
        }
        bbfw.close();
        text = "";
        integers = new ArrayList<>();
/////////////////////////РАБОТА С ИСТОЧНИКОМ/////////////////////////



/////////////////////////РАБОТА С КУСКОМ ИСТОЧНИКА/////////////////////////
        while (strin.hasNextLine()) { // чтение из файла куска источника
            text += strin.nextLine();
        }
        text = text.toLowerCase(); // все буквы становятся строчными
        text = text.replaceAll("\\s+"," "); // удаляем лишние пробелы
        text = caesar.caesarEncrypt(caesar.stringEncrypt(text), 2); // шифровка куска источника
        strfw.write(text); // вывод зашифрованнного куска источника в файл
        textList = new ArrayList<>();
        letters.clear();
        for (int m = 0; m < text.length(); m++) { // запись в список
            char a = text.charAt(m);
            acc += a;
            for (String st : alphabet){ // только буквы из алфавита
                if(acc.equals(st)){
                    textList.add(acc);
                }
            }
            acc = "";
        }
        for (int p = 0; p < textList.size(); p++) { // запись в map буквы куска источника
            if (!letters.containsKey(textList.get(p))) { // если данная буква еще не записанна
                                                                                                                        //System.out.println("acc = " + textList.get(p));
                letters.put(textList.get(p), 1); // то добавляем в map и указываем значение "1"
            }
            else { // если данная буква уже есть в map
                int n = letters.get(textList.get(p));
                letters.put(textList.get(p), n+1); // инкрементируем значение данной буквы
            }
        }
        for (Map.Entry<String, Integer> l : letters.entrySet()) { // сортировка частотного анализа куска источника
            String key = l.getKey();
            int value = l.getValue();
            boolean isAdded = false; // флаг добавления в конец списка
            for (int i = 0; i < integers.size(); i++) {
                if (value > integers.get(i)) {
                    integers.add(i, value);
                    strings.add(i, key);
                    isAdded = true;
                    break;
                }
            }
            if (!isAdded) {
                integers.add(value);
                strings.add(key);
            }
        }
        for (int z = 0; z < strings.size(); z++){
            String lineSeparator = System.getProperty("line.separator"); // перенос строки
            partfw.write( z + " " + strings.get(z) + " = " + integers.get(z) + lineSeparator); // вывод частотного анализа куска источника в файл
        }
        strfr.close();
        strfw.close();
        partfw.close();

        String tmp = text; // сохранение зашифрованного куска источника
        //text = caesar.caesarDecrypt(caesar.stringEncrypt(text), 2); // дешифровка куска источника методом_1 (при блочном)
        text = dercryption(caesar.stringEncrypt(text), strings); // дешифровка куска источника методом_2 (если не блочный)
        testfw.write(text); // вывод дешифрованного куска источника
        testfw.close();
        text = "";
        integers = new ArrayList<>();
        strings = new ArrayList<>();
        letters.clear();

        for (int p = 0; p < textList.size(); p++) { // запись в map биграммы куска источника
            acc += textList.get(p);
            if (acc.length() == 2) {
                if (!letters.containsKey(acc)) { // если данная биграмма еще не записанна
                                                                                                                        //System.out.println("acc = " + acc);
                    letters.put(acc, 1); // то добавляем в map и указываем значение "1"
                    acc = "";
                } else { // если данная биграмма уже есть в map
                    int n = letters.get(acc);
                    letters.put(acc, n + 1); // инкрементируем значение данной биграммы
                    acc = "";
                }
            }
        }
        for (Map.Entry<String, Integer> l : letters.entrySet()) { // сортировка биграмм куска источника
            String key = l.getKey();
            int value = l.getValue();
            boolean isAdded = false; // флаг добавления в конец списка
            for (int i = 0; i < integers.size(); i++) {
                if (value > integers.get(i)) {
                    integers.add(i, value);
                    strings.add(i, key);
                    isAdded = true;
                    break;
                }
            }
            if (!isAdded) {
                integers.add(value);
                strings.add(key);
            }
        }
        for (int z = 0; z < strings.size(); z++){
            String lineSeparator = System.getProperty("line.separator"); // перенос строки
            partbfw.write(z + " " + strings.get(z) + " = " + integers.get(z) + lineSeparator); // вывод частотного анализа куска источника в файл
        }
        partbfw.close();

        text = bidercryption(caesar.stringEncrypt(tmp), strings); // дешифровка биграммами куска источника
        textbfw.write(text); // вывод дешифрованного куска источника
        textbfw.close();
        text = "";
/////////////////////////РАБОТА С КУСКОМ ИСТОЧНИКА/////////////////////////



/////////////////////////РАБОТА С ПРОИЗВОЛЬНЫМ ТЕКСТОМ/////////////////////////
        while (textin.hasNextLine()) { // чтение из файла произвольного текста
            text += textin.nextLine();
        }
        text = text.toLowerCase(); // все буквы становятся строчными
        text = text.replaceAll("\\s+"," "); // удаляем лишние пробелы
        text = caesar.caesarEncrypt(caesar.stringEncrypt(text), 2); // шифровка произвольного текста
        textfw.write(text); // вывод зашифрованнного произвольного текста в файл
        textList = new ArrayList<>();
        letters.clear();
        integers = new ArrayList<>();
        strings = new ArrayList<>();
        for (int m = 0; m < text.length(); m++) { // запись в список
            char a = text.charAt(m);
            acc += a;
            for (String st : alphabet){ // только буквы из алфавита
                if(acc.equals(st)){
                    textList.add(acc);
                }
            }
            acc = "";
        }
        for (int p = 0; p < textList.size(); p++) { // запись в map буквы произвольного текста
            if (!letters.containsKey(textList.get(p))) { // если данная буква еще не записанна
                                                                                                                        //System.out.println("acc = " + textList.get(p));
                letters.put(textList.get(p), 1); // то добавляем в map и указываем значение "1"
            }
            else { // если данная буква уже есть в map
                int n = letters.get(textList.get(p));
                letters.put(textList.get(p), n+1); // инкрементируем значение данной буквы
            }
        }
        for (Map.Entry<String, Integer> l : letters.entrySet()) { // сортировка частотного анализа произвольного текста
            String key = l.getKey();
            int value = l.getValue();
            boolean isAdded = false; // флаг добавления в конец списка
            for (int i = 0; i < integers.size(); i++) {
                if (value > integers.get(i)) {
                    integers.add(i, value);
                    strings.add(i, key);
                    isAdded = true;
                    break;
                }
            }
            if (!isAdded) {
                integers.add(value);
                strings.add(key);
            }
        }
        for (int z = 0; z < strings.size(); z++){
            String lineSeparator = System.getProperty("line.separator"); // перенос строки
            textpartfw.write(z + " " + strings.get(z) + " = " + integers.get(z) + lineSeparator); // вывод частотного анализа произвольного текста в файл
        }
        textfr.close();
        textfw.close();
        textpartfw.close();
        letters.clear();
        integers = new ArrayList<>();
        strings = new ArrayList<>();

        for (int p = 0; p < textList.size(); p++) { // запись в map биграммы произвольного текста
            acc += textList.get(p);
            if (acc.length() == 2) {
                if (!letters.containsKey(acc)) { // если данная биграмма еще не записанна
                                                                                                                        //System.out.println("acc = " + acc);
                    letters.put(acc, 1); // то добавляем в map и указываем значение "1"
                    acc = "";
                } else { // если данная биграмма уже есть в map
                    int n = letters.get(acc);
                    letters.put(acc, n + 1); // инкрементируем значение данной биграммы
                    acc = "";
                }
            }
        }
        for (Map.Entry<String, Integer> l : letters.entrySet()) { // сортировка биграмм произвольного текста
            String key = l.getKey();
            int value = l.getValue();
            boolean isAdded = false; // флаг добавления в конец списка
            for (int i = 0; i < integers.size(); i++) {
                if (value > integers.get(i)) {
                    integers.add(i, value);
                    strings.add(i, key);
                    isAdded = true;
                    break;
                }
            }
            if (!isAdded) {
                integers.add(value);
                strings.add(key);
            }
        }
        for (int z = 0; z < strings.size(); z++){
            String lineSeparator = System.getProperty("line.separator"); // перенос строки
            bfw.write(z + " " + strings.get(z) + " = " + integers.get(z) + lineSeparator); // вывод частотного анализа произвольного текста в файл
        }
        bfw.close();
        return true;
    }
/////////////////////////РАБОТА С ПРОИЗВОЛЬНЫМ ТЕКСТОМ/////////////////////////



    String dercryption (List<String> strenc, List<String> newAlphabet){ // дешифровка методом_2
        List <String> str = new ArrayList<>();

        for(int x=0; x < strenc.size(); x++){
            for(int y = 0; y < newAlphabet.size(); y++){
                if(strenc.get(x).equals(newAlphabet.get(y))){
                    str.add(basestrings.get(y));
                    break;
                }
            }
            if(strenc.get(x).equals(" ")){ // добавляем пробелы
                str.add(" ");
            }
        }
        return String.join("", str); // запись дешифрованной строки как String
    }

    String bidercryption (List<String> strenc, List<String> newAlphabet){ // дешифровка биграммами
        List <String> str = new ArrayList<>();
        String acc = "";

        for(int x = 0; x < strenc.size(); x++){
            if (strenc.get(x).equals(" ")){ // добавляем пробелы
                str.add(" ");
            }
            else {
                acc += strenc.get(x);
                if (acc.length() == 2){
                    for(int y = 0; y < newAlphabet.size(); y++){
                        if(acc.equals(newAlphabet.get(y))){
                            str.add(basebigramme.get(y));
                            acc = "";
                            break;
                        }
                    }
                }
            }
        }
        return String.join("", str); // запись дешифрованной строки как String
    }
}






















