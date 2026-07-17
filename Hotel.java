package HotelManagementSystem;
import java.io.*;

public class Hotel
{
    private Room[] rooms;
    private Booking[] bookings;
    private int bookingCount;

    public Hotel()
    {
        rooms = new Room[5];
        bookings = new Booking[100];
        bookingCount = 0;

        rooms[0] = new Room(101 , "Standard" , 1500 , true);
        rooms[1] = new Room(102 , "Standard" , 1500 , true);

        rooms[2] = new Room(201 , "Deluxe" , 2500 , true);
        rooms[3] = new Room(202 , "Deluxe" , 2500 , true);

        rooms[4] = new Room(301 , "Suite" , 4500 , true);
    }


    public void viewAvailableRooms()
    {
        System.out.println("\n================================================================");
        System.out.println("                         Available Rooms");
        System.out.println("==================================================================");

        System.out.printf("%-10s %-15s %-15s %-15s%n" , "Room No" , "Type" , "Price (Rs.)" , "Status");

        System.out.println("------------------------------------------------------------------");

        boolean roomFound = false;

        for(int i=0; i<rooms.length; i++)
        {
            if(rooms[i].isAvailable())
            {
                roomFound = true;

                System.out.printf("%-10d %-15s %-15.2f %-15s%n" ,
                    rooms[i].getRoomNumber(),
                    rooms[i].getRoomType(),
                    rooms[i].getPrice(),
                    "Available"
                    );
            }
        }

        if(!roomFound)
        {
            System.out.println("No rooms are available.");
        }

        System.out.println("==================================================================");
    }


    public void searchRoomByType(String roomType)
    {
        System.out.println("\n================================================================");
        System.out.println("              " + roomType.toUpperCase() + " ROOMS");
        System.out.println("==================================================================");

        System.out.printf("%-10s %-15s %-15s %-15s%n" , "Room No" , "Type" , "Price (Rs.)" , "Status");

        System.out.println("------------------------------------------------------------------");

        boolean roomFound = false;

        for(int i=0; i<rooms.length; i++)
        {
            if(rooms[i].getRoomType().equalsIgnoreCase(roomType) && rooms[i].isAvailable())
            {
                roomFound = true;

                System.out.printf("%-10d %-15s %-15.2f %-15s%n" , 
                    rooms[i].getRoomNumber(),
                    rooms[i].getRoomType(),
                    rooms[i].getPrice(),
                    "Available"    
                );
            }
        }

        if(!roomFound)
        {
            System.out.println("No Available " + roomType + " rooms found.");
        }

        System.out.println("==================================================================");
    }


    public void bookRoom(int roomNumber , String customerName , int days)
    {
        Room selectedRoom = null;

        for(int i=0; i<rooms.length; i++)
        {
            if(rooms[i].getRoomNumber() == roomNumber)
            {
                selectedRoom = rooms[i];
                break;
            }
        }

        if(selectedRoom == null)
        {
            System.out.println("\nRoom not found.");
            return;
        }

        if(!selectedRoom.isAvailable())
        {
            System.out.println("\nRoom is already booked!");
            return;
        }

        if(days <= 0)
        {
            System.out.println("\nNumber of days must be greater than zero.");
            return;
        }

        double totalAmount = selectedRoom.getPrice() * days;

        System.out.println("\n=================== PAYMENT =====================");
        System.out.printf("Total Amount : Rs. %.2f%n" , totalAmount);
        System.out.println("Processing Payment...");
        System.out.println("Payment Successful!");
        System.out.println("===================================================");

        bookings[bookingCount] = new Booking(
                customerName,
                selectedRoom.getRoomNumber(),
                selectedRoom.getRoomType(),
                days,
                totalAmount,
                "Paid"
            );

        bookingCount++;
        saveBookingsToFile();

        selectedRoom.setAvailable(false);

        System.out.println("\n=================================================");
        System.out.println("               BOOKING SUCCESSFUL");
        System.out.println("===================================================");
        System.out.println("Customer Name : " + customerName);
        System.out.println("Room Number   : " + selectedRoom.getRoomNumber());
        System.out.println("Room Type     : " + selectedRoom.getRoomType());
        System.out.println("Days          : " + days);
        System.out.printf("Total Amount : Rs. %.2f%n" , totalAmount);
        System.out.println("Payment Status : Paid");
        System.out.println("===================================================");
    }


    public void cancelBooking(int roomNumber)
    {
        int bookingIndex = -1;

        for(int i=0; i<bookingCount; i++)
        {
            if(bookings[i].getRoomNumber() == roomNumber)
            {
                bookingIndex = i;
                break;
            }
        }

        if(bookingIndex == -1)
        {
            System.out.println("\nBooking not Found!");
            return;
        }

        // make room available again
        for(int i=0; i<rooms.length; i++)
        {
            if(rooms[i].getRoomNumber() == roomNumber)
            {
                rooms[i].setAvailable(true);
                break;
            }
        }

        for(int i = bookingIndex; i < bookingCount-1; i++)
        {
            bookings[i] = bookings[i + 1];
        }

        bookings[bookingCount - 1] = null;
        bookingCount--;
        saveBookingsToFile();

        System.out.println("\n==================================================");
        System.out.println("                BOOKING CANCELLED");
        System.out.println("====================================================");
        System.out.println("Room Number : " + roomNumber);
        System.out.println("Room is now available for booking.");
        System.out.println("====================================================");
    }

    public void viewBookingsDetails()
    {
        System.out.println("\n===================================================");
        System.out.println("                   BOOKING DETAILS");
        System.out.println("=====================================================");

        if(bookingCount == 0)
        {
            System.out.println("No bookings available.");
            System.out.println("=====================================================");
            return;
        }

        System.out.printf("%-18s %-10s %-12s %-8s %-15s %-10s%n" ,
        "Customer Name",
                "Room No",
                "Type",
                "Days",
                "Amount(Rs.)",
                "Payment"
        );

        System.out.println("----------------------------------------------------------");

        for(int i=0; i<bookingCount; i++)
        {
            System.out.printf("%-18s %-10d %-12s %-8d %-15s.2f %-10s%n" , 
                bookings[i].getCustomerName(),
                bookings[i].getRoomNumber(),
                bookings[i].getRoomType(),
                bookings[i].getNumberOfDays(),
                bookings[i].getTotalAmount(),
                bookings[i].getPaymentStatus()
            );
        }

        System.out.println("=====================================================");
    }


    public void saveBookingsToFile()
    {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("bookings.txt"));

            for(int i=0; i<bookingCount; i++)
            {
                writer.write(
                    bookings[i].getCustomerName() + "," +
                    bookings[i].getRoomNumber() + "," +
                    bookings[i].getRoomType() + "," +
                    bookings[i].getNumberOfDays() + "," +
                    bookings[i].getTotalAmount() + "," +
                    bookings[i].getPaymentStatus()
                );
                writer.newLine();
            }
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println("Error in Saving Bookings!");
        }
    }    
}
