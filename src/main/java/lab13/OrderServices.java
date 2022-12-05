package lab13;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServices {

    @Autowired
    Cart cart;

    private int Price;

    public void Order() {
        Price = 0;
        System.out.println("\nВ вашей корзине находятся следующие продукты: ");
        for (Product x : cart.Cart) {
            Price += x.price;
            System.out.println(x.title);
        }
        System.out.println("Итоговая цена продуктов в корзине: " + Price + "\n");
    }

    public void FastBuy(Product pr) {
        System.out.println("Совершена покупка: " + pr.title + " за " + pr.price);
    }
}