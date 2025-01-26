package com.inventor.app.controller;

import com.inventor.app.service.AssemblyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
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

    @PostMapping("/change-parameters")
    public ResponseEntity<String> changeParameters(@RequestBody Map<String, Object> request) {
        String partFilePath = (String) request.getOrDefault("partFilePath", "C:\\path\\to\\your\\part.ipt");
        List<Map<String, Object>> parameters = (List<Map<String, Object>>) request.getOrDefault("parameters",
                Collections.emptyList());

        assemblyService.changeParameters(partFilePath, parameters);

        return ResponseEntity.ok("{\"message\": \"Parameters updated successfully.\"}");
    }

    @PostMapping("/suppress-component")
    public ResponseEntity<String> suppressComponent(@RequestBody Map<String, Object> request) {
        String assemblyFilePath = (String) request.getOrDefault("assemblyFilePath", "C:\\path\\to\\your\\assembly.iam");
        String componentName = (String) request.getOrDefault("componentName", "ComponentName");
        boolean suppress = (Boolean) request.getOrDefault("suppress", true);

        assemblyService.suppressComponent(assemblyFilePath, componentName, suppress);
        return ResponseEntity.ok("{\"message\": \"Component " + componentName + " "
                + (suppress ? "suppressed" : "unsuppressed") + " successfully.\"}");
    }

    @GetMapping("/assembly-status")
    public ResponseEntity<Map<String, Boolean>> getAssemblyStatus() {
        return ResponseEntity.ok(Collections.singletonMap("isAssemblyOpen", assemblyService.isAssemblyOpen()));
    }
}
