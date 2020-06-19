package Lesson1Homework;

public class Apple implements Cargo{
    private static final float WEIGHT = 1f;

    @Override
    public float getWeight() {
        return WEIGHT;
    }
}
