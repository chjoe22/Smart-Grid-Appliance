import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.database.usecase.*;

module dk.sdu.mmmi.sga.greenhouseDB {
    requires dk.sdu.mmmi.sga.core;
    requires spring.context;
    requires static lombok;
    requires spring.web;
    requires spring.beans;
    requires spring.core;
    requires commonDB;
    requires jakarta.persistence;

    exports dk.sdu.mmmi.sga.database.usecase;
    exports dk.sdu.mmmi.sga.database.entity;
    exports dk.sdu.mmmi.sga.database.dto;
    exports dk.sdu.mmmi.sga.database.mapper;
    exports dk.sdu.mmmi.sga.database.controller;

    provides DataCollection with AirTempCollector, HumidityCollector, OutDoorLightCollector, OutDoorTempCollector, CO2Collector, MaxOutDoorLightCollector;
    opens dk.sdu.mmmi.sga.database.usecase to spring.core, spring.beans, spring.context;
    opens dk.sdu.mmmi.sga.database.config to spring.core, spring.beans, spring.context;
}