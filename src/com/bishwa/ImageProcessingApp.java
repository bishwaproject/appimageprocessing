package com.bishwa;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ImageProcessingApp {
	// Client Class: private JFrame frame;
	private JButton uploadButton;
	private JButton addButton;
	private JButton processButton;
	private JTextArea outputTextArea;
	private List<File> selectedImages;

	public ImageProcessingApp() {
		selectedImages = new ArrayList<>();

	}

	public static void main(String[] args) {

	}
}
