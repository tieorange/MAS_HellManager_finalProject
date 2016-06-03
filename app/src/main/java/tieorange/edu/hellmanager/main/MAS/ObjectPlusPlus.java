/**
 * Created on 2007-04-22
 * 
 */
package tieorange.edu.hellmanager.main.MAS;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.TreeMap;
import java.util.Vector;


/**
 * Klasa ulatwiajaca zarzadzanie asocjacjami (powiazaniami). Poniewaz dziedziczy z ObjectPlus ulatiwa takze zarzadzanie ekstensja. 
 * @author MariuszAdmin
 *
 */
public class ObjectPlusPlus extends ObjectPlus implements Serializable {
	/**
	 * Przechowuje informacje o wszystkich powiazaniach tego obiektu.
	 */
	protected Hashtable<String, HashMap<Object, ObjectPlusPlus>> links = new Hashtable<String, HashMap<Object, ObjectPlusPlus>>();
	
	/**
	 * Przechowuje informacje o wszystkich czesciach powiazanych z ktorymkolwiek z obiektow.
	 */
	protected static HashSet<ObjectPlusPlus> allParts = new HashSet<ObjectPlusPlus>(); 
	
	/**
	 * Konstruktor.
	 *
	 */
	public ObjectPlusPlus() {
		super();		
	}

	private void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier, int counter) {
		HashMap<Object, ObjectPlusPlus> objectLink;

		// Zabezpieczenie dla tworzenia polaczenia zwrotnego
		if(counter < 1) {
			return;
		}
		
		// Znajdz kolekcje powiazan dla tej roli
		if(links.containsKey(roleName)) {
			// Pobierz te powiazania
			objectLink = links.get(roleName);
		}
		else {
			// Brak powiazan dla takiej roli ==> utworz
			objectLink = new HashMap<Object, ObjectPlusPlus>(); 
			links.put(roleName, objectLink);	
		}
		
		// Sprawdz czy powiazanie juz istnieje?
		// Jezeli tak to zignoruj dodawanie
		if(!objectLink.containsKey(qualifier)) {
			// Dodaj powiazanie dla tego obiektu
			objectLink.put(qualifier, targetObject);
			
			// Dodaj powiazanie zwrotne
			targetObject.addLink(reverseRoleName, roleName, this, this, counter - 1);
		}
	}
	
	/**
	 * Tworzy nowe powi�zanie do podanego obiektu (ewentualnie w ramach asocjacji kwalifikowanej).
	 * @param roleName
	 * @param reverseRoleName
	 * @param targetObject
	 * @param qualifier Jezeli rozny od null to tworzona jest asocjacja kwalifikowana.
	 */
	public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier) {
		addLink(roleName, reverseRoleName, targetObject, qualifier, 2);		
	}
	
	/**
	 * Tworzy nowe powi�zanie do podanego obiektu (jako zwyk�� asocjacj� binarn�. NIE jako kwalifikowan�).
	 * @param roleName
	 * @param reverseRoleName
	 * @param targetObject
	 */
	public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject) {
		addLink(roleName, reverseRoleName, targetObject, targetObject);		
	}

	/**
	 * Dodaje informacje o powiazaniu z czescia (jako kompozycja).
	 * Sprawdza czy dodawana czesc nie jest juz polaczona z caloscia. 
	 * @param roleName
	 * @param reverseRoleName
	 * @param partObject
	 * @throws Exception
	 */
	public void addPart(String roleName, String reverseRoleName, ObjectPlusPlus partObject) throws Exception {
		// Sprawdz czy ta czesc juz gdzies nie wystepuje
		if(allParts.contains(partObject)) {
			throw new Exception("Ta czesc jest ju� powiazana z jakas caloscia!");
		}
		
		addLink(roleName, reverseRoleName, partObject);
		
		// Zapamietaj dodanie obiektu jako czesci
		allParts.add(partObject);
	}	
	
	/**
	 * Zwraca tablice zawierajaca docelowe obiekty dla podanej nazwy roli.
	 * @param roleName
	 * @return
	 * @throws Exception
	 */
	public ObjectPlusPlus[] getLinks(String roleName) throws Exception {
		HashMap<Object, ObjectPlusPlus> objectLinks;

		if(!links.containsKey(roleName)) {
			// Brak powiazan dla tej roli
			throw new Exception("Brak powiazan dla roli: " + roleName);
		}
		
		objectLinks = links.get(roleName);
		
		return (ObjectPlusPlus[]) objectLinks.values().toArray(new ObjectPlusPlus[0]);
	}
	
	/**
	 * Informuje czy istnieja powiazania dla podanej nazwy roli.
	 * @param roleName
	 * @return false gdy brak nazwy roli lub gdy liczba powiazan dla tej roli jest 0.
	 */
	public boolean areLinks(String roleName) {
		if(!links.containsKey(roleName)) {
			return false;
		}

		HashMap<Object, ObjectPlusPlus> objectLinks = links.get(roleName);
		return objectLinks.size() > 0;
	}
	
	/**
	 * Wyswietla powiazania (dla podanej nazwy roli) na podanym strumieniu.
	 * @param roleName
	 * @param stream
	 * @throws Exception
	 */
	public void showLinks(String roleName, PrintStream stream) throws Exception {
		HashMap<Object, ObjectPlusPlus> objectLink;

		if(!links.containsKey(roleName)) {
			// Brak powiazan dla tej roli
			throw new Exception("No links for the role: " + roleName);
		}
		
		objectLink = links.get(roleName);
		
		Collection col = objectLink.values();

		stream.println(this.toString() + " (" + this.getClass().getSimpleName() + ") links for the '" + roleName + "' role:");
		
		for(Object obj : col) {
			stream.println("   " + obj);
		}
	}

	/**
	 * Wyswietla wszystkie powiazania danego obiektu na podanym strumieniu.
	 * @param stream
	 * @throws Exception
	 */
	public void showLinks(PrintStream stream) throws Exception {
		// Dla kazdego klucza
		for(String klucz : links.keySet()) {
			showLinks(klucz, stream);
		}
	}
	
	/**
	 * Wyswietla wszystkie powiazania wszystkich obiektow na podanym strumieniu.
	 * @param theClass
	 * @throws Exception
	 */
	public static void showAllLinks(Class theClass, PrintStream stream) throws Exception {
		Vector extent = null;
		
		if(extents.containsKey(theClass)) {
			// Ekstensja tej klasy istnieje w kolekcji ekstensji
			extent = (Vector) extents.get(theClass);
		}
		else {
			throw new Exception("Unknown class " + theClass);
		}
		
		System.out.println("Powiazania ekstensji klasy: " + theClass.getSimpleName());

		for(Object obiekt : extent) {
			stream.println("Obiekt: " + obiekt);
			((ObjectPlusPlus) obiekt).showLinks(stream);
		}
	}
	
	
	/**
	 * Zwraca obiekt odnaleziony na podstawie kwalifikatora (dla podanej nazwy roli).
	 * Dziala w oparciu o asocjacje kwalifikowana.
	 * @param roleName
	 * @param qualifier
	 * @return
	 * @throws Exception
	 */
	public ObjectPlusPlus getLinkedObject(String roleName, Object qualifier) throws Exception {
		HashMap<Object, ObjectPlusPlus> objectLinks;

		if(!links.containsKey(roleName)) {
			// Brak powiazan dla tej roli
			throw new Exception("Brak powiazan dla roli: " + roleName);
		}
		
		objectLinks = links.get(roleName);
		if(!objectLinks.containsKey(qualifier)) {
			// Brak powiazan dla tej roli
			throw new Exception("Brak powiazania dla kwalifikatora: " + qualifier);
		}
		
		return objectLinks.get(qualifier);
	}

	/**
	 * Indicates if there is a link to a given object for a given role.
	 * returns false also if there is no info about the role.
	 * @param roleName
	 * @param targetObject
	 * @return
	 */
	public boolean isLink(String roleName, ObjectPlusPlus targetObject) {
		HashMap<Object, ObjectPlusPlus> objectLink;

		if(!links.containsKey(roleName)) {
			// No links for the role
			return false;
		}
		
		objectLink = links.get(roleName);
		if(objectLink.containsValue(targetObject)) {
			// There is a target object
			return true;
		}

		// No target object
		return false;		
	}
}
