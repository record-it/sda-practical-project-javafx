module pl.sda {
    requires javafx.controls;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires lombok;
    requires java.logging;
    requires org.apache.logging.log4j.slf4j;
    requires org.apache.logging.log4j.core;
    requires org.slf4j;
    requires com.fasterxml.jackson.annotation;
    opens pl.sda;
    opens pl.sda.domain;
    opens pl.sda.api.swapi.repository;
    opens pl.sda.api.swapi.model;
}
