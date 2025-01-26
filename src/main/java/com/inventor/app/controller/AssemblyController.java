package com.inventor.app.controller;

import com.inventor.app.service.AssemblyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AssemblyController {

    @Autowired
    private AssemblyService assemblyService;

    @PostMapping("/open-assembly")
    public ResponseEntity<String> openAssembly(@RequestBody Map<String, String> request) {
        String assemblyPath = request.getOrDefault("assemblyPath", "C:\\path\\to\\your\\assembly.iam");
        assemblyService.openAssembly(assemblyPath);
        return ResponseEntity.ok("{\"message\": \"Assembly opened successfully.\"}");
    }

    @PostMapping("/close-assembly")
    public ResponseEntity<String> closeAssembly() {
        assemblyService.closeAssembly();
        return ResponseEntity.ok("{\"message\": \"Assembly closed successfully.\"}");
    }

    @GetMapping("/assembly-status")
    public ResponseEntity<Map<String, Boolean>> getAssemblyStatus() {
        return ResponseEntity.ok(Collections.singletonMap("isAssemblyOpen", assemblyService.isAssemblyOpen()));
    }
}
