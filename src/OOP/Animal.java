package OOP;

public abstract class Animal {
    protected String name = "Animal";

    Animal(){
        System.out.println("Animal constructor");
    }

    Animal(String name){
        System.out.println("Animal constructor with name: " + name);
    }

    public abstract void speak();

    public void eat(){
        System.out.println("Animal eating");
    }
}
