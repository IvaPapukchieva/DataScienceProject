import java.io.FileNotFoundException;
import java.util.stream.IntStream;

public class FilterByStudentID extends GraduatingGradesLoader {


    public  FilterByStudentID() throws FileNotFoundException {
        super();

    }

    public double[] getStudentGradesById(int id) {
        // Find the index of the student ID
        return IntStream.range(0, StudentID.length)
                .filter(i -> StudentID[i] == id)
                .mapToObj(i -> allStudents[i])
                .findFirst()
                .orElse(new double[0]);
    }


}
