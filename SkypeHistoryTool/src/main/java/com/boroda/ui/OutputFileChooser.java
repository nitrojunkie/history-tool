package com.boroda.ui;

import com.boroda.event.FileSelectionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OutputFileChooser extends JDialog {
	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JTextField outputPath;
	private JButton chooseFileButton;
	private JProgressBar progressBar;

	private FileSelectionListener fileSelectionListener;

	public OutputFileChooser() {
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);

		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onOK();
			}
		});

		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		});

// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});

// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

		chooseFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onChooseFile();
			}
		});

		progressBar.setVisible(false);
	}

	private void onOK() {
		fileSelectionListener.onFileChosen(new FileSelectionListener.FileSelectionEvent(outputPath.getText()));
	}

	private void onCancel() {
// add your code here if necessary
		dispose();
	}

	private void onChooseFile() {
		JFileChooser saveFile = new JFileChooser(outputPath.getText());
		if (saveFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			outputPath.setText(saveFile.getSelectedFile().getPath());
		}
	}

	public void setOutputPath(String path) {
		outputPath.setText(path);
	}

	public void enableProgressBar() {
		progressBar.setVisible(true);
	}

	public void disableProgressBar() {
		progressBar.setVisible(false);
	}

	public void setFileSelectionListener(FileSelectionListener fileSelectionListener) {
		this.fileSelectionListener = fileSelectionListener;
	}
}
