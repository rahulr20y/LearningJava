package com.practice.learningjava.Immutable;

import java.util.ArrayList;
import java.util.List;

class MutableBook {
    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "MutableBook [title=" + title + "]";
    }
}

final class MyImmutableClass {
    private final String name;
    private final List<Object> list;
    private final MutableBook mutableBook;

    public MyImmutableClass(String name, List<Object> list, MutableBook book) {
        this.name = name;

        // this.list = list;// not allowed since argument has reference
        this.list = new ArrayList<>(list); // deep copy

        // this.mutableBook = mutableBook;
        // Make deep copy to ensure this books state won't change.
        MutableBook bookCopy = new MutableBook();
        bookCopy.setTitle(book.getTitle());
        this.mutableBook = bookCopy;
    }

    public String getName() {
        return name;
    }

    public List<Object> getList() {
        // return list; -- not allowed
        /*
         * with this we won't be able to make immutable class it is
         * because after refrence this list to some other can change its value which is
         * not acceptable in case immutable class
         * This is required, because making list final means you can not point it to new
         * list but still can add delete values in it so thats why we send the copy of
         * it.
         */

        return new ArrayList<>(this.list); // copy of member variable
    }

    public MutableBook getMutableBook() {
        // Do not return the book, but a new copy. Do not want the readers
        // book to change it's state if developer changes book after this call.
        MutableBook bookCopy = new MutableBook();
        bookCopy.setTitle(this.mutableBook.getTitle());
        return bookCopy;
    }

    @Override
    public String toString() {
        return "MyImmutableClass [name=" + name + ", list=" + list + ", mutableBook=" + mutableBook + "]";
    }

}

public class ImmutableClassMain {
    public static void main(String[] args) {
        /*
         * Immuatable class:
         * We cannot change the value of an object once it is created
         * Declare class as final so that it can not be extended - preventing it from
         * its child class to change value
         * All class members should be private. So that direct access can be avoided.
         * And class members are initialized only once using constructor.
         * There should not be any setters, which is generally used to change the value.
         * Just getter method and returns copy of the member variable.
         * Example: String, Wrapper Classes etc.
         */
        System.out.println("Immutable Class Example...");
        List<Object> list = new ArrayList<>();
        list.add("SJ");
        list.add("PJ");
        MutableBook mutableBook = new MutableBook();
        mutableBook.setTitle("Kite Runner");
        MyImmutableClass myImmutableClass = new MyImmutableClass("Immutable", list, mutableBook);
        myImmutableClass.getList().add("KJ");
        System.out.println(myImmutableClass.getList());
        System.out.println(myImmutableClass.getMutableBook());
    }
}
