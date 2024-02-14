package org.acme.client;

import org.acme.client.entity.ClientEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface ClientRepository extends PanacheRepository<ClientEntity> {}
