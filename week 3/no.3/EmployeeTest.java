public class EmployeeTest {
    public static void main(String[] args) {
        // Mendeklarasikan dan mengalokasikan array untuk 3 objek Employee
        Employee[] staff = new Employee[3];

        // Inisialisasi data karyawan
        staff[0] = new Employee("Antonio Rossi", 2000000, 1, 10, 1989);
        staff[1] = new Employee("Maria Bianchi", 2500000, 1, 12, 1991);
        staff[2] = new Employee("Isabel Vidal", 3000000, 1, 11, 1993);

        // Menaikkan gaji setiap staf sebesar 5%
        for (int i = 0; i < 3; i++) {
            staff[i].raiseSalary(5);
        }

        System.out.println("=== Data Karyawan Setelah Kenaikan Gaji 5% ===");

        // Mencetak data dari setiap staf
        for (int i = 0; i < 3; i++) {
            staff[i].print();
        }

        // Part A: Memanggil compare()
        System.out.println("\n=== Part A: Test compare() ===");

        // Membandingkan staff[0] dengan staff[1]
        int result1 = staff[0].compare(staff[1]);
        System.out.println("Membandingkan " + staff[0].getName() + " vs " + staff[1].getName() + ": " + result1);
        // -1 berarti salary staff[0] lebih kecil dari staff[1]

        // Membandingkan staff[2] dengan staff[0]
        int result2 = staff[2].compare(staff[0]);
        System.out.println("Membandingkan " + staff[2].getName() + " vs " + staff[0].getName() + ": " + result2);
        // 1 berarti salary staff[2] lebih besar dari staff[0]

        // Membandingkan staff[1] dengan staff[1] (sama)
        int result3 = staff[1].compare(staff[1]);
        System.out.println("Membandingkan " + staff[1].getName() + " vs " + staff[1].getName() + ": " + result3);
        // 0 berarti salary sama

        // Shell Sort 
        System.out.println("\n=== Sebelum Sorting ===");
        for (int i = 0; i < staff.length; i++) {
            staff[i].print();
        }

        Sortable.shell_sort(staff);

        System.out.println("\n=== Setelah Shell Sort (urut dari gaji terkecil) ===");
        for (int i = 0; i < staff.length; i++) {
            staff[i].print();
        }
    }
}
