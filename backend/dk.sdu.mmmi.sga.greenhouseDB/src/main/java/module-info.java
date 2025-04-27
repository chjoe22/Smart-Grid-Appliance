import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.database.usecase.*;

module dk.sdu.mmmi.sga.greenhouseDB {
    requires java.sql;
    requires dk.sdu.mmmi.sga.core;
    requires spring.context;
    requires static lombok;
    requires spring.web;
    provides DataCollection with AirTempCollector, HumidityCollector, OutDoorLightCollector, OutDoorTempCollector, CO2Collector, MaxOutDoorLightCollector;
}