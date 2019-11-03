import org.junit.Assert;
import org.junit.Test;


public class OrderBookTest {

    @Test
    public void testItemsInsertionBidsAndAsks() {
        Order o1 = new Order(true, 1, 100, 100, "Gas", OrderType.LIMIT);
        Order o2 = new Order(false, 2, 70, 70, "Gas", OrderType.LIMIT);

        Order o3 = new Order(true, 3, 400, 80, "donald1", OrderType.LIMIT);
        Order o4 = new Order(false, 4, 50, 100, "sagi1", OrderType.LIMIT);

        OrderBook ob = new OrderBook();
        ob.addOrder(o1);
        ob.addOrder(o2);

        Assert.assertEquals(true, ob.bids.size() == 1);
        Assert.assertEquals(true, ob.asks.size() == 1);

        ob.addOrder(o3);
        ob.addOrder(o4);

        Assert.assertEquals(true, ob.bids.size() == 2);
        Assert.assertEquals(true, ob.bids.size() == 2);


    }

    @Test
    public void testRemoveMethodWorks() {

        Order o1 = new Order(true, 1, 100, 100, "Gas", OrderType.LIMIT);
        Order o2 = new Order(false, 2, 50, 100, "Gas", OrderType.LIMIT);

        OrderBook ob = new OrderBook();
        ob.addOrder(o1);
        ob.addOrder(o2);

        try {
            ob.deleteOrder(1, o1);
            ob.deleteOrder(2, o2);
        } catch (OrderBookExceptions orderBookExceptions) {
            orderBookExceptions.printStackTrace();
        }

        Assert.assertTrue(ob.bids.isEmpty());
        Assert.assertTrue(ob.asks.isEmpty());
    }

    @Test
    public void testModifyOrderChangeQuantity() {
        Order o1 = new Order(true, 1, 100, 100, "Gas", OrderType.LIMIT);
        Order o2 = new Order(false, 2, 100, 70, "Gas", OrderType.LIMIT);

        OrderBook ob = new OrderBook();
        ob.addOrder(o1);
        ob.addOrder(o2);

        Order orderToChangeBid = new Order(true, 1, 100, 200, "Gas", OrderType.LIMIT);
        Order orderToChangeAsk = new Order(false, 2, 100, 200, "Gas", OrderType.LIMIT);

        try {
            ob.modifyOrder(1, orderToChangeBid);
            ob.modifyOrder(2, orderToChangeAsk);
        } catch (OrderBookExceptions orderBookExceptions) {
            orderBookExceptions.printStackTrace();
        }

        Assert.assertEquals(200, o1.getQuantity());
        Assert.assertEquals(200, o2.getQuantity());
    }

    @Test
    public void testModifyOrderChangeBidThrowsException() {
        Order bidOrder = new Order(true, 1, 100, 100, "Gas", OrderType.LIMIT);

        OrderBook ob = new OrderBook();
        ob.addOrder(bidOrder);

        Order offerOrder = new Order(false, 1, 100, 100, "Gas", OrderType.LIMIT);

        try {
            ob.modifyOrder(1, offerOrder);
        } catch (OrderBookExceptions orderBookExceptions) {
            orderBookExceptions.printStackTrace();
            Assert.assertEquals("Can not update de to proposition changes", orderBookExceptions.getMessage());
        }

    }

    @Test
    public void testModifyOrderChangeVenueThrowsException() {
        Order bidOrder = new Order(true, 1, 100, 100, "Gas", OrderType.LIMIT);

        OrderBook ob = new OrderBook();
        ob.addOrder(bidOrder);

        Order orderDiffVenue = new Order(true, 1, 100, 100, "Oil", OrderType.LIMIT);

        try {
            ob.modifyOrder(1, orderDiffVenue);
        } catch (OrderBookExceptions orderBookExceptions) {
            orderBookExceptions.printStackTrace();
            Assert.assertEquals("Can not update due to venue changes", orderBookExceptions.getMessage());

        }
    }

    @Test
    public void testModifyOrderChangePriceThrowsException() {
        Order bidOrder = new Order(true, 1, 100, 100, "Gas", OrderType.LIMIT);

        OrderBook ob = new OrderBook();
        ob.addOrder(bidOrder);

        Order orderDiffPrice = new Order(true, 1, 60, 100, "Gas", OrderType.LIMIT);

        try {
            ob.modifyOrder(1, orderDiffPrice);
        } catch (OrderBookExceptions orderBookExceptions) {
            orderBookExceptions.printStackTrace();
            Assert.assertEquals("Can not update due to price changes", orderBookExceptions.getMessage());

        }

    }

    @Test
    public void testModifyOrderChangeTypeThrowsException() {
        Order bidOrder = new Order(true, 1, 100, 100, "Gas", OrderType.LIMIT);

        OrderBook ob = new OrderBook();
        ob.addOrder(bidOrder);

        Order orderDiffType = new Order(true, 1, 100, 100, "Gas", OrderType.MARKET);
        try {
            ob.modifyOrder(1, orderDiffType);
        } catch (OrderBookExceptions orderBookExceptions) {
            orderBookExceptions.printStackTrace();
            Assert.assertEquals(" Can not update Market Order! ", orderBookExceptions.getMessage());
        }
    }

    @Test
    public void testBestBid() {
        Order o1 = new Order(true, 1, 100, 100, "donald1", OrderType.LIMIT);
        Order o2 = new Order(true, 2, 30, 90, "donald1", OrderType.LIMIT);
        Order o3 = new Order(true, 3, 400, 80, "donald1", OrderType.LIMIT);

        OrderBook ob = new OrderBook();
        ob.addOrder(o1);
        ob.addOrder(o2);
        ob.addOrder(o3);

        Assert.assertEquals(400, ob.bids.peek().getPrice(), 0.1);
    }

    @Test
    public void testBestOffer() {

        Order o1 = new Order(false, 1, 150, 90, "sagi1", OrderType.LIMIT);
        Order o2 = new Order(false, 2, 100, 70, "sagi1", OrderType.LIMIT);
        Order o3 = new Order(false, 3, 10, 70, "sagi1", OrderType.LIMIT);

        OrderBook ob = new OrderBook();
        ob.addOrder(o1);
        ob.addOrder(o2);
        ob.addOrder(o3);

        Assert.assertEquals(10, ob.asks.peek().getPrice(), 0.1);
    }

    @Test
    public void testProcessOrderAskOrderDeleteBid() {
        Order orderBidPrice100 = new Order(true, 1, 100, 100, "Gas", OrderType.LIMIT);
        Order orderBidPrice70 = new Order(true, 2, 70, 70, "Gas", OrderType.LIMIT);
        Order orderOfferPrice100 = new Order(false, 3, 100, 100, "Gas", OrderType.LIMIT);

        OrderBook ob = new OrderBook();
        ob.addOrder(orderBidPrice100);
        ob.addOrder(orderBidPrice70);
        ob.processOrder(orderOfferPrice100);

        Assert.assertEquals(1, ob.bids.size());
        //see if the orderBidPrice70 remaines
        Assert.assertEquals(orderBidPrice70, ob.bids.peek());
    }

    @Test
    public void testProcessOrderBidOrderDeleteAsk() {
        Order orderOfferPrice100 = new Order(false, 1, 100, 100, "donald1", OrderType.LIMIT);
        Order orderOfferPrice50 = new Order(false, 2, 50, 100, "donald1", OrderType.LIMIT);
        Order orderBidPrice50 = new Order(true, 3, 50, 100, "donald1", OrderType.LIMIT);

        OrderBook ob = new OrderBook();
        ob.addOrder(orderOfferPrice100);
        ob.addOrder(orderOfferPrice50);

        ob.processOrder(orderBidPrice50);

        Assert.assertEquals(1, ob.asks.size());
        Assert.assertEquals(orderOfferPrice100, ob.asks.peek());
    }

}