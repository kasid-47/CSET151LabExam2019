import java.io.*;
import java.text.*;
import java.util.*;
import static  CSET151LabExam2019.Constants.*;
public class StudentList {
	public static void main(String[] args) {

		// Check arguments
		if (args == null || args.length != 1)
        {
				System.out.println("Use one of these arguments: -a | -r | -c | +WORD | ?WORD");		
                return; // Exit
        }
		
		if(args[0].equals(ShowAll))
		{
			String words[] = LoadData(StudentList).split(StudentEntryDelimiter);
			for(String word : words)
			{
					System.out.println(word);
			}
		}
		else if(args[0].equals(ShowRandom)) 
		{
			String words[] = LoadData(StudentList).split(StudentEntryDelimiter);
			Random rand = new Random();
			int randomIndex = rand.nextInt(words.length);
			System.out.println(words[randomIndex]);		
		}
		else if(args[0].contains(AddEntry))
		{
			UpdateContent(args[0].substring(1),StudentList);
		}
		else if(args[0].contains(FindEntry)) 
		{
			String words[] = LoadData(StudentList).split(StudentEntryDelimiter);
			boolean done = false;
			String t = args[0].substring(1);
			for(int index = 0; index<words.length && !done; index++)
			{
				if(words[index].equals(t))
				{
					System.out.println("We found it!");
					done=true;
				}
			}			
		}
		else if(args[0].contains(ShowCount)) 
		{
			char a[] = LoadData(StudentList).toCharArray();			
			boolean in_word = false;
			int count=0;
			for(char c:a)
			{
				if(c ==' ') 
				{
					if(!in_word)
					{
						count++;
						in_word =true;
					}
					else
					{
						in_word=false;
					}			
				}
			}
			System.out.println(count +" word(s) found " + a.length);			
		}
	}
	public static String LoadData(String fileName)
	{	
		try
		{
			BufferedReader fileStream = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			return fileStream.readLine();
		}
		catch(Exception e)
		{
			return "Buffer Exception!";
		}	
		
	}
	public static void UpdateContent(String studentName,String fileName)
	{
		System.out.println("Loading data ...");
		String listOfStudents = LoadData(StudentList);
		try
		{
			FileWriter fileStream = new FileWriter(fileName);  
			BufferedWriter buffer = new BufferedWriter(fileStream);
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy-hh:mm:ss a");
			fileStream.write(listOfStudents+StudentEntryDelimiter+" "+studentName+"\nList last updated on "+dateFormat.format(date));
			fileStream.close();
		}
		catch(Exception e)
		{
			
		}
		System.out.println("Data Loaded.");
	}
}