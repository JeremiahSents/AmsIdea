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
import java.util.stream.Collectors;

@Service
public class KpClientServiceImpl implements KpClientService {

    private final KpClientRepository kpClientRepository;
    private final AmsUserRepository amsUserRepository;
    private final CategoryRepository categoryRepository;
    private final SerialNumberService serialNumberService;

    @Autowired
    public KpClientServiceImpl(KpClientRepository kpClientRepository,
                               AmsUserRepository amsUserRepository,
                               CategoryRepository categoryRepository,
                               SerialNumberService serialNumberService) {
        this.kpClientRepository = kpClientRepository;
        this.amsUserRepository = amsUserRepository;
        this.categoryRepository = categoryRepository;
        this.serialNumberService = serialNumberService;
    }

    private KpClientDto convertToDto(KpClient kpClient) {
        KpClientDto kpClientDto = new KpClientDto();

        // Set the fields of KpClientDto
        kpClientDto.setKpClientFName(kpClient.getKpClientFName());
        kpClientDto.setKpClientLName(kpClient.getKpClientLName());

        // For the registeredBy (AmsUser), only set the name or ID (assuming you want the name)
        AmsUser registeredBy = kpClient.getRegisteredBy();
        if (registeredBy != null) {
            kpClientDto.setRegisteredBy(registeredBy.getId()); // Assuming there is a getId() method for user ID
        }

        // For categoryRegistered (Category), only set the name
        Category category = kpClient.getCategoryRegistered();
        if (category != null) {
            kpClientDto.setCategoryRegistered(category.getName()); // Assuming there is a getName() method
        }

        // Set other fields
        kpClientDto.setKpClientTimeAssigned(kpClient.getKpClientTimeAssigned());

        return kpClientDto;
    }



    @Override
    public KpClientDto saveClient(KpClientDto kpClientDto) {
        // Remove the serial number from the DTO, so it's not expected
        KpClient kpClient = new KpClient();

        // Generate and set the serial number
        kpClient.setKpClientSerialNumber(Long.parseLong(serialNumberService.generateSerialNumber()));

        // Set the time assigned
        kpClient.setKpClientTimeAssigned(LocalDateTime.now());

        // Find and set the registered user
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

        // Save and return the client
        return convertToDto(kpClientRepository.save(kpClient));
    }

    @Override
    public List<KpClientDto> getAllClients() {
        return kpClientRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public KpClientDto getKpClientById(int id) {
        KpClient kpClient = kpClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + id));
        return convertToDto(kpClient);
    }

    @Override
    public KpClientDto getKpClientBySerialNumber(long serialNumber) {
        KpClient client = kpClientRepository.findByKpClientSerialNumber(serialNumber);
        if (client == null) {
            throw new RuntimeException("Client not found with serial number: " + serialNumber);
        }
        return convertToDto(client);
    }

    @Override
    public KpClientDto updateKpClient(int id, KpClientDto clientDto) {
        KpClient existingClient = kpClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + id));

        existingClient.setKpClientFName(clientDto.getKpClientFName());
        existingClient.setKpClientLName(clientDto.getKpClientLName());
        existingClient.setKpClientTimeAssigned(clientDto.getKpClientTimeAssigned());

        return convertToDto(kpClientRepository.save(existingClient));
    }

    @Override
    public void deleteKpClient(int id) {
        KpClient existingClient = kpClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + id));
        kpClientRepository.delete(existingClient);
    }
}