module web {
    requires dk.sdu.mmmi.sga.core;
    requires jakarta.annotation;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;
    requires spring.core;
    requires spring.beans;
    requires spring.data.jpa;

    exports dk.sdu.mmmi.sga.web.controllers;
    exports dk.sdu.mmmi.sga.web.service;

    uses dk.sdu.mmmi.sga.core.services.DataCollection;
    opens dk.sdu.mmmi.sga.web to spring.core, spring.beans, spring.context;
    opens dk.sdu.mmmi.sga.web.controllers to spring.core, spring.beans, spring.context;
    opens dk.sdu.mmmi.sga.web.service to spring.core, spring.beans, spring.context;
}