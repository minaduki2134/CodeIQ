
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class FavoriteCalendar {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> inputList = getInputList();
        if (inputList.isEmpty()) {
            return;
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");
        LocalDateTime ldt = LocalDateTime.parse(inputList.get(0) + "01 000000", dtf);
        outputCalendar(makeMonthCalendar(ldt, DayOfWeek.SUNDAY));
        outputCalendar(makeMonthCalendar(ldt, DayOfWeek.MONDAY));

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

    private static List<List<String>> makeMonthCalendar(LocalDateTime ldt, DayOfWeek startDayOfWeek) {
        // 月のリスト
        List<List<String>> month = new ArrayList<>();
        // 作業用LocalDateTime
        LocalDateTime wk = ldt.plusDays(0);
        // 表示する月の初日の週日付
        DayOfWeek firstDayofWeek = ldt.getDayOfWeek();
        // 曜日調整数
        int adjustmentNum;
        if(firstDayofWeek.getValue() - startDayOfWeek.getValue() >= 0) {
            adjustmentNum = firstDayofWeek.getValue() - startDayOfWeek.getValue();
        } else {
            adjustmentNum = 7 + (firstDayofWeek.getValue() - startDayOfWeek.getValue());
        }
        
        // 初週のリスト
        List<String> firstWeek = new ArrayList<>(7);
        for (int i = 0; i < 7; i++) {
            if (i < adjustmentNum) {
                firstWeek.add("  ");
            } else {
                firstWeek.add(String.format("%1$02d", wk.getDayOfMonth()));
                wk = wk.plusDays(1);
            }
        }
        month.add(firstWeek);
        while (wk.getMonthValue() == ldt.getMonthValue()) {
            List<String> week = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                if (wk.getMonthValue() != ldt.getMonthValue()) {
                    break;
                }
                week.add(String.format("%1$02d", wk.getDayOfMonth()));
                wk = wk.plusDays(1);
            }
            month.add(week);
        }
        return month;
    }

    private static void outputCalendar(List<List<String>> monthCalendar) {
        for (List<String> week : monthCalendar) {
            outputWeek(week);
        }
    }

    private static void outputWeek(List<String> week) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < week.size(); i++) {
            sb.append(week.get(i));
            if (i < week.size() - 1) {
                sb.append(",");
            }
        }
        System.out.println(sb.toString());
    }
}
