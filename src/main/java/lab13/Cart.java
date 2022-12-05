package lab13;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    public List<Product> Cart = new ArrayList<>();

    public void Add(Product x)
    {
        Cart.add(x);
    }
}