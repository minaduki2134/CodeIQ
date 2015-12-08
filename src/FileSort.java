
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/*

abc1.txt
abc2.txt
abc10.txt
abc21.txt
abc100.txt
abc01.txt
ABC1.png
abc1.csv
abc021.png
 

 */
public class FileSort {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> fileNameList = getInputList();
        if (!(fileNameList.size() > 0)) {
            return;
        }
        List<String> sortedList = sort(fileNameList);
        sortedList.forEach(System.out::println);
//        fileNameList.forEach(System.out::println);
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

    private static List<String> sort(List<String> fileNameList) {
        List<String> result = fileNameList.stream()
                .map((fileName) -> {
                    String[] split = fileName.split("\\.");
                    return split;
                })
                .map((String[] fileNameAndExtension) -> {
                    Pattern p = Pattern.compile(".*.[^0-9]\\d+");
//                    Pattern p = Pattern.compile(".*.[0-9]+");
                    Matcher m = p.matcher(fileNameAndExtension[0]);
                    String fileNameLeft;
                    String fileNameRight;
                    if (m.matches()) {
                        fileNameLeft = fileNameAndExtension[0].replaceAll("(.*.[^0-9])(\\d+)", "$1");
                        fileNameRight = fileNameAndExtension[0].replaceAll("(.*.[^0-9])(\\d+)", "$2");
//                        fileNameLeft = fileNameAndExtension[0].replaceAll("(.*)(.[0-9]+)", "$1");
//                        fileNameRight = fileNameAndExtension[0].replaceAll("(.*)(.[0-9]+)", "$2");
                    } else {
                        fileNameLeft = fileNameAndExtension[0];
                        fileNameRight = "";
                    }
                    return new String[]{fileNameLeft, fileNameRight, fileNameAndExtension[1]};
                })
                .sorted((String[] file1, String[] file2) -> {
                    Pattern p = Pattern.compile("\\d+");
                    Matcher m1 = p.matcher(file1[1]);
                    Matcher m2 = p.matcher(file2[1]);
                    if (m1.matches() && m2.matches()) {
                        // どちらも数字がある場合
                        if (!file1[0].equals(file2[0])) {
                            // ファイルの左側が違う場合はファイルの左側で並び替え
                            return file1[0].compareTo(file2[0]);
                        } else {
                            // ファイルの左側が同じ場合は数字で並び替え
                            int num1 = Integer.parseInt(file1[1]);
                            int num2 = Integer.parseInt(file2[1]);
                            if (num1 - num2 != 0) {
                                return num1 - num2;
                            } else {
                                // 数値も同じ場合は拡張子で並び替え
                                return file1[2].compareTo(file2[2]);
                            }
                        }
                    } else {
                        // 片方、またはどちらも数字がない場合
                        if (!file1[0].equals(file2[0])) {
                            // ファイルの左側が違う場合はファイルの左側で並び替え
                            return file1[0].compareTo(file2[0]);
                        } else {
                            // ファイルの左側が同じ場合はファイル名でソート
                            String file1Full = String.join(".", file1);
                            String file2Full = String.join(".", file2);
                            return file1Full.compareTo(file2Full);
                        }
                    }
                })
                .map((fileNameArray) -> fileNameArray[0] + fileNameArray[1] + "." + fileNameArray[2])
                .collect(Collectors.toList());
        return result;
    }
}
