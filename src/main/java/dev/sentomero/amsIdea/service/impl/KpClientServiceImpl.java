package dev.sentomero.amsIdea.service.impl;

import dev.sentomero.amsIdea.dto.KpClientDto;
import dev.sentomero.amsIdea.model.AmsUser;
import dev.sentomero.amsIdea.model.Category;
import dev.sentomero.amsIdea.model.KpClient;
import dev.sentomero.amsIdea.repository.AmsUserRepository;
import dev.sentomero.amsIdea.repository.CategoryRepository;
import dev.sentomero.amsIdea.repository.KpClientRepository;
import dev.sentomero.amsIdea.service.KpClientService;
import dev.sentomero.amsIdea.service.SerialNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KpClientServiceImpl implements KpClientService {

    private final AmsUserRepository amsUserRepository;
    private final KpClientRepository kpClientRepository;
    private final CategoryRepository categoryRepository;
    private final SerialNumberService serialNumberService;

    @Autowired
    public KpClientServiceImpl(KpClientRepository kpClientRepository, AmsUserRepository amsUserRepository, CategoryRepository categoryRepository, SerialNumberService serialNumberService) {
        this.kpClientRepository = kpClientRepository;
        this.amsUserRepository = amsUserRepository;
        this.categoryRepository = categoryRepository;
        this.serialNumberService = serialNumberService;
    }

    public void someMethod() {
        String serialNumber = serialNumberService.generateSerialNumber(); // ✅ Correct way to call
        System.out.println("Generated Serial Number: " + serialNumber);
    }

    @Override
    public KpClient saveClient(KpClientDto kpClientDto) {
        if (kpClientDto.getKpClientSerialNumber() == 0) {
            throw new IllegalArgumentException("Serial number is required");
        }
        KpClient kpClient = new KpClient();
        kpClient.setKpClientSerialNumber(Long.parseLong(serialNumberService.generateSerialNumber()));
        kpClient.setKpClientTimeAssigned(LocalDateTime.now());

        // Find and set the user
        AmsUser user = amsUserRepository.findById(kpClientDto.getRegisteredBy())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + kpClientDto.getRegisteredBy()));
        kpClient.setRegisteredBy(user);

        // Find and set the category
        Category category = categoryRepository.findByName(kpClientDto.getCategoryRegistered())
                .orElseThrow(() -> new RuntimeException("Category not found with name: " + kpClientDto.getCategoryRegistered()));
        kpClient.setCategoryRegistered(category);

        // Set other fields
        kpClient.setKpClientFName(kpClientDto.getKpClientFName());
        kpClient.setKpClientLName(kpClientDto.getKpClientLName());
        kpClient.setKpClientSerialNumber(kpClientDto.getKpClientSerialNumber());

        return kpClientRepository.save(kpClient);
    }

    @Override
    public List<KpClient> getAllClients() {
        return kpClientRepository.findAll();
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
    public KpClient updateKpClient(int id, KpClientDto clientDto) {
        KpClient existingClient = kpClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + id));
        return getKpClient(clientDto, existingClient);
    }

    @Override
    public void deleteKpClient(int id) {
        if (!kpClientRepository.existsById(id)) {
            throw new RuntimeException("Client not found with ID: " + id);
        }
        kpClientRepository.deleteById(id);
    }

    private KpClient getKpClient(KpClientDto clientDto, KpClient existingClient) {
        existingClient.setKpClientFName(clientDto.getKpClientFName());
        existingClient.setKpClientLName(clientDto.getKpClientLName());
        existingClient.setKpClientSerialNumber(clientDto.getKpClientSerialNumber());
        existingClient.setKpClientTimeAssigned(clientDto.getKpClientTimeAssigned());
        return kpClientRepository.save(existingClient);
    }
}
