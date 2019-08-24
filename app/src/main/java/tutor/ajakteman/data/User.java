package tutor.ajakteman.data;

public abstract class User {
    private String userID, nama, alamat, type;
    private boolean online;
    private long lastOnline;

    public User(){

    }

    public User(String userID, String type, String nama, String alamat){
        this.userID = userID;
        this.type = type;
        this.nama = nama;
        this.alamat = alamat;
    }

    public String getType() {
        return type;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNama() {
        return nama;
    }

    public String getUserID() { return userID; }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public long getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(long lastOnline) {
        this.lastOnline = lastOnline;
    }

}
