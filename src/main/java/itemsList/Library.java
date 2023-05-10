package itemsList;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;


public class Library {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Library library = new Library();
		
		Book b1 = new Book(123, "The Fellowship of the Ring", 1954, 423, "J. R. R. Tolkien", "Fantasy");
		Book b2 = new Book(234, "Two Towers", 1954, 423, "J. R. R. Tolkien", "Fantasy");
		Book b3 = new Book(190, "The way of the Doge", 2023, 999, "Doge", "Life");
		Book b4 = new Book(235, "Two Towers", 1954, 423, "J. R. R. Tolkien", "Fantasy");
		Book b5 = new Book(235, "The Hobbit", 1954, 300, "J. R. R. Tolkien", "Fantasy");
		Magazine m1 = new Magazine(456, "True Tolkien Fans", 2023, 40, Periodicity.MENSILE);
		
		library.addToLibrary(b1);
		library.addToLibrary(b2);
		library.addToLibrary(b3);
		library.addToLibrary(b4);
		library.addToLibrary(b5); // questo fallirà perché esiste già un oggetto con questo ISBN
		library.addToLibrary(m1);
		library.removeFromLibrary(235); // esempio rimozione
//		library.removeFromLibrary(236); // esempio rimozione fallita per codice non trovato
//		library.findByYear(1954); // esempio ricerca per anno con successo
//		library.findByYear(2000); // esempio ricerca per anno fallita
//		library.findByAuthor("J. R. R. Tolkien"); // esempio ricerca per autore con successo
//		library.findByAuthor("Doge"); // esempio ricerca per autore fallita
		// Non sono soddisfatto di questa ricerca, bisogna scrivere il nome esatto ovviamente, se mi rimane
		// tempo la aggiusto
		
		try {
			library.saveLibrary();
			library.loadLibrary();
			
			System.out.println("\nSearching code 234: ");
			System.out.println(library.findByCode(234));
			
			System.out.println("\nSearch by author J. R. R. Tolkien: ");
			List<Book> searchTolkien = library.findByAuthor("J. R. R. Tolkien");
			searchTolkien.forEach(element -> System.out.println(element.toString()));
			
			System.out.println("\nSearch by year 2023: ");
			List<ReadableItem> recentItems = library.findByYear(2023);
			recentItems.forEach(element -> System.out.println(element.toString()));
			
		} catch (IOException e){
			System.out.println("There was an error while trying reading/writing: " + e);
		}
	}
	
	private static final String PATH = "c:\\Users\\brink\\Desktop\\Epicode\\Backend\\U1\\w2d5\\src\\library.txt";
	
	public List<ReadableItem> test() {
		return itemList.values().stream().collect(Collectors.toList());
	}
	
	private Map<Integer, ReadableItem> itemList;
	
	public Library() {
		this.itemList = new HashMap<Integer, ReadableItem>();
	}
	
	public void addToLibrary(ReadableItem item) {
		// Verifico che non esista già un item con l'ISBN che ci viene dato
		if (findByCode(item.getISBN()) == null) {
			itemList.put(item.getISBN(), item);
			System.out.println("Added element: " + item.getTitle());			
		} else {
			System.out.println("Could not add " + item.getTitle() + ", there is already an item in the library with the given ISBN!");
		}
		
	}
	
	public void removeFromLibrary (int code) {
		ReadableItem removedItem = itemList.remove(code);

		if (removedItem != null) {
			System.out.println("Removed Element: " + removedItem.toString());
		} else {
			System.out.println("Could not delete item as no item in Library matches given ISBN");
		}
	}
	
	public ReadableItem findByCode (int code) {
		ReadableItem foundItem = itemList.get(code);
		//Initial sysouts to test methods
//		if (foundItem != null) {
//			System.out.println("Found element with given code: " + foundItem);
//		} else {
//			System.out.println("No item found with given ISBN in Library.");
//		}
		return foundItem;
	}

	public List<ReadableItem> findByYear(int year) {
		List<ReadableItem> list = itemList.values().stream().filter(element -> year == element.getYearOfRelease()).collect(Collectors.toList());
		//Initial sysouts to test methods
//		 if (list.size() > 0) {
//			System.out.println("Items released in given year:");
//		 	list.forEach(elem -> System.out.println(elem.toString()));
//		 } else {
//			 System.out.println("No item found in Library with release year given.");
//		 }
		return list;
	}
	
	public List<Book> findByAuthor(String author) {
		List<Book> list = itemList.values().stream().filter(element -> element instanceof Book)
				.map(elem -> (Book) elem).filter(element -> author.equals(element.getAuthor()) ).collect(Collectors.toList());
		
		return list;
	}
	
	public void saveLibrary() throws IOException {
		String initialString = "" ;
		
		for (ReadableItem elem : itemList.values()) {
			if (initialString.length() != 0) {
				initialString += "#";
			}
			if (elem instanceof Book) {
				initialString += Book.toFile((Book) elem);
			} else if (elem instanceof Magazine){
				initialString += Magazine.toFile((Magazine) elem);
			}
		}
		
		File f = new File(PATH);
		FileUtils.writeStringToFile(f, initialString, "UTF-8");
	}
	
	public void loadLibrary() throws IOException {
		this.itemList.clear();
		
		File f = new File(PATH);
		String initialString = FileUtils.readFileToString(f, "UTF-8");
		List<String> stringToArray = Arrays.asList(initialString.split("#"));
		
		for (String elem : stringToArray) {
			ReadableItem newItem = null;
			if (elem.startsWith("book")) {
				newItem = Book.fromFile(elem);
			} 
			else if (elem.startsWith("magazine")) {
				newItem = Magazine.fromFile(elem);
			} 
			this.itemList.put(newItem.getISBN(), newItem);
		}
	}
}
