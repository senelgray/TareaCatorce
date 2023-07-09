package ejercciciosCatorce;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
public class UniqueWords {
	
	
	public static void main (String [] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese sus palabras separadas por espacio: ");
        String input = scanner.nextLine();

        String[] palabras = input.split(" ");

        Set<String> palabrasUnicas = new TreeSet<>();
        for (String palabra : palabras) {
            palabrasUnicas.add(palabra);
        }
        System.out.println("Palabras únicas en orden alfabético:");
        for(String palabrasUnica: palabrasUnicas) {
        	System.out.println(palabrasUnica);
        }

        }

	}


