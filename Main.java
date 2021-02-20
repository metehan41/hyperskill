import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int start = scanner.nextInt();
        int end = scanner.nextInt();
        double total = 0;
        int counter = 0;
        for (int i = start; i <= end; i++) {
            if (i % 3 == 0) {
                total += i;
                counter += 1;
            }
        }
        System.out.println(total / counter);

    }
}