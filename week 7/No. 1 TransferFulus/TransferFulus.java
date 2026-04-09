class Account {
    int balance = 150;
}

public class TransferFulus {
    public static void main(String[] args) throws InterruptedException {
        Account acc1 = new Account();
        Account acc2 = new Account();

        // Thread 1: Menjumlahkan/ transfer fulus dari acc1 ke acc2
        Thread t1 = new Thread(() -> {
            synchronized (acc1) { // Thread 1 mengunci (lock) objek acc1 agar tidak bisa diakses thread lain saat blok ini berjalan
                System.out.println("Thread 1: Mengunci acc1, mencoba mengunci acc2..."); // Penanda bahwa Thread 1 sedang memegang kunci acc1 dan akan mencoba mengunci acc2
                try { Thread.sleep(100); } catch (Exception e) {} // Simulasi dengan memberikan jeda. Exception diperlukan karena Thread.sleep() adalah checked exception (InterruptedException) yang wajib ditangani agar program tidak error saat dikompilasi

                synchronized (acc2) { // Thread 1 mencoba mengunci objek acc2, ini penyebab deadlock, karena acc2 sudah dikunci Thread 2
                    System.out.println("Thread 1: Berhasil mengunci acc2, melakukan transfer dari acc1 ke acc2"); // Penanda bahwa Thread 1 berhasil masuk ke blok ini dan sedang melakukan transfer saldo
                    acc2.balance += acc1.balance;
                }
            }
        });

        // Thread 2: Menjumlahkan/ transfer fulus dari acc2 ke acc1
        Thread t2 = new Thread(() -> {
            synchronized (acc2) { // Thread 2 mengunci (lock) objek acc2 agar tidak bisa diakses thread lain saat blok ini berjalan
                System.out.println("Thread 2: Mengunci acc2, mencoba mengunci acc1..."); // Penanda bahwa Thread 2 sedang memegang kunci acc2 dan akan mencoba mengunci acc1
                try { Thread.sleep(100); } catch (Exception e) {}

                synchronized (acc1) { // Thread 2 mencoba mengunci objek acc1, ini penyebab deadlock, karena acc1 sudah dikunci Thread 1
                    System.out.println("Thread 2: Berhasil mengunci acc1, melakukan transfer dari acc2 ke acc1"); // Penanda bahwa Thread 2 berhasil masuk ke blok ini dan sedang melakukan transfer saldo
                    acc1.balance += acc2.balance;
                }
            }
        });

        t1.start(); // Menjalankan Thread 1
        t2.start(); // Menjalankan Thread 2

        t1.join(); // Main thread menunggu Thread 1 selesai
        t2.join(); // Main thread menunggu Thread 2 selesai
        // Karena deadlock, kedua join() ini akan menunggu selamanya
        // dan baris kode di bawahnya tidak akan pernah dieksekusi

        System.out.println("--- HASIL AKHIR ---");
        System.out.println("Saldo Akhir acc1: " + acc1.balance);
        System.out.println("Saldo Akhir acc2: " + acc2.balance);
    }
}
