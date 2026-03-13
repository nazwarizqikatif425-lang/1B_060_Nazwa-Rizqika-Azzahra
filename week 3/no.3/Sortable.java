public abstract class Sortable {
    public abstract int compare(Sortable b);

    public static void shell_sort(Sortable[] a) {
        int n = a.length;
        int gap = n / 2;

        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                Sortable temp = a[i];
                int j = i;

                while (j >= gap && a[j - gap].compare(temp) > 0) {
                    a[j] = a[j - gap];
                    j -= gap;
                }
                a[j] = temp;
            }
            gap /= 2;
        }
    }
}
