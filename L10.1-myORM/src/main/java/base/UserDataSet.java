package base;

import java.math.BigInteger;

public class UserDataSet extends DataSet {
    private final BigInteger id;
    private final String name;
    private final int age;

    public UserDataSet(BigInteger id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
