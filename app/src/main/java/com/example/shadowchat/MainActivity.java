package com.example.shadowchat;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;
import androidx.activity.EdgeToEdge;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private LinearLayout chatLayout;
    private EditText messageInput;
    private ScrollView scrollView;
    private ImageButton sendButton;
    private TextView statusText;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        chatLayout = findViewById(R.id.chatLayout);
        messageInput = findViewById(R.id.messageInput);
        scrollView = findViewById(R.id.scrollView);
        sendButton = findViewById(R.id.sendButton);
        statusText = findViewById(R.id.statusText);

        sendButton.setOnClickListener(v -> sendMessage());

        // Startup Sequence
        startupSequence();
    }

    private void startupSequence() {
        addSystemLog("INITIALIZING SECURE TUNNEL...");
        handler.postDelayed(() -> addSystemLog("LOADING ENCRYPTION MODULE..."), 800);
        handler.postDelayed(() -> {
            addSystemLog("SECURE CONNECTION ESTABLISHED.");
            addMessage("SYSTEM", "SECURE CHANNEL ACTIVE. READY FOR TRANSMISSION.", false);
            statusText.setText("● SECURE NODE ONLINE");
            statusText.setTextColor(0xFF00FF00);
        }, 1600);
    }

    private void sendMessage() {
        String text = messageInput.getText().toString().trim();
        if (text.isEmpty()) {
            Toast.makeText(this, "EMPTY PACKET REJECTED", Toast.LENGTH_SHORT).show();
            return;
        }

        messageInput.setText("");
        
        // 1. Show "Encrypting..." animation
        addSystemLog("PREPARING ENCRYPTION...");
        
        handler.postDelayed(() -> {
            try {
                String encryptedMessage = EncryptionHelper.encrypt(text);
                
                String displayMessage = "ENCRYPTED:\n" + encryptedMessage + "\n\nDECRYPTED:\n" + text;

                // 2. Display original message as sent
                addMessage("YOU", displayMessage, true);
                addSystemLog("PACKET ROUTED: " + encryptedMessage.substring(0, Math.min(10, encryptedMessage.length())) + "...");

                // 3. Simulate receiver reply with typing effect
                simulateAIReply(text);
                
            } catch (Exception e) {
                addSystemLog("ENCRYPTION FAILED");
            }
        }, 600);
    }

    private void simulateAIReply(String userMessage) {
        handler.postDelayed(() -> {
            statusText.setText("SHADOW_NODE IS TYPING...");
            statusText.setTextColor(0xFFFF004C); // Accent color
            
            handler.postDelayed(() -> {
                try {
                    String userText = userMessage.toLowerCase();
                    String response;

                    if (userText.contains("hello") || userText.contains("hi") || userText.contains("hey")) {
                        response = "Greetings. Secure tunnel active. How can I assist?";
                    } else if (userText.contains("how are you") || userText.contains("status")) {
                        response = "Systems stable. Encryption modules at 100%.";
                    } else if (userText.contains("encryption") || userText.contains("security") || userText.contains("aes")) {
                        response = "AES-128 active. Robust and verified.";
                    } else if (userText.contains("key") || userText.contains("password")) {
                        response = "Security Alert: Use Android Keystore for key storage.";
                    } else if (userText.contains("who") || userText.contains("what")) {
                        response = "SHADOW_NODE: Localized security relay.";
                    } else if (userText.contains("hack") || userText.contains("crack")) {
                        response = "Defense protocols active. Specializing in protection.";
                    } else if (userText.contains("thanks") || userText.contains("thank you")) {
                        response = "Acknowledged. Shielding active.";
                    } else if (userText.contains("bye") || userText.contains("exit") || userText.contains("quit")) {
                        response = "Stealth standby enabled. Ready for next packet.";
                    } else if (userText.length() < 5) {
                        response = "Packet brief. Please provide more data.";
                    } else {
                        String[] fallbacks = {
                                "Packet analyzed. Data routed securely.",
                                "Transmission processed. No anomalies detected.",
                                "Message acknowledged. Monitoring channel.",
                                "Signal clear. Standing by for instructions.",
                                "Understood. Transmission logged securely.",
                                "Verified. Proceed with next transmission."
                        };
                        response = fallbacks[(int) (Math.random() * fallbacks.length)];
                    }

                    String encryptedResponse = EncryptionHelper.encrypt(response);
                    String finalResponse = "ENCRYPTED:\n" + encryptedResponse + "\n\nDECRYPTED:\n" + response;
                    
                    addSystemLog("INCOMING PACKET DETECTED...");
                    
                    handler.postDelayed(() -> {
                        addMessage("SHADOW_NODE", finalResponse, false);
                        statusText.setText("● SECURE NODE ONLINE");
                        statusText.setTextColor(0xFF00FF00);
                    }, 1000);
                    
                } catch (Exception e) {
                    addSystemLog("DECRYPTION ERROR: " + e.getMessage());
                    statusText.setText("● SECURE NODE ONLINE");
                    statusText.setTextColor(0xFF00FF00);
                }
            }, 2000); // 2 second delay for realism
        }, 1000); // Initial delay before "typing"
    }

    private void addMessage(String sender, String message, boolean isSent) {
        View messageView = LayoutInflater.from(this).inflate(
                isSent ? R.layout.item_message_sent : R.layout.item_message_received, 
                chatLayout, false);

        TextView textMsg = messageView.findViewById(R.id.textMessage);
        TextView textTime = messageView.findViewById(R.id.textTime);
        TextView textSender = messageView.findViewById(R.id.textSender);
        TextView textStatus = messageView.findViewById(R.id.textStatus);
        View strengthBar = messageView.findViewById(R.id.strengthBar);
        TextView securityBadge = messageView.findViewById(R.id.textSecurityBadge);

        textMsg.setText(message);
        textTime.setText(new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()));
        if (textSender != null) textSender.setText(sender);
        if (textStatus != null) textStatus.setText("Encrypted • Delivered");

        // Animate Strength Bar and Security Badge
        if (strengthBar != null && securityBadge != null && !sender.equals("SYSTEM")) {
            strengthBar.setVisibility(View.VISIBLE);
            strengthBar.setScaleX(0f);
            strengthBar.setPivotX(0f);
            handler.postDelayed(() -> {
                strengthBar.animate().scaleX(1f).setDuration(800).withEndAction(() -> {
                    handler.postDelayed(() -> {
                        strengthBar.setVisibility(View.GONE);
                        securityBadge.setVisibility(View.VISIBLE);
                        securityBadge.setAlpha(0f);
                        securityBadge.animate().alpha(1f).setDuration(400);
                    }, 200);
                });
            }, 100);
        }

        // Feature 8: Copy Encrypted Text
        messageView.setOnClickListener(v -> {
            if (message.contains("ENCRYPTED:")) {
                String encryptedPart = message.split("\n\n")[0].replace("ENCRYPTED:\n", "");
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(android.content.Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Encrypted Packet", encryptedPart);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(this, "CIPHER COPIED TO CLIPBOARD", Toast.LENGTH_SHORT).show();
            }
        });

        chatLayout.addView(messageView);
        scrollToBottom();
    }

    private void addSystemLog(String log) {
        TextView logView = new TextView(this);
        logView.setText("[LOG] " + log);
        logView.setTextColor(0xFF444444);
        logView.setTextSize(10);
        logView.setTypeface(android.graphics.Typeface.MONOSPACE);
        logView.setPadding(0, 8, 0, 8);
        chatLayout.addView(logView);
        scrollToBottom();
    }

    private void scrollToBottom() {
        scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
    }
}
