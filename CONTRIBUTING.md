# Contributing to AI Deal Sniper Engine

Thank you for taking the time to contribute! Projects like the AI Deal Sniper Engine thrive because of open-source contributors like you. 

To maintain code quality, system stability, and clear documentation, please read and follow these guidelines before submitting any changes.

---

## 🗺️ How Can I Contribute?

### 1. Reporting Bugs
If you encounter runtime crashes, string parsing exceptions, or webhook failures:
* Check the **Issues** tab to ensure the bug hasn't already been reported.
* Open a new issue and use a clear, descriptive title.
* Include your environment specs (Java version, OS) and paste the exact **PowerShell console log output**.
* **Do not** post logs that expose your live API tokens or personal phone numbers.

### 2. Suggesting Features
We want to expand the sniper's capabilities! Feel free to suggest:
* Support for new multi-vendor platforms (e.g., Flipkart, Myntra, Tata CLIQ).
* Advanced NLP modules to replace simple string tokenization.
* Alternative egress notification channels (e.g., Telegram, Discord, Signal).

### 3. Submitting Code Changes (Pull Requests)
Ready to write some Java code? Follow this workflow:
1. **Fork** the repository to your own GitHub account.
2. **Clone** your fork locally and create a descriptive branch:
```bash
   git checkout -b feature/add-flipkart-parser
