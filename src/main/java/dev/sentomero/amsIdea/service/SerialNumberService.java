package dev.sentomero.amsIdea.service;

import dev.sentomero.amsIdea.repository.KpClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class SerialNumberService {

    private static final int STARTING_SERIAL_NUMBER = 5000;

    @Autowired
    private  KpClientRepository kpClientRepository;

    @Autowired
    public SerialNumberService(KpClientRepository kpClientRepository) {
        this.kpClientRepository = kpClientRepository;
    }

    public  synchronized String generateSerialNumber() {
        Optional<Long> maxSerialNumber = kpClientRepository.findMaxSerialNumber();

        long nextSerialNumber = maxSerialNumber.map(num-> num + 1).orElse((long) STARTING_SERIAL_NUMBER);
        return String.format("%05d", nextSerialNumber);
    }
}
