package OOP;

public class Cat extends Animal {
    protected String name = "Cat";

    Cat(){
        System.out.println(2);
    }

    Cat(String name){
        super(name);
        System.out.println(name);
    }

    @Override
    public void speak(){
        System.out.println("Cat speak");
    }
}
