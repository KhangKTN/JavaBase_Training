package OOP;

public class Dog extends Animal {
    Dog(String s){
        super(s);
    }

    @Override
    public void speak(){
        System.out.println("Dog speak");
    }
}
