module authentication {
    requires java.sql;
    requires dk.sdu.mmmi.sga.core;
    requires spring.context;
    requires static lombok;
    requires spring.web;
    requires spring.beans;
    requires spring.core;
    requires spring.webmvc;
    requires spring.security.crypto;
    requires spring.data.jpa;
    requires jakarta.persistence;
    requires spring.security.core;

    exports dk.sdu.mmmi.sga.auth.repository;
    exports dk.sdu.mmmi.sga.auth.entity;
    exports dk.sdu.mmmi.sga.auth.config;
    exports dk.sdu.mmmi.sga.auth.service;
    exports dk.sdu.mmmi.sga.auth.controller;

    opens dk.sdu.mmmi.sga.auth.repository to spring.core, spring.beans, spring.data.jpa;
    opens dk.sdu.mmmi.sga.auth.entity to spring.core, spring.beans, org.hibernate.orm.core;

}