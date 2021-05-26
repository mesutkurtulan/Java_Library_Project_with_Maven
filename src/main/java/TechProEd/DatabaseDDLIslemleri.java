package TechProEd;
    /*=======================================================================
	Bu Class DataBase DDL İşlemleri yapmak için oluşturuluştur.
	    Bu işlemler ilk başlangıçta kütüphanenin temellerini oluşturmak için bir kez kullanılacağı için
	    Main Method oluşturulmuş ve birkez çalıştırılmıştır. Bir daha çalıştırılmasına ihtiyaç yoktur.
	    O yüzden  methodları Yorum içine alınmıştır. (Yanlışlıkla tekrar çalıştırılmaması için)

	    DatabaseDMLIslemleri.insertlibraryContentWhole(); // Toplu kitap ekleme bir kez yapılacağı için buraya eklenmiştir.
	*   CREATE işlemleri;
	    -   accountDetails adinda bir tablo olusturunuz =   accountDetails();
	    -   libraryContent adinda bir tablo olusturunuz =   libraryContent();
	    -   userDetails adinda bir tablo olusturunuz =      userDetails();
	*   ALTER işlemleri;
	    -   Bu işleme gerek duyulmadığı için kullanılmamıştır.
	*   DROP İşlemleri
	    -   Bu işleme gerek duyulmadığı için kullanılmamıştır.
	========================================================================*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseDDLIslemleri {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

//        accountDetails();
//        libraryContent();
//        userDetails();
//        DatabaseDMLIslemleri.insertlibraryContentWhole(); // Toplu kitap ekleme bir kez yapılacağı için buraya eklenmiştir.
    }

    /*=======================================================================
	accountDetails adinda bir tablo olusturunuz kullaniciID NUMBER(4), email VARCHAR2(30),
	sifre VARCHAR2(20)
	========================================================================*/

   public static void accountDetails() throws ClassNotFoundException, SQLException {
       String url = "jdbc:oracle:thin:@localhost:1521/xe";
       String user = "Mesut";
       String password = "4421465";

       Class.forName("oracle.jdbc.driver.OracleDriver");
       Connection con = DriverManager.getConnection(url, user, password);
       Statement st = con.createStatement();
       String createQuery = "CREATE TABLE accountDetails("
               + " kullaniciID NUMBER(4), email VARCHAR2(20), sifre VARCHAR2(20) "
               + " )";
       st.executeUpdate(createQuery);
       System.out.println("accountDetails Tablosu Oluşturuldu ");
       st.close();
       con.close();
    }

    /*=======================================================================
	libraryContent adinda bir tablo olusturunuz kitapID NUMBER(5), kitapAdi VARCHAR2(50),
	yazar VARCHAR2(50), yayinevi VARCHAR2(50),sayfaSayisi NUMBER(4), oduncMuVARCHAR2(5), oduncAlan NUMBER(4)
	========================================================================*/

    private static void libraryContent() throws ClassNotFoundException, SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521/xe";
        String user = "Mesut";
        String password = "4421465";
        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        String createQuery = "CREATE TABLE libraryContent("
                + " kitapID NUMBER(5), kitapAdi VARCHAR2(50), yazar VARCHAR2(50), yayinevi VARCHAR2(50), sayfaSayisi NUMBER(4), oduncMu VARCHAR2(5), oduncAlan NUMBER(4) "
                + " )";

        st.executeUpdate(createQuery);
        System.out.println("libraryContent Tablosu Oluşturuldu ");
        st.close();
        con.close();
    }

    /*=======================================================================
    userDetails adinda bir tablo olusturunuz kullaniciId NUMBER(4), kitapId NUMBER(4)
    ========================================================================*/

    private static void userDetails() throws ClassNotFoundException, SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521/xe";
        String user = "Mesut";
        String password = "4421465";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();

        String createQuery = "CREATE TABLE userDetails("
                + " kullaniciId NUMBER(4), kitapId NUMBER(5) "
                + " )";
        st.executeUpdate(createQuery);
        System.out.println("userDetails Tablosu Oluşturuldu");
        st.close();
        con.close();
    }
}
