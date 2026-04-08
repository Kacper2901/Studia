package dsaa.lab03;

import java.util.Scanner;

public class Document{
    static final String prefix = "link=";
    static final int linkBodyStartIdx = prefix.length();
    public String name;
	public TwoWayUnorderedListWithHeadAndTail<Link> link;
	public Document(String name, Scanner scan) {
		this.name=name;
		link =new TwoWayUnorderedListWithHeadAndTail<Link>();
		load(scan);
	}
	public void load(Scanner scan) {
        while(scan.hasNext()){
            String line = scan.next();
            line = line.toLowerCase().trim();
            if(line.equals("eod")) break;
            if (correctLink(line)) link.add(new Link(line.substring(linkBodyStartIdx)));
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

	@Override
	public String toString() {
		// TODO
        String ret = "Document: " + name;
        for (Link link : link) {
            ret += "\n" + link.ref;
        }
        return ret;
	}

	public String toStringReverse() {
		String retStr="Document: "+name;
		return retStr+ link.toStringReverse();
	}

}
