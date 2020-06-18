package ex2.sportbag;

import java.util.ArrayList;
import java.util.List;

public class SportBag<T> {
    List<Transfer> storage = new ArrayList();

    public void addThing(Transfer o){
        storage.add(o);
    }
    public int getSize(){
        return storage.size();
    }
}

class City{}
class Milk implements Transfer{}
class Apple implements Transfer{}
class FootWear implements Transfer{}
class Cat{}
class Meat implements Transfer{}

interface Transfer{}

class Run{
    public static void main(String[] args) {
        SportBag sportBag = new SportBag();
//        sportBag.addThing(new City());
        sportBag.addThing(new Milk());
        sportBag.addThing(new Apple());
        sportBag.addThing(new FootWear());
//        sportBag.addThing(new Cat());
        sportBag.addThing(new Meat());
    }
}

