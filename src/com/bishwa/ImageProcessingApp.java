package com.bishwa;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;



public class ImageProcessingApp {
	// Client Class: 
	private JFrame frame;
	private JButton uploadButton;
	private JButton addButton;
	private JButton processButton;
	private JTextArea outputTextArea;
	private List<File> selectedImages;

	public ImageProcessingApp() {
		selectedImages = new ArrayList<>();
	}
	private void initializeGUI() {
		frame = new JFrame("Image Processing App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());

		uploadButton = new JButton("Upload Image");
		addButton = new JButton("Add");
		processButton = new JButton("Process Images");
		outputTextArea = new JTextArea(10, 30);
		outputTextArea.setEditable(false);

		uploadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(frame);

				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					addImage(selectedFile);
				}
			}
		});

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(frame);

				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					addImage(selectedFile);
				}
			}
		});

		processButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				processImages();
			}
		});

		processButton.setVisible(false);

		frame.add(uploadButton);
		frame.add(addButton);
		frame.add(processButton);
		frame.add(outputTextArea);
		frame.pack();
		frame.setVisible(true);
	}
	private void addImage(File file) {
		// Add the image to the list of selected images
		selectedImages.add(file);
		int imageCount = selectedImages.size();

		outputTextArea.append("Image added: " + file.getName() + "\n");
		outputTextArea.append("Total images: " + imageCount + "\n");

		// Enable the process button if there is at least one image
		if (imageCount > 0) {
			processButton.setVisible(true);
		}
	}
	private void processImages() {
		// Process all selected images
		for (File imageFile : selectedImages) {
			BufferedImage image = loadImage(imageFile);

			if (image != null) {
				applyFiltersConcurrently(image);

				String outputFilePath = getOutputFilePath(imageFile);
				saveImage(image, outputFilePath);

				outputTextArea.append("Image processed and saved: " + outputFilePath + "\n");
			} else {
				outputTextArea.append("Failed to load image: " + imageFile.getAbsolutePath() + "\n");
			}
		}

		// Clear the list of selected images
		selectedImages.clear();

		// Disable the process button
		processButton.setVisible(false);
	}

	private BufferedImage loadImage(File file) {
		try {
			return ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void saveImage(BufferedImage image, String filePath) {
		try {
			ImageIO.write(image, "JPEG", new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void applyFiltersConcurrently(BufferedImage image) {
		int numThreads = Runtime.getRuntime().availableProcessors(); // Number of available processor cores

		Thread[] threads = new Thread[numThreads];
		int imageHeight = image.getHeight();

		// Divide the image into equal parts for concurrent processing
		int partHeight = imageHeight / numThreads;
		int remainingHeight = imageHeight % numThreads;

		for (int i = 0; i < numThreads; i++) {
			int startY = i * partHeight;
			int endY = startY + partHeight;

			if (i == numThreads - 1) {
				endY += remainingHeight;
			}

			ImageProcessor processor = new ImageProcessor(image, startY, endY);
			threads[i] = new Thread(processor);
			threads[i].start();
		}

		// Wait for all threads to finish
		try {
			for (Thread thread : threads) {
				thread.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String getOutputFilePath(File inputFile) {
		String inputFilePath = inputFile.getAbsolutePath();
		String outputFilePath = inputFilePath.substring(0, inputFilePath.lastIndexOf(".")) + "_processed.jpg";
		return outputFilePath;
	}

	public static void main(String[] args) {

	}
}
