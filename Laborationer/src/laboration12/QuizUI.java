package laboration12;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class QuizUI extends JPanel {
	private JTextField tfIp = new JTextField();
	private JTextField tfPort = new JTextField();
	private JTextField tfQuestionIndex = new JTextField();
	private JTextField tfAnswer = new JTextField();
	private JTextArea textarea = new JTextArea();
	private JButton btnConnection = new JButton("Anslut");
	private QuizClient client;

	public QuizUI() {
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(textarea);
		scroll.setPreferredSize(new Dimension(400, 150));
		setLayout(new BorderLayout());
		add(mainPanel(), BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
	}

	private JPanel connectionPanel() {
		JPanel pnlConnection = new JPanel(new BorderLayout());
		JPanel pnlInfo = new JPanel(new BorderLayout());
		JPanel pnlTitles = new JPanel(new GridLayout(2, 1));
		JPanel pnlInput = new JPanel(new GridLayout(2, 1));

		btnConnection.addActionListener(new ConnectListener());
		pnlTitles.add(new JLabel("Ip-adress:"));
		pnlTitles.add(new JLabel("Portnummer:"));
		pnlInput.add(tfIp);
		pnlInput.add(tfPort);
		pnlInfo.add(pnlTitles, BorderLayout.WEST);
		pnlInfo.add(pnlInput, BorderLayout.CENTER);

		pnlConnection.add(pnlInfo, BorderLayout.CENTER);
		pnlConnection.add(btnConnection, BorderLayout.SOUTH);
		return pnlConnection;
	}

	private JPanel questionPanel() {
		JPanel pnlQuestion = new JPanel(new GridLayout(1, 4));

		tfQuestionIndex.setEnabled(false);
		tfQuestionIndex.addActionListener(new Question());
		tfAnswer.setEnabled(false);
		tfAnswer.addActionListener(new Answer());

		pnlQuestion.add(new JLabel("Fråga nr (1-20):", JLabel.RIGHT));
		pnlQuestion.add(tfQuestionIndex);
		pnlQuestion.add(new JLabel("Svar:", JLabel.RIGHT));
		pnlQuestion.add(tfAnswer);
		return pnlQuestion;
	}

	private JPanel mainPanel() {
		JPanel pnlMain = new JPanel(new BorderLayout());
		JLabel lblTitle = new JLabel("FRÅGESPORT", JLabel.CENTER);

		lblTitle.setForeground(Color.black);
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 18));

		pnlMain.add(lblTitle, BorderLayout.NORTH);
		pnlMain.add(connectionPanel(), BorderLayout.CENTER);
		pnlMain.add(questionPanel(), BorderLayout.SOUTH);
		return pnlMain;
	}

	public void setText(String txt) {
		textarea.setText(txt);
	}

	public void appendText(String txt) {
		textarea.append(txt);
	}

	public String getIp() {
		return tfIp.getText();
	}

	public String getPort() {
		return tfPort.getText();
	}

	public void connected() {
		btnConnection.setText("Koppla ner");
		tfQuestionIndex.setEnabled(true);
		tfQuestionIndex.setText("");
		tfAnswer.setEnabled(false);
		tfAnswer.setText("");
		tfIp.setEnabled(false);
		tfPort.setEnabled(false);
	}

	public void disconnected() {
		btnConnection.setText("Anslut");
		tfQuestionIndex.setEnabled(false);
		tfAnswer.setEnabled(false);
		tfIp.setEnabled(true);
		tfPort.setEnabled(true);
	}

	private void createClient() {
		client = new QuizClient(getIp(), Integer.parseInt(getPort()), this);
	}

	private class ConnectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (tfIp.isEnabled()) {
				createClient();
				try {
					client.connect();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (!tfIp.isEnabled()) {
				client.disconnect();
			}
		}
	}

	private class Question implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			tfAnswer.setText("");
			tfQuestionIndex.setEnabled(true);
			tfAnswer.setEnabled(true);
			try {
				client.question(tfQuestionIndex.getText());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private class Answer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			tfQuestionIndex.setText("");
			tfQuestionIndex.setEnabled(true);
			tfAnswer.setEnabled(false);
			try {
				client.awnser(tfAnswer.getText());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(new QuizUI());
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
