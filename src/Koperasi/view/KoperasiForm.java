package Koperasi.view;

import javax.swing.*;
import java.awt.*;

public class KoperasiForm extends JFrame {
    // 1. Komponen Input (JTextField)
    private JTextField txtId;
    private JTextField txtNama;
    private JTextField txtJumlahUang;

    // 2. Komponen Tombol Aksi (JButton)
    private JButton btnTambahAnggota;
    private JButton btnSimpanan;
    private JButton btnPenarikan;
    private JButton btnPinjaman;
    private JButton btnCicilan;

    // 3. Komponen Output (JTextArea untuk menampilkan log/data)
    private JTextArea txtAreaOutput;

    // Konstruktor untuk merancang tampilan
    public KoperasiForm() {
        // Mengatur judul dan ukuran jendela aplikasi
        setTitle("Aplikasi Koperasi - Tugas Besar PBO");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Agar jendela muncul di tengah layar
        setLayout(new BorderLayout(10, 10)); // Menggunakan BorderLayout

        // --- PANEL INPUT (Bagian UTARA/North) ---
        JPanel panelInput = new JPanel(new GridLayout(3, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Form Input Data"));

        panelInput.add(new JLabel("  ID Anggota:"));
        txtId = new JTextField();
        panelInput.add(txtId);

        panelInput.add(new JLabel("  Nama Anggota:"));
        txtNama = new JTextField();
        panelInput.add(txtNama);

        panelInput.add(new JLabel("  Jumlah Uang (Rp):"));
        txtJumlahUang = new JTextField();
        panelInput.add(txtJumlahUang);

        // --- PANEL OUTPUT (Bagian TENGAH/Center) ---
        txtAreaOutput = new JTextArea();
        txtAreaOutput.setEditable(false); // Tidak bisa diketik langsung oleh user
        JScrollPane scrollPane = new JScrollPane(txtAreaOutput);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Anggota & Log Transaksi"));

        // --- PANEL TOMBOL (Bagian SELATAN/South) ---
        JPanel panelTombol = new JPanel(new GridLayout(2, 3, 5, 5));
        panelTombol.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        btnTambahAnggota = new JButton("Tambah Anggota");
        btnSimpanan = new JButton("Setor Simpanan");
        btnPenarikan = new JButton("Tarik Simpanan");
        btnPinjaman = new JButton("Ajukan Pinjaman");
        btnCicilan = new JButton("Bayar Cicilan");

        // Memasukkan tombol ke dalam panel
        panelTombol.add(btnTambahAnggota);
        panelTombol.add(btnSimpanan);
        panelTombol.add(btnPenarikan);
        panelTombol.add(panelTombol.add(new JLabel(""))); // Kosong untuk estetika layout grid
        panelTombol.add(btnPinjaman);
        panelTombol.add(btnCicilan);

        // --- MEMASUKKAN SEMUA PANEL KE JFRAME UTAMA ---
        add(panelInput, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelTombol, BorderLayout.SOUTH);
    }

    // Fungsi-fungsi Getter agar Controller bisa membaca inputan dan mendeteksi klik tombol
    public JTextField getTxtId() { return txtId; }
    public JTextField getTxtNama() { return txtNama; }
    public JTextField getTxtJumlahUang() { return txtJumlahUang; }
    
    public JButton getBtnTambahAnggota() { return btnTambahAnggota; }
    public JButton getBtnSimpanan() { return btnSimpanan; }
    public JButton getBtnPenarikan() { return btnPenarikan; }
    public JButton getBtnPinjaman() { return btnPinjaman; }
    public JButton getBtnCicilan() { return btnCicilan; }
    
    public JTextArea getTxtAreaOutput() { return txtAreaOutput; }
}