
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class PlusMinusZero {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> inputList = getInputList();
        outputCalc(inputList.stream()
                .map((String str) -> Integer.parseInt(str))
                .collect(Collectors.toList()));
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
    
    private static void outputCalc(List<Integer> nums) {
        if (nums == null || nums.isEmpty() || nums.size() < 2) {
            System.out.println(0);
            return;
        }
        int resultNum = 0;
        for (int i = 0; i < nums.size(); i++) {
            Integer base = nums.get(i);
            for (int j = i + 1; j < nums.size(); j++) {
                Integer subject = nums.get(j);
                if ((base + subject) == 0) {
                    resultNum++;
                }
            }
        }
        System.out.println(resultNum);
    }
}
