package lab13;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = context.getBean("productService", ProductService.class);
        productService.init();
        productService.printAll();
        Cart cart = context.getBean("cart",Cart.class);
        cart.Add(productService.findByTitle("Product#1"));
        cart.Add(productService.findByTitle("Product#2"));
        cart.Add(productService.findByTitle("Product#3"));
        cart.Add(productService.findByTitle("Product#10"));
        OrderServices order = context.getBean("orderServices", OrderServices.class);
        order.Order();
        order.FastBuy(productService.findByTitle("Product#3"));
        order.FastBuy(productService.findByTitle("Product#10"));
        ((AnnotationConfigApplicationContext)context).close();
    }
}