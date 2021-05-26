package TechProEd;
/*=======================================================================
	Bu Class DataBase DQL İşlemleri yapmak için oluşturuluştur.
	*   SELECT işlemleri;
	    -   selectAccountDetails();         accountDetails tablosundan kullaniciID, eMail ve sifre verilerini alıp bunları List'lere eklemek için kullanılacaktır.
	    -   oduncKitapListele();            libraryContent tablosundan Ödünç Alınmış Kitapları Listelemek için
	    -   selectkullaniciId()             accountDetails tablosundan Giriş yapan Uyenin ID'sini Belirlemek için (Kitap aldığında bu id'yi oduncAlan hücresine eklemek için)


	    -   aramaKitapId();                 libraryContent tablosundan kitapId'ye göre arama yapmak ve bunları yazdırmak için
	    -   aramaKitapAdi();                libraryContent tablosundan kitapAdi'na göre
	    -   aramaYazar();                   libraryContent tablosundan Yazar'a göre
	    -   aramaYayimevi();                libraryContent tablosundan yayinEvi'ne göre
	    -   aramaSayfaSayisi();             libraryContent tablosundan sayfaSayisi'na göre

	========================================================================*/

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabaseDQLIslemleri {
    static List<Integer> kullaniciIDList = new ArrayList<>();   // Kullanıcı ID'lerinin bulunduğu liste
    static List<String> kullaniciMailList = new ArrayList<>();  // Kullanıcı Mail'lerinin bulunduğu liste
    static List<String> kullaniciSifreList = new ArrayList<>(); // Kullanıcı Sifre'lerinin bulunduğu liste
    static List<Integer> kitapIdList = new ArrayList<>();       // Kütüphanedeki Tüm Kitapların ID'lerinin liste
    static List<Integer> oduncKitapList = new ArrayList<>();    // Kütüphanedeki Odunç Alınmış Kitapların ID'lerinin liste

    static int id = 0;
    static String eMail ="";
    static int kitapId = 0;
    static String kitapAdi ="";
    static String yazar="";
    static String yayinEvi ="";
    static int sayfaSayisi1=0;
    static int sayfaSayisi2=0;
    static int oduncAlan=0;

    static String url = "jdbc:oracle:thin:@localhost:1521/xe";
    static String user = "Mesut";
    static String password = "4421465";

    /*=======================================================================
    accountDetails tablosundan kullaniciID, eMail ve sifre verierini alıp bunları Listelere ekleyelim.
    =======================================================================*/

    public static void selectAccountDetails() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        String selectQueryid = "SELECT kullaniciID FROM accountDetails";
        ResultSet rs = st.executeQuery(selectQueryid);
        while (rs.next()) {
            kullaniciIDList.add(rs.getInt("kullaniciID"));
        }
//        System.out.println(kullaniciIDList);


        String selectQueryEmail = "SELECT eMail FROM accountDetails";
        ResultSet rs2 = st.executeQuery(selectQueryEmail);
        while (rs2.next()) {
            kullaniciMailList.add(rs2.getString("email"));
        }
//        System.out.println(kullaniciMailList);

        String selectQuerySifre = "SELECT sifre FROM accountDetails";
        ResultSet rs3 = st.executeQuery(selectQuerySifre);
        while (rs3.next()) {
            kullaniciSifreList.add(rs3.getString("sifre"));

        }
//        System.out.println(kullaniciSifreList);
    }

    /*=======================================================================
  libraryContent tablosundan kitapID verisini alıp bunları Listelere ekleyelim.
   =======================================================================*/
    static void selectlibraryContentKitapId() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        String selectQueryKitapId = "SELECT kitapID FROM libraryContent";
        ResultSet rs = st.executeQuery(selectQueryKitapId);
        while (rs.next()) {
            kitapIdList.add(rs.getInt("kitapID"));
        }
//        System.out.println(kitapIdList);
    }

    /*=======================================================================
    libraryContent tablosundan tüm verilerini alıp bunları yazdıralım.
     =======================================================================*/

    public static void selectlibraryContent() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM libraryContent");
        while(rs.next()) {
            System.out.println("Kitap ID:" + rs.getString("kitapId")+"\t"+"Kitap Isim:" + rs.getString("kitapAdi")+"\t\t"+"Yazar:" + rs.getString("yazar")+"\t\t"+"Yayinevi:" + rs.getString("yayinevi")+"\t\t"+"Sayfa Sayisi:" + rs.getInt("sayfaSayisi"));    // +"\t\t"+"Ödünç mü:" + rs.getString("oduncMu")
        }
    }
    static void aramaKitapId() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM libraryContent WHERE kitapId="+kitapId+"");
        while(rs.next()) {
            System.out.println("Kitap ID:" + rs.getString("kitapId")+"\t"+"Kitap Isim:" + rs.getString("kitapAdi")+"\t\t"+"Yazar:" + rs.getString("yazar")+"\t\t"+"Yayinevi:" + rs.getString("yayinevi")+"\t\t"+"Sayfa Sayisi:" + rs.getInt("sayfaSayisi"));
        }
    }

    static void aramaKitapAdi() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM libraryContent WHERE kitapAdi='"+kitapAdi+"'"); // "SELECT personel_isim FROM personel WHERE personel_id=7369"
        while(rs.next()) {
            System.out.println("Kitap ID:" + rs.getString("kitapId")+"\t"+"Kitap Isim:" + rs.getString("kitapAdi")+"\t\t"+"Yazar:" + rs.getString("yazar")+"\t\t"+"Yayinevi:" + rs.getString("yayinevi")+"\t\t"+"Sayfa Sayisi:" + rs.getInt("sayfaSayisi"));
        }
    }

    static void aramaYazar() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM libraryContent WHERE yazar='"+yazar+"'"); // "SELECT personel_isim FROM personel WHERE personel_id=7369"
        while(rs.next()) {
            System.out.println("Kitap ID:" + rs.getString("kitapId")+"\t"+"Kitap Isim:" + rs.getString("kitapAdi")+"\t\t"+"Yazar:" + rs.getString("yazar")+"\t\t"+"Yayinevi:" + rs.getString("yayinevi")+"\t\t"+"Sayfa Sayisi:" + rs.getInt("sayfaSayisi"));
        }
    }

    static void aramaYayimevi() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM libraryContent WHERE yayinevi='"+yayinEvi+"'"); // "SELECT personel_isim FROM personel WHERE personel_id=7369"
        while(rs.next()) {
            System.out.println("Kitap ID:" + rs.getString("kitapId")+"\t"+"Kitap Isim:" + rs.getString("kitapAdi")+"\t\t"+"Yazar:" + rs.getString("yazar")+"\t\t"+"Yayinevi:" + rs.getString("yayinevi")+"\t\t"+"Sayfa Sayisi:" + rs.getInt("sayfaSayisi"));
        }
    }

    static void aramaSayfaSayisi() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM libraryContent WHERE sayfaSayisi BETWEEN "+sayfaSayisi1+" AND "+sayfaSayisi2+""); // WHERE urun_id BETWEEN 20 AND 40
        while(rs.next()) {
            System.out.println("Kitap ID:" + rs.getString("kitapId")+"\t"+"Kitap Isim:" + rs.getString("kitapAdi")+"\t\t"+"Yazar:" + rs.getString("yazar")+"\t\t"+"Yayinevi:" + rs.getString("yayinevi")+"\t\t"+"Sayfa Sayisi:" + rs.getInt("sayfaSayisi"));
        }
    }
    static void oduncKitapListele() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM libraryContent WHERE oduncMu='true'"); // WHERE urun_id BETWEEN 20 AND 40
        while(rs.next()) {
            System.out.println("Kitap ID:" + rs.getString("kitapId")+"\t"+"Kitap Isim:" + rs.getString("kitapAdi")+"\t\t"+"Yazar:" + rs.getString("yazar")+"\t\t"+"Yayinevi:" + rs.getString("yayinevi")+"\t\t"+"Sayfa Sayisi:" + rs.getInt("sayfaSayisi")+"\t\t"+"Odunc Alan ID:" + rs.getInt("oduncAlan"));
        }
    }
    static void selectOduncAlan() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        String selectOduncAlan = "SELECT oduncAlan FROM libraryContent WHERE kitapID ="+kitapId+"";
        ResultSet rs = st.executeQuery(selectOduncAlan);

        while(rs.next()) {
            oduncAlan = rs.getInt("oduncAlan");
            // System.out.println(oduncAlan);
        }
    }
    static void selectkullaniciId() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        String selectkullaniciId = "SELECT kullaniciId FROM accountDetails WHERE email ='"+eMail+"'";
        ResultSet rs = st.executeQuery(selectkullaniciId);
        while(rs.next()) {
            id= rs.getInt("kullaniciId");
        }
        // System.out.println(id);
    }
    static void oduncAlinmisKitap() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        String oduncAlinmisKitap = "SELECT kitapId FROM libraryContent WHERE oduncAlan ='"+id+"'";
        ResultSet rs = st.executeQuery(oduncAlinmisKitap);
        while(rs.next()) {
            oduncKitapList.add(kitapId= rs.getInt("kitapId"));
        }

    }

   }
