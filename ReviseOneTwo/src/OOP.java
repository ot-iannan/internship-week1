import java.util.ArrayList;

public class OOP {
    public static void main(String[] args){
        Book book= new Book("Frequencies to the Cells", "Irene Annan");
        Magazine magazine= new Magazine("The Light", 456356);

        Member member= new Member("Trinity");
        member.borrow(book);
        member.borrow(magazine);

        member.listBorrowedItems();
    }

}
abstract class LibraryItem{
    private String title;

    public LibraryItem(String title){
        this.title=title;
    }

    public String getTitle(){
        return title;
    }

    abstract void describe();
}

interface Borrowable{
    void checkout();
}

class Book extends LibraryItem implements Borrowable{
    private String author;

    public Book (String title, String author){
        super(title);
        this.author=author;

    }

    void describe(){
        System.out.println("Book:"+ getTitle()+ "by"+ author);
    }

    public void checkout(){
        System.out.println(getTitle()+ "has been purchased");
    }
}

class Magazine extends LibraryItem implements Borrowable{
    private int issueNumber;

    public Magazine(String title, int issueNumber){
        super(title);
        this.issueNumber=issueNumber;
    }

    void describe(){
        System.out.println(getTitle()+ " magazine with"+ issueNumber+ "has been borrowed");

    }

    public void checkout(){
        System.out.println(getTitle()+ "has been purchased");
    }

}

class Member{
    private String name;
    private ArrayList<LibraryItem> borrowedItems= new ArrayList<>();
    public Member(String name){
        this.name=name;
    }
    public void borrow(LibraryItem item){
        borrowedItems.add(item);
        if (item instanceof Borrowable){
            ((Borrowable) item).checkout();
            ;
        }
    }

    public void listBorrowedItems(){
        System.out.println(name + " 's borrowed items: ");
        for (int i =0; i< borrowedItems.size(); i++){
            borrowedItems.get(i).describe();
        }


    }
}


