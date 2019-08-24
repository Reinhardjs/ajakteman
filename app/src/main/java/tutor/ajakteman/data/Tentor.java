package tutor.ajakteman.data;

public class Tentor extends User {
    private String deskripsi, pengalaman1,pengalaman2, pendidikan1,pendidikan2, skill1, skill2;
    public Tentor(String nama, String alamat, String deskripsi, String pengalaman1, String pengalaman2, String pendidikan1, String pendidikan2, String skill1, String skill2 ) {
        super("nullnullnull","tentor", nama, alamat);
        this.deskripsi = deskripsi;
        this.pengalaman1 = pengalaman1;
        this.pengalaman2 = pengalaman2;
        this.pendidikan1 = pendidikan1;
        this.pendidikan2 = pendidikan2;
        this.skill1 = skill1;
        this.skill2 = skill2;
    }

    @Override
    public String getNama() {
        return super.getNama();
    }

    @Override
    public String getAlamat() {
        return super.getAlamat();
    }

    @Override
    public String getType() {
        return super.getType();
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getPendidikan1() {
        return pendidikan1;
    }

    public String getPendidikan2() {
        return pendidikan2;
    }

    public String getPengalaman1() {
        return pengalaman1;
    }

    public String getPengalaman2() {
        return pengalaman2;
    }

    public String getSkill1() {
        return skill1;
    }

    public String getSkill2() {
        return skill2;
    }
}
