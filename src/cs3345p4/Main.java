package cs3345p4;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner in;
        if (args.length!=2) {
            System.out.print("No input/output files specified.");
            System.exit(0);
        }
        try {
	        File input_file = new File(args[0]);
	        in = new Scanner(input_file);
	        File output_file = new File(args[1]); 
	        PrintWriter out;
	        out = new PrintWriter(output_file);
	        
	        String type = "";
	        
	        if(in.hasNextLine())
	        	type = in.nextLine();
	        
	        if(type.equals("String")) {
	        	RedBlackTree<String> t = new RedBlackTree<String>();
	        	
	        	while(in.hasNextLine()) {
	        		String line = in.nextLine();
	        		
	        		if(line.equals("PrintTree"))
	        			out.println(t.toString());
	        		else if(line.contains(":")) {
	        			String operation = line.substring(0, line.indexOf(':'));
	        			String operand = line.substring(line.indexOf(':') + 1);
	        			
	        			if(operation.equals("Insert"))
	        				out.println(t.insert(operand) ? "True" : "False");
	        			else if(operation.equals("Contains"))
	        				out.println(t.contains(operand) ? "True" : "False");
	        			else
	        				out.println("Error in Line: " + line);
	        		}
	        		else
	        			out.println("Error in Line: " + line);
	        	}
	        }
	        else if(type.equals("Integer")) {
	        	RedBlackTree<Integer> t = new RedBlackTree<Integer>();
	        	
	        	while(in.hasNextLine()) {
	        		String line = in.nextLine();
	        		
	        		if(line.equals("PrintTree"))
	        			out.println(t.toString());
	        		else if(line.contains(":")) {
	        			String operation = line.substring(0, line.indexOf(':'));
	        			String operand = line.substring(line.indexOf(':') + 1);
	        			
	        			if(operation.equals("Insert"))
	        				out.println(t.insert(Integer.parseInt(operand)) ? "True" : "False");
	        			else if(operation.equals("Contains"))
	        				out.println(t.contains(Integer.parseInt(operand)) ? "True" : "False");
	        			else
	        				out.println("Error in Line: " + line);
	        		}
	        		else
	        			out.println("Error in Line: " + line);
	        	}
	        }
	        else
	        	out.println("Only works for objects Integers and Strings");
	        
	        in.close();
	        out.close();
		}
	    catch(Exception e){
	        System.out.println("Exception: " + e.getMessage());
	        e.printStackTrace();
	    }
		
	}
	
}
