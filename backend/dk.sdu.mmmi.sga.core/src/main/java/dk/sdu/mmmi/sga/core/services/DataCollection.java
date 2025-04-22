package dk.sdu.mmmi.sga.core.services;

import java.util.List;

public interface DataCollection<T> {
    public abstract String getName();
    public abstract List<T> collect();
}
