package dsaa.lab01;
import java.util.Scanner;


public class Document {
    static final String prefix = "link=";
    static final int linkBodyStartIdx = prefix.length();

	public static void loadDocument(String name, Scanner scan) {
            while(scan.hasNext()){
                String line = scan.next();
                line = line.toLowerCase().trim();
                if(line.equals("eod")) break;
                if (correctLink(line)) System.out.println(line.substring(linkBodyStartIdx));
            }
    }

	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	public static boolean correctLink(String link) {
        if(link.length() < prefix.length() + 1) return false;
        String linkHead = link.substring(0, linkBodyStartIdx);
        String linkBody = link.substring(linkBodyStartIdx);
        if(!linkHead.equals(prefix) || !linkBody.matches("^[a-z][a-z0-9_]*$")) return false;
        return true;
	}

}
