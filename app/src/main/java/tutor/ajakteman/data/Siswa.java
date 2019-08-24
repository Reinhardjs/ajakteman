package tutor.ajakteman.data;

import java.util.Map;

public class Siswa extends User {
    private String jenjang;
    private Map<String, UserLobby> kelas;

    public Siswa(){

    }

    public Siswa(String userID, String nama, String alamat, String jenjang) {
        super(userID,"siswa", nama, alamat);
        this.jenjang = jenjang;
    }

    @Override
    public String getAlamat() {
        return super.getAlamat();
    }

    @Override
    public String getNama() {
        return super.getNama();
    }

    @Override
    public String getType() {
        return super.getType();
    }

    public String getJenjang() {
        return jenjang;
    }

    public void setJenjang(String jenjang) {
        this.jenjang = jenjang;
    }

    public Map<String, UserLobby> getKelas() {
        return kelas;
    }

    public void setKelas(Map<String, UserLobby> kelas) {
        this.kelas = kelas;
    }
}
