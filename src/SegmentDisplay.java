
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class SegmentDisplay {

    public static void main(String[] args) throws IOException {
        
        BufferedReader r
                = new BufferedReader(new InputStreamReader(System.in), 1);
        String line = r.readLine();
        int result = 0;
        try {
            int inputNum = Integer.parseInt(line);
            int hour;
            int minute;
            int seconds;
            for (hour = 0; hour < 24; hour++) {
                for (minute = 0; minute < 60; minute++) {
                    for (seconds = 0; seconds < 60; seconds++) {
                        int multiplesSeconds = NumberCount.getTargetCount(seconds);
                        int multiplesMinute = NumberCount.getTargetCount(minute);
                        int multiplesHour = NumberCount.getTargetCount(hour);
                        if ((multiplesSeconds + multiplesMinute + multiplesHour) == inputNum) {
                            result++;
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
        }
        System.out.println(result);
    }
}

enum NumberCount {

    ZERO(0, 6),
    ONE(1, 2),
    TWO(2, 5),
    THREE(3, 5),
    FOUR(4, 4),
    FIVE(5, 5),
    SIX(6, 6),
    SEVEN(7, 3),
    EIGHT(8, 7),
    NINE(9, 6);

    private int num;
    private int count;

    private NumberCount(int num, int count) {
        this.num = num;
        this.count = count;
    }

    /**
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    private static int getCountOfNum(int num) {
        NumberCount[] values = NumberCount.values();
        for (int i = 0; i < values.length; i++) {
            NumberCount value = values[i];
            if (value.getNum() == num) {
                return value.getCount();
            }
        }
        return 0;
    }

    public static int getTargetCount(int target) {
        int multiples10 = target / 10;
        int multiples10Count = getCountOfNum(multiples10);
        int multiples1 = target % 10;
        int multiples1Count = getCountOfNum(multiples1);
        return multiples10Count + multiples1Count;
    }
}
