package lab13;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ProductService {
    private List<Product> productList;

    @PostConstruct
    public void init() {
        productList = new ArrayList<>();
        Random Cost = new Random();
        for (int i = 0; i < 12; i++) {
            productList.add(new Product(i, "Product#" + i, Cost.nextInt(400 - 100) + 100));
        }
    }

    public Product getProduct(int x) {
        return productList.get(x);
    }

    public void printAll() {
        for (Product x : productList) {
            System.out.println(x.info());
        }
    }

    public Product findByTitle(String title) {
        for (Product x : productList) {
            if (x.getTitle().equals(title)) {
                return getProduct(x.getId());
            }
        }
        return null;
    }

}