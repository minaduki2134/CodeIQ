
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class CrossTabulation {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> inputList = getInputList();
        if (!(inputList.size() > 0)) {
            return;
        }
        inputList.remove(0);
        int[][] cross = new int[10][10];
        // x <= max < y
        // x <= min < y
        // 
        for (String line : inputList) {
            String[] split = line.split(",");
            double max = Double.parseDouble(split[1]);
            double min = Double.parseDouble(split[2]);
            int x = (int) ((max + 10.0) / 5.0);
            int y = (int) ((min + 10.0) / 5.0);
            cross[x][y]++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = -10; i < 40; i += 5) {
            if (i == -10) {
                sb.append(",");
            }
            sb.append(i).append("<=min<").append(i + 5);
            if (i < 35) {
                sb.append(",");
            }
        }
        System.out.println(sb.toString());

        for (int i = 0; i < 10; i++) {
            sb = new StringBuilder();
            sb.append((i - 2) * 5).append("<=max<").append((i - 2 + 1) * 5);
            int[] yLine = cross[i];
            for (int j = 0; j < yLine.length; j++) {
                int yValue = yLine[j];
                sb.append(",").append(yValue);
            }
            System.out.println(sb.toString());
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
}
