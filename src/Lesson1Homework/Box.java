package Lesson1Homework;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Cargo> {
    private final List<T> storage = new ArrayList<>();

    public void add(T piece) { storage.add(piece); }

    public float getWeight() {
        if (storage.size() > 0) { return storage.get(0).getWeight() * storage.size(); }
        return 0;
    }

    public boolean compare(Box anotherBox) {
        return anotherBox.getWeight() == this.getWeight();
    }

    public void shift(Box<T> destinationBox) {
        for (T piece:this.storage) {
            destinationBox.add(piece);
        }
        this.storage.clear();
    }
}
