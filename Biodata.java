// Package data
package modul42;

public class Biodata {
    private String nama;
    private String jenisKelamin;
    private String noHp;
    private String alamat;

    public Biodata(String nama, String jenisKelamin, String noHp, String alamat) {
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.noHp = noHp;
        this.alamat = alamat;
    }

    public String getNama() {
        return nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
