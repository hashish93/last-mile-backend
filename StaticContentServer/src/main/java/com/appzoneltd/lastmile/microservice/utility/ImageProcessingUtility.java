package com.appzoneltd.lastmile.microservice.utility;

import net.coobird.thumbnailator.Thumbnails;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageProcessingUtility {

	public static BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type) {
		BufferedImage resizedImage = new BufferedImage(width, height, type);

		try {
			resizedImage = Thumbnails.of(resizedImage).scale(2.0).asBufferedImage();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();

		return resizedImage;
	}

	public static BufferedImage resizeImage(BufferedImage originalImage, double scale) {
		BufferedImage resizedImage = null;

		try {
			resizedImage = Thumbnails.of(originalImage).scale(scale).asBufferedImage();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return resizedImage;
	}

}
