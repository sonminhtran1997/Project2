/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumble;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import jumble.ExtensionFileFilter;


/**
 *
 * @author Son Tran
 */
public class Jumble extends JFrame{

    final int FRAME_WIDTH = 500;
    final int FRAME_HEIGHT = 220;
    final int MAX_CHAR = 7;
    JComboBox[] comboArray = new JComboBox[MAX_CHAR];
    File dictFile = null;
    JFileChooser chooser;
    String dictionaryName = "";
    Dictionary dictionary;
    public static void main(String[] args) {
        JFrame frame = new Jumble();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public Jumble()
    {
        setLayout(new BorderLayout());
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setMenuBar();
        setJMenuBar(menuJMenuBar);
        setNorthPanel();
        add(northPanel, BorderLayout.NORTH);
        setCenterPanel();
        add(centerPanel, BorderLayout.CENTER);
        setSouthPanel();
        add(southPanel, BorderLayout.SOUTH);
     
    }
    public void setMenuBar(){
        menuJMenuBar = new JMenuBar();
        loadJMenuItem = new JMenuItem("Load Dictionary");
        loadJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu = new JMenu("File");
        fileJMenu.add(loadJMenuItem);
        menuJMenuBar.add(fileJMenu);
    }
    public void setNorthPanel(){
        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.setBackground(Color.LIGHT_GRAY);
        statusLabel = new JLabel("Dictionary: None");
        northPanel.add(statusLabel);
        numberSolutionsLabel = new JLabel("");
        northPanel.add(numberSolutionsLabel);
    }
    public void setCenterPanel(){
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        wordPanel = new JPanel();
        Color wordPanelColor = new Color(0,255 ,153);
        wordPanel.setBackground(wordPanelColor);
        wordLabel = new JLabel();
        wordLabel.setFont(new Font("Serif", Font.BOLD, 18));
        wordLabel.setForeground(Color.BLUE);
        wordPanel.add(wordLabel);
        answerPanel = new JPanel();
        answerPanel.setBackground(Color.CYAN);
        centerPanel.add(wordPanel, BorderLayout.NORTH);
        centerPanel.add(answerPanel, BorderLayout.SOUTH);
        comboPanel = new JPanel();
        for (int i = 0; i < MAX_CHAR; i++) 
        {
            comboArray[i] = new JComboBox();
            comboPanel.add(comboArray[i]);
            comboArray[i].setVisible(false);
        }
        centerPanel.add(comboPanel, BorderLayout.CENTER);
    }
    public void setSouthPanel(){
        southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());
        southPanel.setBackground(Color.lightGray);
        getButton = new JButton("get Jumble");
        getButton.setEnabled(false);
        getButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getButtonActionPerformed(evt);
            }
        });
        testButton = new JButton("test answer");
        testButton.setEnabled(false);
        seeButton = new JButton("see answer");
        seeButton.setEnabled(false);
        southPanel.add(getButton);
        southPanel.add(testButton);
        southPanel.add(seeButton);
    }
    private void loadJMenuItemActionPerformed(java.awt.event.ActionEvent evt){
        boolean dictionaryLoaded = true;
        String[] extensionRequired = new String[]{"Dic", "DIC", "dic"};
        ExtensionFileFilter filter = new ExtensionFileFilter("dic", extensionRequired);
        chooser = new JFileChooser();
        String dictionaryName = "";
        dictFile = new File(dictionaryName);
        chooser.setCurrentDirectory(dictFile.getAbsoluteFile().getParentFile());
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) 
        {
            dictFile = chooser.getSelectedFile();
            dictionaryName = dictFile.getName().trim();
        }
        try{
            if (dictFile.exists() && dictFile.canRead()) 
            {
                dictionary = new Dictionary(dictFile);
                if (dictionary.checkStatusSetup()) {
                    dictionaryLoaded = true;
                    dictionaryName = dictFile.getName().trim();
                }
                else
                {
                    dictFile = null;
                    dictionaryLoaded = false;
                    dictionaryName = "None";
                }
            }
            else
            {
                dictFile = null;
                dictionaryLoaded = false;
                dictionaryName = "None";
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error", "Cannot load Dictionary", JOptionPane.ERROR_MESSAGE);
        }
        if (dictionaryLoaded)
        {
            statusLabel.setText("Dictionary: " + dictionaryName);
            getButton.setEnabled(true);
            testButton.setEnabled(true);
            seeButton.setEnabled(true);
        }
        else
            statusLabel.setText("Dictionary: None");
    }
    private void getButtonActionPerformed(java.awt.event.ActionEvent evt){
        String jumble = "";
        jumble = dictionary.getJumble();
        wordLabel.setText(jumble);
        for (int i = 0; i < jumble.length(); i++) {
            comboArray[i].setVisible(true);
            comboArray[i].addItem("");
            for (int j = 0; j < jumble.length(); j++) {
                comboArray[i].addItem(jumble.toCharArray()[j]);
            }
        }
    }
    JMenuBar menuJMenuBar;
    JMenu fileJMenu;
    JMenuItem loadJMenuItem;
    JPanel northPanel;
    JLabel statusLabel;
    JLabel numberSolutionsLabel;
    JPanel southPanel;
    JButton getButton;
    JButton testButton;
    JButton seeButton;
    JPanel centerPanel;
    JPanel comboPanel;
    JPanel wordPanel;
    JLabel wordLabel;
    JPanel answerPanel;
}