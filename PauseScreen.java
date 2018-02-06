package my.game;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PauseScreen extends JDialog {
	private static final long serialVersionUID = 1L;
	
	JLabel titleLabel;
	JButton quitButton;
	JButton resumeButton;
	
	JPanel BBPanel; //-- stands for bottom button panel
	
	private static Color bgc = new Color(255, 255, 255, 80);
	private static Color transparent = new Color(255, 255, 255, 0);
	private static Font titleFont = new Font("Verdana", Font.BOLD, 40);
	private static Font bodyFont  = new Font("Verdana", Font.PLAIN, 28);
	
	public PauseScreen() {
		setSize(600, 400);
		setUndecorated(true);
		setBackground(bgc);
		getRootPane().setOpaque(false);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		titleLabel = new JLabel("paused", SwingConstants.CENTER);
		titleLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
		titleLabel.setFont(titleFont);
		titleLabel.setBackground(bgc);
		titleLabel.setForeground(Color.black);
		
		BBPanel = new JPanel();
		BBPanel.setBackground(transparent);
		BBPanel.setLayout(new FlowLayout());
		BBPanel.setBorder(new EmptyBorder(50, 25, 10, 25));
		
		quitButton = new JButton("quit");
		quitButton.setFont(bodyFont);
		quitButton.setBackground(Color.white);
		quitButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 1), BorderFactory.createLineBorder(Color.white, 15)));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		
		resumeButton = new JButton("resume");
		resumeButton.setFont(bodyFont);
		resumeButton.setBackground(Color.white);
		resumeButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 1), BorderFactory.createLineBorder(Color.white, 15)));
		resumeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.pauseScreen.setVisible(false);
				Game.timer.start();
			}
		});
		
		BBPanel.add(resumeButton);
		BBPanel.add(quitButton);
		
		
		add(titleLabel, gbc);
		add(BBPanel, gbc);
		
		pack();
		setLocationRelativeTo(null);
	}
	
}
