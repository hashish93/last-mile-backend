package com.appzoneltd.lastmile.microservice.filter.impl;

import com.appzoneltd.lastmile.microservice.exception.FileFilterException;
import com.appzoneltd.lastmile.microservice.filter.FileFilter;
import com.appzoneltd.lastmile.microservice.model.LastMileFile;
import com.appzoneltd.lastmile.microservice.utility.ImageProcessingUtility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResizeImageFilter extends FileFilter {

    @Override
    public void execute(LastMileFile lastMileFile) throws FileFilterException {
        try {


            BufferedImage originalImage = ImageIO.read(new File(lastMileFile.getFilePhysicalPath()));

            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            //TODO convert to small
            BufferedImage smallImage = ImageProcessingUtility.resizeImage(originalImage, 0.10);
//				.resizeImage(originalImage, 50, 50, type);
            ImageIO.write(smallImage, lastMileFile.getExtension(), new File(lastMileFile.getFilePhysicalPath() + "-small"));

            //TODO convert to medium
            BufferedImage mediumImage = ImageProcessingUtility.resizeImage(originalImage, 0.25);
//				.resizeImage(originalImage, 100, 100, type);
            ImageIO.write(mediumImage, lastMileFile.getExtension(), new File(lastMileFile.getFilePhysicalPath() + "-medium"));

            //TODO convert to large
            BufferedImage largeImage = ImageProcessingUtility.resizeImage(originalImage, 0.50);
//				.resizeImage(originalImage, 200, 200, type);
            ImageIO.write(largeImage, lastMileFile.getExtension(), new File(lastMileFile.getFilePhysicalPath() + "-large"));

        } catch (IOException e) {
            throw new FileFilterException(e);


        }


    }

}
