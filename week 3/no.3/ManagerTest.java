public class ManagerTest {
    public static void main(String[] args) {
        // Mendeklarasikan dan mengalokasikan array untuk 3 objek Employee
        Employee[] staff = new Employee[3];

        // Mengisi array dengan kombinasi Employee dan Manager (Polimorfisme)
        staff[0] = new Employee("Antonio Rossi", 2000000, 1, 10, 1989);
        staff[1] = new Manager("Maria Bianchi", 2500000, 1, 12, 1991);
        staff[2] = new Employee("Isabel Vidal", 3000000, 1, 11, 1993);

        // Menaikkan gaji semua staf sebesar 5%
        for (int i = 0; i < 3; i++) {
            staff[i].raiseSalary(5);
        }

        System.out.println("=== Data Setelah Kenaikan Gaji ===");

        // Mencetak data setiap staf
        for (int i = 0; i < 3; i++) {
            staff[i].print();
        }

        // Test compare() pada Manager
        System.out.println("\n=== Test compare() pada Manager ===");
        int result = staff[1].compare(staff[0]);
        System.out.println("Membandingkan Manager " + staff[1].getName()
            + " vs Employee " + staff[0].getName() + ": " + result);

        // Shell sort pada campuran Employee dan Manager
        System.out.println("\n=== Sebelum Sorting ===");
        for (int i = 0; i < staff.length; i++) {
            staff[i].print();
        }

        Sortable.shell_sort(staff);

        System.out.println("\n=== Setelah Shell Sort ===");
        for (int i = 0; i < staff.length; i++) {
            staff[i].print();
        }
    }
}
