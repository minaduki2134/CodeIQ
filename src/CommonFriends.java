
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class CommonFriends {

    public static void main(String[] args) throws InterruptedException, IOException {
        List<String> inputList = getInputList();
        if (!(inputList.size() > 0)) {
            return;
        }
        int maxFriendNumber = Integer.parseInt(inputList.get(0));
        List<FriendRelation> friendRelations = getFriendRelations(inputList);
        int maxCommonFriendsNum = 0;
        for (int i = 0; i < friendRelations.size(); i++) {
            FriendRelation fr = friendRelations.get(i);
            for (int j = i + 1; j < friendRelations.size(); j++) {
                FriendRelation compFr = friendRelations.get(j);
                int thisMax = getMaxCommonFriendsNum(fr.getFriendList(), compFr.getFriendList(), maxCommonFriendsNum);
                if (maxCommonFriendsNum < thisMax) {
                    maxCommonFriendsNum = thisMax;
                }
                if ((maxFriendNumber - 1) == maxCommonFriendsNum) {
                    // 可能な共有友人数の為、終了
                    break;
                }
            }
        }
        System.out.println(maxCommonFriendsNum);
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

    private static List<FriendRelation> getFriendRelations(List<String> inputList) {
        List<FriendRelation> friendRelations = new ArrayList<>();
        for (int i = 1; i < inputList.size(); i++) {
            String value = inputList.get(i);
            String[] connection = value.split(" ");
            List<String> leftFriendList = getFriendList(friendRelations, connection[0]);
            leftFriendList.add(connection[1]);
            List<String> rightFriendList = getFriendList(friendRelations, connection[1]);
            rightFriendList.add(connection[0]);
        }
        return friendRelations;
    }

    private static List<String> getFriendList(List<FriendRelation> list, String key) {
        List<FriendRelation> collect = list.stream()
                .filter((fr) -> fr.getUser().equals(key))
                .collect(Collectors.toList());
        if (collect == null || collect.size() < 1) {
            // 友人関係を作成
            FriendRelation fr = new FriendRelation();
            fr.setUser(key);
            fr.setFriendList(new ArrayList<>());
            list.add(fr);
            return fr.getFriendList();
        }
        return collect.get(0).getFriendList();
    }

    private static int getMaxCommonFriendsNum(List<String> srcFriendList, List<String> subFriendList, int maxCommonFriendsNum) {
        // 現在の最大数より今回の比較リストのサイズが小さい場合はチェックしない。
        if (srcFriendList.size() <= maxCommonFriendsNum || subFriendList.size() <= maxCommonFriendsNum) {
            return 0;
        }
        int result = 0;
        for (String friendNum : srcFriendList) {
            if (subFriendList.contains(friendNum)) {
                result++;
            }
        }
        return result;
    }
}

class FriendRelation {

    private String user;
    private List<String> friendList;

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the friendList
     */
    public List<String> getFriendList() {
        return friendList;
    }

    /**
     * @param friendList the friendList to set
     */
    public void setFriendList(List<String> friendList) {
        this.friendList = friendList;
    }

}
