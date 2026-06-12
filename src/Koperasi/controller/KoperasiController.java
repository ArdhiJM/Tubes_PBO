package Koperasi.controller;

import Koperasi.model.Anggota;
import Koperasi.model.Koperasi;
import Koperasi.view.KoperasiForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class KoperasiController implements ActionListener {
    private Koperasi model;
    private KoperasiForm view;

    public KoperasiController(Koperasi model, KoperasiForm view) {
        this.model = model;
        this.view = view;

        this.view.getBtnTambahAnggota().addActionListener(this);
        this.view.getBtnSimpanan().addActionListener(this);
        this.view.getBtnPenarikan().addActionListener(this);
        this.view.getBtnPinjaman().addActionListener(this);
        this.view.getBtnCicilan().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnTambahAnggota()) {
            prosesTambahAnggota();
        } else if (e.getSource() == view.getBtnSimpanan()) {
            prosesSetorSimpanan();
        } else if (e.getSource() == view.getBtnPenarikan()) {
            prosesTarikSimpanan();
        } else if (e.getSource() == view.getBtnPinjaman()) {
            prosesAjukanPinjaman();
        } else if (e.getSource() == view.getBtnCicilan()) {
            prosesBayarCicilan();
        }
    }

    // Bagian Proses Tambah Anggota, Setor Simpanan, Tarik Simpanan, Ajukan Pinjaman, dan Bayar Cicilan
    private void prosesTambahAnggota() {
        String id = view.getTxtId().getText().trim();
        String nama = view.getTxtNama().getText().trim();
        String jumlahStr = view.getTxtJumlahUang().getText().trim(); // Membaca input jumlah uang

        if (id.isEmpty() || nama.isEmpty()) {
            JOptionPane.showMessageDialog(view, "ID dan Nama tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (model.cariAnggota(id) != null) {
            JOptionPane.showMessageDialog(view, "ID Anggota sudah terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double saldoAwal = 0.0;
        // Validasi jika user mengisi saldo awal saat mendaftar
        if (!jumlahStr.isEmpty()) {
            try {
                saldoAwal = Double.parseDouble(jumlahStr);
                if (saldoAwal < 0) {
                    JOptionPane.showMessageDialog(view, "Saldo awal tidak boleh negatif!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Jumlah uang harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Membuat objek anggota baru
        Anggota baru = new Anggota(id, nama);
        
        // Jika saldo awal lebih dari 0, masukkan ke simpanan
        if (saldoAwal > 0) {
            baru.tambahSimpanan(saldoAwal);
        }

        model.tambahAnggota(baru);
        JOptionPane.showMessageDialog(view, "Anggota " + nama + " Berhasil Didaftarkan!");
        bersihkanInputan();
        perbaruiTampilanDaftar();
    }

    private void prosesSetorSimpanan() {
        String id = view.getTxtId().getText().trim();
        String jumlahStr = view.getTxtJumlahUang().getText().trim();

        Anggota m = model.cariAnggota(id);
        if (m == null) {
            JOptionPane.showMessageDialog(view, "Anggota tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double jumlah = Double.parseDouble(jumlahStr);
            m.tambahSimpanan(jumlah);
            JOptionPane.showMessageDialog(view, "Setor simpanan Rp" + jumlah + " berhasil!");
            bersihkanInputan();
            perbaruiTampilanDaftar();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Jumlah uang harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void prosesTarikSimpanan() {
        String id = view.getTxtId().getText().trim();
        String jumlahStr = view.getTxtJumlahUang().getText().trim();

        Anggota m = model.cariAnggota(id);
        if (m == null) {
            JOptionPane.showMessageDialog(view, "Anggota tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double jumlah = Double.parseDouble(jumlahStr);
            if (m.tarikSimpanan(jumlah)) {
                JOptionPane.showMessageDialog(view, "Penarikan simpanan Rp" + jumlah + " berhasil!");
            } else {
                JOptionPane.showMessageDialog(view, "Gagal! Saldo simpanan tidak mencukupi.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            bersihkanInputan();
            perbaruiTampilanDaftar();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Jumlah uang harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void prosesAjukanPinjaman() {
        String id = view.getTxtId().getText().trim();
        String jumlahStr = view.getTxtJumlahUang().getText().trim();

        Anggota m = model.cariAnggota(id);
        if (m == null) {
            JOptionPane.showMessageDialog(view, "Anggota tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double jumlah = Double.parseDouble(jumlahStr);
            m.ajukanPinjaman(jumlah);
            JOptionPane.showMessageDialog(view, "Pengajuan pinjaman Rp" + jumlah + " berhasil disetujui!");
            bersihkanInputan();
            perbaruiTampilanDaftar();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Jumlah uang harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void prosesBayarCicilan() {
        String id = view.getTxtId().getText().trim();
        String jumlahStr = view.getTxtJumlahUang().getText().trim();

        Anggota m = model.cariAnggota(id);
        if (m == null) {
            JOptionPane.showMessageDialog(view, "Anggota tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double jumlah = Double.parseDouble(jumlahStr);
            if (m.bayarPinjaman(jumlah)) {
                JOptionPane.showMessageDialog(view, "Pembayaran cicilan Rp" + jumlah + " berhasil!");
            } else {
                JOptionPane.showMessageDialog(view, "Gagal! Input salah atau pembayaran melebihi sisa pinjaman.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            bersihkanInputan();
            perbaruiTampilanDaftar();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Jumlah uang harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void bersihkanInputan() {
        view.getTxtId().setText("");
        view.getTxtNama().setText("");
        view.getTxtJumlahUang().setText("");
    }

    private void perbaruiTampilanDaftar() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================================================\n");
        sb.append(String.format("%-12s %-25s %-18s %-18s\n", "ID ANGGOTA", "NAMA ANGGOTA", "SALDO SIMPANAN", "SALDO PINJAMAN"));
        sb.append("========================================================================\n");

        for (Anggota m : model.getDaftarAnggota()) {
            sb.append(String.format("%-12s %-25s Rp%-16.2f Rp%-16.2f\n",
                    m.getIdAnggota(), m.getNama(), m.getSaldoSimpanan(), m.getSaldoPinjaman()));
        }
        view.getTxtAreaOutput().setText(sb.toString());
    }
}