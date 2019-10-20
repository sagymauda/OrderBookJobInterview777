import org.junit.Test;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class OrderBookTest {
    @Test
    public void testOrderInsertionToOrderBook() {
        Order o1 = new Order(true, 1, 10, 100, "donald1", OrderType.LIMIT);
        Order o2 = new Order(true, 2, 11, 100, "donald1", OrderType.LIMIT);
        Order o3 = new Order(true, 3, 9, 100, "donald1", OrderType.LIMIT);
        Order o4 = new Order(false, 4, 12, 100, "sagi1", OrderType.LIMIT);
        Order o5 = new Order(false, 5, 13, 100, "sagi1", OrderType.LIMIT);
        Order o6 = new Order(false, 6, 15, 100, "sagi1", OrderType.LIMIT);
        Order o7 = new Order(false, 7, 17, 100, "sagi1", OrderType.LIMIT);

        // i still dony understand it
        Order o8 = new Order(false, 7, 17, 100, "sagi1", OrderType.MARKET);

        OrderBook ob = new OrderBook();
        ob.addOrder(o1);
        ob.addOrder(o2);
        ob.addOrder(o3);
        ob.addOrder(o4);
        ob.addOrder(o5);
        ob.addOrder(o6);
        ob.addOrder(o7);
        ob.addOrder(o8);

        PriorityQueue<Order> listTocheck =ob.bids;








    }
}