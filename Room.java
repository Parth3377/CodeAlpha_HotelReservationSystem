package HotelManagementSystem;

public class Room
{
    private int roomNumber;
    private String roomType;
    private double price;
    private boolean available;
    
    public Room(int roomNumber , String roomType , double price , boolean available)
    {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.available = available;
    }

    public int getRoomNumber()
    {
        return roomNumber;
    }

    public String getRoomType()
    {
        return roomType;
    }

    public double getPrice()
    {
        return price;
    }

    public boolean isAvailable()
    {
        return available;
    }
    
    // setter
    public void setAvailable(boolean available)
    {
        this.available = available;
    }
}
