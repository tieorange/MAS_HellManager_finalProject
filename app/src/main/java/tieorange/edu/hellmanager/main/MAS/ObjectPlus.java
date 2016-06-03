/**
 * Created on 2007-04-11
 * 
 */
package tieorange.edu.hellmanager.main.MAS;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

/**
 * @author MariuszAdmin
 *
 */
public class ObjectPlus implements Serializable {
	protected static Hashtable extents = new Hashtable();

	/**
	 * Konstruktor.
	 */
	public ObjectPlus() {
		Vector extent = null;
		Class theClass = this.getClass();
		
		if(extents.containsKey(theClass)) {
			// Ekstensja tej klasy istnieje w kolekcji ekstensji
			extent = (Vector) extents.get(theClass);
		}
		else {
			// Ekstensji tej klasy jeszcze nie ma -> dodaj ja
			extent = new Vector();
			extents.put(theClass, extent);
		}
		
		extent.add(this);
	}
	
	/**
	 * Writes all extents to a given stream (uses serialization).
	 * Class method.
	 * @throws IOException 
	 */
	public static void writeExtents(ObjectOutputStream stream) throws IOException {
		stream.writeObject(extents);
	}

	/**
	 * Reads all extents from a given stream (uses serialization).
	 * Class method.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		extents = (Hashtable) stream.readObject();
	}	
	
	/**
	 * Shows a given extent.
	 * Metoda klasowa.
	 * @throws Exception 
	 */
	public static void showExtent(Class klasa) throws Exception {
		Vector extent = null;
		
		if(extents.containsKey(klasa)) {
			// Ekstensja tej klasy istnieje w kolekcji ekstensji
			extent = (Vector) extents.get(klasa);
		}
		else {
			throw new Exception("Unknown class " + klasa);
		}
		
		System.out.println("Extent of the class: " + klasa.getSimpleName());

		for(Object obiekt : extent) {
			System.out.println(obiekt);
		}
	}
	
}
