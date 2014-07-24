package com.kwk.image;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageIoTest {

    @Test
    public void basicTest() throws IOException {
        URL url = this.getClass().getResource("/image/1.jpg");
        BufferedImage bufferedImage = ImageIO.read(url);
        ImageIO.write(bufferedImage, "png", new File("C:/a.png"));
    }
}
