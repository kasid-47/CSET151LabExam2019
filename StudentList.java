import java.io.*;
import java.text.*;
import java.util.*;
public class StudentList {
	public static void main(String[] args) {

		// Check arguments
		if (args == null || args.length != 1)
        {
				System.out.println("Use one of these arguments: a | r | c | +WORD | ?WORD");		
                return; // Exit
        }
		
		// Refactoring file reading code used in all blocks to a single method
		String reader = LoadData("students.txt");
		
		if(args[0].equals("a"))
		{
			String words[] = reader.split(",");
			for(String word : words)
			{
					System.out.println(word);
			}
		}
		else if(args[0].equals("r")) 
		{
			String words[] = reader.split(",");
			Random rand = new Random();
			int randomIndex = rand.nextInt(words.length);
			System.out.println(words[randomIndex]);		
		}
		else if(args[0].contains("+"))
		{
			UpdateContent(args,"students.txt",reader);
		}
		else if(args[0].contains("?")) 
		{
			String words[] = reader.split(",");
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
		else if(args[0].contains("c")) 
		{
			char a[] = reader.toCharArray();			
			boolean in_word = false;
			int count=0;
			for(char c:a)
			{
				if(c ==' ') 
				{
					if(!in_word)
					{
						count++; in_word =true;
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
		//System.out.println("Loading data ...");
		String reader = null;		
		try
		{
			BufferedReader fileStream = new BufferedReader(new InputStreamReader(new FileInputStream(fileName))); 
			reader = fileStream.readLine();
		}
		catch (Exception e)
		{
				
		}
		//System.out.println("Data Loaded.");		
		return reader;
	}
	public  static  void  UpdateContent(String[] args, String fileName, String reader)
	{
		System.out.println("Loading data ...");
		try {
			FileWriter s = new FileWriter(fileName);  
			BufferedWriter buffer = new BufferedWriter(s);  
			String studentName = args[0].substring(1);
			Date date = new Date();
			String df = "dd/mm/yyyy-hh:mm:ss a";
			DateFormat dateFormat = new SimpleDateFormat(df);
			String fd= dateFormat.format(date);
			s.write(reader+", "+studentName+"\nList last updated on "+fd);
			s.close();
		}
		catch (Exception e)
		{
			
		}
		System.out.println("Data Loaded.");
	}
}