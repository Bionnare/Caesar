public class Main {
    public static void main(String[] args) throws Exception {
        final String string = "��������������"; // ������ ��� �������� // 14 ���������
        Caesar caesar = new Caesar();
        Analysis analysis = new Analysis();
        String newString = "";

        System.out.println("\n�������� ������ / Original string: \n" + string); // �������� ������
        System.out.println("\n������������� ����� ������ / Using the Caesar cipher: ");
        newString = caesar.caesarEncrypt(caesar.stringEncrypt(string), 2);
        System.out.println(newString); // ���������� ������
        System.out.println("\n���������� ����� ������ / Caesar cipher decryption: ");
        System.out.println(caesar.caesarDecrypt(caesar.stringEncrypt(newString), 2)); // ������������ ������
        if (analysis.Analysis()){ // ����� ��������� ��������

        }
    }
}

// ����������� ������� ��� n = 2:
//  1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18   19   20   21   22   23   24   25   26   27   28   29   30   31   32   33
// "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�"
// "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�", "�"

// ����� ����� ������ ������ "��������������" ����� ������������ ��� "��������������" (��� ������� ����������)
// ����� ����� ������ ������ "���������" ����� ������������ ��� "���������" (��� ������� ����������)
// ��� ����, ���� ��� ���� ������, ���������� ��� ������������ � ���� ����� (��������!!!)