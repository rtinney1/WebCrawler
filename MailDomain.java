import java.util.*;
import java.util.Arrays;
import java.util.Collections;
import java.lang.*;

public class MailDomain implements Comparable<MailDomain>
{
	String mailDomain;
	ArrayList<String> accountList;

	public MailDomain(String s)
	{
		mailDomain = s;//associated mail domain for list
		accountList = new ArrayList<String>();
	}

	public void add(String s)
	{
		if(!this.alreadyInList(s))
			accountList.add(s);
	}

	@Override
	public boolean equals(Object other)
	{
		String leftHMailDomain;
		String rightHMailDomain;

		leftHMailDomain = this.mailDomain.toUpperCase();
		rightHMailDomain = ((MailDomain)other).mailDomain.toUpperCase();

		if(leftHMailDomain.equals(rightHMailDomain))
			return true;
		else
			return false;
	}

	@Override
	public int compareTo(MailDomain other)
	{
		int sort;

		sort = this.mailDomain.compareTo(other.mailDomain);

		return sort;
	}

	public String getMailDomain()
	{
		return mailDomain;
	}

	public boolean alreadyInList(String s)
	{
		if(accountList.contains(s))
			return true;
		else
			return false;
	}

	public void sort()
	{
		Collections.sort((List) accountList);
	}

	public void display()
	{
		int i = 0;

		while(accountList.get(i) != null)
		{
			System.out.println(accountList.get(i) + "@" + mailDomain);
			i++;
		}
	}
}