import java.io.*;
import java.text.*;
import java.util.*;
import static  CSET151LabExam2019.Constants.*;
public class StudentList {
	public static void main(String[] args) {

		// Check argument count
		if (args == null || args.length != 1)
        {
				showValidArguments();
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
			String searchTerm = args[0].substring(1);
			int index;
			for(index = 0; index<words.length; index++)
			{
				if(words[index].trim().equals(searchTerm))
				{
					System.out.println("We found it!");
					break;
				}
			}
			if(index==words.length)
				System.out.println(searchTerm+" does not exist");
		}
		else if(args[0].contains(ShowCount)) 
		{
			String words[] = LoadData(StudentList).split(StudentEntryDelimiter);
			System.out.println(words.length +" word(s) were found ");			
		}
		else
		{
			showValidArguments();
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
	
	// Message to be shown when invalid arguments are passed
	public static void showValidArguments()
	{
		System.out.println("Use one of these arguments: -a | -r | -c | +WORD | ?WORD");	
	}
}