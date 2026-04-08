package dsaa.lab03;

public class Link{
	public String ref;
	// in the future there will be more fields
	public Link(String ref) {
		this.ref=ref;
	}
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Link other = (Link) obj;
        return ref != null && ref.equals(other.ref);
    }

    @Override
    public String toString(){
        return "\n" + ref;
    }
	
}