package Koperasi;

import Koperasi.model.Koperasi;
import Koperasi.view.KoperasiForm;
import Koperasi.controller.KoperasiController;

public class Main {
    public static void main(String[] args) {
        // Membuat objek model
        Koperasi model = new Koperasi();

        // Membuat objek view
        KoperasiForm view = new KoperasiForm();

        // Membuat objek controller dan menghubungkan model dan view
        KoperasiController controller = new KoperasiController(model,view);

        // Menampilkan view
        view.setVisible(true);
    }   
}