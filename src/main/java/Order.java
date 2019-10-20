public class Order implements Comparable<Order> {

        private long timeStemp;

        private  boolean isBid;

        private long id;

        private  float price;

        private  long quantity;

        private String venue;

        private  OrderType type;


        public Order( boolean isBid, long id, float price,long quantity, String venue, OrderType type) {
            this.isBid = isBid;
            this.id = id;
            this.price = price;
            this.quantity = quantity;
            this.venue = venue;
            this.type = type;
        }


    public int compareTo(Order o) {
        if(this.isBid){
            if(this.price<o.price){
                return 1;
            }else if(this.price>o.price){
                return -1;

            }
            return 0;
        }else{
            if(this.price<o.price){
                return -1;
            }else if(this.price>o.price){
                return 1;

            }
            return 0;
        }
    }


    public long getTimeStemp() {
            return timeStemp;
        }

        public void setTimeStemp(long timeStemp) {
            this.timeStemp = timeStemp;
        }

        public boolean isBid() {
            return isBid;
        }

        public void setBid(boolean bid) {
            isBid = bid;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public long getQuantity() {
            return quantity;
        }

        public void setQuantity(long quantity) {
            this.quantity = quantity;
        }

        public String getVenue() {
            return venue;
        }

        public void setVenue(String venue) {
            this.venue = venue;
        }

        public OrderType getType() {
            return type;
        }

        public void setType(OrderType type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "timeStemp=" + timeStemp +
                    ", isBid=" + isBid +
                    ", id=" + id +
                    ", price=" + price +
                    ", quantity=" + quantity +
                    ", venue='" + venue + '\'' +
                    ", type=" + type +
                    '}';
        }


}




