package it.univaq.gamification.deployment.services;


import it.univaq.gamification.dsl.builders.lhs.PackageDescr;

import java.io.IOException;

public interface DeploymentService {

    void upsert(String gameId, PackageDescr... packageDescrs) throws IOException;

    void delete(String gameId, PackageDescr... packageDescrs) throws IOException;

}
