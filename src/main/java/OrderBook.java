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

     //problem in modeify method
    public void modifyOrder(long id, Order order) {
        if (order.isBid() == true) {
            Iterator<Order> it = bids.iterator();
            while (it.hasNext()) {;
                Order currentOrder =it.next();
                if (currentOrder.getId() == id) {
                    currentOrder.setQuantity(order.getQuantity());
                    break;
                }
            }
        } else {
            Iterator<Order> it = asks.iterator();
            while (it.hasNext()) {
                Order currentOrder =it.next();
                if (currentOrder.getId() == id) {
                    currentOrder.setQuantity(order.getQuantity());
                    break;
                }
            }
        }

        //remove method works good
    } public void deleteOrder ( long id, Order order){
        if (order.isBid() == true) {
            Iterator<Order> it = bids.iterator();
            while(it.hasNext()) {
                Order orderCurrent = it.next();
                if (orderCurrent.getId() == id) {
                    bids.remove(orderCurrent);
                    break;
                }
            }

        } else {
            Iterator<Order> it = asks.iterator();
            while(it.hasNext()) {
                Order orderCurrent = it.next();
                if (orderCurrent.getId() == id) {
                    asks.remove(orderCurrent);
                    break;
                }
            }
        }
    }


    public Order getBestBid () {
      return bids.peek();
    }



    public Order getBestAsk () {
      return asks.peek();
    }



    public void processOrder (Order order){
        if (order.getType() == OrderType.LIMIT) {
            if (order.isBid()) {
                if (order.getPrice() <= getBestAsk().getPrice()) {
                    deleteOrder(getBestAsk().getId(), getBestAsk());
                } else {
                    addOrder(order);
                }
                //in case were on asks isbid==false//
            } else {
                if (order.getPrice() >= getBestBid().getPrice()) {
                    deleteOrder(getBestBid().getId(), getBestBid());
                } else {
                    addOrder(order);
                }

            }
            //in case that the orderType is Market//
        } else {
            if (order.isBid() == true) {
                Order bestOfferToDelete = getBestAsk();
                deleteOrder(bestOfferToDelete.getId(), bestOfferToDelete);

                //in case the bid is false(meaning its an ask)//
            } else {
                Order bestBidToDelete = getBestBid();
                deleteOrder(bestBidToDelete.getId(), bestBidToDelete);

            }
        }
    }



        public void printBidsList() {
        Iterator<Order> it = bids.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }

    }

    public void printAsksList() {
        Iterator<Order> it = asks.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
    public void realOrder(){
        while(!bids.isEmpty()){
            System.out.println(bids.poll());
        }
    }
}

