package view;

import controller.MessageController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SurgeonChatView extends JPanel {

    private JTextField messageField;
    private JTextArea chatArea;
    private PrintWriter out;
    private BufferedReader in;
    private int surgeonId;
    private int patientId; // ID of the patient

    public SurgeonChatView(int surgeonId, int patientId) {
        this.surgeonId = surgeonId;
        this.patientId = patientId;
        setLayout(new BorderLayout());

        // Chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Message input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        messageField = new JTextField("Type your message");
        JButton sendButton = new JButton("Send");

        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText().trim();
                if (!message.isEmpty()) {
                    out.println(message);
                    chatArea.append("You: " + message + "\n");
                    messageField.setText("");
                    saveMessageToDatabase(surgeonId, patientId, message); // Save sent message to the database
                }
            }
        });

        connectToServer();
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 12345); // Change to your server address and port
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            new Thread(new Runnable() {
                public void run() {
                    try {
                        String message;
                        while ((message = in.readLine()) != null) {
                            chatArea.append("Patient: " + message + "\n");
                            saveMessageToDatabase(patientId, surgeonId, message); // Save received message to the database
                        }
                    } catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                }
            }).start();

        } catch (Exception e) {
            System.err.println("Error connecting to the server: " + e.getMessage());
        }
    }

    private void saveMessageToDatabase(int senderId, int receiverId, String message) {
        MessageController controller = new MessageController();
        controller.saveMessage(senderId, receiverId, message);
    }
}
