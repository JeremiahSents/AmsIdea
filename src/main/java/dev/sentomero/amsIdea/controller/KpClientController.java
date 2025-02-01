package dev.sentomero.amsIdea.controller;


import dev.sentomero.amsIdea.dto.KpClientDto;
import dev.sentomero.amsIdea.model.KpClient;
import dev.sentomero.amsIdea.service.KpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class KpClientController {

    private final KpClientService kpClientService;

    @Autowired
    public KpClientController(KpClientService kpClientService) {
        this.kpClientService = kpClientService;
    }

    @PostMapping("/create")
    public ResponseEntity<KpClientDto> createKpClient(@RequestBody KpClientDto kpClientDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kpClientService.saveClient(kpClientDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<KpClientDto>> getAllClients() {
        return ResponseEntity.ok(kpClientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KpClientDto> getKpClientById(@PathVariable int id) {
        return ResponseEntity.ok(kpClientService.getKpClientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KpClientDto> updateKpClient(@PathVariable int id, @RequestBody KpClientDto kpClientDto) {
        return ResponseEntity.ok(kpClientService.updateKpClient(id, kpClientDto));
    }

    @DeleteMapping("/delete/{id}")  //http://localhost:8080/clients/delete/{id}
    public ResponseEntity<Void> deleteKpClient(@PathVariable int id) {
        kpClientService.deleteKpClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<KpClientDto> getClientBySerialNumber(@PathVariable long serialNumber) {
        return ResponseEntity.ok(kpClientService.getKpClientBySerialNumber(serialNumber));
    }
}


