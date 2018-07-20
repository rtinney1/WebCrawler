import java.util.*;
import java.util.Arrays;
import java.util.Collections;
import java.lang.*;

public class Site implements Comparable<Site>
{
	String siteName;
	ArrayList<MailDomain> mailDomainList;
	int index;

	public Site(String s)
	{
		siteName = s; //associated site name for list
		mailDomainList = new ArrayList<MailDomain>();
	}

	public void add(String mailDomain, String account)
	{
		MailDomain newDomain;
		newDomain = new MailDomain(mailDomain);

		if(!this.alreadyInList(newDomain))
		{
			mailDomainList.add(newDomain);
			newDomain.add(account);
		}
		else
		{
			mailDomainList.get(index).add(account);
		}
	}

	@Override
	public boolean equals(Object other)
	{
		String leftHSite;
		String rightHSite;

		leftHSite = this.siteName.toUpperCase();
		rightHSite = ((Site)other).siteName.toUpperCase();

		if(leftHSite.equals(rightHSite))
			return true;
		else
			return false;
	}

	@Override
	public int compareTo(Site other)
	{
		int sort;

		sort = this.siteName.compareTo(other.siteName);

		return sort;
	}

	public String getSiteName()
	{
		return siteName;
	}

	public boolean alreadyInList(MailDomain mailDomain)
	{
		if(mailDomainList.contains(mailDomain))
				return true;
		else
			return false;
	}

	public void sort()
	{
		Collections.sort((List) mailDomainList);

		for(int i = 0; i < mailDomainList.size(); i++)
		{
			mailDomainList.get(i).sort();
		}
	}

	public void display()
	{
		int i = 0;

		while(mailDomainList.get(i) != null)
		{
			mailDomainList.get(i).display();
			i++;
		}
	}
}