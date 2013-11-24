import java.util.*;

public class calendar
{
	final static String dayrow = "Su Mo Tu We Th Fr Sa";

	public static void main(String[] args)
	{
		Calendar cal2 = Calendar.getInstance();
//		System.out.println(cal2.OCTOBER);
		if(args.length != 0)
		{
			Calendar cal = Calendar.getInstance();
			int year = Integer.parseInt(args[1]);
			int month = Integer.parseInt(args[0]);
			int date = 1;
			cal.set(year, month-1, date);
			boolean leap = year%4 == 0 && year != 0;
			int day = getDayOfWeek(month, date, year);
			int daymonth = getDaysOfMonth(month, leap);
			printCalendar(month, year, day, date, daymonth);
		}
		else
		{
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			int year = cal.get(Calendar.YEAR);
			int date = cal.get(Calendar.DATE);
			int day = getDayOfWeek(month, date, year);
			boolean leap = year%4 == 0 && year != 0;
			int daymonth = getDaysOfMonth(month, leap);
			printCalendar(month, year, day, date, daymonth);
		}
	}
	public static void printCalendar(int m, int y, int day, int date,
int daymonth)
	{//this method prints the actual calendar
		int space = getMonthSpace(m);
		int count = 1;
		int firstday = getFirstDayOfMonth(day, date);
		for(int i = 0; i < (20 - (space + 5)) / 2; i++)
			System.out.print(" ");
		printMonthName(m);
		System.out.println(" " + y);
		System.out.println(dayrow);
		for(int i = 1;i <= 7; i++)
		{
			if(i < firstday)
				System.out.print("  ");
			else if(i == firstday)
			{
				System.out.print(" " + count);
				count++;
			}
			else
			{
				System.out.print(" " + count);
				count++;
			}
			System.out.print(" ");
		}
		System.out.println();
		while(daymonth >= count)
		{
			for(int i = 0; i < 7; i++)
			{
				if(daymonth < count)
					break;
				else if(count >= 10)
				{
					System.out.print(count);
				}
				else
				{
					System.out.print(" " + count);
				}
				count++;
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	public static int getMonthSpace(int m)
	{
		int number = 0;
		switch(m)
		{
		case 5: number = 3;
			break;
		case 6: 
		case 7: number = 4;
			break;
		case 3:
		case 4: number = 5;
			break;
		case 8: number = 6;
			break;
		case 1:
		case 10: number = 7;
			break;
		case 2:
		case 11:
		case 12: number = 8;
			break;
		case 9: number = 9;
			break;
		}
		return number;
	}

	public static int getFirstDayOfMonth(int day, int date)
	{
		int hold = (date-1)%7;
		while(hold != 0)
		{
			if(day == 1)
				day = 7;
			else
				day--;
			hold--;
		}
		return day;
	}

	public static int getDaysOfMonth(int month, boolean leap)
	{
		int number = 0;
		switch(month)
		{
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12: number = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11: number = 30;
			break;
		case 2: if(leap)
				number = 29;
			else
				number = 28;
			break;
		}
		return number;
	}
	
	public static void printMonthName(int m)
	{
		switch(m)
		{
		case 1: System.out.print("January");
			break;
		case 2: System.out.print("February");
			break;
		case 3: System.out.print("March");
			break;
		case 4: System.out.print("April");
			break;
		case 5: System.out.print("May");
			break;
		case 6: System.out.print("June");
			break;
		case 7: System.out.print("July");
			break;
		case 8: System.out.print("August");
			break;
		case 9: System.out.print("September");
			break;
		case 10: System.out.print("October");
			break;
		case 11: System.out.print("November");
			break;
		case 12: System.out.print("December");
			break;
		}
	}
	public static int getDayOfWeek(int m, int date, int y)
	{
		int day = 0;
		int y0 = y - (14 - m) / 12;
		int x = y0 + y0 / 4 - y0 / 100 + y0 / 400;
		int m0 = m + 12 * ((14 - m) / 12) - 2;
		day = 1 + (date + x + (31 * m0) / 12) % 7;
		return day;
	}

}
