package corejava.phase1.project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AssessProject {
	
	static boolean result = true;
	
	
	public static boolean searchFile(File [] files, String file)throws IOException
	{
		for (File filename : files)  
        { 
            if (filename.isDirectory())  
            { 
                searchFile(filename.listFiles(),file); 
             }  
            else 
            { 
                if(filename.getName().equalsIgnoreCase(file)) 
                	{
                	    System.out.println("File: " + filename.getName() + "\tFound At Path : " + filename.getCanonicalPath()); 
                		result = false;
                	}
            } 
        } 
		
		return result;
	}
	
	public static void displayFiles(File[] files) throws Exception
    { 
        // traversing through the files array 
        for (File filename : files)  
        { 
                   	
            if (filename.isDirectory())  
            { 
                System.out.println("Directory: " + filename.getName() + "\tCanonical Path : " + filename.getCanonicalPath()); 
                displayFiles(filename.listFiles()); 
             } 
            else 
            { 
                System.out.println("\tFile: " + filename.getName() + "\tCanonical Path : " + filename.getCanonicalPath()); 
            }         	
        } 
    }

	public static void main(String[] args) throws Exception
	{
		
		String mainmenuchoice = "4";
		Scanner scan=new Scanner(System.in);
		System.out.println("***********************");
		System.out.println("Welcome to LockedMe.com");
		System.out.println("***********************");
		System.out.println("By--Suruchi Rajguru");
		System.out.println("***********************");

		
		do{
			System.out.println("Main Menu: ");
			System.out.println("\t1. Dispaly the current files name in ascending order");
			System.out.println("\t2. Select operation:Add,Delete,Search,Navigate Back ");
			System.out.println("\t3. close the application");	
			System.out.println("Enter Main Menu Choice : ");
			mainmenuchoice = scan.next();
			
			switch(mainmenuchoice)
			{
			    case "1": 
			    	System.out.println("Root directory is: D:\\Lockers_Pvt_Ltd");
			    	
			    	File[] files = new File("D:\\Lockers_Pvt_Ltd").listFiles(); 
			    	
			    	Arrays.sort(files);			        
			        // call displayFiles function to display files 
			        displayFiles(files); 
			    	
			    	break;
			    case "2":
			    	
					String submenuchoice = "5";
					
					Scanner scanFile = new Scanner(System.in);
					do
					{
						System.out.println("Sub Menu: ");
				    	System.out.println("\t1. Add a file name to the existing working directory");
						System.out.println("\t2. Delete a file from the existing directory list");
						System.out.println("\t3. Search a file from the main directory");
						System.out.println("\t4. Navigate back to the main context");
						System.out.println("Enter Sub Menu Choice:");
						//Scanner scan2=new Scanner(System.in);
						submenuchoice = scan.next();
					
					switch(submenuchoice)
					{
						case "1":
							System.out.println("1. Adding a file name to the existing working directory");
							try
							{
								String root = "D:\\Lockers_Pvt_Ltd\\";
								System.out.println("Enter File Name With Path (If only Filename is specified then file will be added to root Diretory) : ");
								String filename= scanFile.nextLine();
								String filePath = null;
								
								if(filename.length() > 19 && filename.substring(0, 19).equals(root) ) 
								{									
									filePath = filename;
								}							
								
								else
								{				
									filePath = "D:\\Lockers_Pvt_Ltd\\"+filename;
								}
								
								File file=new File(filePath);
								
								if(file.exists())
								{
									System.out.println("File already exists. With Path : " + file.getCanonicalPath());
								}
								else
								{
									file.createNewFile();			
									System.out.println("File is added to the directory. Path is : " + file.getCanonicalPath());	
								}
								
							}
							catch(Exception e)
							{
								System.out.println("Path Specified is not Valid.");
							}				
						break;
						
						case "2":
							String root = "D:\\Lockers_Pvt_Ltd\\";
							String filename = null;
							String deletechoice = "N";
							System.out.println("2. Delete a file from the existing directory list");
							try
							{
								
								System.out.println("Enter a file name With Path to be deleted  (If only Filename is specified then file will be searched & deleted from root Diretory) : ");
								String f=scanFile.nextLine();
								String filepath = null;
								
								if(f.length() > 19 && f.substring(0, 19).equals(root))
								{
									filepath = f;
									filename = f.substring(f.lastIndexOf("\\")+1).trim();
								}
								else
								{
									filepath = root + f;
									filename = f;
								}
								
								File file=new File(filepath);
								//File file=new File("D:/Suruchi/SimpliLearn/Eclipse Programs/Phase1_Project/trial.txt");
								if(file.exists())
								{
									Boolean b;
									//b=file.exists();
									String canonicalpath = file.getCanonicalPath();
									String sysfilename = canonicalpath.substring(canonicalpath.lastIndexOf("\\")+1).trim();
									if(!filename.equals(sysfilename))
									{
										System.out.println("\tFile found : " + sysfilename + " at Canonical Path: " + canonicalpath);
										System.out.println("\tEntered File name is : " + filename + " At Path : " + filepath);
										System.out.println("Press 'Y' or 'y'  to confirm to delete else 'N' or 'n' to Cancel the Delete operation.");
										deletechoice = scan.next();
										
									}
									else
									{
										deletechoice = "Y";
									}
									
									if( deletechoice.equals("Y") || deletechoice.equals("y"))
									{ 
										//System.out.println(file.getName());
										b = file.delete();
										if(b==true)
										{
											System.out.println("File deleted");
										}	
									
									}
									else if (deletechoice.equals("N") || deletechoice.equals("n"))
									{
										System.out.println("Delete Operation Cancelled!!!.");
									}
									else
									{
										System.out.println("Wrong Choice Entered & Delete Operation Cancelled!!!.");
									}
								}	
								else
								{
									System.out.println("File Not Found");
								}
							}
							catch(Exception e)
							{
								System.out.println("File Not Found or Path is not Valid.");								
							}
						
							break;
							
						case "3":
							System.out.println("Enter a File Name to Search: ");
							String file = scanFile.nextLine();
							
							File[] files1 = new File("D:\\Lockers_Pvt_Ltd").listFiles(); 
					    	
							boolean r = searchFile(files1,file);
							if(r)
							{
								System.out.println("File Not Found");
							}
							break;
						case "4":
							break ;
						default :
							System.out.println("Enter Valid Choice!");
					}
					
					}
					while(!submenuchoice.equals("4"));
					//scanFile.close();
					break;
			case "3": 
			    	System.out.println("System Exit.");
			    	System.exit(0);
			    	break;
			    default :
					System.out.println("Enter Valid Choice!");
			}

			
		}while(true);
	
	}

}
