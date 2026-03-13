import java.util.Scanner;

public class RestaurantMain {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Restaurant menu = new Restaurant();

        menu.tambahMenuMakanan("Pizza", 250000, 20);
        menu.tambahMenuMakanan("Spaghetti", 80000, 20);
        menu.tambahMenuMakanan("Tenderloin Steak", 60000, 30);
        menu.tambahMenuMakanan("Chicken Steak", 45000, 30);

        System.out.println("=== DAFTAR MENU ===");
        menu.tampilMenuMakanan();

        System.out.print("\nMasukkan nama menu: ");
        String nama = input.nextLine();

        System.out.print("Masukkan jumlah pesanan: ");
        int jumlah = input.nextInt();

        menu.pesanMenu(nama, jumlah);

        System.out.println("\n=== MENU SETELAH PEMESANAN ===");
        menu.tampilMenuMakanan();

        input.close();
    }
}
