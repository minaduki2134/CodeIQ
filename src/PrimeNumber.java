
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class PrimeNumber {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> inputList = getInputList();
        if (inputList.isEmpty()) {
            return;
        }
        for (String inputValue : inputList) {
            int target;
            try {
                target = Integer.parseInt(inputValue.trim());
            } catch (NumberFormatException ex) {
                continue;
            }
            IntStream range = IntStream.range(2, target);
            long count = range.parallel()
                    .filter((value) -> checkPrime(value))
                    .count();
            System.out.println(count);
        }
    }

    private static List<String> getInputList() throws IOException, InterruptedException {
        BufferedReader r
                = new BufferedReader(new InputStreamReader(System.in), 1);
        List<String> inputList = new ArrayList<>();
        while (true) {
            int i = 0;
            while (!r.ready()) {
                Thread.sleep(200);
                i++;
                if (i == 10) {
                    return inputList;
                }
            }
            inputList.add(r.readLine());
        }
    }

    private static boolean checkPrime(int value) {
        if (value == 2) {
            return true;
        }
        if (value == 3) {
            return true;
        }
        if (value % 2 == 0) {
            return false;
        }
        for (int i = 3; i < (value / (i - 2)); i += 2) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }
}
