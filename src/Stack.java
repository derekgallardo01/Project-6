
public class Stack
{
	Car[] contents;
	int top;
	
	public Stack()
	{
		contents = new Car[15];
		top = 0;				// the index of the location where the next Car will be inserted.
	}
	
	public boolean isEmpty()
	{
		return top == 0;
	}
	
	public boolean isFull()
	{
		return top == 15;
	}
	
	public Car top()
	{
		if(isEmpty())
			return null;
		return contents[top - 1];
	}
	
	public void push(Car car)
	{
		contents[top++] = car;
	}
	
	public Car pop()
	{
		if(top == 0)
			return null;
		
		Car c = contents[top];
		--top;
		return c;
	}
	
	public String toString()
	{
		String result = "";
		for(int i = top - 1; i >= 0; i--)
			result = result + (contents[i]) + "\n";
		
		return result;
	}
}