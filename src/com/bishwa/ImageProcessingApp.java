package com.bishwa;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
	private void addImage(File file) {} //working on it
	private void processImages() {} // working on it
	public static void main(String[] args) {

	}
}
