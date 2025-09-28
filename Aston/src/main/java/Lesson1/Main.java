package Lesson1;

public class Main {

    public static void main(String[] args) {
        MyHashMap<String, Object> myHashMap = new MyHashMap<>();
        myHashMap.put("apple", 5);
        myHashMap.put("orange", 2);
        myHashMap.put("cherry", 9);
        myHashMap.put("apple", 4);
//        myHashMap.remove("apple");


        System.out.println(myHashMap.get("cherry"));
    }
}
