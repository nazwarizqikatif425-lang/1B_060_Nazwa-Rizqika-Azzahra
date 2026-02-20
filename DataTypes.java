import java.math.BigInteger;
import java.util.Scanner; 

public class DataTypes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); 
        sc.nextLine(); 

        for (int i = 0; i < T; i++) {
            String input = sc.nextLine();
            try {
                BigInteger number = new BigInteger(input);

                boolean canFit = false;
                System.out.println(input + " can be fitted in:");

                // Cek tiap tipe data
                if (number.compareTo(BigInteger.valueOf(Byte.MIN_VALUE)) >= 0 &&
                    number.compareTo(BigInteger.valueOf(Byte.MAX_VALUE)) <= 0) {
                    System.out.println("  * byte");
                    canFit = true;
                }

                if (number.compareTo(BigInteger.valueOf(Short.MIN_VALUE)) >= 0 &&
                    number.compareTo(BigInteger.valueOf(Short.MAX_VALUE)) <= 0) {
                    System.out.println("  * short");
                    canFit = true;
                }

                if (number.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) >= 0 &&
                    number.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0) {
                    System.out.println("  * int");
                    canFit = true;
                }

                if (number.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) >= 0 &&
                    number.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) <= 0) {
                    System.out.println("  * long");
                    canFit = true;
                }

                if (!canFit) {
                    System.out.println(input + " can't be fitted anywhere.");
                }

            } catch (Exception e) {
                System.out.println(input + " can't be fitted anywhere.");
            }
        }

        sc.close();
    }
}
