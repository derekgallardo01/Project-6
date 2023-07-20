 //***********************************************************************************************************************************************
 //
 //  Name: Derek Gallardo
 //  Date: July 26th, 2022
 //  Version: 1.0.0
 //  Programming Language: Java
 //  Java Version: 17
 //  Description: This program creates two stacks and a queue to keep track of cars using a parking lot.
 //  Each car should be assigned a random decal and only those who have a specific special decal will be permitted to park.
 //  Randomly assign a decal and check the decal before attempting to park the car.
 //  *if the user does not have the correct parking decal t park in the lot, the driver should be tld nicely that they do not
 //   have the correct permit t park in this lot.
 //  The parking lot is last-in, first-out stack.
 //  The program must first check to see if the car has the correct decal.
 //  If it does have the correct permit, the car may be added to the lost as long as there is enough room.
 //  To park a car, the car's license tag is added to the parking lot stack.
 //  The parking can hold 15 car at time.
 //  If the parking lot is full, a waiting care can be added to a waiting queue.
 //  When a car leaves the lot, the next car in the queue will be able to park in the parking lot before allowing a new car to be entered.
 //  ** The parking lot should populate with 10 car when the program launches **
 //  One stack representing the parking lot, One stack representing the street. 
 //  A queue will be used to store the cars waiting for a place to park if the lot is full.
 //  *Use integers a license plate numbers.
 //  *License plates should have 5 digits
//  Positive numbers add a car, negative numbers remove a car, zero stps the simulation.
//  Park - positive number -> Retrieve - Negative Number in front of license number
//  The program should tell me if I have the correct decal and if there is room to park my car.
//  If the stack representing the parking lot is full, my car should be added to the waiting queue.
//  If I remove my car, the next car in the queue should be parked before a new car can be entered unless the queue is empty.
//  The status of the parking lot and the waiting queue should be printed to the console after each operation is complete.
//  The status includes the cars that are parked in the lot as well as the cars that are currently waiting in the queue to park. 
/// 
//  The expected output should contain the list of cars currently parked in the parking lost and the list of cars currently
//  waiting to park after each car is entered. The prgraom should also tell the user if they have a correct decal. If they do,
//  The program should tell the driver if their car will be parked in the lot or if it must wait in the waiting queue for a space
//  to become available. If they do not, the program should politely tell the user they do not have the correct decal to park in this lot.
//  
//  Utilize a stack for the parking lot
//  Utilize a stack for the street
//  Utilize a queue to store the cars waiting to park.
//  Generate a random decal type.
//  Correct decal check
//  Limit the number of cars in the lot to max of 15
//  Populate the parking lot with 10 cars to start
//  Output that includes the cars parked in the lot and any
//  cars waiting in the queue to park after each operation.
//  Easy to understand user interface that allows the user to park a car or retrieve a parked car.
 //
//***********************************************************************************************************************************************

import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args)
	{
		Stack parking_lot = new Stack();
		Stack street = new Stack();
		Queue waiting_list = new Queue();
		
		// Declare some variables
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		
		// First, populate ten cars
		
		while(parking_lot.size() < 10)
		{
			int license = 5000 + rand.nextInt(5000) % 5000;
			int decal = 5000 + rand.nextInt(5000) % 5000;
			if(decal % 10 < 5)
			{
				Car c = new Car(license, decal);
				parking_lot.push(c);
			}
		}
		
		System.out.println("Parking lot : ");
		System.out.println(parking_lot);
		
		// Start the simulation
		
		String input;
		int choice = 1;
		
		while(choice != 0)
		{
			System.out.println("Enter choice (0 to stop):");
			input = scan.nextLine();
			choice = Integer.parseInt(input);
			
			if(choice == 0)
				System.out.println("Thank you!");
			else if(choice > 0)
			{
				// Assign a random decal
				int decal = 5000 + rand.nextInt(10000) % 5000;
				System.out.println("Decal assigned = " + decal);
				if(decal % 10 < 5)
					System.out.println("You don't have the permission to park here.");
				else
				{
					Car c = new Car(choice, decal);
					if(parking_lot.isFull())
						waiting_list.enqueue(c);
					else
						parking_lot.push(c);
				}
			}
			else
			{
				// Car trying to exit the system.
				int license = -choice;
				while(parking_lot.top().get_license() != license)
				{
					Car c = parking_lot.pop();
					street.push(c);
					// System.out.println(c.get_license() + " pushed to the street");
				}
				
				if(parking_lot.isEmpty())
					System.out.println("The car does not exist in the parking lot.");
				else
					parking_lot.pop();
				
				while(!street.isEmpty())
				{
					Car c = street.pop();
					parking_lot.push(c);
				}
				
				// See if a car can be moved from the queue to the parking lot
				if(!waiting_list.isEmpty() && parking_lot.size() < 15)
				{
					Car c = waiting_list.dequeue();
					parking_lot.push(c);
					System.out.println("Car " + c.get_license() + " dequeued from the Q");
				}
			}
			
			System.out.println("Parking lot : ");
			System.out.println(parking_lot);
		}
		
		System.out.println("Thank you!");

	}

}
