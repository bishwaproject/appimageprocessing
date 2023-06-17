package com.bishwa;

import java.awt.image.BufferedImage;

public class ImageProcessor implements Runnable {
	private BufferedImage image;
	private int startY;
	private int endY;

	public ImageProcessor(BufferedImage image, int startY, int endY) {
		this.image = image;
		this.startY = startY;
		this.endY = endY;
	}

	@Override
	public void run() {
		// Apply image processing filters to the specified portion of the image
		for (int y = startY; y < endY; y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				// Apply image processing logic
				// Example: Grayscale conversion
				int rgb = image.getRGB(x, y);
				int gray = (int) (0.21 * ((rgb >> 16) & 0xFF) + 0.72 * ((rgb >> 8) & 0xFF) + 0.07 * (rgb & 0xFF));
				int grayRgb = (gray << 16) | (gray << 8) | gray;
				image.setRGB(x, y, grayRgb);
			}
		}
	}
}
