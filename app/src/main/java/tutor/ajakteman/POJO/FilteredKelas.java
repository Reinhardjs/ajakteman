package tutor.ajakteman.POJO;

public class FilteredKelas {

    String jarak;
    String jenjang;
    String kelas;
    String jurusan;
    String pelajaran;

    int jumlahPeserta;

    public FilteredKelas(){

    }

    public FilteredKelas(String jarak, String jenjang, String kelas, String jurusan, String pelajaran){
        this.jarak = jarak;
        this.jenjang = jenjang;
        this.kelas = kelas;
        this.jurusan = jurusan;
        this.pelajaran = pelajaran;
    }

    public String getJarak() {
        return jarak;
    }

    public void setJarak(String jarak) {
        this.jarak = jarak;
    }

    public String getJenjang() {
        return jenjang;
    }

    public void setJenjang(String jenjang) {
        this.jenjang = jenjang;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getPelajaran() {
        return pelajaran;
    }

    public void setPelajaran(String pelajaran) {
        this.pelajaran = pelajaran;
    }

    public int getJumlahPeserta() {
        return jumlahPeserta;
    }

    public void setJumlahPeserta(int jumlahPeserta) {
        this.jumlahPeserta = jumlahPeserta;
    }
}
