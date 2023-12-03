import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Satır sayısını giriniz: ");
        int satirSayisi = scanner.nextInt();

        System.out.print("Sütun sayısını giriniz: ");
        int sutunSayisi = scanner.nextInt();

        MineSweeper oyun = new MineSweeper(satirSayisi, sutunSayisi);
        oyun.oyunuBaslat();

    }
    }
