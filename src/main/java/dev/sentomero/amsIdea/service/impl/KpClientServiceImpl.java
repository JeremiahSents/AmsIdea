package dev.sentomero.amsIdea.service.impl;

import dev.sentomero.amsIdea.dto.KpClientDto;
import dev.sentomero.amsIdea.model.KpClient;
import dev.sentomero.amsIdea.repository.KpClientRepository;
import dev.sentomero.amsIdea.service.KpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KpClientServiceImpl implements KpClientService {

    private final KpClientRepository kpClientRepository;


    @Autowired
    public KpClientServiceImpl(KpClientRepository kpClientRepository) {
        this.kpClientRepository = kpClientRepository;
    }

    @Override
    public KpClient saveClient(KpClientDto kpClientDto) {
        KpClient kpClient = new KpClient();
        kpClient.setKpClientFName(kpClientDto.getKpClientFName());
        kpClient.setKpClientLName(kpClientDto.getKpClientLName());
        kpClient.setKpClientSerialNumber(kpClientDto.getKpClientSerialNumber());
        kpClient.setRegisteredBy(kpClientDto.getRegisteredBy());
        kpClient.setCategoryRegistered(kpClientDto.getCategoryRegistered());
        kpClient.setKpClientTimeAssigned(kpClientDto.getKpClientTimeAssigned());

        return kpClientRepository.save(kpClient);
    }

    @Override
    public List<KpClient> getAllClients() {
        List<KpClient> kpClients = kpClientRepository.findAll();
        return kpClients;
    }

    @Override
    public KpClient getKpClientById(int id) {
        return kpClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + id));
    }


    @Override
    public KpClient getKpClientBySerialNumber(long serialNumber) {
        KpClient client = kpClientRepository.findByKpClientSerialNumber(serialNumber);
        if (client == null) {
            throw new RuntimeException("Client not found with serial number: " + serialNumber);
        }
        return client;
    }

    @Override
    public KpClient updateKpClient(int id, KpClientDto client) {
        return null;
    }

    @Override
    public void deleteKpClient(int id) {

    }


    @Override
    public KpClient updateClient(int id, KpClientDto clientDto) {
        KpClient existingClient = kpClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + id));
        existingClient.setKpClientFName(clientDto.getKpClientFName());
        existingClient.setKpClientLName(clientDto.getKpClientLName());
        existingClient.setKpClientSerialNumber(clientDto.getKpClientSerialNumber());
        existingClient.setRegisteredBy(clientDto.getRegisteredBy());
        existingClient.setCategoryRegistered(clientDto.getCategoryRegistered());
        existingClient.setKpClientTimeAssigned(clientDto.getKpClientTimeAssigned());
        return kpClientRepository.save(existingClient);
    }

    @Override
    public void deleteClient(int id) {
        if (!kpClientRepository.existsById(id)) {
            throw new RuntimeException("Client not found with ID: " + id);
        }
        kpClientRepository.deleteById(id);
    }


}
