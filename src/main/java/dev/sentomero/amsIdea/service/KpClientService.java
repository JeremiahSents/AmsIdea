package dev.sentomero.amsIdea.service;

import dev.sentomero.amsIdea.dto.KpClientDto;
import dev.sentomero.amsIdea.model.KpClient;

import java.util.List;

public interface KpClientService {
    KpClientDto saveClient(KpClientDto client);

    List<KpClientDto> getAllClients();

    KpClientDto getKpClientById(int id);

    KpClientDto getKpClientBySerialNumber(long serialNumber);

    KpClientDto updateKpClient(int id, KpClientDto client);

    void deleteKpClient(int id);

}
