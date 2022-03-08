package algorithms;

public class Prac1 
{
	public static void main(String[] args)
	{
		System.out.println("My name is Madyan Omar");
		Employee emp1 = new Employee("Madyan", 23, "Game development");
		Employee emp2 = new Employee("Mohammed", 22, "Level designer");
		emp1.printInfo();
		System.out.println("Number of employees = " + Employee.getNumberofEmployees());
	}
}

class Employee
{
	private String name;
	private int age;
	private String department;
	private static int numofemployees = 0;
	
	public Employee(String name, int num, String dep)
	{
		numofemployees+=1;
		this.name = name;
		age = num;
		department = dep;
	}
	
	public void printInfo()
	{
		System.out.println(name + " is " + age + " years old and works in the " + department + " department");
	}
	
	public static int getNumberofEmployees()
	{
		return numofemployees;
	}
}