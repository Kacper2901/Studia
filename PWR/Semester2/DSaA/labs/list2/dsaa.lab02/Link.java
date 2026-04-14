package dsaa.lab02;

public class Link{
	public String ref;
	public Link(String ref) {
		this.ref=ref;
	}
	// in the future there will be more fields

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Link other = (Link) obj;
        return ref != null && ref.equals(other.ref);
    }
}
