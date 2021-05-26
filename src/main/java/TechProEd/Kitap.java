package TechProEd;

    // POJO Class

public class Kitap {

    private int kitapId;
    private String kitapAdi;
    private String yazar;
    private String yayinevi;
    private int sayfaSayisi;
    private String oduncMu;
    private int oduncAlan;

    public Kitap(int kitapId, String kitapAdi, String yazar, String yayinevi, int sayfaSayisi, String oduncMu, int oduncAlan) {
        this.kitapId = kitapId;
        this.kitapAdi = kitapAdi;
        this.yazar = yazar;
        this.yayinevi = yayinevi;
        this.sayfaSayisi = sayfaSayisi;
        this.oduncMu = oduncMu;
        this.oduncAlan=oduncAlan;
    }

    public int getKitapId() {
        return kitapId;
    }

    public void setKitapId(int kitapId) {
        this.kitapId = kitapId;
    }

    public String getKitapAdi() {
        return kitapAdi;
    }

    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    public int getOduncAlan() {
        return oduncAlan;
    }

    public void setOduncAlan(int oduncAlan) {
        this.oduncAlan = oduncAlan;
    }

    public String getYazar() {
        return yazar;
    }

    public void setYazar(String yazar) {
        this.yazar = yazar;
    }

    public String getYayinevi() {
        return yayinevi;
    }

    public void setYayinevi(String yayinevi) {
        this.yayinevi = yayinevi;
    }

    public int getSayfaSayisi() {
        return sayfaSayisi;
    }

    public void setSayfaSayisi(int sayfaSayisi) {
        this.sayfaSayisi = sayfaSayisi;
    }

    public String getOduncMu() {
        return oduncMu;
    }

    public void setOduncMu(String oduncMu) {
        this.oduncMu = oduncMu;
    }

    @Override
    public String toString() {
        return "Kitap{" +
                "kitapId=" + kitapId +
                ", kitapAdi='" + kitapAdi + '\'' +
                ", yazar='" + yazar + '\'' +
                ", yayinevi='" + yayinevi + '\'' +
                ", sayfaSayisi=" + sayfaSayisi +
                ", oduncMu='" + oduncMu + '\'' +
                ", oduncAlan=" + oduncAlan +
                '}';
    }
}
