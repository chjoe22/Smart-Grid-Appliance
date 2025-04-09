package sdu.mmmi.sga.spring.service;

import dk.sdu.mmmi.sga.core.services.DataCollection;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.util.*;

@Service
public class DataCollectorService {
    private final List<DataCollection<?>> dataCollectionList = new ArrayList<>();
    private final Map<String, List<?>> dataCollectionMap = new HashMap<>();

    public DataCollectorService(){
        //ServiceLoader.load(DataCollection.class).forEach(dataCollectionList::add);
        ServiceLoader<DataCollection> loader = ServiceLoader.load(DataCollection.class);
        for (DataCollection<?> service : loader) {
            System.out.println("Loaded service: " + service.getClass().getName());
            dataCollectionList.add(service);
        }
        if (dataCollectionList.isEmpty()) {
            System.out.println("No services loaded.");
        }
    }

    @PostConstruct
    public void runCollectors(){
        for (DataCollection<?> dataCollection : dataCollectionList) {
            System.out.println("Running collector: " + dataCollection.getName());
            List<?> data = dataCollection.collect();
            System.out.println("Collected data: " + data);
            dataCollectionMap.put(dataCollection.getName(), data);
        }
    }

    public Map<String, List<?>> getDataCollection() {
        return dataCollectionMap;
    }

    public List<?> getDataCollection(String name) {
        return dataCollectionMap.getOrDefault(name, List.of());
    }

}
