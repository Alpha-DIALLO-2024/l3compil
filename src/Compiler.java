import sc.parser.*;
import sc.lexer.*;
import sc.node.*;
import java.io.*;
import sa.*;
import ts.*;
/*import c3a.*;
import nasm.*;
import fg.*;
import ig.*;
*/

public class Compiler
{
    public static void main(String[] args)
    {
     boolean outputArm = false;
     boolean outputX86 = true;
     boolean outputPython = false;
     PushbackReader br = null;
     String baseName = null;
     String inputFileName = null;
     int verboseLevel = 0;
	
	for (int i = 0; i < args.length; i++) {
	    if(args[i].equals("-v")){
		    i++;
		    if (args[i].equals("0")){
			verboseLevel = 0;
		    }
		    if (args[i].equals("1")){
			verboseLevel = 1;
		    }
		    if (args[i].equals("2")){
			verboseLevel = 2;
		    }
	    }
			
	    else if(args[i].equals("-f")){
		    i++;
		    if (args[i].equals("x86")){
		    }
		    else if (args[i].equals("arm")){
			outputArm = true;
			outputX86 = false;
		    }
		    else if (args[i].equals("python")){
			outputPython = true;
			outputX86 = false;
		    }
		    else{
			System.out.println("output format file unknown");
			System.exit(1);
		    }
		}
	    else{
		inputFileName = args[i];
		//		System.out.println(inputFileName);
	    }
	}
	
	if(inputFileName == null){
	    System.out.println("specify input file");
	    System.exit(1);
	}
	
	try {
	    br = new PushbackReader(new FileReader(inputFileName));
	    baseName = removeSuffix(inputFileName, ".l");
	}
	catch (IOException e) {
	    e.printStackTrace();
	} 
	try {
	    // Create a Parser instance.
	    Parser p = new Parser(new Lexer(br));
	    // Parse the input.
	    System.out.println("[BUILD SC] ");
	    Start tree = p.parse();
	    
	    if(verboseLevel > 1){
		System.out.println("[PRINT SC]");
		tree.apply(new Sc2Xml(baseName));
	    }
	    
	    System.out.println("[BUILD SA] ");
	    Sc2Sa sc2sa = new Sc2Sa();
	    tree.apply(sc2sa);
	    SaNode saRoot = sc2sa.getRoot();

	    if(verboseLevel > 1){
		System.out.println("[PRINT SA]");
		new Sa2Xml(saRoot, baseName);
	    }
	    
	    System.out.println("[BUILD TS] ");
		Sa2ts visitor = new Sa2ts(saRoot);
		System.out.println("has visited");
	    Ts tableGlobale = visitor.getTableGlobale();
		System.out.println("has built");
	    if (verboseLevel > 1){
			System.out.println("[PRINT TS]");
			tableGlobale.afficheTout(baseName);
	    }/*
	    
	    if(verboseLevel > 0){
		System.out.println("[EXEC SA] ");
		SaEval saEval = new SaEval(saRoot, tableGlobale);
		if(verboseLevel > 1){
		    System.out.println("[PRINT SA]");
		    saEval.affiche(baseName);
		}
	    }
	    
	    System.out.println("[BUILD C3A] ");
	    C3a c3a = new Sa2c3a(saRoot, tableGlobale).getC3a();

	    if(verboseLevel > 1){
		System.out.println("[PRINT C3A] ");
		c3a.affiche(baseName);
	    }
	    
	    if(verboseLevel > 0){
		System.out.println("[EXEC C3A]");
		C3aEval c3aEval = new C3aEval(c3a, tableGlobale);
		c3aEval.affiche(baseName);
	    }
	    
	    if(outputX86){
		System.out.println("[BUILD PRE NASM] ");
		Nasm nasm = new C3a2nasm(c3a, tableGlobale).getNasm();
		if(verboseLevel > 1){
		    
		System.out.println("[PRINT PRE NASM] ");
		nasm.affichePre(baseName);
		}
		
		System.out.println("[BUILD FG] ");
		Fg fg = new Fg(nasm);
		
		if(verboseLevel > 1){
		    System.out.println("[PRINT FG] ");
		    fg.affiche(baseName);
		}
		
		System.out.println("[SOLVE FG]");
		FgSolution fgSolution = new FgSolution(nasm, fg);
		if(verboseLevel > 1){
		    System.out.println("[PRINT FG SOLUTION] ");
		    fgSolution.affiche(baseName);
		}	    
		System.out.println("[BUILD IG] ");
		Ig ig = new Ig(fgSolution);
		
		if(verboseLevel > 1){
		    System.out.println("[PRINT IG] ");
		    ig.affiche(baseName);
		}

		System.out.println("[ALLOCATE REGISTERS]");
		ig.allocateRegisters();
		
		System.out.println("[PRINT NASM]");
		nasm.affiche(baseName);
	    }
	    if(outputArm){
		System.out.println("ARM output not implemented yet");
	    }
	    if(outputPython){
		System.out.println("Python output not implemented yet");
	    }
	    */
	}
	catch(Exception e){
	    System.out.println(e.getMessage());
	    System.exit(1);
	}
    }


    public static String removeSuffix(final String s, final String suffix)
    {
	if (s != null && suffix != null && s.endsWith(suffix)){
	    return s.substring(0, s.length() - suffix.length());
	}
	return s;
    }
    
}
