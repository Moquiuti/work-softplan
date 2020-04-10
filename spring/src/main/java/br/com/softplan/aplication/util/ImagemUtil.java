/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softplan.aplication.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

/**
 *
 * @author moquiuti
 */
public class ImagemUtil {

    public static String redimensionarLarge(String base64) throws IOException {
        String def = base64.split(",")[0];
        String b64 = base64.split(",")[1];

        BufferedImage image;
        byte[] imageByte;

        
        imageByte = Base64.getDecoder().decode(b64);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();

        BufferedImage out = ImagemUtil.scaleImage(image,
                BufferedImage.TYPE_INT_RGB, 125, 125);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(out, "JPG", os);

        return def + "," + Base64.getEncoder().encodeToString(os.toByteArray());
    }

    public static String redimensionar(String base64) throws IOException {
        String def = base64.split(",")[0];
        String b64 = base64.split(",")[1];

        BufferedImage image;
        byte[] imageByte;
        
        imageByte = Base64.getDecoder().decode(b64);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();

        BufferedImage out = ImagemUtil.scaleImage(image,
                BufferedImage.TYPE_INT_RGB, 500, 262);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(out, "JPG", os);

        return def + "," + Base64.getEncoder().encodeToString(os.toByteArray());
    }

    private static BufferedImage scaleImage(BufferedImage image, int imageType,
            int newWidth, int newHeight) {
        // Make sure the aspect ratio is maintained, so the image is not distorted
        double thumbRatio = (double) newWidth / (double) newHeight;
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double aspectRatio = (double) imageWidth / (double) imageHeight;

        if (thumbRatio < aspectRatio) {
            newHeight = (int) (newWidth / aspectRatio);
        } else {
            newWidth = (int) (newHeight * aspectRatio);
        }

        // Draw the scaled image
        BufferedImage newImage = new BufferedImage(newWidth, newHeight,
                imageType);
        Graphics2D graphics2D = newImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, newWidth, newHeight, null);

        return newImage;
    }

//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader("/home/moquiuti/image"));
//        Throwable var2 = null;
//        try {
//            String currentLine = reader.readLine();
//            System.out.println(redimensionarLarge(currentLine));
//        } catch (Exception var12) {
//            var2 = var12;
//            throw var12;
//        } finally {
//            if (var2 != null) {
//                try {
//                    reader.close();
//                } catch (IOException var11) {
//                    var2.addSuppressed(var11);
//                }
//            } else {
//                reader.close();
//            }
//
//        }
//    }
}
