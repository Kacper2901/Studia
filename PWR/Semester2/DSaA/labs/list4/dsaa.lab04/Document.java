package dsaa.lab04;

import javax.swing.text.Element;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class Document{
	public String name;
	public TwoWayCycledOrderedListWithSentinel<Link> link;
	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new TwoWayCycledOrderedListWithSentinel<Link>();
		load(scan);
	}
	public void load(Scanner scan) {
		//TODO
        while (scan.hasNext()) {
            String line = scan.next();
            if (line.equals("eod")) break;
            if (line.toLowerCase().startsWith("link=")) {
                Link newLink = createLink(line.substring(5));
                if (newLink != null) {
                    link.add(newLink);
                }
            }
            }
        }


	public static boolean isCorrectId(String id) {
        //TODO
        if (id == null || id.length() == 0) return false;
        if (!Character.isLetter(id.charAt(0))) {
            return false;
        }

        for (int i = 0; i < id.length(); i++) {
            char c = id.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '_'){
                return false;
            }
        }
        return true;
	}

	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	 static Link createLink(String link) {
        //TODO

        int openBracket = link.indexOf('(');
        int closeBracket = link.indexOf(')');
        String id;
        int weight = 1;

        if (openBracket != -1 && closeBracket != -1 && closeBracket > openBracket) {
            id = link.substring(0, openBracket).toLowerCase();
            if(closeBracket != link.length() - 1) return null;
            try {
                weight = Integer.parseInt(link.substring(openBracket + 1, closeBracket));
                if(weight <=0 ) return null;
            } catch (NumberFormatException e) {
                return null;
            }
        } else if(openBracket == -1 && closeBracket == -1){
            id = link.toLowerCase();
        }
        else return null;

        if (isCorrectId(id) ) {
            return new Link(id, weight);
        }
		return null;
	}

	@Override
	public String toString() {
		String retStr="Document: "+name;
        Iterator<Link> it = link.iterator();
        int i = 0;


        while (i < link.size){
            if(i % 10 == 0){
                retStr += "\n";
            }
            else {
                retStr += " ";
            }
            Link nextLink = it.next();
            retStr += nextLink.toString();
            i++;
        }
		//TODO
		return retStr;
	}

	public String toStringReverse() {
		String retStr="Document: "+name;
		ListIterator<Link> iter=link.listIterator();
        int i = 0;
		while(i < link.size) {
            i++;
            iter.next();
        }
		//TODO

        i = 0;
		while(i < link.size){
            if(i % 10 == 0){
                retStr += "\n";
            }
            else{
                retStr += " ";
            }
            Link nextLink = iter.previous();
            retStr += nextLink.toString();
            i++;
		}
		return retStr;
	}
}

