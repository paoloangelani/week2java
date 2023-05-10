package itemsList;

public class Magazine extends ReadableItem {
	
	private Periodicity release;

	public Magazine(int code, String tit, int year, int pages, Periodicity r) {
		super(code, tit, year, pages);
		this.release = r;
	}

	public Periodicity getRelease() {
		return release;
	}

	public void setRelease(Periodicity release) {
		this.release = release;
	}

	public String toString() {
		String res= "\nMagazine:";
		res += "\n Title: " + this.getTitle();
		res += "\n Release: " + this.getRelease();
		res += "\n Year: " + this.getYearOfRelease();
		res += "\n";
		return res;
	}
	
	public static String toFile (Magazine m) {
		return "magazine"
			 + "@" + m.isbn
			 + "@" + m.title
			 + "@" + m.yearOfRelease
			 + "@" + m.numberOfPage
			 + "@" + m.release;
	}
	
	public static Magazine fromFile(String stringedFile) {
		String[] splitString = stringedFile.split("@");
		
		return new Magazine(Integer.valueOf(splitString[1]), splitString[2], Integer.valueOf(splitString[3]), Integer.valueOf(splitString[4]), Periodicity.valueOf(splitString[5]));
	}
}
