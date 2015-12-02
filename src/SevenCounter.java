
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

class SevenCounter {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> inputList = getInputList();
        for (String value : inputList) {
            long result = count(value);
            System.out.println(result);
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

    private static long count(String value) {
        long longValue = Long.parseLong(value);
        String searchStr = "7";
        LongStream ls = LongStream.rangeClosed(1, longValue);
        long count = ls
//                .parallel()
                .filter((l) -> String.valueOf(l).contains(searchStr))
                .map((l) -> {
                    String tmpStr = String.valueOf(l);
                    int targetSize = tmpStr.length();
                    int replacedSize = tmpStr.replace(searchStr, "").length();
                    return (targetSize - replacedSize) / searchStr.length();
                })
                .sum();
        return count;
    }
}
