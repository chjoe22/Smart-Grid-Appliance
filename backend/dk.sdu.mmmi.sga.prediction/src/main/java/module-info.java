module dk.sdu.mmmi.sga.prediction {
    requires static lombok;
    requires spring.context;
    requires weka.stable;
    requires commonDB;
    requires dk.sdu.mmmi.sga.core;
    requires java.sql;
    requires spring.web;

    exports dk.sdu.mmmi.sga.prediction.controller;
    opens dk.sdu.mmmi.sga.prediction.controller to spring.core, spring.beans, spring.web, spring.context;
    opens dk.sdu.mmmi.sga.prediction.usecase to spring.core, spring.beans;
}