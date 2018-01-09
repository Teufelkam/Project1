package serialization;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public class XMLSerialization<T> implements Serialization<T> {

    private XmlMapper xmlMapper = new XmlMapper();

    public XMLSerialization() {
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    public void toFile(List<T> list, String filename) throws IOException {
        xmlMapper.writeValue(new File(filename), list);
    }

    @Override
    public List<T> listFromFile(String fileName,Class<T> tClass) throws IOException{
        TypeFactory typeFactory = xmlMapper.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(List.class, tClass);
        return xmlMapper.readValue(new File(fileName), collectionType);
    }
}