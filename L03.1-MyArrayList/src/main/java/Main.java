import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        MyArrayList malFirst = new MyArrayList(3);
        MyArrayList malSecond = new MyArrayList(3);
        Collections.addAll(malFirst,1,2,3);
        Collections.addAll(malSecond,6,5,4);
        Collections.copy(malFirst, malSecond);
        Collections.sort(malFirst);
        System.out.println(malFirst.toString());
    }
}
