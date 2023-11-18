import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        int qtyText = 10_000;

        Random random = new Random();
        BlockingQueue<String> texts = new ArrayBlockingQueue<>(100);

        new Thread(() -> {
            for (int i = 0; i < qtyText; i++) {
                try {
                    texts.put(generateText("abc", 100_000 + random.nextInt(100_000)));
                } catch (InterruptedException e) {
                    return;
                }
            }
        }).start();


        new Thread(() -> {

            int max = 0;
            int x = 0;
            for (int i = 0; i < qtyText; i++) {
                try {
                    x = countOccurrences(texts.take(), 'a');
                } catch (InterruptedException e) {
                    return;
                }
                if (max < x) {
                    max = x;
                }
            }
            System.out.println("Максимальное количество 'a' " + max);

        }).start();

        new Thread(() -> {

            int max = 0;
            int x = 0;
            for (int i = 0; i < qtyText; i++) {
                try {
                    x = countOccurrences(texts.take(), 'b');
                } catch (InterruptedException e) {
                    return;
                }
                if (max < x) {
                    max = x;
                }
            }
            System.out.println("Максимальное количество 'b' " + max);

        }).start();

        new Thread(() -> {

            int max = 0;
            int x = 0;
            for (int i = 0; i < qtyText; i++) {
                try {
                    x = countOccurrences(texts.take(), 'c');
                } catch (InterruptedException e) {
                    return;
                }
                if (max < x) {
                    max = x;
                }
            }
            System.out.println("Максимальное количество 'c' " + max);

        }).start();


    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    private static int countOccurrences(String str, char ch) {
        return str.length() - str.replace(String.valueOf(ch), "").length();
    }
}
