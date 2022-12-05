package lab11;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Connect {
    public static void main(String[] args) throws SQLException {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").
                addAnnotatedClass(Product.class).
                addAnnotatedClass(Customer.class).
                addAnnotatedClass(CustomerHistory.class).
                buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        //Очистка таблиц
        Query Queri = session.createQuery("DELETE CustomerHistory");
        Queri.executeUpdate();

        Queri = session.createQuery("DELETE Customer");
        Queri.executeUpdate();

        Queri = session.createQuery("DELETE Product");
        Queri.executeUpdate();

        //ДОБАВЛЕНИЕ ПОКУПАТЕЛЕЙ
        String[] Customers = {"Рома", "Егор", "Володя"};
        for (int i = 1; i <= Customers.length; i++) {
            Customer customer = new Customer();
            customer.setId(i);
            customer.setName(Customers[i - 1]);
            session.save(customer);
        }

        //ДОБАВЛЕНИЕ ТОВАРОВ //ПОКУПКА ТОВАРОВ
        HashMap<String, Integer> products = new HashMap<String, Integer>();
        products.put("Молоко", 300);
        products.put("Сахар", 500);
        products.put("Кефир", 380);
        int b = 1;
        for (String key : products.keySet()) {
            Product product = new Product();
            product.setId(b);
            product.setName(key);
            product.setPrice(products.get(key));
            session.save(product);

            CustomerHistory history = new CustomerHistory();
            history.setId(b);
            history.setName(Customers[b - 1]);
            history.setNameProduct(key);
            session.save(history);
            b++;
        }
        session.getTransaction().commit();

        //ЗАПРОСЫ
        Scanner scanner = new Scanner(System.in);
        int command;
        do {
            System.out.println("Введите цифру для запроса");
            System.out.println("1 - посмотреть какие товары покупал клиент");
            System.out.println("2 - какие клиенты купили определенный товар");
            System.out.println("3 - удалить из базы товар");
            System.out.println("4 - удалить из базы покупателя");
            System.out.println("5 - Выход");
            System.out.println("6 - Задание 4 Cколько стоил товар, в момент покупки клиентом");
            command = Integer.parseInt(scanner.nextLine());
            switch(command)
            {
                case 1:
                    session.beginTransaction();
                    System.out.println("Введите имя клиента");
                    Queri = session.createQuery("from CustomerHistory WHERE Name = :x");
                    Queri.setParameter( "x", scanner.nextLine());
                    List<CustomerHistory> cus = Queri.list();
                    for(CustomerHistory s : cus)
                    { System.out.println("id="+s.getId() + " Name="+s.getName()+" NameProduct="+s.getNameProduct());}
                    session.getTransaction().commit();
                    break;
                case 2:
                    session.beginTransaction();
                    System.out.println("Введите имя товара");
                    Queri = session.createQuery("from CustomerHistory WHERE NameProduct = :x");
                    Queri.setParameter( "x", scanner.nextLine());
                    cus = Queri.list();
                    for(CustomerHistory s : cus)
                    { System.out.println("id="+s.getId() + " Name="+s.getName()+" NameProduct="+s.getNameProduct());}
                    session.getTransaction().commit();
                    break;
                case 3:
                    session.beginTransaction();
                    System.out.println("Введите имя товара для удаления");
                    Queri = session.createQuery("DELETE Product WHERE Name = :x");
                    Queri.setParameter( "x", scanner.nextLine()) ;
                    Queri.executeUpdate();
                    session.getTransaction().commit();
                    break;
                case 4:
                    session.beginTransaction();
                    System.out.println("Введите имя клиента для удаления");
                    Queri = session.createQuery("DELETE Customer WHERE Name = :x");
                    Queri.setParameter( "x", scanner.nextLine()) ;
                    Queri.executeUpdate();
                    session.getTransaction().commit();
                    break;
                case 6:
                    session.beginTransaction();
                    Queri = session.createQuery("select g1.id,g1.Name,g1.NameProduct,g2.Price from CustomerHistory as g1 inner join Product as g2 on g1.id = g2.id");
                    List<Object[]> sus = (List<Object[]>) Queri.list();
                    for(Object[] x : sus)
                    { System.out.println("id="+x[0]+ " Покупатель=" + x[1] + " Продукт=" +x[2] + " Цена=" +x[3]);}
                    session.getTransaction().commit();
                    break;
                default:
                    System.out.println("Завершение проги");
            }

        }
        while (command != 5);
    }
}
