import java.util.Iterator;
import java.util.PriorityQueue;

public class OrderBook {

    PriorityQueue<Order> bids = new PriorityQueue<Order>();
    PriorityQueue<Order> asks = new PriorityQueue<Order>();

    public OrderBook() {

    }

    public void addOrder(Order order) {
        if (order.isBid() == true) {
            bids.add(order);
        } else {
            asks.add(order);
        }
    }


    public void modifyOrder(long id, Order order) throws OrderBookExceptions {
        if (order.getType() != OrderType.MARKET) {
            if (order.isBid() == true) {
                Iterator<Order> it = bids.iterator();
                while (it.hasNext()) {
                    ;
                    Order currentOrder = it.next();
                    if (currentOrder.getId() == id) {
                        if (currentOrder.isBid() != order.isBid()) {
                            throw new OrderBookExceptions(" Can not update de to proposition changes");
                        } else if (currentOrder.getPrice() != order.getPrice()) {
                            throw new OrderBookExceptions("Can not update due to price changes");
                        } else if (currentOrder.getVenue()!=order.getVenue()) {
                            throw new OrderBookExceptions("Can not update due to venue changes");
                        }
                        currentOrder.setQuantity(order.getQuantity());
                        break;
                    }
                }
            } else {
                Iterator<Order> it = asks.iterator();
                while (it.hasNext()) {
                    Order currentOrder = it.next();
                    if (currentOrder.getId() == id) {
                        if (currentOrder.isBid() != order.isBid()) {
                            throw new OrderBookExceptions(" Can not update de to proposition changes ");
                        } else if (currentOrder.getPrice() != order.getPrice()) {
                            throw new OrderBookExceptions("Can not update due to price changes");
                        } else if (currentOrder.getVenue()!=order.getVenue()){
                            throw new OrderBookExceptions("Can not update due to venue changes");
                        }
                        currentOrder.setQuantity(order.getQuantity());
                        break;
                    }
                }
            }

        } else {
            throw new OrderBookExceptions(" Can not update Market Order! ");

        }
    }

    public void deleteOrder(long id, Order order) throws OrderBookExceptions {
        if (order.getType() != OrderType.MARKET) {
            if (order.isBid() == true) {
                Iterator<Order> it = bids.iterator();
                while (it.hasNext()) {
                    Order orderCurrent = it.next();
                    if (orderCurrent.getId() == id) {
                        bids.remove(orderCurrent);
                        break;
                    }
                }

            } else {
                Iterator<Order> it = asks.iterator();
                while (it.hasNext()) {
                    Order orderCurrent = it.next();
                    if (orderCurrent.getId() == id) {
                        asks.remove(orderCurrent);
                        break;
                    }
                }
            }

        } else {
            throw new OrderBookExceptions("Can not delete Market Order");
        }
    }

    public Order getBestBid() {
        return bids.peek();
    }

    public Order getBestAsk() {
        return asks.peek();
    }

    public void processOrder(Order order) {
        if (order.getType() == OrderType.LIMIT) {
            if (order.isBid()) {
                if (order.getPrice() <= getBestAsk().getPrice()) {
                    try {
                        deleteOrder(getBestAsk().getId(), getBestAsk());
                    } catch (OrderBookExceptions ex) {
                        ex.printStackTrace();
                    }
                } else {
                    addOrder(order);
                }
                //in case where  asks isbid = false//
            } else {
                if (order.getPrice() >= getBestBid().getPrice()) {
                    try {
                        deleteOrder(getBestBid().getId(), getBestBid());
                    } catch (OrderBookExceptions ex) {
                        ex.printStackTrace();
                    }
                    addOrder(order);
                }
            }
            //in case that the orderType is Market//
        } else {
            if (order.isBid() == true) {
                Order bestOfferToDelete = getBestAsk();
                try {
                    deleteOrder(bestOfferToDelete.getId(), bestOfferToDelete);
                } catch (OrderBookExceptions ex) {
                    ex.printStackTrace();
                }
                //in case the bid is false(meaning its an ask)//
            } else {
                Order bestBidToDelete = getBestBid();
                try {
                    deleteOrder(bestBidToDelete.getId(), bestBidToDelete);
                } catch (OrderBookExceptions ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

