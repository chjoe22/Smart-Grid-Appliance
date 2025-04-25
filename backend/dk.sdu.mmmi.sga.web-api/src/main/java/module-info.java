module web.api {
    requires dk.sdu.mmmi.sga.core;
    requires jakarta.annotation;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;
    uses dk.sdu.mmmi.sga.core.services.DataCollection;
    opens dk.sdu.mmmi.sga.web;
}