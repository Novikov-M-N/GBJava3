package ru.geekbrains.java3.lesson4.mt;

public class Singleton {
    private static Singleton instance = new Singleton();
    public static Singleton getInstance(){
        return  instance;
    }

    /**
     * ====
     */
}

class Singleton2{
    private static Singleton2 instance;
    private Singleton2(){

    }
    public static Singleton2 getInstance(){
        if(instance==null){
            instance = new Singleton2();
        }
        return  instance;
    }
}

class Singleton3{
    private static Singleton3 instance;
    private Singleton3(){}
    public static synchronized Singleton3 getInstance(){
        if(instance==null){
            instance = new Singleton3();
        }
        return  instance;
    }
}

class Singleton4{ // ленинивая инициализация
    private static volatile Singleton4 instance;
    public static Singleton4 getInstance(){
        Singleton4 localInstance = instance;
        if(localInstance==null){
            synchronized (Singleton4.class){
                localInstance=instance;
                if(localInstance==null){
                    instance=localInstance=new Singleton4();
                }
            }
        }
        return  localInstance;
    }
}

