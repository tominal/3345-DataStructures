package cs3345p3;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Testing {

	public static void main(String[] args) {
		
		Scanner in;
        if (args.length!=2) {
            System.out.print("Error Incorrect Arguments:" + Arrays.toString(args));
            System.exit(0);
        } 

        try {
        	File input_file = new File(args[0]);
            in = new Scanner(input_file);
            File output_file = new File(args[1]); 
            PrintWriter out;
            out = new PrintWriter(output_file);
         
            LazyBinarySearchTree LBST = new LazyBinarySearchTree(); 

            String operation;
            int lineno = 0, key;
            boolean result;

	        //whileloop:
	        while (in.hasNextLine()) {
			    lineno++;

			    operation = in.nextLine();
			    
			    if(operation != "") {
				    
				    String command = operation.split(":")[0];
				    
				    if(command.equals("Insert")) {
			    		try {
			    			key = Integer.parseInt(operation.split(":")[1]);
			    			
			    			try {
			    				result = LBST.insert(key);
			    				
			    				out.println(result ? "True" : "False");
			    			} catch (IllegalArgumentException e) {
			    				out.println("Error in insert: IllegalArgumentException raised");
			    			}
			    		} catch (Exception e) {
			    			out.println("Error in Line: " + command);
			    		}
				    } else if(command.equals("FindMin")) {
				    	out.println(LBST.findMin());
				    } else if(command.equals("FindMax")) {
				    	out.println(LBST.findMax());
				    } else if(command.equals("Contains")) {
				    	try {
			    			key = Integer.parseInt(operation.split(":")[1]);
			    			
			    			try {
			    				result = LBST.contains(key);
				    			
				    			out.println(result ? "True" : "False");
			    			} catch (IllegalArgumentException e) {
			    				out.println("Error in insert: IllegalArgumentException raised");
			    			}
			    		} catch (Exception e) {
			    			out.println("Error in Line: " + command);
			    		}
				    } else if(command.equals("Delete")) {
				    	try {
			    			key = Integer.parseInt(operation.split(":")[1]);
			    			
			    			try {
			    				result = LBST.delete(key);
				    			
				    			out.println(result ? "True" : "False");
			    			} catch (IllegalArgumentException e) {
			    				out.println("Error in insert: IllegalArgumentException raised");
			    			}
			    		} catch (Exception e) {
			    			out.println("Error in Line: " + command);
			    		}
				    } else if(command.equals("PrintTree")) {
				    	out.println(LBST.toString());
				    } else if(command.equals("Height")) {
				    	out.println(LBST.height());
				    } else if(command.equals("Size")) {
				    	out.println(LBST.size());
				    } else {
				    	out.println("ERROR in Line: " + operation);
				    }
			    }

	        } 
	        in.close();
	        out.close();
    
        }
        catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
	}

}
