module dk.sdu.mmmi.sga.core {
    requires jakarta.persistence;
    requires static lombok;
    exports dk.sdu.mmmi.sga.core.services;
    exports dk.sdu.mmmi.sga.core.dto;
}