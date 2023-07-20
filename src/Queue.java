
public class Queue
{
	Car[] contents;
	int rear;
	
	public Queue()
	{
		contents = new Car[15];
		rear = 0;				// the index of the location where the next Car will be inserted.
	}
	
	public boolean isEmpty()
	{
		return rear == 0;
	}
	
	public void enqueue(Car car)
	{
		if(rear == 15)
			return;
		contents[rear++] = car;
	}
	
	public Car dequeue()
	{
		if(rear == 0)
			return null;
		
		Car c = contents[0];
		
		for(int i = 0; i < rear - 1; i++)
			contents[i] = contents[i + 1];
		--rear;
 		
		return c;
	}
	
	public String toString()
	{
		String result = "";
		for(int i = 0; i < rear; i++)
			result = result + (contents[i]) + "\n";
		return result;
	}
}