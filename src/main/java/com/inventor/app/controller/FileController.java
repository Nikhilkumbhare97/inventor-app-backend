package com.inventor.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Value("${file.source-path}")
    private String SOURCE_PATH;

    @Value("${file.destination-path}")
    private String DESTINATION_PATH;

    /**
     * API to Copy Folder to Destination
     */
    @PostMapping("/copy-folder/{folderName}")
    public ResponseEntity<String> copyFolder(@PathVariable("folderName") String folderName) {
        Path sourceDir = Paths.get(SOURCE_PATH, folderName);
        Path destDir = Paths.get(DESTINATION_PATH, folderName);

        if (!Files.exists(sourceDir) || !Files.isDirectory(sourceDir)) {
            return ResponseEntity.badRequest().body("Source folder does not exist.");
        }

        try {
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path targetPath = destDir.resolve(sourceDir.relativize(dir));
                    Files.createDirectories(targetPath);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Path targetPath = destDir.resolve(sourceDir.relativize(file));
                    Files.copy(file, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }
            });

            return ResponseEntity.ok("Folder copied successfully to " + destDir);

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error copying folder: " + e.getMessage());
        }
    }

    /**
     * API to Rename Folder in Destination
     */
    @PostMapping("/rename-folder/{oldFolderName}/{newFolderName}")
    public ResponseEntity<String> renameFolder(
            @PathVariable("oldFolderName") String oldFolderName,
            @PathVariable("newFolderName") String newFolderName) {

        Path oldPath = Paths.get(DESTINATION_PATH, oldFolderName);
        Path newPath = Paths.get(DESTINATION_PATH, newFolderName);

        if (!Files.exists(oldPath) || !Files.isDirectory(oldPath)) {
            return ResponseEntity.badRequest().body("Folder to rename does not exist.");
        }

        try {
            Files.move(oldPath, newPath, StandardCopyOption.REPLACE_EXISTING);
            return ResponseEntity.ok("Folder renamed successfully from " + oldFolderName + " to " + newFolderName);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error renaming folder: " + e.getMessage());
        }
    }
}
