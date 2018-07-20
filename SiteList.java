import java.util.*;
import java.util.Arrays;
import java.util.Collections;
import java.lang.*;

public class SiteList implements Comparable<SiteList>
{
	String siteName;
	ArrayList<MailDomain> mailDomainList;
	int index;

	public SiteList(String s)
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
		String leftHSiteList;
		String rightHSiteList;

		leftHSiteList = this.siteName.toUpperCase();
		rightHSiteList = ((SiteList)other).siteName.toUpperCase();

		if(leftHSiteList.equals(rightHSiteList))
			return true;
		else
			return false;
	}

	@Override
	public int compareTo(SiteList other)
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