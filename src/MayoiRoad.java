
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class MayoiRoad {

    private static final String[] road = {"y", "a", "b", "c", "z"};
    private static final int startNum = 2;

    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> inputList = getInputList();
        if (!(inputList.size() > 0)) {
            return;
        }
        int n = Integer.parseInt(inputList.get(0)); // 折り返し回数
        int p = 0;
        p = choice(startNum, n, 1, p); // パターン
        System.out.println(p);
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

    /**
     *
     * @param index 現在の座標
     * @param remainderN 反転回数
     * @param direction 進行方向
     * @param count Pのカウンタ
     * @return
     */
    private static int choice(int index, int remainderN, int direction, int count) {
        if (index <= 0) {
            // yに抜ける場合
            count++;
            return count;
        } else if (index >= road.length - 1) {
            // zに抜ける場合
            return count;
        }
        // まずは直進
        count = choice(index + direction, remainderN, direction, count);
        if (remainderN > 0) {
            // 反転回数が残っている場合は反転
            direction *= -1;
            remainderN -= 1;
            count = choice(index + direction, remainderN, direction, count);
        }
        return count;
    }
}
