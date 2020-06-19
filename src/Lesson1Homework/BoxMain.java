package Lesson1Homework;

public class BoxMain {
    public static void main(String[] args) {
        Box<Apple> box1 = new Box<>();
        Box<Orange> box2 = new Box<>();
        Box<Orange> box3 = new Box<>();

        box1.add(new Apple());
        box1.add(new Apple());
        box1.add(new Apple());
        box2.add(new Orange());
        box2.add(new Orange());
        box3.add(new Orange());
        box3.add(new Orange());
        box3.add(new Orange());

        System.out.println("box1 weight: " + box1.getWeight());
        System.out.println("box2 weight: " + box2.getWeight());
        System.out.println("box3 weight: " + box3.getWeight());

        System.out.println("Compare weights of box1 and box2: " + box1.compare(box2));
        System.out.println("Compare weights of box1 and box3: " + box1.compare(box3));

        box2.shift(box3);

        System.out.println("box3 weight after shifting: " + box3.getWeight());
        System.out.println("box2 weight after shifting: " + box2.getWeight());
    }
}
