package OOP;

import java.util.List;

public class Test {
    private List<String> stringList;
    String a;

    public Test(List<String> stringList, String a) {
        this.stringList = stringList;
        this.a = a;
    }

    public void addElement(){
        stringList.add("ZZ");
        stringList.add("AA");
        a = "Hello";
    }

    public void removeElement(){
        stringList.remove(stringList.size() - 1);
    }

    @Override
    public String toString() {
        return "Test{" +
                "stringList=" + stringList +
                '}';
    }
}
