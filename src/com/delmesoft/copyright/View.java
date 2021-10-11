/* 
 * Copyright (c) 2021, Sergio S.- sergi.ss4@gmail.com http://sergiosoriano.com
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package com.delmesoft.copyright;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import com.delmesoft.copyright.CopyrightOmatic.FileFilter;

public class View extends JFrame {
	
	public static final String DEFAULT_COPYRIGHT = String.format(
			"/* \r\n"
			+ " * Copyright (c) %d, Sergio S.- sergi.ss4@gmail.com http://sergiosoriano.com\r\n"
			+ " * All rights reserved.\r\n"
			+ " * \r\n"
			+ " * Redistribution and use in source and binary forms, with or without\r\n"
			+ " * modification, are permitted provided that the following conditions are met:\r\n"
			+ " * \r\n"
			+ " * 1. Redistributions of source code must retain the above copyright notice, this\r\n"
			+ " *    list of conditions and the following disclaimer.\r\n"
			+ " * \r\n"
			+ " * 2. Redistributions in binary form must reproduce the above copyright notice,\r\n"
			+ " *    this list of conditions and the following disclaimer in the documentation\r\n"
			+ " *    and/or other materials provided with the distribution.\r\n"
			+ " * \r\n"
			+ " * 3. Neither the name of the copyright holder nor the names of its\r\n"
			+ " *    contributors may be used to endorse or promote products derived from\r\n"
			+ " *    this software without specific prior written permission.\r\n"
			+ " * \r\n"
			+ " * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\"\r\n"
			+ " * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE\r\n"
			+ " * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE\r\n"
			+ " * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE\r\n"
			+ " * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL\r\n"
			+ " * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR\r\n"
			+ " * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER\r\n"
			+ " * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,\r\n"
			+ " * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE\r\n"
			+ " * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\r\n"
			+ "*/", Calendar.getInstance().get(Calendar.YEAR));
	
	public static final String DEFAULT_EXTENSIONS = ".java,.js,.jsx,.css";

	private JPanel contentPane;
	private JTextField textFieldFolder;
	private JTextField textFieldExtensions;
	private JEditorPane editorPane;

	private JCheckBox chckbxSubFolders;

	private JCheckBox chckbxMatchCase;

	/**
	 * Create the frame.
	 */
	public View() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		setTitle("Copyright O'matic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 690, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0 };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel panel1 = new JPanel();
		panel1.setBorder(new TitledBorder(null, "Copyright", TitledBorder.LEADING, TitledBorder.TOP,
				new Font("Arial", Font.PLAIN, 16), null));
		GridBagConstraints gbc_panel1 = new GridBagConstraints();
		gbc_panel1.insets = new Insets(0, 0, 5, 0);
		gbc_panel1.fill = GridBagConstraints.BOTH;
		gbc_panel1.gridx = 0;
		gbc_panel1.gridy = 0;
		contentPane.add(panel1, gbc_panel1);
		GridBagLayout gbl_panel1 = new GridBagLayout();
		gbl_panel1.columnWidths = new int[] { 0, 0 };
		gbl_panel1.rowHeights = new int[] { 0, 0 };
		gbl_panel1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel1.setLayout(gbl_panel1);
		
		editorPane = new JEditorPane();
		editorPane.setFont(new Font("Arial", Font.PLAIN, 16));
		DefaultCaret caret = (DefaultCaret) editorPane.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		editorPane.setText(DEFAULT_COPYRIGHT);

		
		JScrollPane scrollPane = new JScrollPane(editorPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel1.add(scrollPane, gbc_scrollPane);	
		
		JPanel panel2 = new JPanel();
		panel2.setBorder(new TitledBorder(null, "Settings", TitledBorder.LEADING, TitledBorder.TOP,
				new Font("Arial", Font.PLAIN, 16), null));
		GridBagConstraints gbc_panel2 = new GridBagConstraints();
		gbc_panel2.insets = new Insets(0, 0, 5, 0);
		gbc_panel2.fill = GridBagConstraints.BOTH;
		gbc_panel2.gridx = 0;
		gbc_panel2.gridy = 1;
		contentPane.add(panel2, gbc_panel2);
		GridBagLayout gbl_panel2 = new GridBagLayout();
		gbl_panel2.columnWidths = new int[] { 0, 0 };
		gbl_panel2.rowHeights = new int[] { 0, 0 };
		gbl_panel2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel2.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel2.setLayout(gbl_panel2);

		JPanel panelSettings = new JPanel();
		GridBagConstraints gbc_panelSettings = new GridBagConstraints();
		gbc_panelSettings.insets = new Insets(5, 5, 5, 5);
		gbc_panelSettings.fill = GridBagConstraints.BOTH;
		gbc_panelSettings.gridx = 0;
		gbc_panelSettings.gridy = 0;
		panel2.add(panelSettings, gbc_panelSettings);
		GridBagLayout gbl_panelSettings = new GridBagLayout();
		gbl_panelSettings.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panelSettings.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panelSettings.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelSettings.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelSettings.setLayout(gbl_panelSettings);

		JLabel lblFolder = new JLabel("Folder");
		lblFolder.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_lblFolder = new GridBagConstraints();
		gbc_lblFolder.insets = new Insets(0, 0, 5, 5);
		gbc_lblFolder.anchor = GridBagConstraints.WEST;
		gbc_lblFolder.gridx = 0;
		gbc_lblFolder.gridy = 0;
		panelSettings.add(lblFolder, gbc_lblFolder);

		textFieldFolder = new JTextField();
		textFieldFolder.setEditable(false);
		textFieldFolder.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_textFieldFolder = new GridBagConstraints();
		gbc_textFieldFolder.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFolder.fill = GridBagConstraints.BOTH;
		gbc_textFieldFolder.gridx = 1;
		gbc_textFieldFolder.gridy = 0;
		panelSettings.add(textFieldFolder, gbc_textFieldFolder);
		textFieldFolder.setColumns(10);

		JButton btnSetFolder = new JButton("...");
		btnSetFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = fileChooser.showOpenDialog(View.this);
				if(option == JFileChooser.APPROVE_OPTION) {
					textFieldFolder.setText(fileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		btnSetFolder.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		panelSettings.add(btnSetFolder, gbc_btnNewButton_1);

		chckbxSubFolders = new JCheckBox("Subfolders");
		chckbxSubFolders.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.gridwidth = 3;
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxNewCheckBox.anchor = GridBagConstraints.WEST;
		gbc_chckbxNewCheckBox.gridx = 0;
		gbc_chckbxNewCheckBox.gridy = 1;
		panelSettings.add(chckbxSubFolders, gbc_chckbxNewCheckBox);
		chckbxSubFolders.setSelected(true);

		JLabel lblNewLabel = new JLabel("Extensions");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		panelSettings.add(lblNewLabel, gbc_lblNewLabel);

		textFieldExtensions = new JTextField();
		textFieldExtensions.setToolTipText("File extensions separated by a comma");
		textFieldExtensions.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		panelSettings.add(textFieldExtensions, gbc_textField_1);
		textFieldExtensions.setColumns(10);
		
		textFieldExtensions.setText(DEFAULT_EXTENSIONS);

		chckbxMatchCase = new JCheckBox("Match Case");
		chckbxMatchCase.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_1.anchor = GridBagConstraints.WEST;
		gbc_chckbxNewCheckBox_1.gridwidth = 3;
		gbc_chckbxNewCheckBox_1.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox_1.gridx = 0;
		gbc_chckbxNewCheckBox_1.gridy = 3;
		panelSettings.add(chckbxMatchCase, gbc_chckbxNewCheckBox_1);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 2;

		JButton btnPerform = new JButton("Perform");
		btnPerform.setFont(new Font("Arial", Font.BOLD, 16));
		btnPerform.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String folder = textFieldFolder.getText();
				if(folder.trim().isEmpty()) {
					JOptionPane.showMessageDialog(View.this, "Please, first select a directory", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				int result = JOptionPane.showConfirmDialog(View.this,
					    "Are you sure you want to perform the operation?\n(REMEMBER, THIS OPERATION MODIFIES THE HEADER OF THE FILES)",
					    "Confirm",
					    JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.OK_OPTION) {
					try {
						
						String copyright = editorPane.getText();
						boolean subfolders = chckbxSubFolders.isSelected();
						boolean matchCase = chckbxMatchCase.isSelected();
						String[] extensions = textFieldExtensions.getText().split(",");
						
						int n = CopyrightOmatic.addCopyright(new File(folder), new FileFilter(subfolders, matchCase, extensions), copyright);
						JOptionPane.showMessageDialog(View.this, String.format("Operation successful, %d files modified", n));
						
					} catch (Exception ex) {
						ex.printStackTrace();
						String error = ex.getLocalizedMessage();
						JOptionPane.showMessageDialog(View.this, "Error performing the operation: " + error, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		GridBagConstraints gbc_btnPerform = new GridBagConstraints();
		gbc_btnPerform.insets = new Insets(5, 5, 5, 5);
		gbc_btnPerform.gridx = 0;
		gbc_btnPerform.gridy = 2;
		contentPane.add(btnPerform, gbc_btnPerform);
		setLocationRelativeTo(null);
	}

}
