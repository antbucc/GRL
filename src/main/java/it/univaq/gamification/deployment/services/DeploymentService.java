package it.univaq.gamification.deployment.services;

import org.drools.compiler.lang.descr.PackageDescr;

import java.io.IOException;

public interface DeploymentService {

    void upsert(String gameId, PackageDescr... packageDescrs) throws IOException;

    void delete(String gameId, PackageDescr... packageDescrs) throws IOException;

}
