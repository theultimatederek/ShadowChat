# 🔐 ShadowChat — Encrypted Android Chat

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-11-orange?logo=java)](https://www.java.com)
[![Android](https://img.shields.io/badge/Android-API%2024%2B-green?logo=android)](https://developer.android.com)
[![AES](https://img.shields.io/badge/Encryption-AES--128-red)](https://en.wikipedia.org/wiki/Advanced_Encryption_Standard)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen)](https://github.com/theultimatederek/ShadowChat/pulls)

> **ShadowChat** is a futuristic stealth-themed Android encrypted messaging prototype built using Java and AES cryptography. Every message is encrypted client-side before display, with full cipher text visualization and an intelligent simulated secure relay node.

---

## ✨ Features

- 🔐 **AES Encryption & Decryption** — every message encrypted before transmission
- 👁️ **Cipher Text Visualization** — see the raw encrypted payload for every message
- 🤖 **SHADOW_NODE AI Relay** — intelligent context-aware encrypted reply simulation
- 💬 **Startup Secure Tunnel** — animated system boot sequence on launch
- 📋 **Copy Cipher** — tap any message to copy the encrypted text to clipboard
- 📊 **Strength Bar Animation** — visual encryption strength indicator per message
- 🛡️ **Security Badge** — confirmed encryption status shown after each message
- 🌑 **Stealth Dark UI** — black + electric blue cybersecurity themed interface
- ⌨️ **Typing Simulation** — realistic SHADOW_NODE typing delay before reply
- 📜 **System Log** — live system log feed showing encryption events

---

## 📸 Screenshots

| Boot Sequence | Chat Screen | Encrypted Message |
|---|---|---|
| Secure tunnel init | Dark stealth UI | Cipher + plaintext view |

---

## 🚀 How to Run

### Prerequisites
- Android Studio (any recent version)
- Android device or emulator API 24+

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/theultimatederek/ShadowChat.git

# 2. Open in Android Studio
File → Open → Select ShadowChat folder

# 3. Let Gradle sync (1-2 minutes)

# 4. Run on emulator or device
Click ▶ Run or press Shift+F10
```

---

## 🗂️ Project Structure

```
ShadowChat/
├── app/src/main/
│   ├── java/com/example/shadowchat/
│   │   ├── EncryptionHelper.java   # AES encrypt/decrypt engine
│   │   └── MainActivity.java       # Chat UI + SHADOW_NODE relay logic
│   │
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml        # Main chat screen
│   │   │   ├── item_message_sent.xml    # Sent bubble layout
│   │   │   └── item_message_received.xml # Received bubble layout
│   │   ├── drawable/                    # Bubble shapes, button styles
│   │   └── values/
│   │       ├── colors.xml
│   │       ├── strings.xml
│   │       └── themes.xml               # Dark stealth theme
│   │
│   └── AndroidManifest.xml
│
├── build.gradle
├── settings.gradle
└── README.md
```

---

## 🔒 Encryption Workflow

```
User types message
       ↓
EncryptionHelper.encrypt(plaintext)
       ↓
AES cipher → Base64 encoded ciphertext
       ↓
Both cipher + plaintext shown in bubble
       ↓
SHADOW_NODE generates reply → same encryption applied
       ↓
Tap bubble → copy raw ciphertext to clipboard
```

---

## ⚙️ Tech Stack

| Component | Technology |
|---|---|
| Language | Java 11 |
| Min SDK | API 24 (Android 7.0) |
| Encryption | AES via `javax.crypto` |
| UI | XML layouts + Material Components |
| Animation | ViewPropertyAnimator |

---

## ⚠️ Security Notes & Key Storage Risks

This is an **educational prototype**. Current implementation:

| Aspect | Current | Production Recommendation |
|---|---|---|
| Key storage | Hardcoded in source | Android Keystore (hardware-backed) |
| IV | None (ECB mode) | Random 96-bit IV per message (GCM) |
| Mode | AES-ECB | AES-GCM (authenticated) |
| Key exchange | N/A | Diffie-Hellman / Signal Protocol |

> ⚠️ Hardcoded keys are a known risk — exposed in source code and APK. Production apps must use Android Keystore to store keys in a hardware-backed enclave.

---

## 🤝 Contributing

1. Fork the repo
2. Create your branch: `git checkout -b feature/your-feature`
3. Commit: `git commit -m "Add: feature"`
4. Push: `git push origin feature/your-feature`
5. Open a Pull Request

---

## 📄 License

MIT License — see [LICENSE](LICENSE) for details.

---

```
⭐ Star this repo if you found it useful!
```
