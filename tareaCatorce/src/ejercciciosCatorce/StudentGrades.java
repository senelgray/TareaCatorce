package ejercciciosCatorce;
import java.util.*;

public class StudentGrades {
	private Map<String, List<Double>> grados;

    public StudentGrades() {
    	grados = new HashMap<>();
    }

    public void addGrades(String estudianteNombre, List<Double> studentGrades) {
    	grados.put(estudianteNombre, studentGrades);
    }

    public List<Double> getGrades(String estudianteNombre) {
        return grados.getOrDefault(estudianteNombre, new ArrayList<>());
    }

    public double calculateAverageGrade(String estudianteNombre) {
        List<Double> studentGrades = grados.get(estudianteNombre);
        if (studentGrades == null || studentGrades.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (double grade : studentGrades) {
            sum += grade;
        }
        return sum / studentGrades.size();

}

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    StudentGrades studentGrades = new StudentGrades();
    
    System.out.print("Ingrese el nombre del estudiante: ");
    String estudianteNombre = scanner.nextLine();

    System.out.print("Ingrese las calificaciones separadas por espacios: ");
    String calif = scanner.nextLine();
    
    List<Double> gradosList = new ArrayList<>();
    String[] gradosArray = calif.split(" ");
    for (String grado : gradosArray) {
        gradosList.add(Double.parseDouble(grado));
    }
    studentGrades.addGrades(estudianteNombre, gradosList);
    double averageGrade = studentGrades.calculateAverageGrade(estudianteNombre);

    System.out.println("El promedio de calificaciones de " + estudianteNombre + " es: " + averageGrade);
}
}