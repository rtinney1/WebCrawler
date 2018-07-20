import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;

public class ParseHandler extends HTMLEditorKit.ParserCallback
{
	MainProgram mainProgram;
	String      siteURL;
	int         distFromSeed;

	public ParseHandler(MainProgram program, String site, int dist)
	{
		mainProgram = program;
		siteURL = site;
		distFromSeed = dist;
	}

	@Override
	public void handleStartTag(HTML.Tag tag, MutableAttributeSet mas, int pos)
	{
		Object   attributes;
		String   link;
		String   regEx;
		Pattern  pattern;
		Matcher  matcher;
		boolean  done = false;
		String[] mailComps;
		String   tempString;

		if(tag == HTML.Tag.A)
		{
			attributes = mas.getAttribute(HTML.Attribute.HREF);

			if(attributes != null)
			{
				//System.out.println("Inside handleStartTag");
				regEx = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
				pattern = Pattern.compile(regEx);

				link = attributes.toString();
				matcher = pattern.matcher(link);

				while(!done)
				{
					if(matcher.find())
					{
						//System.out.println(link);
						pattern = Pattern.compile("mailto:");
						matcher = pattern.matcher(link);
						while(!done)
						{
							if(matcher.find())
							{
								mailComps = link.split("mailto:");
								//System.out.println(mailComps[0]);
								tempString = mailComps[1].toString();
								mailComps = tempString.split("@");

								mainProgram.webSiteList.add(siteURL, mailComps[1], mailComps[0]);
								done = true;
							}
							else
								done = true;
						}
						//System.out.println("Found an email");
					}
					else
					{
						pattern = Pattern.compile("http");
						matcher = pattern.matcher(link);

						while(!done)
						{
							if(!matcher.find())
							{
								link = siteURL + link;
								done = true;
							}
							else
								done = true;
						}

						if(distFromSeed < mainProgram.MAX_RADIUS && mainProgram.canExpand)
						{
							if(!mainProgram.canExpand)
								System.out.println("Ran out of time to expand");

							mainProgram.siteQueue.add(new Website(link, distFromSeed));
						}

						//System.out.println("added link: " + link);
					}
				}
			}
		}
	}

	@Override
	public void handleText(char[] data, int pos)
	{
		String   urlString;
		String   regEx;
		String   strToMatch;
		String   emailAddress;
		String[] emailComps;
		Pattern  pattern;
		Matcher  matcher;
		boolean  done = false;

		regEx = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
		pattern = Pattern.compile(regEx);

		strToMatch = new String(data);
		matcher = pattern.matcher(strToMatch);

		//System.out.println("Inside handleText");
		while(!done)
		{
			if(matcher.find())
			{
				//System.out.println("Match found in handleText");
				emailAddress = matcher.group(0);
				emailComps = emailAddress.split("@");

				mainProgram.webSiteList.add(siteURL, emailComps[1], emailComps[0]);
				//System.out.println("Found an email");
			}
			else
			{
				done = true;
			}
		}
	}
}
