
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class PrimeCheck {

    public static void main(String[] args) throws IOException {
        BufferedReader r
                = new BufferedReader(new InputStreamReader(System.in), 1);
        String line = r.readLine();
        try {
            int parseInt = Integer.parseInt(line);
        } catch (NumberFormatException e) {
        }

    }
}
