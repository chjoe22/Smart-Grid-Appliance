import dk.sdu.mmmi.sga.core.services.DataCollection;

module dk.sdu.mmmi.sga.greenhouseDB {
    requires java.sql;
    requires dk.sdu.mmmi.sga.core;
    provides dk.sdu.mmmi.sga.core.services.DataCollection with dk.sdu.mmmi.sga.database.collectors.AirTempCollector;
}