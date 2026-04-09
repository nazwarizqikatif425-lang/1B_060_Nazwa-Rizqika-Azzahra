class Resto {
    private int chickenStock = 100;

    public synchronized void serveCustomer(String cashierName) { // Kata kunci synchronized ditambahkan pada method ini agar hanya satu thread yang bisa mengeksekusi method ini pada satu waktu. Ketika Kasir-A sedang melayani pelanggan, Kasir-B dan Kasir-C harus menunggu sampai Kasir-A selesai sebelum giliran mereka masuk. Ini mencegah race condition karena tidak ada lagi dua thread yang bisa membaca dan mengubah chickenStock secara bersamaan.
        if (chickenStock > 0) { // Setelah method diamankan dengan synchronized, pengecekan kondisi ini menjadi aman. Hanya satu thread yang sampai di baris ini pada satu waktu, sehingga nilai chickenStock yang dibaca sudah pasti merupakan nilai terkini yang belum diubah thread lain.
            try { Thread.sleep(10); } catch (InterruptedException e) {} // Jeda ini sekarang tidak lagi berbahaya karena thread lain tidak bisa menyela selama satu thread masih berada di dalam method synchronized ini.

            chickenStock--; // Pengurangan stok kini terjamin aman karena hanya ada satu thread yang menjalankan baris ini pada satu waktu, sehingga tidak ada pengurangan yang hilang atau nilai yang tumpang tindih.
            System.out.println(cashierName + " berhasil menjual 1 ayam. Sisa stok: " + chickenStock);
        } else {
            System.out.println(cashierName + " gagal: Stok Habis!");
        }
    }

    public int getRemainingStock() {
        return chickenStock;
    }
}

public class RestoSimulasiSolusi {
    public static void main(String[] args) throws InterruptedException {
        Resto ayamJuicyLuicyGallagher = new Resto();

        Runnable task = () -> {
            for (int i = 0; i < 40; i++) {
                ayamJuicyLuicyGallagher.serveCustomer(Thread.currentThread().getName()); // Ketiga kasir tetap memanggil method yang sama secara bersamaan, namun kini method tersebut sudah dilindungi synchronized sehingga akses ke data chickenStock menjadi teratur dan tidak saling bertabrakan.
            }
        };

        Thread kasir1 = new Thread(task, "Kasir-A");
        Thread kasir2 = new Thread(task, "Kasir-B");
        Thread kasir3 = new Thread(task, "Kasir-C"); // Tiga thread tetap berjalan bersamaan seperti sebelumnya, namun dengan synchronized pada method serveCustomer, eksekusinya kini antre dengan tertib. Program dijamin menghasilkan sisa stok yang akurat di akhir.

        kasir1.start();
        kasir2.start();
        kasir3.start();

        kasir1.join();
        kasir2.join();
        kasir3.join();

        System.out.println("--- HASIL AKHIR STOK: " + ayamJuicyLuicyGallagher.getRemainingStock() + " ---");
    }
}