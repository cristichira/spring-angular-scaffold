package com.servix.springangularscaffold.service.impl;

import com.servix.springangularscaffold.dto.FileSaveDto;
import com.servix.springangularscaffold.exception.FileStorageException;
import com.servix.springangularscaffold.service.FileProcessingService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class FileProcessingServiceImpl implements FileProcessingService {

    @Value("${storage.picture-resizing.max-size}")
    private int maxSize;

    @Override
    public void process(FileSaveDto fileSaveDto) {
        resize(fileSaveDto);
    }

    private void resize(FileSaveDto fileSaveDto) {
        if (fileSaveDto.getContentType().startsWith("image")) {
            try {
                final BufferedImage image = ImageIO.read(new ByteArrayInputStream(fileSaveDto.getContent()));

                if (image.getWidth() > maxSize || image.getHeight() > maxSize) {
                    final double scaleFactor = Math.min((double) maxSize / (double) image.getWidth(), (double) maxSize / (double) image.getHeight());

                    final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                    final String extension = fileSaveDto.getStorageFileName().substring(fileSaveDto.getStorageFileName().lastIndexOf(".") + 1);

                    Thumbnails.of(image)
                            .scale(scaleFactor)
                            .outputFormat(extension)
                            .toOutputStream(buffer);

                    byte[] content = buffer.toByteArray();
                    fileSaveDto.setContent(content);
                    fileSaveDto.setSize(content.length);
                }
            } catch (IOException e) {
                throw new FileStorageException("COULD_NOT_RESIZE");
            }
        }
    }
}
