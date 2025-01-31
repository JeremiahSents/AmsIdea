package dev.sentomero.amsIdea.service;

import dev.sentomero.amsIdea.dto.KpClientDto;
import dev.sentomero.amsIdea.model.KpClient;

import java.util.List;

public interface KpClientService {
    KpClient saveClient(KpClientDto client);

    List<KpClient> getAllClients();

    KpClient getKpClientById(int id);

    KpClient getKpClientBySerialNumber(long serialNumber);

    KpClient updateKpClient(int id, KpClientDto client);

    void deleteKpClient(int id);

    KpClient updateClient(int id, KpClientDto clientDto);

    void deleteClient(int id);
}
