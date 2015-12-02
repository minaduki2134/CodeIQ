
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class WhatAboutProgress {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> inputList = getInputList();
        if (inputList.isEmpty()) {
            return;
        }
        String inputValue = inputList.get(0);
        String[] splitValue = inputValue.split(",");
        double fullProgress = Integer.parseInt(splitValue[0]);
        double nowProgress = Integer.parseInt(splitValue[1]);
        String marker = splitValue[2];
        if (fullProgress < nowProgress) {
            System.out.print("invalid");
            return;
        }
        int par = (int) (nowProgress / fullProgress * 100);
        for (int i = 0; i < par; i++) {
            System.out.print(marker);
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
