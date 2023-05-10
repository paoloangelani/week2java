package itemsList;

public class Book extends ReadableItem{
	
	private String author;
	private String genre;

	public Book(int isbn, String title, int year, int pages, String a, String g) {
		super(isbn, title, year, pages);
		this.author = a;
		this.genre = g;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String toString() {
		String res= "\nBook:";
		res += "\n Title: " + this.getTitle();
		res += "\n Author: " + this.getAuthor();
		res += "\n Year: " + this.getYearOfRelease();
		res += "\n";
		return res;
	}
	
	public static String toFile (Book b) {
		return "book"
			 + "@" + b.isbn
			 + "@" + b.title
			 + "@" + b.yearOfRelease
			 + "@" + b.numberOfPage
			 + "@" + b.author
			 + "@" + b.genre;
	}
	
	public static Book fromFile(String stringedFile) {
		String[] splitString = stringedFile.split("@");
		
//		for (int i = 0; i <= splitString.length; i++) {
//			System.out.println(splitString[i]);
//		}
		return new Book(Integer.valueOf(splitString[1]), splitString[2], Integer.valueOf(splitString[3]), Integer.valueOf(splitString[4]), splitString[5], splitString[6]);
	}
}
