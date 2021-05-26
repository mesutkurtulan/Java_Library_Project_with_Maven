package TechProEd;
    /*=======================================================================
	Bu Class DataBase DML İşlemleri yapmak için oluşturuluştur.
	*   INSERT işlemleri;
	    -   insertAccountDetails();         accountDetails tablosuna yeni bir kayit ekleme (Yeni Üye Kaydı Yapıldığında kullanılacaktır.)
	    -   insertlibraryContentSingle();   libraryContent tablosuna yeni bir kitap ekleme (Yeni Kitap Girişi Yapıldığında kullanılacaktır)
	    -   insertlibraryContentWhole();    libraryContent tablosuna ilk başlangıçta toplu kitap ekleme (İlk Başlangıçta bir kez kullanıldı.)
	    -   insertUserDetails();            userDetails tablosuna yeni bir kitap teslim alındığında veri eklemek için kullanılacaktır
	*   UPDATE işlemleri;
	    -   updatelibraryContentOduncMuOduncAlan;   libraryContent tablosundaki oduncMu ve OduncAlan Hücrelerini Update için Kullanılacaktır. (Kullanıcı Kitap Teslim aldığında)
	*   DELETE İşlemleri
	    -   deleteLibraryContent();         libraryContent tablosundan bir kitap silme (Admin tarafından bir kitap silindiğinde kullanılacaktır)
	========================================================================*/
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDMLIslemleri {

    static List<Kitap> KitapListesi = new ArrayList<>();        // Kütüphanedeki Tüm Kitapların Bulunduğu liste

    static String url = "jdbc:oracle:thin:@localhost:1521/xe";
    static String user = "Mesut";
    static String password = "4421465";

    static int id = 0;
    static String eMail ="";
    static String sifre="";

    static int kitapId = 0;
    static String kitapAdi ="";
    static String yazar="";
    static String yayinEvi ="";
    static int sayfaSayisi=0;
    static String oduncMu ="false";
    static int oduncAlan = 0;

    /*=======================================================================
    accountDetails tablosuna yeni bir kayit (ID, ‘email’, ‘sifre’) ekleyelim
     =======================================================================*/

    public static void insertAccountDetails() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        String insertQuery = "INSERT INTO accountDetails VALUES ("+id+",'"+eMail+"', '"+sifre+"')";
        int s1 = st.executeUpdate(insertQuery);
//        System.out.println("Bolumler Tablosuna "+id+" "+eMail+" "+sifre+" satiri eklendi");
        System.out.println("Kullanıcı Email'i Sistemimize Basariyla Kaydedilmiştir. Üye Islemleri Menusunu Kullanabilirsiniz.");

    }

    /*=======================================================================
        libraryContent tablosuna yeni bir verileri toplu halde ekleyelim
    =======================================================================*/
    static void insertlibraryContentWhole() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();

        KitapListesi.add(new Kitap(10001,"Vatan Yahut Silistre", "Namık Kemal", "Timaş Yayınları", 96, "false",0));	// hem obje oluşturuldu, hemde liste^ye ekledi
        KitapListesi.add(new Kitap(10002,"Tom Amca’nın Kulübesi", "Harriet Beecher Stowe", "Morpa Kültür", 120, "false",0));
        KitapListesi.add(new Kitap(10003,"Badem Operasyonu",	"Melek Çe", "Uğurböceği", 231, "false",0));
        KitapListesi.add(new Kitap(10004,"Sefiller",	"Victor Hugo",	"Timaş",	112, "false",0));
        KitapListesi.add(new Kitap(10005,"Uçan Sınıf",	"Erich Kastner",	"Can Sanat", 188, "false",0));
        KitapListesi.add(new Kitap(10006,"Vatan Yahut Silistre",	"Namık Kemal",	"Morpa Kültür",	247,"false",0));
        KitapListesi.add(new Kitap(10007,"Valideki Zambak",	"Honore De Balzac",	"Karanfil",	232, "false",0));
        KitapListesi.add(new Kitap(10008,"Yaşlı Balıkçı Ve Deniz",	"Ernest Hemingway",	"Morpa Kültür",	104, "false",0));
        KitapListesi.add(new Kitap(10009,"Billur Köşk Masalları",	"Tuba Öztürk",	"Akvaryum",	93, "false",0));
        KitapListesi.add(new Kitap(10010,"Mucitler ve İcat Öyküleri",	"Çiğdem Can",	"Gençlik Bilgi",	173,"false",0));

        String insertQuery = "INSERT INTO libraryContent VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(insertQuery);

        for (Kitap each : KitapListesi) {
            pst.setInt(1, each.getKitapId());
            pst.setString(2, each.getKitapAdi());
            pst.setString(3, each.getYazar());
            pst.setString(4, each.getYayinevi());
            pst.setInt(5, each.getSayfaSayisi());
            pst.setString(6, each.getOduncMu());
            pst.setInt(7, each.getOduncAlan());
            pst.addBatch();
        }
        int[] sonuc= pst.executeBatch();
        System.out.println(sonuc.length +" kayit eklendi.");
    }
    /*=======================================================================
    libraryContent tablosundan bir Kitap verileri silelim.
     =======================================================================*/

    static void deleteLibraryContent() throws ClassNotFoundException, SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521/xe";
        String user = "Mesut";
        String password = "4421465";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        String deleteQuery = "DELETE FROM libraryContent WHERE kitapId ='"+kitapId+"'";
        st.executeUpdate(deleteQuery);
        System.out.println(kitapId+" Satiri Silindi");
    }


    /*=======================================================================
        accountDetails tablosuna yeni bir veriyi tek tek ekleyelim
    =======================================================================*/

    public static void insertlibraryContentSingle() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        String insertQuery = "INSERT INTO libraryContent VALUES ("+kitapId+",'"+kitapAdi+"', '"+yazar+"', '"+yayinEvi+"', "+sayfaSayisi+", '"+oduncMu+"', "+oduncAlan+")";
        st.executeUpdate(insertQuery);
        System.out.println(kitapId+", "+kitapAdi+", "+yazar+", "+yayinEvi+", "+sayfaSayisi+" Kütüphane Kayitlarina Eklenmiştir.");

    }

    public static void updatelibraryContentOduncMuOduncAlan() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        String updateQuery = "UPDATE libraryContent SET oduncMu='true', oduncAlan = "+DatabaseDQLIslemleri.id+" WHERE kitapId = "+DatabaseDQLIslemleri.kitapId+"";
        st.executeUpdate(updateQuery);
        // System.out.println(DatabaseDQLIslemleri.kitapId+" Tarafınıza Teslim Edilmiştir.");

    }
    public static void updatelibraryContentOduncMuOduncAlanIade() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        String updateQuery = "UPDATE libraryContent SET oduncMu='false', oduncAlan = 0 WHERE kitapId = "+kitapId+"";
        st.executeUpdate(updateQuery);
        // System.out.println(DatabaseDQLIslemleri.kitapId+" Tarafınıza Teslim Edilmiştir.");

    }

    static void insertUserDetails() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        // String insertQuery = "INSERT INTO accountDetails VALUES ("+id+",'"+eMail+"', '"+sifre+"')";
        String insertQuery = "INSERT INTO userDetails VALUES ("+id+","+kitapId+")";
        st.executeUpdate(insertQuery);

    }
}
