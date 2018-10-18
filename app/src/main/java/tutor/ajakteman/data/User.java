package tutor.ajakteman.data;

public abstract class User {
    private String nama, alamat, state;
    public User(String state, String nama, String alamat){
        this.state = state;
        this.nama = nama;
        this.alamat = alamat;
    }

    public String getState() {
        return state;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNama() {
        return nama;
    }
}
