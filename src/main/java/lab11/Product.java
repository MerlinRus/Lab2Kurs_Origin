package lab11;

import javax.persistence.*;

@Entity
public class Product
{
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column (name ="Name", nullable = false)
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Column (name ="Price", nullable = false)
    private int Price;

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        this.Price = price;
    }
}
