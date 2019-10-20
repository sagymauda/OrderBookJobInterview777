public class Main {
    public static void main(String[] args) {


        OrderBook orderBook = new OrderBook();


        Order o1 = new Order(true, 1, 100, 100, "donald1", OrderType.LIMIT);
        Order o2 = new Order(true, 2, 30, 90, "donald1", OrderType.LIMIT);
        Order o3 = new Order(true, 3, 400, 80, "donald1", OrderType.LIMIT);
        Order o4 = new Order(true, 4, 50, 100, "sagi1", OrderType.LIMIT);
        Order o5 = new Order(true, 5, 200, 80, "sagi1", OrderType.LIMIT);
        Order o6 = new Order(false, 6, 150, 90, "sagi1", OrderType.LIMIT);
        Order o7 = new Order(false, 7, 100, 70, "sagi1", OrderType.LIMIT);
        Order o8 = new Order(false, 8, 10, 70, "sagi1", OrderType.LIMIT);
        Order o9 = new Order(false, 9, 240, 70, "sagi1", OrderType.LIMIT);

        Order orderToChange = new Order(true, 3, 400, 800, "donald1", OrderType.LIMIT);

        orderBook.addOrder(o1);
        orderBook.addOrder(o2);
        orderBook.addOrder(o3);
        orderBook.addOrder(o4);
        orderBook.addOrder(o5);
        orderBook.addOrder(o6);
        orderBook.addOrder(o7);
        orderBook.addOrder(o8);
        orderBook.addOrder(o9);
        System.out.println("bids");
        orderBook.printBidsList();
//
//        System.out.println("asks");
//        orderBook.printAsksList();

//
//        //checking the delete method
//        System.out.println("after remove items bids list nned to be 4");
//        orderBook.deleteOrder(1,o1);
//        orderBook.printBidsList();

//        System.out.println("best bid and ask");
//        System.out.println( orderBook.getBestBid());
//        System.out.println(orderBook.getBestAsk());
//
  //     System.out.println("checking new ask to see if it delete the bid with process method");
  //      Order orderToCompareWithBidSamePrice = new Order(false, 10, 100, 100, "donald1", OrderType.LIMIT);
//
//        orderBook.processOrder(orderToCompareWithBidSamePrice);
//
//        System.out.println("now the bid needs to be without id 4");
//        orderBook.printBidsList();
//
//
//
 //          orderBook.modifyOrder(3,orderToChange);


        System.out.println("bids");
        orderBook.printBidsList();
//
//    //    System.out.println("bids");
//
////
    }

}
