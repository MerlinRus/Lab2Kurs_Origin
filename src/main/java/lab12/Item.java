package lab12;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
        name = "items"
)
public class Item extends Thread {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id_item"
    )
    private int id;
    @Column(
            name = "values"
    )
    private int values;

    public Item(int values) {this.values = values;}

    public void setId(int id) {
        this.id = id;
    }

    public int getId(int id) {
        return id;
    }

    public int getValues() {
        return this.values;
    }

    public void setValues(int values) {
        this.values = values;
    }
}
