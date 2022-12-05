package lab11;

import javax.persistence.*;

@Entity
public class CustomerHistory
{
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column (name ="Name", nullable = false)
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }


    @Column (name ="NameProduct", nullable = false)
    private String NameProduct;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String NameProduct) {
        this.NameProduct = NameProduct;
    }

}
