package dk.sdu.mmmi.sga.spring.service;

import dk.sdu.mmmi.sga.core.services.DataCollection;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class DataCollectorService {
    private final List<DataCollection<?>> dataCollectionList = new ArrayList<>();
    private final Map<String, List<?>> dataCollectionMap = new HashMap<>();

    public DataCollectorService(){
        for (DataCollection<?> dataCollection : getDataCollections()) {
            System.out.println("Loaded service: " + dataCollection.getClass().getName());
            dataCollectionList.add(dataCollection);
        }
        if (dataCollectionList.isEmpty()) {
            System.out.println("No services loaded.");
        }
    }
    @Scheduled(fixedRate = 100)
    public void refreshData(){
        System.out.println("Refreshing data...");
        runCollectors();
    }

    @PostConstruct
    public void run(){
        //runCollectors();
    }

    public void runCollectors(){
        for (DataCollection<?> dataCollection : dataCollectionList) {
            List<?> data = dataCollection.collect();
            dataCollectionMap.put(dataCollection.getName(), data);
            System.out.println("Updated " + dataCollection.getName() + ": " + data.size() + " entries");
        }
    }

    private List<DataCollection<?>> getDataCollections() {
        List<DataCollection<?>> collectors = ServiceLoader.load(DataCollection.class).stream()
                .map(provider -> {
                    DataCollection<?> collector = provider.get();
                    System.out.println("Found collector: " + collector.getClass().getName());
                    return collector;
                })
                .collect(toList());

        if (collectors.isEmpty()) {
            System.out.println("No DataCollection implementations found!");
        }

        return collectors;
    }

    public Map<String, List<?>> getDataCollection() {
        return dataCollectionMap;
    }

    public List<?> getDataCollection(String name) {
        return dataCollectionMap.getOrDefault(name, List.of());
    }

}
