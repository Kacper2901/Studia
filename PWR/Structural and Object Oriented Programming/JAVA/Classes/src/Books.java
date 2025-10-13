class Book{
    private String title;
    private String author;
    private int pages;

    public Book(String title, String author, int pages){
        this.setAuthor(author);
        this.setTitle(title);
        this.setPages(pages);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setPages(int pages){
        if (pages < 1){
            System.out.println("Such book does not exist!");
        }
        else {
            this.pages = pages;
        }
    }

    public int getPages(){
        return this.pages;
    }

    public void info(){
        System.out.println("[Title]: " + this.title);
        System.out.println("[Author]: " + this.author);
        System.out.println("[Pages]: " + this.pages);
        System.out.println();
    }
}


public class Books{
    public static void main(String[] args){
        Book harryPotter = new Book("Harry Potter", "J.K. Rowling", 346);
        Book lordOfTheRings = new Book("Lord of the rings", "Tolkien", 555);

        harryPotter.info();
        lordOfTheRings.setTitle("Hobbit");
        lordOfTheRings.info();
    }
}