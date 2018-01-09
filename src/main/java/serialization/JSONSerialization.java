package serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONSerialization<T> implements Serialization<T>{

    private ObjectMapper objectMapper = new ObjectMapper();

    public JSONSerialization() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    public void toFile(List<T> list, String filename) throws IOException{
        objectMapper.writeValue(new File(filename), list);

    }

    @Override
    public List<T> listFromFile(String fileName,Class<T> tClass) throws IOException{
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(List.class, tClass);
        return objectMapper.readValue(new File(fileName), collectionType);
    }
}
