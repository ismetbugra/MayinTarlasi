import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    private int satirSayisi;
    private int sutunSayisi;
    private int[][] mayinKonumlari;
    private boolean[][] acilanNoktalar;
    private boolean oyunDevamEdiyor;

    public MineSweeper(int satirSayisi, int sutunSayisi) {
        this.satirSayisi = satirSayisi;
        this.sutunSayisi = sutunSayisi;
        this.mayinKonumlari = new int[satirSayisi][sutunSayisi];
        this.acilanNoktalar = new boolean[satirSayisi][sutunSayisi];
        this.oyunDevamEdiyor = true;

        mayinlariYerlestir();
    }

    private void mayinlariYerlestir() {
        Random random = new Random();
        int mayinSayisi = satirSayisi * sutunSayisi / 4;

        for (int i = 0; i < mayinSayisi; i++) {
            int randomSatir = random.nextInt(satirSayisi);
            int randomSutun = random.nextInt(sutunSayisi);

            // Eğer seçilen konumda zaten mayın varsa, tekrar seçilmesi
            while (mayinKonumlari[randomSatir][randomSutun] == -1) {
                randomSatir = random.nextInt(satirSayisi);
                randomSutun = random.nextInt(sutunSayisi);
            }

            mayinKonumlari[randomSatir][randomSutun] = -1; // -1 mayını temsil eder
        }
    }

    public void oyunuBaslat() {
        Scanner scanner = new Scanner(System.in);

        while (oyunDevamEdiyor) {
            oyunTahtasiniGoster();
            System.out.print("Satır seçiniz: ");
            int satir = scanner.nextInt();

            System.out.print("Sütun seçiniz: ");
            int sutun = scanner.nextInt();

            if (!gecerliNokta(satir, sutun)) {
                System.out.println("Geçersiz nokta! Lütfen tekrar seçiniz.");
                continue;
            }

            if (mayinKonumlari[satir][sutun] == -1) {
                oyunuKaybet();
            } else {
                mayinCevresindekiPuanlariHesapla(satir, sutun);
            }

            if (kazandinizMi()) {
                oyunuKazan();
            }
        }

    }

    private boolean gecerliNokta(int satir, int sutun) {
        return satir >= 0 && satir < satirSayisi && sutun >= 0 && sutun < sutunSayisi && !acilanNoktalar[satir][sutun];
    }

    private void mayinCevresindekiPuanlariHesapla(int satir, int sutun) {
        int puan = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int yeniSatir = satir + i;
                int yeniSutun = sutun + j;

                if (gecerliNokta(yeniSatir, yeniSutun) && mayinKonumlari[yeniSatir][yeniSutun] == -1) {
                    puan++;
                }
            }
        }

        mayinKonumlari[satir][sutun] = puan;
        acilanNoktalar[satir][sutun] = true;
    }

    private void oyunuKazan() {
        oyunTahtasiniGoster();
        System.out.println("Tebrikler! Oyunu kazandınız.");
        oyunDevamEdiyor = false;
    }

    private void oyunuKaybet() {
        oyunTahtasiniGoster();
        System.out.println("Oyunu kaybettiniz. Mayına bastınız!");
        oyunDevamEdiyor = false;
    }

    private boolean kazandinizMi() {
        for (int i = 0; i < satirSayisi; i++) {
            for (int j = 0; j < sutunSayisi; j++) {
                if (mayinKonumlari[i][j] != -1 && !acilanNoktalar[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void oyunTahtasiniGoster() {
        System.out.println("Oyun Tahtası:");
        for (int i = 0; i < satirSayisi; i++) {
            for (int j = 0; j < sutunSayisi; j++) {
                if (acilanNoktalar[i][j]) {
                    System.out.print(mayinKonumlari[i][j] + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
}
