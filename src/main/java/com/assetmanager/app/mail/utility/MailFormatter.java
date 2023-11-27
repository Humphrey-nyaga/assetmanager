package com.assetmanager.app.mail.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class MailFormatter {
    public String EmailTemplate() {
        try {
            String HTML_FILE_PATH = "/home/hum/projects/j2ee/assetmanager/src/main/java/com/assetmanager/app/mail/templates/index.html";

            Path path = Paths.get(HTML_FILE_PATH);
            return Files.lines(path)
                    .collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
