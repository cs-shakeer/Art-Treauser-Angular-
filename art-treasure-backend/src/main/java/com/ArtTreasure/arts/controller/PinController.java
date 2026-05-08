package com.ArtTreasure.arts.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ArtTreasure.arts.Service.PinService;
import com.ArtTreasure.arts.entity.PinEntity;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/art/api/pins")
public class PinController {

    @Autowired
    private PinService pinService;

    @GetMapping
    public ResponseEntity<List<PinEntity>> getAllPins() {
        List<PinEntity> pins = pinService.getAllPins();
        return ResponseEntity.ok(pins);
    }

    @GetMapping("/{pinId}")
    public ResponseEntity<PinEntity> getPinById(@PathVariable Long pinId) {
        PinEntity pin = pinService.getPinById(pinId);
        if (pin != null) {
            return ResponseEntity.ok(pin);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PinEntity>> getPinsByUserId(@PathVariable Long userId) {
        List<PinEntity> pins = pinService.getPinsByUserId(userId);
        return ResponseEntity.ok(pins);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPin(@RequestBody PinEntity pin) {
        String result = pinService.createPins(pin);
        if ("success".equals(result)) {
            return ResponseEntity.ok("Pin created successfully! 🎨");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create pin.");
    }

    @PutMapping("/update")
    public ResponseEntity<PinEntity> updatePin(@RequestBody PinEntity pin) {
        PinEntity updated = pinService.updatePin(pin);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete/{pinId}")
    public ResponseEntity<String> deletePin(@PathVariable Long pinId) {
        String result = pinService.deletePinById(pinId);
        if ("success".equals(result)) {
            return ResponseEntity.ok("Pin deleted successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pin not found.");
    }
}
