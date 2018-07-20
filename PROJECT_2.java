import java.util.*;
//import java.util.List;
import java.util.Arrays;
import java.io.*;
import java.net.*;
import java.awt.*;
//import java.awt.List;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import java.util.Collections;
import javax.swing.Timer;

public class PROJECT_2
{
	public static void main (String args[])
	{
		new MainProgram().startProgram();
	}
}//end main class

class MainProgram implements ActionListener
{
	static final int    MAX_RADIUS = 5;
	static final int    MAX_EXPTIME = 10;
	static final int    MAX_RUNTIME = 60;
	ArrayList<Website>  siteQueue;
	WebSiteList         webSiteList;
	boolean         	continueRunning;
	boolean             canExpand;

	public MainProgram()
	{
		continueRunning = true;
		canExpand = true;
		siteQueue = new ArrayList<Website>();
		siteQueue.add(new Website("http://www.fairmontstate.edu", 0));
		webSiteList = new WebSiteList();
		//this.startProgram();
	}

	public void startProgram()
	{
		URL               url;
		Timer             expTimer;
		URLConnection     urlConnection;
		InputStreamReader inputReader;
		ParseHandler      tagHandler;
		int               arrayIndex = 0;
		Timer             programTimer;
		Timer             expandTimer;

		programTimer = new Timer(MAX_RUNTIME*1000, this);
		programTimer.setActionCommand("TOTALRUN");
		programTimer.setRepeats(false);
		programTimer.start();

		expandTimer = new Timer(MAX_EXPTIME*1000, this);
		expandTimer.setActionCommand("EXPTIME");
		expandTimer.setRepeats(false);
		expandTimer.start();


		//expTimer = new Timer(maxTime, this);
		//expTimer.setRepeats(false);
		//expTimer.start();

		while(continueRunning)
		{
			if( arrayIndex < siteQueue.size())
			{
				try
				{
					url = new URL(siteQueue.get(arrayIndex).url);

					//System.out.println("Going to: " + siteQueue.get(i).url);

					urlConnection = url.openConnection();

					inputReader = new InputStreamReader(urlConnection.getInputStream());

					tagHandler = new ParseHandler(this, siteQueue.get(arrayIndex).url, siteQueue.get(arrayIndex).distFromSeed + arrayIndex);

					new ParserDelegator().parse(inputReader, tagHandler, true);
				}
				catch(MalformedURLException mue)
				{
					//System.out.println("MalURLExcep. Tried to access: " + siteQueue.get(i));
				}
				catch(IOException ioe)
				{
					//System.out.println("Error with io. Tried to go to: " + siteQueue.get(i));
				}

				arrayIndex++;
			}
			else
				continueRunning = false;
		}

		webSiteList.sort();

		//System.out.println("Total distance from seed: " + siteQueue.get(i).distFromSeed);

		webSiteList.display();
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("TOTALRUN"))
			continueRunning = false;
		else if(ae.getActionCommand().equals("EXPTIME"));
			canExpand = false;
	}
}