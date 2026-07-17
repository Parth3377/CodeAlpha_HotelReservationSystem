package HotelManagementSystem;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Hotel hotel = new Hotel();

        int choice;

        while(true)
        {
            System.out.println("\n=================================================");
            System.out.println("             HOTEL RESERVATION SYSTEM");
            System.out.println("===================================================");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Search Room By Type");
            System.out.println("3. Book Room");
            System.out.println("4. Cancel Booking");
            System.out.println("5. View Booking Details");
            System.out.println("6. Exit");
            System.out.println("===================================================");

            System.out.print("Enter Your Choice : ");
            choice = input.nextInt();

            switch(choice)
            {
                case 1:
                    hotel.viewAvailableRooms();
                    break;

                case 2:
                    input.nextLine();
                    System.out.print("Enter Room Type (Standard / Deluxe / Suite) : ");
                    String roomType = input.nextLine();

                    hotel.searchRoomByType(roomType);
                    break;

                case 3:
                    System.out.print("Enter Room Number : ");
                    int roomNumber = input.nextInt();

                    input.nextLine();

                    System.out.print("Enter Customer Name : ");
                    String customerName = input.nextLine();

                    System.out.print("Enter Number of Days : ");
                    int days = input.nextInt();

                    hotel.bookRoom(roomNumber , customerName , days);
                    break;

                case 4:
                    System.out.print("Enter Room Number to Cancel Booking : ");
                    int cancelRoom = input.nextInt();

                    hotel.cancelBooking(cancelRoom);
                    break;

                case 5:
                    hotel.viewBookingsDetails();
                    break;

                case 6:
                    System.out.println("\n=================================================");
                    System.out.println(" Thank You For Using");
                    System.out.println("Hotel Reservation System");
                    System.out.println("===================================================");

                    input.close();
                    return;

                default:
                    System.out.println("\nInvalid Choice! Please Try Again.");
            }
        }
    }
    
}
