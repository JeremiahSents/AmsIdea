package dev.sentomero.amsIdea.service;

import dev.sentomero.amsIdea.repository.KpClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class SerialNumberService {

    private static final int STARTING_SERIAL_NUMBER = 5000;
    private static final int MAX_SERIAL_NUMBER = 99999;

    @Autowired
    private KpClientRepository kpClientRepository;

    @Autowired
    public SerialNumberService(KpClientRepository kpClientRepository) {
        this.kpClientRepository = kpClientRepository;
    }

    public synchronized String generateSerialNumber() {
        // Get the maximum serial number from the repository
        Optional<Long> maxSerialNumber = kpClientRepository.findMaxSerialNumber();

        // Generate the next serial number
        long nextSerialNumber = maxSerialNumber.map(num -> num + 1).orElse((long) STARTING_SERIAL_NUMBER);

        // Check if the serial number exceeds the max limit
        if (nextSerialNumber > MAX_SERIAL_NUMBER) {
            throw new RuntimeException("Maximum serial number reached. Cannot generate more serial numbers.");
        }

        // Format the serial number to be always 5 digits (e.g., 50001, 50002, etc.)
        return String.format("%05d", nextSerialNumber);
    }
}

