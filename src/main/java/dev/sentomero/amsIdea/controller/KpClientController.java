package dev.sentomero.amsIdea.controller;


import dev.sentomero.amsIdea.dto.KpClientDto;
import dev.sentomero.amsIdea.model.KpClient;
import dev.sentomero.amsIdea.service.KpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class KpClientController {

    private final KpClientService kpClientService;

    @Autowired
    public KpClientController(KpClientService kpClientService){
        this.kpClientService = kpClientService;
    }

    @PostMapping("/clients/create")
    public ResponseEntity<KpClient> createKpClient(@RequestBody KpClientDto kpClientDto){
        KpClient savedClient = kpClientService.saveClient(kpClientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    public List<KpClient> getAllClients() {
        return kpClientService.getAllClients();
    }

    public KpClient getKpClientById(int id) {
        return kpClientService.getKpClientById(id);
    }

    public KpClient updateKpClient(int id, KpClientDto kpClientDto) {
        return kpClientService.updateKpClient(id, kpClientDto);
    }

    public void deleteKpClient(int id) {
        kpClientService.deleteKpClient(id);
    }

    public KpClient getClientBySerialNumber(long serialNumber) {
        return kpClientService.getKpClientBySerialNumber(serialNumber);
    }
}
