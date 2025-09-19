package reflection;

public class Human {
    private int age;
    private double height;

    private Human(double height){
        this.height = height;
    }

    public void printAge() {
        System.out.println(age);
    }

    public void grow() {
        this.height += 0.2;
    }

    public void grow(double value) {
        this.height += value;
    }

    public double getHeight() {
        return height;
    }

    private void simplePrivate() {
        System.out.println("I`m private");
    }

}
