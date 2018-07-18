package tutor.ajakteman;

public class Siswa extends User{
    private String jenjang;

    public Siswa(String nama, String alamat, String jenjang) {
        super("siswa", nama, alamat);
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
    public String getState() {
        return super.getState();
    }

    public String getJenjang() {
        return jenjang;
    }
}
