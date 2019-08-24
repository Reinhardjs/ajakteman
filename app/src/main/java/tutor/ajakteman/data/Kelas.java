package tutor.ajakteman.data;

import java.io.Serializable;
import java.util.Map;

public class Kelas implements Serializable {

    String kelasID, jenjang, jurusan, kelas, pelajaran;
    Map<String, String> waktu;
    Map<String, String> lokasi;

    public Kelas(){

    }

    public Kelas(String kelasID){
        this.kelasID = kelasID;
    }

    public String getKelasID() {
        return kelasID;
    }

    public void setKelasID(String kelasID) {
        this.kelasID = kelasID;
    }

    public String getJenjang() {
        return jenjang;
    }

    public void setJenjang(String jenjang) {
        this.jenjang = jenjang;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getPelajaran() {
        return pelajaran;
    }

    public void setPelajaran(String pelajaran) {
        this.pelajaran = pelajaran;
    }

    public Map<String, String> getLokasi() {
        return lokasi;
    }

    public void setLokasi(Map<String, String> lokasi) {
        this.lokasi = lokasi;
    }

    public Map<String, String> getWaktu() {
        return waktu;
    }

    public void setWaktu(Map<String, String> waktu) {
        this.waktu = waktu;
    }
}
