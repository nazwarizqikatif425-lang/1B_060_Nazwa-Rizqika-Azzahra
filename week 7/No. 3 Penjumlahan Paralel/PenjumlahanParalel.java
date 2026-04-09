import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class PenjumlahanParalel {

    // AtomicLong digunakan sebagai wadah hasil akhir yang thread-safe. Berbeda dengan
    // variabel long biasa, AtomicLong menjamin bahwa operasi penambahan dari banyak
    // thread sekaligus tidak akan saling menimpa atau menghasilkan nilai yang salah.
    private static AtomicLong totalAkhir = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan jumlah thread: ");
        int jumlahThread = scanner.nextInt();

        System.out.print("Masukkan angka akhir: ");
        long angkaAkhir = scanner.nextLong();

        scanner.close();

        // Setiap thread mendapat bagian (range) angka yang harus dijumlahkan.
        // Pembagian dilakukan dengan membagi angka akhir secara merata ke semua thread.
        long bagianPerThread = angkaAkhir / jumlahThread;

        // Array ini menyimpan semua objek Thread yang akan dibuat, sehingga nanti
        // bisa dipanggil join() pada masing-masing thread untuk menunggu semuanya selesai.
        Thread[] threads = new Thread[jumlahThread];

        System.out.println("\n--- Memulai Penjumlahan Paralel ---");

        for (int i = 0; i < jumlahThread; i++) {
            // Menghitung angka awal dan angka akhir untuk setiap thread.
            // Thread pertama mulai dari 1, thread berikutnya melanjutkan dari batas thread sebelumnya.
            long start = (i * bagianPerThread) + 1;
            long end = (i == jumlahThread - 1) ? angkaAkhir : (i + 1) * bagianPerThread;

            // Menyimpan nomor urut thread ke variabel tersendiri agar bisa diakses
            // di dalam lambda. Variabel dari luar lambda harus bersifat effectively final.
            int nomorThread = i + 1;

            threads[i] = new Thread(() -> {
                System.out.println("Thread-" + nomorThread + " mengerjakan penjumlahan dari " + start + " sampai " + end);

                // Setiap thread menjumlahkan bagiannya sendiri ke variabel lokal terlebih dahulu.
                // Menggunakan variabel lokal lebih efisien karena tidak perlu sinkronisasi
                // di setiap langkah perulangan, cukup sekali di akhir saat menyetor hasil.
                long hasilParsial = 0;
                for (long j = start; j <= end; j++) {
                    hasilParsial += j;
                }

                System.out.println("Thread-" + nomorThread + " selesai. Hasil parsial: " + hasilParsial);

                // Setelah selesai menghitung, hasil parsial disetor ke totalAkhir menggunakan
                // addAndGet() yang merupakan operasi atomic sehingga aman digunakan oleh
                // banyak thread sekaligus tanpa menyebabkan race condition.
                totalAkhir.addAndGet(hasilParsial);
            });

            threads[i].start();
        }

        // Memastikan main thread menunggu semua thread selesai sebelum mencetak hasil akhir.
        // Tanpa join(), hasil akhir bisa dicetak sebelum semua thread selesai menyetor hasilnya.
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("\n--- Hasil Akhir ---");
        System.out.println("Total penjumlahan 1 sampai " + angkaAkhir + " dengan " + jumlahThread + " thread: " + totalAkhir.get());
    }
}
