class Ticket {
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        Order order = new Order(2, Type.GENERAL_ADMISSION, Day.MONDAY, false, false, false);
        orders.add(order);
        order = new Order(2, Type.STUDENT, Day.THURSDAY, true, true, true);
        orders.add(order);
        double total = calculateTotalPrice(orders);
        System.out.println(total);
    }

    private enum Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY 
    }
 
    // Type of ticket and corresponding price
    private enum Type {        
        GENERAL_ADMISSION(11),
        STUDENT(8),
        SENIOR(6.5),
        CHILD(6);
        
        private double price;
        Type(double price) {
            this.price = price;
        }
        
        private double getPrice() {
            return price;
        }            
    }
    
    // Feature of ticket and corresponding extra charge or discount
    private enum Extra {
        OVERLENGTH(2),
        THREE_D(2),
        BALCONY(3),
        SPECIAL(-2),
        WEEKEND(2);
        
        private double extra;
        Extra(double extra) {
            this.extra = extra;
        }
        
        private double getExtra() {
            return extra;
        }   
    }    

    private static class Order {
        private int num;
        private Type type;
        private Day day;
        private boolean overlength;
        private boolean threeD;
        private boolean balcony;    
        
        Order(int num, Type type, Day day, boolean overlength, boolean threeD, boolean balcony) {
            this.num = num;
            this.type = type;
            this.day = day;
            this.overlength = overlength;
            this.threeD = threeD;
            this.balcony = balcony;
        }
    }
    
    private static double calculateTotalPrice(List<Order> orders) {
        double total = 0;
        for(Order order : orders) {
            double one = 0; // Price of one ticket
            one += order.type.getPrice();
            if(order.day == Day.THURSDAY) {
                one += Extra.SPECIAL.getExtra();
            } else if (order.day == Day.SATURDAY || order.day == Day.SUNDAY) {
                one += Extra.WEEKEND.getExtra();
            }
            if(order.overlength) {
                one += Extra.OVERLENGTH.getExtra();
            }
            if(order.threeD) {
                one += Extra.THREE_D.getExtra();
            }
            if(order.balcony) {
                one += Extra.BALCONY.getExtra();
            }
            total += one * order.num;
        }
        return total;
    }   
}
