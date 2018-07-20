import java.util.*;
import java.util.Arrays;
import java.util.Collections;
import java.lang.*;

public class WebSiteList
{
	ArrayList<Site> siteList;
	int index;

	public WebSiteList()
	{
		siteList = new ArrayList<Site>();
	}

	public void add(String url, String mailDomain, String account)
	{
		Site newSite;

		newSite = new Site(url);

		if(!this.alreadyInList(newSite))
		{
			newSite.add(mailDomain, account);
			siteList.add(newSite);
		}
		else
			siteList.get(index).add(mailDomain, account);
	}

	public boolean alreadyInList(Site siteName)
	{
		if(siteList.contains(siteName))
			return true;
		else
			return false;
	}

	public void sort()
	{
		Collections.sort((List) siteList);

		for(int i = 0; i < siteList.size(); i++)
		{
			siteList.get(i).sort();
		}
	}

	public void display()
	{
		for(int i = 0; i < siteList.size(); i++)
		{
			System.out.println(siteList.get(i).getSiteName());

			for(int j = 0; j < siteList.get(i).mailDomainList.size(); j++)
			{
				//siteList.get(i).mailDomainList.get(j).display();
				for(int k = 0; k < siteList.get(i).mailDomainList.get(j).accountList.size(); k++)
				{
					System.out.println(siteList.get(i).mailDomainList.get(j).accountList.get(k) + "@" + siteList.get(i).mailDomainList.get(j).getMailDomain());
				}
			}
			System.out.println("");
			System.out.println("*******************************");
		}
	}
}