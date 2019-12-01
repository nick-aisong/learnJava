package cn.part05.ch38.nullObject;

//代码清单38-39 听动物叫声的人
public class Person {
    //听到动物叫声
    public void hear(Animal animal) {
        if (animal != null) {
            animal.makeSound();
        }
    }
}