package Day1;

public class Day1_Bai11 {
    public static void changeValue(int number){
        number = number + 1;
    }

    public static class Animal{
        private String name;

        Animal(String name){
            this.name = name;
        }
    }

    public static void main(String[] args) {
        // Ex for Pass by Value
        int number = 1;
        System.out.println("Number: " + number);
        changeValue(number);
        // Value of number without modifying
        System.out.println("Number: " + number);

        // Ex for Pass by reference
        Animal animal = new Animal("Cat");
        animal.name = "Dog";

        System.out.println(animal.name);
    }
}
