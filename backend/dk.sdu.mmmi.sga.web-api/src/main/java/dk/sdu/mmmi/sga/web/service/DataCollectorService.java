package dk.sdu.mmmi.sga.web.service;

import dk.sdu.mmmi.sga.core.services.DataCollection;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

@Service
public class DataCollectorService {
    private final List<DataCollection<?>> dataCollectionList;
    private final Map<String, List<?>> dataCollectionMap = new HashMap<>();

    public DataCollectorService(List<DataCollection<?>> dataCollectionsList) {
        this.dataCollectionList = dataCollectionsList;
        this.dataCollectionList.forEach(dataCollect ->
                System.out.println("beans: " + dataCollect.getClass().getName()));
        if (dataCollectionList.isEmpty()) {
            System.out.println("No beans.");
        }
    }
    @Scheduled(fixedRate = 10000)
    public void refreshData(){
        System.out.println("Refreshing data...");
        //runCollectors();
        runAllCollectors();
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

    public void runAllCollectors(){
        List<CompletableFuture<Void>> completableFutures = new ArrayList<>();
        for (DataCollection<?> dataCollection : dataCollectionList) {
            completableFutures.add(CompletableFuture.runAsync(() -> {
                List<?> data = dataCollection.collect();
                dataCollectionMap.put(dataCollection.getName(), data);
            }));
            System.out.println("Updated " + dataCollection.getName());
        }
        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).join();
    }

    public Map<String, List<?>> getDataCollection() {
        return dataCollectionMap;
    }

    public List<?> getDataCollection(String name) {
        return dataCollectionMap.getOrDefault(name, List.of());
    }

}
