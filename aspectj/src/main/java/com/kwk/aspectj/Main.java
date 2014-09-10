package com.kwk.aspectj;

public class Main {
    public static void main(String[] args) {
//        MessageCommunicator messageCommunicator
//                = new MessageCommunicator();
//        messageCommunicator.deliver("Wanna learn AspectJ?");
//        messageCommunicator.deliver("Harry", "having fun?");

        Person p = new Person(10);
        p.update();
    }
}