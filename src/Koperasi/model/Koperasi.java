package Koperasi.model;

import java.util.ArrayList;

public class Koperasi {
    // Menampung daftar semua anggota koperasi
    private ArrayList<Anggota> daftarAnggota;

    public Koperasi() {
        this.daftarAnggota = new ArrayList<>();
    }

    // Fungsi untuk menambah anggota baru
    public void tambahAnggota(Anggota anggotaBaru) {
        daftarAnggota.add(anggotaBaru);
    }

    // Fungsi untuk mencari anggota berdasarkan ID
    public Anggota cariAnggota(String idAnggota) {
        for (Anggota m : daftarAnggota) {
            if (m.getIdAnggota().equalsIgnoreCase(idAnggota)) {
                return m; // Anggota ditemukan
            }
        }
        return null; // Anggota tidak ditemukan
    }

    // Getter untuk mengambil semua daftar anggota 
    public ArrayList<Anggota> getDaftarAnggota() {
        return daftarAnggota;
    }
}