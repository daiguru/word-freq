package org.sample.wordfreq.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Parses the lines of a file and return as a list of {@link java.nio.file.Path}
 */
@Component
public class FileReader {

    @Value("${seed.file.location}")
    private String seedFile;

    public List<Path> getFiles() {
        try {
            Path path = Paths.get(seedFile);
            try (Stream<String> lines = Files.lines(path)) {
                return lines.map(line->createRelativePath(line)).filter(relativePath->Objects.nonNull(relativePath)).collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to read seedfile in location:"+seedFile);
        }
    }

    private Path createRelativePath(String path) {
        String seedDirectory = new File(seedFile).getParent();
        try {
            return Paths.get(new File(seedDirectory.concat(path)).getCanonicalPath());
        } catch (IOException e) {
            //Alert user that creation of relative path failed for the input
        }
        return null;
    }
}
