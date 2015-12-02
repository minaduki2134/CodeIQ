
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ishida-shi
 */
class BinaryTreeSymmetry {

    static int center;

    static List<String> trees = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> inputList = getInputList();
        if (inputList.isEmpty()) {
            return;
        }
        String inputValue = inputList.get(0);
        int themeNumber = Integer.parseInt(inputValue);
        if (themeNumber % 2 == 0 || themeNumber < 3) {
            System.out.println("0");
            return;
        }
        center = (int) ((themeNumber + 1) / 2);
        execute();
        System.out.println(trees.size());
    }

    private static void execute() {
        List<String> listValues = new ArrayList<>();
        List<String> valueList = setNewValueList(listValues);
        for (int i = valueList.size() - 1; i >= 0; i--) {
            String value = valueList.get(i);
            BinaryTreeData root = new BinaryTreeData(Integer.parseInt(value), null);
            valueList.remove(value);
            roop(root, valueList);
            valueList.add(i, value);
        }
    }

    private static void roop(BinaryTreeData masterBtd, List<String> valueList) {
        if (valueList.isEmpty()) {
            boolean match = trees.stream().parallel()
                    .anyMatch((tree) -> tree.equals(masterBtd.toString()));
            if (!match) {
                trees.add(masterBtd.toString());
            }
            return;
        }
        for (int i = valueList.size() - 1; i >= 0; i--) {
            String value = valueList.get(i);
            masterBtd.insert(Integer.parseInt(value));
            valueList.remove(i);
            roop(masterBtd, valueList);
            masterBtd.delete(Integer.parseInt(value));
            valueList.add(i, value);
        }
    }

    private static List<String> setNewValueList(List<String> src) {
        IntStream range = IntStream.range(1, center);
        range.forEach((f) -> src.add(String.valueOf(f)));
        return src;
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

class BinaryTreeData {

    public BinaryTreeData(int value, BinaryTreeData parent) {
        this.value = value;
        this.parent = parent;
    }

    private int value;
    private BinaryTreeData left;
    private BinaryTreeData right;
    private BinaryTreeData parent;

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the left
     */
    public BinaryTreeData getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(BinaryTreeData left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public BinaryTreeData getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(BinaryTreeData right) {
        this.right = right;
    }

    /**
     * @return the parent
     */
    public BinaryTreeData getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(BinaryTreeData parent) {
        this.parent = parent;
    }

    public void insert(int newValue) {
        if (parent == null && this.value == 0) {
            this.value = newValue;
        } else if (this.value > newValue) {
            if (this.left == null) {
                this.left = new BinaryTreeData(newValue, this);
            } else {
                this.left.insert(newValue);
            }
        } else if (this.value < newValue) {
            if (this.right == null) {
                this.right = new BinaryTreeData(newValue, this);
            } else {
                this.right.insert(newValue);
            }
        }
    }

    public boolean equals(BinaryTreeData target) {
        if (this.value == target.getValue()) {
            boolean isRight = false;
            boolean isLeft = false;
            if (this.getRight() != null && target.getRight() != null) {
                isRight = this.getRight().equals(target.getRight());
            } else if (this.getRight() == null && target.getRight() == null) {
                isRight = true;
            }
            if (this.getLeft() != null && target.getLeft() != null) {
                isLeft = this.getLeft().equals(target.getLeft());
            } else if (this.getLeft() == null && target.getLeft() == null) {
                isLeft = true;
            }
            if (isRight && isLeft) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.left != null) {
            sb.append(this.left.toString());
        }
        if (this.right != null) {
            sb.append(this.right.toString());
        }
        sb.append(String.valueOf(this.value));
        return sb.toString();
    }

    public void delete(int value) {
        if (this.value == value) {
            if (this.parent != null) {
                if (this.parent.left != null) {
                    if (this.value == this.parent.left.value) {
                        this.parent.left = null;
                    }
                }
                if (this.parent.right != null) {
                    if (this.value == this.parent.right.value) {
                        this.parent.right = null;
                    }
                }
            } else {
                this.value = 0;
            }
        } else if (this.value < value) {
            if (this.right != null) {
                this.right.delete(value);
            }
        } else if (this.value > value) {
            if (this.left != null) {
                this.left.delete(value);
            }
        }

    }
}
