package serialization;

import java.io.IOException;
import java.util.List;

/**
 * Created by Roman on 02.01.2018.
 */
public interface Serialization<T> {
    void toFile(List<T> list, String fileName) throws IOException;
    List<T> listFromFile(String fileName, Class<T> classes) throws IOException;
}
