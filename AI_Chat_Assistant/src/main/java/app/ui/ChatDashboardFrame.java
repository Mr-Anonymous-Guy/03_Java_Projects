package app.ui;

import app.model.AssistantMode;
import app.model.PromptTemplate;
import app.service.OllamaClient;
import app.service.PromptTemplateService;
import app.service.SystemPromptManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChatDashboardFrame extends JFrame {
    private AssistantMode currentMode = AssistantMode.GENERAL;
    
    private final SystemPromptManager promptManager;
    private final PromptTemplateService templateService;
    private final OllamaClient ollamaClient;

    private JTextArea chatArea;
    private JTextArea inputArea;
    private JPanel templatesPanel;
    private JLabel lblCurrentMode;

    public ChatDashboardFrame() {
        this.promptManager = new SystemPromptManager();
        this.templateService = new PromptTemplateService();
        this.ollamaClient = new OllamaClient();

        setTitle("AI Chat Assistant");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Sidebar (Assistant Modes)
        JPanel sidebar = new JPanel(new GridLayout(AssistantMode.values().length + 1, 1, 5, 5));
        sidebar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sidebar.setPreferredSize(new Dimension(200, 0));
        
        JLabel lblModes = new JLabel("Assistant Modes", SwingConstants.CENTER);
        lblModes.setFont(new Font("Arial", Font.BOLD, 14));
        sidebar.add(lblModes);

        for (AssistantMode mode : AssistantMode.values()) {
            JButton btnMode = new JButton(mode.getDisplayName());
            btnMode.addActionListener(e -> setMode(mode));
            sidebar.add(btnMode);
        }

        // Center Panel (Chat Area)
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblCurrentMode = new JLabel("Current Mode: " + currentMode.getDisplayName(), SwingConstants.LEFT);
        lblCurrentMode.setFont(new Font("Arial", Font.BOLD, 16));
        centerPanel.add(lblCurrentMode, BorderLayout.NORTH);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane scrollChat = new JScrollPane(chatArea);
        centerPanel.add(scrollChat, BorderLayout.CENTER);

        // Input Area
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputArea = new JTextArea(4, 50);
        inputArea.setLineWrap(true);
        JScrollPane scrollInput = new JScrollPane(inputArea);
        
        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(e -> sendMessage());
        
        inputPanel.add(scrollInput, BorderLayout.CENTER);
        inputPanel.add(btnSend, BorderLayout.EAST);
        
        centerPanel.add(inputPanel, BorderLayout.SOUTH);

        // Right Panel (Prompt Templates)
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(250, 0));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblTemplates = new JLabel("Prompt Templates", SwingConstants.CENTER);
        lblTemplates.setFont(new Font("Arial", Font.BOLD, 14));
        rightPanel.add(lblTemplates, BorderLayout.NORTH);

        templatesPanel = new JPanel();
        templatesPanel.setLayout(new BoxLayout(templatesPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollTemplates = new JScrollPane(templatesPanel);
        rightPanel.add(scrollTemplates, BorderLayout.CENTER);

        // Assemble
        add(sidebar, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        // Init Data
        refreshTemplates();
    }

    private void setMode(AssistantMode newMode) {
        this.currentMode = newMode;
        lblCurrentMode.setText("Current Mode: " + currentMode.getDisplayName());
        chatArea.append("--- Switched to " + currentMode.getDisplayName() + " ---\n");
    }

    private void refreshTemplates() {
        templatesPanel.removeAll();
        List<String> categories = templateService.getCategories();

        for (String category : categories) {
            JLabel lblCat = new JLabel(">> " + category);
            lblCat.setFont(new Font("Arial", Font.BOLD, 12));
            templatesPanel.add(lblCat);

            List<PromptTemplate> categoryTemplates = templateService.getTemplatesByCategory(category);
            for (PromptTemplate pt : categoryTemplates) {
                JButton btnPt = new JButton(pt.getTitle());
                btnPt.setAlignmentX(Component.LEFT_ALIGNMENT);
                btnPt.addActionListener(e -> inputArea.setText(pt.getContent()));
                templatesPanel.add(btnPt);
            }
            templatesPanel.add(Box.createVerticalStrut(10));
        }
        templatesPanel.revalidate();
        templatesPanel.repaint();
    }

    private void sendMessage() {
        String userInput = inputArea.getText().trim();
        if (userInput.isEmpty()) return;

        chatArea.append("You: " + userInput + "\n\n");
        inputArea.setText("");
        
        // Disable button to prevent spam while waiting
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        // Use SwingWorker to not block UI
        SwingWorker<String, Void> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() {
                String systemPrompt = promptManager.getPromptForMode(currentMode);
                return ollamaClient.generateResponse(systemPrompt, userInput);
            }

            @Override
            protected void done() {
                try {
                    String response = get();
                    chatArea.append("AI: " + response + "\n\n");
                    chatArea.setCaretPosition(chatArea.getDocument().getLength());
                } catch (Exception ex) {
                    chatArea.append("System Error: " + ex.getMessage() + "\n\n");
                } finally {
                    setCursor(Cursor.getDefaultCursor());
                }
            }
        };
        worker.execute();
    }
}
