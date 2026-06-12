package Koperasi.model;

public class Anggota {
    private String idAnggota;
    private String nama;
    private double saldoSimpanan;
    private double saldoPinjaman;

    public Anggota(String idAnggota, String nama) {
        this.idAnggota = idAnggota;
        this.nama = nama;
        this.saldoSimpanan = 0.0;
        this.saldoPinjaman = 0.0;
    }

    public void tambahSimpanan(double jumlah) {
        if (jumlah > 0) {
            this.saldoSimpanan += jumlah;
        }
    }

    public boolean tarikSimpanan(double jumlah) {
        if (jumlah > 0 && this.saldoSimpanan >= jumlah) {
            this.saldoSimpanan -= jumlah;
            return true;
        }
        return false;
    }

    // --- BAGIAN LOGIKA YANG DIPERBAIKI ---
    public void ajukanPinjaman(double jumlah) {
        if (jumlah > 0) {
            this.saldoPinjaman += jumlah;   // Menambah utang pinjaman
            this.saldoSimpanan += jumlah;   // Menambah saldo simpanan (pencairan dana)
        }
    }

    public boolean bayarPinjaman(double jumlah) {
    // Validasi: jumlah harus positif, utang harus cukup, DAN saldo simpanan harus cukup
    if (jumlah > 0 && this.saldoPinjaman >= jumlah && this.saldoSimpanan >= jumlah) {
        this.saldoPinjaman -= jumlah;   // Utang anggota berkurang
        this.saldoSimpanan -= jumlah;   // Saldo simpanan terpotong untuk membayar
        return true;                    // Pembayaran sukses
    }
    return false;                       // Gagal jika utang tidak cukup atau saldo simpanan kurang
    }

    public String getIdAnggota() { return idAnggota; }
    public String getNama() { return nama; }
    public double getSaldoSimpanan() { return saldoSimpanan; }
    public double getSaldoPinjaman() { return saldoPinjaman; }
}