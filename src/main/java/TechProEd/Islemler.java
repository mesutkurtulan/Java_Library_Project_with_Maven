package TechProEd;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class Islemler {
    public void anaMenu() throws SQLException, ClassNotFoundException {
        System.out.println("========================================");
        System.out.println("\t\tKUTUPHANE YONETIM SISTEMI");
        System.out.println("========================================");
        System.out.println("1-UYE KAYIT \n2-UYE GIRIS \n3-ADMIN GIRIS \nQ-CIKIS\nLutfen Yapmak Istediginiz Islemi Seciniz:");
        Scanner scan = new Scanner(System.in);
        String anaMenuSecim = scan.next().toUpperCase();
        switch (anaMenuSecim){
            case "1":
                uyeKayit();
                break;
            case "2":
                uyeGiris();
                break;
            case "3":
                adminGiris();
                break;
            case "Q":
                cikis();
                break;
            default:{
                System.out.println("Hatali Secim Yaptiniz");
                anaMenu();
            }
        }
    }
    /*==============================================
                    uyeKayit Methodu
    ==============================================*/
    private void uyeKayit() throws SQLException, ClassNotFoundException {
        DatabaseDQLIslemleri.selectAccountDetails(); // kullaniciIDList ve kullaniciMailList'in Güncellenmesi için
        String eMail ="";
        do {
            Scanner scan = new Scanner(System.in);
            System.out.println("Lutfen Kullanici Gmail Adresinizi Giriniz\nNot:Kullanıcı email adresi @gmail.com içermelidir");
            eMail = scan.next().toLowerCase();
            if (!eMail.contains("@")) {
                System.out.println("Kullanici Gmail Adresi @ karakteri Icermelidir");
            } else if (!eMail.contains("@gmail.com")) {
                System.out.println("Kullanici Gmail Adresi 'gmail.com' domain'i Icermelidir");
            } else if (!eMail.endsWith("@gmail.com")) {
                System.out.println("Kullanici Gmail Adresi 'gmail.com' ile bitmelidir");
            } else if (DatabaseDQLIslemleri.kullaniciMailList.contains(eMail)) {
                System.out.println("Girdiğiniz Kullanici Email'i Sistemimizde Mevcuttur.\nNOT; UYE GIRIS modülünü kullanabilirsiniz");
            }
        } while (!eMail.contains("@") || !eMail.contains("@gmail.com") || !eMail.endsWith("@gmail.com")|| DatabaseDQLIslemleri.kullaniciMailList.contains(eMail));
        System.out.println("Kullanici Gmail Adresiniz Geçerlidir.");
        String sifre = "";
        String AlphaCaps = "ABCÇDEFGĞHIİJKLMNOÖPQRSŞTUÜVWXYZ";
        String Alpha = "abcçdefgğhıijklmnoöpqrsştuüvwxyz";
        String Numeric = "0123456789";
        String specialChar = "!'^+%&/()=?.:,;_-+*£#$½{[]}|><";
        int alphaCapsVar = 0;
        int alphaVar = 0;
        int numericVar = 0;
        int specialCharVar = 0;
        do {
            System.out.println("Lutfen Sifrenizi Giriniz.\nNot: Şifreniz; Büyük ve Küçük Harf, Rakam ve Özel Karakter İçermelidir.");
            Scanner scan = new Scanner(System.in);
            sifre = scan.next().trim();
            int i=0;
            for (i=0; i < sifre.length(); i++) {
                if (AlphaCaps.indexOf(sifre.charAt(i)) != -1) { // contains'de kullanilabilir.
                    alphaCapsVar = 1;
                    break;
                }
            }
            for (i=0; i < sifre.length(); i++) {
                if (Alpha.indexOf(sifre.charAt(i)) != -1) {
                    alphaVar = 1;
                    break;
                }
            }
            for (i=0; i < sifre.length(); i++) {
                if (Numeric.indexOf(sifre.charAt(i)) != -1) {
                    numericVar = 1;
                    break;
                }
            }
            for (i=0; i < sifre.length(); i++) {
                if (specialChar.indexOf(sifre.charAt(i)) != -1) {
                    specialCharVar = 1;
                    break;
                }
            }

            if(alphaCapsVar+alphaVar+numericVar+specialCharVar!=4){
                System.out.println("Şifreniz Geçersizdir.");
            }

        } while (alphaCapsVar+alphaVar+numericVar+specialCharVar!=4);
            System.out.println("Şifreniz Geçerlidir.");
            System.out.println("Üye İşlemleri Menüsünü kullanabilirsiniz.");
            // Uye kaydı yapıldıktan sonra email ve sifrenin accountDetails listesine eklenmesi gereklidir.
            DatabaseDMLIslemleri.id = DatabaseDQLIslemleri.kullaniciIDList.get(DatabaseDQLIslemleri.kullaniciIDList.size()-1)+1;
            DatabaseDMLIslemleri.eMail=eMail;
            DatabaseDQLIslemleri.eMail=eMail;   // Bu işlem Email ile giriş yapan kişinin KullanıcıID sini Almak için kullanılmıştır.
                                                // İleride Kitap Ödünç Alırken veya Kitap iade ederken oduncAlan sütunun güncellenmesi için kullanılacaktır.
                                                //
            DatabaseDMLIslemleri.sifre=sifre;
            DatabaseDMLIslemleri.insertAccountDetails(); // Yeni kaydedilen email ve sifrenin accountDetails listesine eklenme işlemi
            uyeIslemleriMenu();
    }
    /*==============================================
                uyeGiris Methodu
    ==============================================*/
    private void uyeGiris() throws SQLException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Lutfen Kullanici Gmail Adresinizi Giriniz");
        String eMail = scan.next().toLowerCase();
        DatabaseDQLIslemleri.selectAccountDetails(); // kullaniciIDList ve kullaniciMailList'in Güncellenmesi için
        if (!DatabaseDQLIslemleri.kullaniciMailList.contains(eMail)){
            System.out.println("Kullanici Gmail Adresi Sistemimizde kayitli Değildir. Lütfen UYE KAYIT modülünü kullanınız.");
            anaMenu();
        } else{
            System.out.println("Kullanici Gmail Adresi Sistemimizde kayitdir.");
        }

        System.out.println("Lutfen Kullanici Sifresini Giriniz");
        String sifre = scan.next().trim();
        if (!sifre.equals(DatabaseDQLIslemleri.kullaniciSifreList.get(DatabaseDQLIslemleri.kullaniciMailList.indexOf(eMail)))){ // DatabaseDMLIslemleri.kullaniciMailList.indexOf(eMail)
            System.out.println("Kullanici Sifreniz Sistemimizde kayitli Değildir. Lütfen UYE KAYIT modülünü kullanınız.");
            anaMenu();
        } else{
            System.out.println("Kullanici Şifresi Sistemimizde Kayitdir.");
            System.out.println("Üye İşlemleri Menüsünü kullanabilirsiniz.");
            DatabaseDQLIslemleri.eMail=eMail;   // Bu işlem Email ile giriş yapan kişinin KullanıcıID sini Almak için kullanılmıştır
                                                // İleride Kitap Ödünç Alırken veya Kitap iade ederken oduncAlan sütunun güncellenmesi için kullanılacaktır.

        }
        uyeIslemleriMenu();
    }
    /*==============================================
                adminGiris Methodu
    ==============================================*/

    private void adminGiris() throws SQLException, ClassNotFoundException {
        String adminEmail="";
        do{
            System.out.println("Lütfen Admin Email Adresini Giriniz");
            Scanner scan = new Scanner(System.in);
            adminEmail=scan.next().toLowerCase();
            if (!adminEmail.equalsIgnoreCase("admin@gmail.com")){
                System.out.println("Admin Email Adresiniz Yanlıştır.");
                adminGiris();
            } else {
                System.out.println("Admin Email Adresiniz Doqrudur.");
            }
        } while (!adminEmail.equalsIgnoreCase("admin@gmail.com"));
        String adminSifre="";
        do{
            System.out.println("Lütfen Admin Sifresini Giriniz");
            Scanner scan = new Scanner(System.in);
            adminSifre=scan.next().toLowerCase();
            if (!adminSifre.equalsIgnoreCase("admin")){
                System.out.println("Admin Sifreniz Yanlıştır.");
            } else {
                System.out.println("Admin Sifreniz Doqrudur.");
            }
        } while (!adminSifre.equalsIgnoreCase("admin"));
        adminIslemleriMenu();
    }

    private void cikis() {
        System.out.println("KUTUPHANE YONETIM SISTEMINI kullandiginiz için Teşekkür Ederiz.");
    }

    /*==============================================
                adminIslemleri Methodu
    ==============================================*/

    private void adminIslemleriMenu() throws SQLException, ClassNotFoundException {
        System.out.println("========================================");
        System.out.println("\t\tKUTUPHANE YONETIM SISTEMI / Admin Islemleri");
        System.out.println("========================================");
        System.out.println("1-KITAP LISTELEME \n2-KITAP EKLEME \n3-KITAP SILME \n4-KITAP ARAMA  \n5-ODUNC KITAP LİSTELEME \nQ-CIKIS \nLutfen Yapmak Istediginiz Islemi Seciniz:");
        Scanner scan = new Scanner(System.in);
        String adminIslemleriMenuSecim = scan.next().toUpperCase();
        switch (adminIslemleriMenuSecim){
            case "1":
                kitapListelemeAdmin();
                break;
            case "2":
                kitapEkleme();
                break;
            case "3":
                kitapSilme();
                break;
            case "4":
                kitapArama();
                break;
            case "5":
                oduncKitapListele();
                break;
            case "Q":
                cikis();
                break;
            default:{
                System.out.println("Hatali Secim Yaptiniz");
                adminIslemleriMenu();
            }
        }
    }


    /*==============================================
                   kitapListeleme Methodu
    ==============================================*/
    private void kitapListelemeAdmin() throws SQLException, ClassNotFoundException {
        DatabaseDQLIslemleri.selectlibraryContent();
        adminIslemleriMenu();
    }
    /*==============================================
                   kitapEkleme Methodu
    ==============================================*/
    private void kitapEkleme() throws SQLException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Lütfen Kitap İsmini Giriniz");
        String kitapAdi = scan.nextLine();
        System.out.println("Lütfen Kitap Yazarini Giriniz");
        String yazar = scan.nextLine();
        System.out.println("Lütfen Yayinevi Giriniz");
        String yayinEvi = scan.nextLine();
        System.out.println("Lütfen Sayfa Sayinisi Giriniz");
        int sayfaSayisi = scan.nextInt();

        DatabaseDQLIslemleri.selectlibraryContentKitapId();
        DatabaseDMLIslemleri.kitapId = DatabaseDQLIslemleri.kitapIdList.get(DatabaseDQLIslemleri.kitapIdList.size()-1)+1;
        DatabaseDMLIslemleri.kitapAdi=kitapAdi;
        DatabaseDMLIslemleri.yazar=yazar;
        DatabaseDMLIslemleri.yayinEvi=yayinEvi;
        DatabaseDMLIslemleri.sayfaSayisi=sayfaSayisi;
        DatabaseDMLIslemleri.insertlibraryContentSingle();
        adminIslemleriMenu();

    }
    /*==============================================
                       kitapSilme Methodu
    ==============================================*/
    private void kitapSilme() throws SQLException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Lütfen Silmek İstediğiniz Kitap ID'sini Giriniz");
        int kitapId = scan.nextInt();
        DatabaseDMLIslemleri.kitapId=kitapId;
        DatabaseDMLIslemleri.deleteLibraryContent();
        adminIslemleriMenu();
    }
    /*==============================================
                       kitapArama Methodu
    ==============================================*/
    private void kitapArama() throws SQLException, ClassNotFoundException {
        System.out.println("1-Kitap ID'ye Göre\n2-Kitap Adina Göre\n3-Yazara Göre\n4-Yayin Evine göre\n5-Sayfa Sayisina göre\nQ-Bir Oncek Sayfaya Dön\nLütfen Arama Yapmak Istediğiniz Başligi Seciniz.");
        Scanner scan = new Scanner(System.in);
        String kitapAramaSecim = scan.next().toUpperCase();
        switch (kitapAramaSecim){
            case "1":
                aramaKitapId();
                break;
            case "2":
                aramaKitapAdi();
                break;
            case "3":
                aramaYazar();
                break;
            case "4":
                aramaYayinEvi();
                break;
            case "5":
                aramaSayfaSayisi();
                break;
            case "Q":
                adminIslemleriMenu();;
                break;
            default:{
                System.out.println("Hatali Secim Yaptiniz");
                kitapArama();
            }
        }
    }
    /*==============================================
                aramaKitapId Methodu
    ==============================================*/
    private void aramaKitapId() throws SQLException, ClassNotFoundException {
        System.out.println("Lütfen Arama Yapmak Istediğiniz Kitap ID'sini Giriniz.");
        Scanner scan = new Scanner(System.in);
        int kitapId = scan.nextInt();
        DatabaseDQLIslemleri.kitapId=kitapId;
        DatabaseDQLIslemleri.aramaKitapId();

        adminIslemleriMenu();
    }
    /*==============================================
                  aramaKitapAdi Methodu
    ==============================================*/
    private void aramaKitapAdi() throws SQLException, ClassNotFoundException {
        System.out.println("Lütfen Arama Yapmak Istediğiniz Kitap Adi'ni Giriniz.");
        Scanner scan = new Scanner(System.in);
        String kitapAdi = scan.nextLine();
        DatabaseDQLIslemleri.kitapAdi=kitapAdi;
        DatabaseDQLIslemleri.aramaKitapAdi();
        adminIslemleriMenu();
    }
    /*==============================================
                  aramaYazar Methodu
    ==============================================*/
    private void aramaYazar() throws SQLException, ClassNotFoundException {
        System.out.println("Lütfen Arama Yapmak Istediğiniz Yazar Adi'ni Giriniz.");
        Scanner scan = new Scanner(System.in);
        String yazar = scan.nextLine();
        DatabaseDQLIslemleri.yazar=yazar;
        DatabaseDQLIslemleri.aramaYazar();
        adminIslemleriMenu();
    }
    /*==============================================
                  aramaYayinEvi Methodu
    ==============================================*/
    private void aramaYayinEvi() throws SQLException, ClassNotFoundException {
        System.out.println("Lütfen Arama Yapmak Istediğiniz YayinEvi'ni Giriniz.");
        Scanner scan = new Scanner(System.in);
        String yayinevi = scan.nextLine();
        DatabaseDQLIslemleri.yayinEvi=yayinevi;
        DatabaseDQLIslemleri.aramaYayimevi();
        adminIslemleriMenu();
    }
    /*==============================================
                  aramaSayfaSayisi Methodu
    ==============================================*/
    private void aramaSayfaSayisi() throws SQLException, ClassNotFoundException {
        System.out.println("Lütfen Arama Yapmak Istediğiniz Sayfa Araligini Giriniz.");
        Scanner scan = new Scanner(System.in);
        int sayfaSayisi1 = scan.nextInt();
        int sayfaSayisi2 = scan.nextInt();
        DatabaseDQLIslemleri.sayfaSayisi1=sayfaSayisi1;
        DatabaseDQLIslemleri.sayfaSayisi2=sayfaSayisi2;
        DatabaseDQLIslemleri.aramaSayfaSayisi();
        adminIslemleriMenu();
    }

    /*==============================================
                oduncKitapListele Methodu
    ==============================================*/
    private void oduncKitapListele() throws SQLException, ClassNotFoundException {
        DatabaseDQLIslemleri.oduncKitapListele();
        adminIslemleriMenu();
    }


    /*==============================================
                uyeIslemleri Methodu
    ==============================================*/
    private void uyeIslemleriMenu() throws SQLException, ClassNotFoundException {
        System.out.println("========================================");
        System.out.println("\t\tKUTUPHANE YONETIM SISTEMI / Uye Islemleri");
        System.out.println("========================================");
        System.out.println("1-KITAP LISTELEME \n2-KITAP TESLIM ALMA \n3-KITAP IADE \n4-KITAP ARAMA  \n5-OKUDUGUM KITAPLARI GURUNTULEME \nQ-CIKIS \nLutfen Yapmak Istediginiz Islemi Seciniz:");
        Scanner scan = new Scanner(System.in);
        String uyeIslemleriMenuSecim = scan.next().toUpperCase();
        switch (uyeIslemleriMenuSecim){
            case "1":
                kitapListelemeUye();
                break;
            case "2":
                kitapTeslimAlma();
                break;
            case "3":
                kitapIade();
                break;
            case "4":
                kitapAramaUye();
                break;
            case "5":
                okudugumKitapListele();
                break;
            case "Q":
                cikis();
                break;
            default:{
                System.out.println("Hatali Secim Yaptiniz");
                uyeIslemleriMenu();
            }
        }

    }

    private void kitapListelemeUye() throws SQLException, ClassNotFoundException {
        DatabaseDQLIslemleri.selectlibraryContent();
        uyeIslemleriMenu();
    }

    private void kitapTeslimAlma() throws SQLException, ClassNotFoundException {
        System.out.println("Lütfen Teslim Almak Istediğiniz Kitabın ID'sini Giriniz.");
        Scanner scan = new Scanner(System.in);
        int kitapId= scan.nextInt();
        DatabaseDQLIslemleri.kitapId=kitapId;       // selectOduncAlan(); methodunun çalışması için bu variable'a ihtiyacı var
        DatabaseDMLIslemleri.kitapId=kitapId;       // updatelibraryContentOduncMuOduncAlan(); metodunun çalışması için bu variable'a ihtiyacı var
                                                    // insertUserDetails(); metodunun çalışması için bu variable'a ihtiyacı var
        DatabaseDQLIslemleri.selectOduncAlan();     // Seçilen kitapId'li kitabın oduncAlan sütununun 0 olup olmadığını kontrol etmek için
        if (DatabaseDQLIslemleri.oduncAlan!=0) {
            System.out.println("Belirttiğiniz Kitap "+DatabaseDQLIslemleri.oduncAlan+" ID'li Kullanıcıya Tahsis Edilmiştir.");
            System.out.println("Lutfen Başka Bir Kitap Almayı Deneyiniz");
            uyeIslemleriMenu();
        } else {
            DatabaseDQLIslemleri.selectkullaniciId();                       // email adresi ile kayıt yapan veya giriş yapan kişinin ID sini alır.
            DatabaseDMLIslemleri.updatelibraryContentOduncMuOduncAlan();    // libraryContent tablosundaki  OduncMu ve OduncAlan sütunlarını günceller.
            DatabaseDMLIslemleri.id=DatabaseDQLIslemleri.id;                // email adresi ile kayıt yapan veya giriş yapan kişinin ID sini alır. ve DQL'den DML'e gönderir
            DatabaseDMLIslemleri.insertUserDetails();                      // Kullanıcının okuduğu kitapları User Details'e kaydetmek için
            System.out.println(DatabaseDQLIslemleri.kitapId+" ID numaralı Kitap Tarafınıza Teslim Edilmiştir.");
        }
        uyeIslemleriMenu();
    }

    private void kitapIade() throws SQLException, ClassNotFoundException {
        DatabaseDQLIslemleri.oduncKitapList.clear();

        DatabaseDQLIslemleri.selectkullaniciId();       // email adresi ile kayıt yapan veya giriş yapan kişinin ID sini alır.
        DatabaseDQLIslemleri.oduncAlinmisKitap();
        if (DatabaseDQLIslemleri.oduncKitapList.isEmpty()) {
            System.out.println("Üzerinize Kayitli Kitap Bulunmamaktadır.");
        } else {
            System.out.println("Üzerinize Kayitli Kitaplar Aşağıda Listelenmiştir.");
            System.out.println(DatabaseDQLIslemleri.oduncKitapList);
            System.out.println("Lütfen Teslim Etmek Istediğiniz Kitabın ID'sini Giriniz.");
            Scanner scan = new Scanner(System.in);
            int kitapId= scan.nextInt();
            DatabaseDMLIslemleri.kitapId=kitapId;
            DatabaseDMLIslemleri.updatelibraryContentOduncMuOduncAlanIade();
            System.out.println(kitapId+" ID numaralı Kitap Kütüphanemize Iade Edilmiştir.");
        }
        uyeIslemleriMenu();
    }



    private void kitapAramaUye() {

    }
    private void okudugumKitapListele() throws SQLException, ClassNotFoundException {

        uyeIslemleriMenu();
    }

}
