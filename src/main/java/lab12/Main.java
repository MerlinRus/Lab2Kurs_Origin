package lab12;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.persistence.OptimisticLockException;
import java.util.concurrent.CountDownLatch;

public class Main {
    private static final int NUMBER_OF_THREAD = 8;

    public static void main(String[] args) {
        SessionFactory factory = (new Configuration()).configure("hibernate.cfg.xml").addAnnotatedClass(Item.class).buildSessionFactory();
        Session session = factory.getCurrentSession();
        Sessions();
    }

    public static void Sessions() {
        CountDownLatch countDownLatch = new CountDownLatch(8);
        Thread[] threads = new Thread[8];
        SessionFactory factory = (new Configuration()).configure("hibernate.cfg.xml").addAnnotatedClass(Item.class).buildSessionFactory();

        try {
            int i = 0;

            while(true) {
                if (i >= 8) {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException var6) {
                        var6.printStackTrace();
                    }

                    System.out.println("END");
                    break;
                }

                int finalI = i;
                threads[i] = new Thread(() -> {
                    System.out.println("Thread #" + finalI + " started");

                    for(int j = 0; j < 20000; ++j) {
                        boolean update = false;
                        int randomRow = (int)(Math.random() * 40.0 + 1.0);

                        while(!update) {
                            Session session = factory.getCurrentSession();

                            try {
                                session.beginTransaction();
                                Item item = (Item)session.get(Item.class, randomRow);
                                int itm = item.getValues();
                                ++itm;
                                item.setValues(itm);
                                session.save(item);
                                session.getTransaction().commit();
                                update = true;
                            } catch (OptimisticLockException | HibernateException var9) {
                                session.getTransaction().rollback();
                            }

                            if (session != null) {
                                session.close();
                            }
                        }
                    }
                    countDownLatch.countDown();
                });
                threads[i].start();
                ++i;
            }
        } catch (Throwable var7) {
            if (factory != null) {
                try {
                    factory.close();
                } catch (Throwable var5) {
                    var7.addSuppressed(var5);
                }
            }

            throw var7;
        }

        if (factory != null) {
            factory.close();
        }

    }
}
