import java.util.ArrayList;
import java.util.List;

public class Caesar {

    String[] alphabet = {"�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�"}; // �������
    String[] newAlphabet = alphabet.clone(); // ����������� �������
    List<String> string = new ArrayList<>(); // ���������� �������� ������ � ������
    List<String> newString = new ArrayList<>(); // ��������� ��������
    List<String> decryptString = new ArrayList<>(); // ��������� ����������
    List<String> newlist = new ArrayList<>(); // ������������� ���� �� 5 ��������� ������
    String acc = ""; // ����������� ����������
    int pos = 0; // ������ ������
    int n = 2; // ����
    boolean encryption = false; // ���� ��������
    boolean decryption = false; // ���� ����������

    List<String> stringEncrypt(String str){ // ���������� �������� ������ � ������
        str = str.replaceAll("\\s+"," "); // ������� ������ �������
        for (int i = 0; i < str.length(); i++) {
            char tmp = str.charAt(i); // �������� ������
            acc += tmp; // ������ �������� � ������ ��� String
            string.add(acc);
            acc = ""; // ��������� ����������
        }
        return string;
    }

    String caesarEncrypt(List<String> strenc, int key) { // �������� ����� ����������
        List<String> list = new ArrayList<>(); // ���� �� 5 �������� ���������
        boolean flag = false; // ����_�����_��������_������

        newString = new ArrayList<>(); // ��������� ������� ����������
        n = key; // ������������� �������� ����� � ���������� �����
        encryption = true; // �����������, ��� ������ ���� ��������
        for (pos = 0; pos < strenc.size(); pos++) { // ����������
            for (String zzz : alphabet) { // ���������� � ���������
                if (strenc.get(pos).equals(zzz)) { // ���� ������� ������ - �����
                                                                                                                        //System.out.println("zzz = " + zzz);
                    list.add(strenc.get(pos)); // �� ������
                }
            }
            if (strenc.get(pos).equals(" ")) { // ���� ������� ������ - ������
                                                                                                                        //System.out.println("zzz = " + zzz);
                list.add(" "); // �� ������
            }
            /*if (pos == strenc.size() - 1){ // ���� �� ����� �� ���������� �������� (������� ��������)
                flag = true; // �� flag �������
            }*/
            //if (list.size() == 5 || flag == true) { // ����� ������ ����� ���������� ����� 5 ��� �� ����� �� ����� ������ (������� ��������)
                if(createNewAlphabet(list)){ // ������� ���� / � ���������� ������������� ��������
                }
            //}
                                                                                                                        //System.out.println("list = " + list);
            if (!newlist.isEmpty()) { // ���� newlist �� ������
                for (String s : newlist){
                    newString.add(s); // �������� � ����������� ������ newlist
                }
                newlist = new ArrayList<>(); // ��������� ������������ �����
                list = new ArrayList<>(); // ��������� ��������� �����
            }
        }
        encryption = false; // �������� ���������
        string = new ArrayList<>(); // ��������� ������_������
                                                                                                                        //System.out.println("size = " + newString.size());
        return String.join("", newString); // ������ ����������� ������ ��� String
    }

    String caesarDecrypt(List<String> strenc, int key) { // �������� ����� ����������
        List<String> list = new ArrayList<>(); // ���� �� 5 �������� ���������
        boolean flag = false; // ����_�����_��������_������

        newString = new ArrayList<>(); // ��������� ������� ����������
        n = key; // ������������� �������� ����� � ���������� �����
        decryption = true; // �����������, ��� ������ ���� ����������
        for (pos = 0; pos < strenc.size(); pos++) { // ����������
            for (String zzz : alphabet) { // ���������� � ���������
                if (strenc.get(pos).equals(zzz)) { // ���� ������� ������ - �����
                    list.add(strenc.get(pos)); // �� ������
                }
            }
            if (strenc.get(pos).equals(" ")) { // ���� ������� ������ - ������
                                                                                                                        //System.out.println("zzz = " + zzz);
                list.add(" "); // �� ������
            }
            /*if (pos == strenc.size() - 1){ // ���� �� ����� �� ���������� �������� (������� ��������)
                flag = true; // �� flag �������
            }*/
            //if (list.size() == 5 || flag == true) { // ����� ������ ����� ���������� ����� 5 ��� �� ����� �� ����� ������ (������� ��������)
                if(createNewAlphabet(list)){ // ���������� ����� / � ���������� ������������� ��������
                }
            //}
                                                                                                                        //System.out.println("list = " + list);
            if (!newlist.isEmpty()) { // ���� newlist �� ������
                for (String s : newlist){
                    decryptString.add(s); // �������� � ������������� ������ newlist
                }
                newlist = new ArrayList<>(); // ��������� ������������ �����
                list = new ArrayList<>(); // ��������� ��������� �����
            }
        }
        decryption = false; // ���������� ���������
                                                                                                                        //System.out.println("size = " + newString.size());
        return String.join("", decryptString); // ������ ������������� ������ ��� String
    }

    boolean createNewAlphabet (List<String> str){ // �������� ������������ ��������
        int a = 0; // ���������� ������ ���� �������� ����������� � ����� ������������ ��������
        int x = 0; // ������� ��� ������ � ����������� �������

        //if (n == 25) { // �������� ������������ ����� (������� ��������)
        //    n = 2;
        //}
        for (int i = 0; i < alphabet.length; i++) {
            if(i + n < alphabet.length) { // ���� �����_+_���� �� ��������� ������ ��������
                newAlphabet[x] = alphabet[i + n]; // �� �������� ������� ������������ �������� �� ����
            }
            if(i + n >= alphabet.length) { // ���� �����_+_���� ��������� ��� ����� ������� ��������
                newAlphabet[x] = alphabet[a]; // // �� �������� ������� ������������ �������� 'a' (������ ����� �������� ������ � �����)
                a++;
            }
            x++;
        }
        if (encryption) { // ���� ������ ���� ��������
            for (int y = 0; y < str.size(); y++) { // �� ������� ���� ������
                newlist.add(encrypt(str.get(y)));
            }
        }
        if (decryption){ // ���� ������ ���� ����������
            for(int y = 0; y < str.size(); y++) { // �� ��������� ���� ������
                newlist.add(decrypt(str.get(y)));
            }
        }
        //n++; // (������� ��������)
        return true;
    }

    String encrypt (String str){ // ���������� ������ �����
        String lll = "";

        for(int z = 0; z < alphabet.length; z++) {
            if(str.equals(alphabet[z])){ // ���� ������� ������ ����� � ��������
                lll = newAlphabet[z]; // �� ��������� ������������� ����� ������������ ��������
            }
        }
        if (str.equals(" ")){ // ��������� �������
            lll = " ";
        }
        return lll;
    }

    String decrypt (String str){ // ���������� ������ �����
        String lll = "";

        for(int z = 0; z < newAlphabet.length; z++) {
            if(str.equals(newAlphabet[z])){ // ���� ������� ������ ����� � ����������� ��������
                lll = alphabet[z]; // �� ��������� ������������� ����� ��������� ��������
            }
        }
        if (str.equals(" ")){ // ��������� �������
            lll = " ";
        }
        return lll;
    }
}
