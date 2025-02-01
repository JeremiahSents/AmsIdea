package dev.sentomero.amsIdea.controller;


import dev.sentomero.amsIdea.dto.KpClientDto;
import dev.sentomero.amsIdea.model.KpClient;
import dev.sentomero.amsIdea.service.KpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class KpClientController {

    private final KpClientService kpClientService;

    @Autowired
    public KpClientController(KpClientService kpClientService) {
        this.kpClientService = kpClientService;
    }

    // POST - Create new client
    @PostMapping("/create")  // http://localhost:8080/clients/create
    public ResponseEntity<KpClient> createKpClient(@RequestBody KpClientDto kpClientDto) {
        KpClient savedClient = kpClientService.saveClient(kpClientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    // GET - Get all clients
    @GetMapping("/")  // http://localhost:8080/clients/
    public List<KpClient> getAllClients() {
        return kpClientService.getAllClients();
    }

    // GET - Get client by ID
    @GetMapping("/{id}")  // http://localhost:8080/clients/{id}
    public KpClient getKpClientById(@PathVariable int id) {
        return kpClientService.getKpClientById(id);
    }

    // PUT - Update client
    @PutMapping("/{id}")  // http://localhost:8080/clients/{id}
    public KpClient updateKpClient(@PathVariable int id, @RequestBody KpClientDto kpClientDto) {
        return kpClientService.updateKpClient(id, kpClientDto);
    }

    // DELETE - Delete client
    @DeleteMapping("/{id}")  // http://localhost:8080/clients/{id}
    public void deleteKpClient(@PathVariable int id) {
        kpClientService.deleteKpClient(id);
    }

    // GET - Get client by serial number
    @GetMapping("/serial/{serialNumber}")  // http://localhost:8080/clients/serial/{serialNumber}
    public KpClient getClientBySerialNumber(@PathVariable long serialNumber) {
        return kpClientService.getKpClientBySerialNumber(serialNumber);
    }
}

