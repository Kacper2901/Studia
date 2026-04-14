package dsaa.lab02;

import java.util.Iterator;
import java.util.Scanner;

public class Document{
    static final String prefix = "link=";
    static final int linkBodyStartIdx = prefix.length();
	public String name;
	public OneWayLinkedList<Link> links;

	public Document(String name, Scanner scan) {
			// TODO
            this.links = new OneWayLinkedList<>();
            this.name = name;
			load(scan);

		}
	public void load(Scanner scan) {
        while(scan.hasNext()){
            String line = scan.next();
            line = line.toLowerCase().trim();
            if(line.equals("eod")) break;
            if (correctLink(line)) links.add(new Link(line.substring(linkBodyStartIdx)));
        }
		//TODO
	}
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	private static boolean correctLink(String link) {
        if(link.length() < prefix.length() + 1) return false;
        String linkHead = link.substring(0, linkBodyStartIdx);
        String linkBody = link.substring(linkBodyStartIdx);
        if(!linkHead.equals(prefix) || !linkBody.matches("^[a-z][a-z0-9_]*$")) return false;
        return true;	}
	
	@Override
    public String toString() {
        String ret = "Document: " + name;
        for (Link link : links) {
            ret += "\n" + link.ref;
        }
        return ret;
    }
}
