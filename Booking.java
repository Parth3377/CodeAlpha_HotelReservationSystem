package HotelManagementSystem;

public class Booking
{
    private String customerName;
    private int roomNumber;
    private String roomType;
    private int numberOfDays;
    private double totalAmount;
    private String paymentStatus;

    public Booking(String customerName,
                    int roomNumber,
                    String roomType,
                    int numberOfDays,
                    double totalAmount,
                    String paymentStatus)
    {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.numberOfDays = numberOfDays;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
    }

    public String getCustomerName()
    {
        return customerName;
    }
    public int getRoomNumber()
    {
        return roomNumber;
    }
    public String getRoomType()
    {
        return roomType;
    }
    public int getNumberOfDays()
    {
        return numberOfDays;
    }
    public double getTotalAmount()
    {
        return totalAmount;
    }
    public String getPaymentStatus()
    {
        return paymentStatus;
    }    
}
