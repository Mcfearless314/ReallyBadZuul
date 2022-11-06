public class Item {

    private String itemName;
    private int itemWeight;
    private String itemDescription;


    public Item() {

        Item Computer = new Item();
        Computer.itemName = "Computer";
        Computer.itemDescription = "a laptop computer";
        Computer.itemWeight = 10;

        Item Pencil = new Item();
        Pencil.itemName = "Pencil";
        Pencil.itemDescription = "a pencil";
        Pencil.itemWeight = 1;

        Item Eraser = new Item();
        Eraser.itemName = "Eraser";
        Eraser.itemDescription = "an eraser";
        Eraser.itemWeight = 1;

        Item Notebook = new Item();
        Notebook.itemName = "Notebook";
        Notebook.itemDescription = "a notebook";
        Notebook.itemWeight = 2;

        Item Book1 = new Item();
        Book1.itemName = "Math Book";
        Book1.itemDescription = "a book about Math";
        Book1.itemWeight = 10;

        Item Book2 = new Item();
        Book2.itemName = "Physics Book";
        Book2.itemDescription = "a book about Physics";
        Book2.itemWeight = 10;

        Item Apple = new Item();
        Apple.itemName = "Apple";
        Apple.itemDescription = "a delicious red apple";
        Apple.itemWeight = 2;


    }

    public int getItemWeight() {
        return itemWeight;
    }

    public String getItemDescription() {
        return itemDescription;
    }

}
