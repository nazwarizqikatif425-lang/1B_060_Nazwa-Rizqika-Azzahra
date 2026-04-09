class Account {
    int balance = 150;
}

public class TransferFulusSolusi {
    public static void main(String[] args) throws InterruptedException {
        Account acc1 = new Account();
        Account acc2 = new Account();

        // Thread 1: Menjumlahkan/ transfer fulus dari acc1 ke acc2
        Thread t1 = new Thread(() -> {
            synchronized (acc1) { // Solusinya Thread 1 mengunci acc1 terlebih dahulu jadi urutan kuncinya: acc1 → acc2 (sama dengan Thread 2)
                System.out.println("Thread 1: Mengunci acc1, mencoba mengunci acc2..."); // Penanda bahwa Thread 1 sedang memegang kunci acc1 dan akan mencoba mengunci acc2
                try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); } // InterruptedException ditangkap secara spesifik agar jenis error lebih jelas diketahui (lebih baik dari catch Exception umum)

                synchronized (acc2) { // Thread 1 mengunci acc2 sebagai kunci kedua — tidak ada deadlock karena urutannya konsisten dengan Thread 2
                    System.out.println("Thread 1: Berhasil mengunci acc2, melakukan transfer dari acc1 ke acc2"); // Penanda bahwa Thread 1 berhasil masuk ke blok ini dan sedang melakukan transfer saldo
                    acc2.balance += acc1.balance;
                }
            }
        });

        // Thread 2: Menjumlahkan/ transfer fulus dari acc2 ke acc1
        Thread t2 = new Thread(() -> {
            synchronized (acc1) { // Thread 2 juga mengunci acc1 terlebih dahulu (bukan acc2) — inilah kunci utama solusi deadlock
                System.out.println("Thread 2: Mengunci acc1, mencoba mengunci acc2..."); // Penanda bahwa Thread 2 sedang memegang kunci acc1 dan akan mencoba mengunci acc2
                try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }

                synchronized (acc2) { // Thread 2 mengunci acc2 sebagai kunci kedua — urutan kunci: acc1 → acc2 (sama dengan Thread 1, konsisten!)
                    System.out.println("Thread 2: Berhasil mengunci acc2, melakukan transfer dari acc2 ke acc1"); // Penanda bahwa Thread 2 berhasil masuk ke blok ini dan sedang melakukan transfer saldo
                    acc1.balance += acc2.balance;
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("--- HASIL AKHIR ---");
        System.out.println("Saldo Akhir acc1: " + acc1.balance);
        System.out.println("Saldo Akhir acc2: " + acc2.balance);
    }
}