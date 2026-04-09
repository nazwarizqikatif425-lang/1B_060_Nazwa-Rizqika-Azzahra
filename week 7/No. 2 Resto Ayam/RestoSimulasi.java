class Resto {
    private int chickenStock = 100;

    public void serveCustomer(String cashierName) {
        if (chickenStock > 0) { // Kondisi pengecekan stok ini tidak aman untuk lingkungan multi-thread. Bisa terjadi situasi di mana dua kasir mengecek stok secara bersamaan, keduanya sama-sama melihat stok masih ada, lalu keduanya sama-sama mengurangi stok. Ini yang menyebabkan stok bisa minus.
            try { Thread.sleep(10); } catch (InterruptedException e) {} // Jeda kecil ini memperparah masalah karena memberikan celah waktu bagi thread lain untuk masuk ke blok if yang sama sebelum stok sempat dikurangi oleh thread pertama.
           
            chickenStock--; // Operasi pengurangan ini tidak bersifat atomic, artinya prosesnya terdiri dari beberapa langkah kecil di dalam CPU, yaitu baca nilai, kurangi, lalu tulis kembali. Jika dua thread melakukan ini bersamaan, salah satu pengurangan bisa hilang atau nilai akhirnya menjadi tidak akurat.
            System.out.println(cashierName + " berhasil menjual 1 ayam. Sisa stok: " + chickenStock);
        } else {
            System.out.println(cashierName + " gagal: Stok Habis!");
        }
    }

    public int getRemainingStock() {
        return chickenStock;
    }
}

public class RestoSimulasi {
    public static void main(String[] args) throws InterruptedException {
        Resto ayamJuicyLuicyGallagher = new Resto();

        Runnable task = () -> {
            for (int i = 0; i < 40; i++) {
                ayamJuicyLuicyGallagher.serveCustomer(Thread.currentThread().getName()); // Ketiga kasir memanggil method serveCustomer pada objek yang sama secara bersamaan tanpa ada mekanisme pengamanan. Inilah titik terjadinya race condition karena semua thread berlomba mengakses dan mengubah data chickenStock di waktu yang sama.
            }
        };

        Thread kasir1 = new Thread(task, "Kasir-A");
        Thread kasir2 = new Thread(task, "Kasir-B");
        Thread kasir3 = new Thread(task, "Kasir-C"); // Tiga thread dijalankan sekaligus dan semuanya mengerjakan task yang sama, yaitu menjual ayam sebanyak 40 kali. Total percobaan penjualan adalah 120 kali, sementara stok hanya 100, sehingga persaingan antar thread sangat tinggi dan memperbesar kemungkinan terjadinya race condition.

        kasir1.start();
        kasir2.start();
        kasir3.start();

        kasir1.join();
        kasir2.join();
        kasir3.join();

        System.out.println("--- HASIL AKHIR STOK: " + ayamJuicyLuicyGallagher.getRemainingStock() + " ---");
    }
}
