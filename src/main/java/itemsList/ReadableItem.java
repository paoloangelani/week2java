package itemsList;

public class ReadableItem {
	
	protected int isbn;
	protected String title;
	protected int yearOfRelease;
	protected int numberOfPage;
	
	public ReadableItem (int isbn, String title, int year, int pages) {
		this.isbn = isbn;
		this.title = title;
		this.yearOfRelease = year;
		this.numberOfPage = pages;
	}

	public int getISBN() {
		return isbn;
	}

	public void setISBN(int isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYearOfRelease() {
		return yearOfRelease;
	}

	public void setYearOfRelease(int yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	public int getNumberOfPage() {
		return numberOfPage;
	}

	public void setNumberOfPage(int numberOfPage) {
		this.numberOfPage = numberOfPage;
	}
}
