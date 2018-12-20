import java.util.*;
import java.io.FileReader;
import java.io.FileWriter;


public class Analysis {
    Map<String, Integer> letters = new HashMap<>(); // ��������� ������� ���������
    String[] alphabet = {"�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�"}; // �������
    List <String> basestrings = new ArrayList<>(); // ������ ��� �������� ���� ���������� ������� ��������� ������
    List <String> basebigramme = new ArrayList<>(); // ������ ��� �������� ������� ��������� ������
    List <String> strings = new ArrayList<>(); // ������ ��� �������� ���� ���������� �������
    List <Integer> integers = new ArrayList<>();  // ������ ��� �������� �������� ���������� �������

    public boolean Analysis() throws Exception {
        FileReader fr = new FileReader("�.�. ������� - ����� � ���.txt"); // ������ ���������
        FileReader strfr = new FileReader("����� �.�. ������� - ����� � ���.txt"); // ������ ����� ���������
        FileReader textfr = new FileReader("������������ �����.txt"); // ������ ������������� ������
        FileWriter fw = new FileWriter("��������� ������ �.�. ������� - ����� � ���.txt"); // ������ � ���� ���-�� ���� ���������
        FileWriter partfw = new FileWriter("��������� ������ ����� �.�. ������� - ����� � ���.txt"); // ������ � ���� ���-�� ���� ����� ���������
        FileWriter strfw = new FileWriter("������������� ����� �.�. ������� - ����� � ���.txt"); // ������ � ���� �������������� ����� ���������
        FileWriter testfw = new FileWriter("������������� ����� �.�. ������� - ����� � ���.txt"); // ������ � ���� �������������� ����� ���������
        FileWriter textfw = new FileWriter("������������� ������������ �����.txt"); // ������ � ���� �������������� ������������� ������
        FileWriter textpartfw = new FileWriter("��������� ������ ������������� ������.txt"); // ������ � ���� ���-�� ���� ������������� ������
        FileWriter bfw = new FileWriter("������� ������� ������������� ������.txt"); // ������ � ���� ������� ������������� ������
        FileWriter textbfw = new FileWriter("���������� ���������� ����� �.�. ������� - ����� � ���.txt"); // ������ � ���� �������������� ���������� ����� ���������
        FileWriter bbfw = new FileWriter("������� ������� �.�. ������� - ����� � ���.txt"); // ������ � ���� ������� ���������
        FileWriter partbfw = new FileWriter("������� ������� ����� �.�. ������� - ����� � ���.txt"); // ������ � ���� ������� ����� ���������
        Scanner in = new Scanner(fr);
        Scanner strin = new Scanner(strfr);
        Scanner textin = new Scanner(textfr);
        Caesar caesar = new Caesar();
        List <String> textList = new ArrayList<>(); // ������ ��������� ������
        String text = ""; // ������ ������ �� ����� � ������
        String acc = ""; // �����. ����������



/////////////////////////������ � ����������/////////////////////////
        while (in.hasNextLine()) { // ������ �� �����
            text += in.nextLine();
        }
        text = text.toLowerCase(); // ��� ����� ���������� ���������
        text = text.replaceAll("\\s+"," "); // ������� ������ �������
        for (int m = 0; m < text.length(); m++) { // ������ � ������
            char a = text.charAt(m);
            acc += a;
            for (String st : alphabet){ // ������ ����� �� ��������
                if (acc.equals(st)){
                    textList.add(acc);
                }
            }
            acc = "";
        }
                                                                                                                        //System.out.println("textList = " + textList);
        for (int p = 0; p < textList.size(); p++) { // ������ � map ����� ��������� ������
            if (!letters.containsKey(textList.get(p))) { // ���� ������ ����� ��� �� ���������
                                                                                                                        //System.out.println("acc = " + textList.get(p));
                letters.put(textList.get(p), 1); // �� ��������� � map � ��������� �������� "1"
            }
            else { // ���� ������ ����� ��� ���� � map
                int n = letters.get(textList.get(p));
                letters.put(textList.get(p), n+1); // �������������� �������� ������ �����
            }
        }
        for (Map.Entry<String, Integer> l : letters.entrySet()) { // ���������� ���������� ������� ���������
            String key = l.getKey();
            int value = l.getValue();
            boolean isAdded = false; // ���� ���������� � ����� ������
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
            String lineSeparator = System.getProperty("line.separator"); // ������� ������
            fw.write(z + " " + basestrings.get(z) + " = " + integers.get(z) + lineSeparator); // ����� ���������� ������� ��������� � ����
        }
        fr.close();
        fw.close();
        letters.clear();
        integers = new ArrayList<>();

        for (int p = 0; p < textList.size(); p++) { // ������ � map �������� ��������� ������
            acc += textList.get(p);
            if (acc.length() == 2) {
                if (!letters.containsKey(acc)) { // ���� ������ �������� ��� �� ���������
                                                                                                                        //System.out.println("acc = " + acc);
                    letters.put(acc, 1); // �� ��������� � map � ��������� �������� "1"
                    acc = "";
                } else { // ���� ������ �������� ��� ���� � map
                    int n = letters.get(acc);
                    letters.put(acc, n + 1); // �������������� �������� ������ ��������
                    acc = "";
                }
            }
        }
        for (Map.Entry<String, Integer> l : letters.entrySet()) { // ���������� ������� ���������
            String key = l.getKey();
            int value = l.getValue();
            boolean isAdded = false; // ���� ���������� � ����� ������
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
            String lineSeparator = System.getProperty("line.separator"); // ������� ������
            bbfw.write(z + " " +  basebigramme.get(z) + " = " + integers.get(z) + lineSeparator); // ����� ���������� ������� ����� ��������� � ����
        }
        bbfw.close();
        text = "";
        integers = new ArrayList<>();
/////////////////////////������ � ����������/////////////////////////



/////////////////////////������ � ������ ���������/////////////////////////
        while (strin.hasNextLine()) { // ������ �� ����� ����� ���������
            text += strin.nextLine();
        }
        text = text.toLowerCase(); // ��� ����� ���������� ���������
        text = text.replaceAll("\\s+"," "); // ������� ������ �������
        text = caesar.caesarEncrypt(caesar.stringEncrypt(text), 2); // �������� ����� ���������
        strfw.write(text); // ����� ��������������� ����� ��������� � ����
        textList = new ArrayList<>();
        letters.clear();
        for (int m = 0; m < text.length(); m++) { // ������ � ������
            char a = text.charAt(m);
            acc += a;
            for (String st : alphabet){ // ������ ����� �� ��������
                if(acc.equals(st)){
                    textList.add(acc);
                }
            }
            acc = "";
        }
        for (int p = 0; p < textList.size(); p++) { // ������ � map ����� ����� ���������
            if (!letters.containsKey(textList.get(p))) { // ���� ������ ����� ��� �� ���������
                                                                                                                        //System.out.println("acc = " + textList.get(p));
                letters.put(textList.get(p), 1); // �� ��������� � map � ��������� �������� "1"
            }
            else { // ���� ������ ����� ��� ���� � map
                int n = letters.get(textList.get(p));
                letters.put(textList.get(p), n+1); // �������������� �������� ������ �����
            }
        }
        for (Map.Entry<String, Integer> l : letters.entrySet()) { // ���������� ���������� ������� ����� ���������
            String key = l.getKey();
            int value = l.getValue();
            boolean isAdded = false; // ���� ���������� � ����� ������
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
            String lineSeparator = System.getProperty("line.separator"); // ������� ������
            partfw.write( z + " " + strings.get(z) + " = " + integers.get(z) + lineSeparator); // ����� ���������� ������� ����� ��������� � ����
        }
        strfr.close();
        strfw.close();
        partfw.close();

        String tmp = text; // ���������� �������������� ����� ���������
        //text = caesar.caesarDecrypt(caesar.stringEncrypt(text), 2); // ���������� ����� ��������� �������_1 (��� �������)
        text = dercryption(caesar.stringEncrypt(text), strings); // ���������� ����� ��������� �������_2 (���� �� �������)
        testfw.write(text); // ����� �������������� ����� ���������
        testfw.close();
        text = "";
        integers = new ArrayList<>();
        strings = new ArrayList<>();
        letters.clear();

        for (int p = 0; p < textList.size(); p++) { // ������ � map �������� ����� ���������
            acc += textList.get(p);
            if (acc.length() == 2) {
                if (!letters.containsKey(acc)) { // ���� ������ �������� ��� �� ���������
                                                                                                                        //System.out.println("acc = " + acc);
                    letters.put(acc, 1); // �� ��������� � map � ��������� �������� "1"
                    acc = "";
                } else { // ���� ������ �������� ��� ���� � map
                    int n = letters.get(acc);
                    letters.put(acc, n + 1); // �������������� �������� ������ ��������
                    acc = "";
                }
            }
        }
        for (Map.Entry<String, Integer> l : letters.entrySet()) { // ���������� ������� ����� ���������
            String key = l.getKey();
            int value = l.getValue();
            boolean isAdded = false; // ���� ���������� � ����� ������
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
            String lineSeparator = System.getProperty("line.separator"); // ������� ������
            partbfw.write(z + " " + strings.get(z) + " = " + integers.get(z) + lineSeparator); // ����� ���������� ������� ����� ��������� � ����
        }
        partbfw.close();

        text = bidercryption(caesar.stringEncrypt(tmp), strings); // ���������� ���������� ����� ���������
        textbfw.write(text); // ����� �������������� ����� ���������
        textbfw.close();
        text = "";
/////////////////////////������ � ������ ���������/////////////////////////



/////////////////////////������ � ������������ �������/////////////////////////
        while (textin.hasNextLine()) { // ������ �� ����� ������������� ������
            text += textin.nextLine();
        }
        text = text.toLowerCase(); // ��� ����� ���������� ���������
        text = text.replaceAll("\\s+"," "); // ������� ������ �������
        text = caesar.caesarEncrypt(caesar.stringEncrypt(text), 2); // �������� ������������� ������
        textfw.write(text); // ����� ��������������� ������������� ������ � ����
        textList = new ArrayList<>();
        letters.clear();
        integers = new ArrayList<>();
        strings = new ArrayList<>();
        for (int m = 0; m < text.length(); m++) { // ������ � ������
            char a = text.charAt(m);
            acc += a;
            for (String st : alphabet){ // ������ ����� �� ��������
                if(acc.equals(st)){
                    textList.add(acc);
                }
            }
            acc = "";
        }
        for (int p = 0; p < textList.size(); p++) { // ������ � map ����� ������������� ������
            if (!letters.containsKey(textList.get(p))) { // ���� ������ ����� ��� �� ���������
                                                                                                                        //System.out.println("acc = " + textList.get(p));
                letters.put(textList.get(p), 1); // �� ��������� � map � ��������� �������� "1"
            }
            else { // ���� ������ ����� ��� ���� � map
                int n = letters.get(textList.get(p));
                letters.put(textList.get(p), n+1); // �������������� �������� ������ �����
            }
        }
        for (Map.Entry<String, Integer> l : letters.entrySet()) { // ���������� ���������� ������� ������������� ������
            String key = l.getKey();
            int value = l.getValue();
            boolean isAdded = false; // ���� ���������� � ����� ������
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
            String lineSeparator = System.getProperty("line.separator"); // ������� ������
            textpartfw.write(z + " " + strings.get(z) + " = " + integers.get(z) + lineSeparator); // ����� ���������� ������� ������������� ������ � ����
        }
        textfr.close();
        textfw.close();
        textpartfw.close();
        letters.clear();
        integers = new ArrayList<>();
        strings = new ArrayList<>();

        for (int p = 0; p < textList.size(); p++) { // ������ � map �������� ������������� ������
            acc += textList.get(p);
            if (acc.length() == 2) {
                if (!letters.containsKey(acc)) { // ���� ������ �������� ��� �� ���������
                                                                                                                        //System.out.println("acc = " + acc);
                    letters.put(acc, 1); // �� ��������� � map � ��������� �������� "1"
                    acc = "";
                } else { // ���� ������ �������� ��� ���� � map
                    int n = letters.get(acc);
                    letters.put(acc, n + 1); // �������������� �������� ������ ��������
                    acc = "";
                }
            }
        }
        for (Map.Entry<String, Integer> l : letters.entrySet()) { // ���������� ������� ������������� ������
            String key = l.getKey();
            int value = l.getValue();
            boolean isAdded = false; // ���� ���������� � ����� ������
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
            String lineSeparator = System.getProperty("line.separator"); // ������� ������
            bfw.write(z + " " + strings.get(z) + " = " + integers.get(z) + lineSeparator); // ����� ���������� ������� ������������� ������ � ����
        }
        bfw.close();
        return true;
    }
/////////////////////////������ � ������������ �������/////////////////////////



    String dercryption (List<String> strenc, List<String> newAlphabet){ // ���������� �������_2
        List <String> str = new ArrayList<>();

        for(int x=0; x < strenc.size(); x++){
            for(int y = 0; y < newAlphabet.size(); y++){
                if(strenc.get(x).equals(newAlphabet.get(y))){
                    str.add(basestrings.get(y));
                    break;
                }
            }
            if(strenc.get(x).equals(" ")){ // ��������� �������
                str.add(" ");
            }
        }
        return String.join("", str); // ������ ������������� ������ ��� String
    }

    String bidercryption (List<String> strenc, List<String> newAlphabet){ // ���������� ����������
        List <String> str = new ArrayList<>();
        String acc = "";

        for(int x = 0; x < strenc.size(); x++){
            if (strenc.get(x).equals(" ")){ // ��������� �������
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
        return String.join("", str); // ������ ������������� ������ ��� String
    }
}






















