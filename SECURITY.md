# Security Policy

We take the security and integrity of the AI Deal Sniper Engine seriously. Because this application processes real-time network webhooks, integrates third-party API gateways, and handles live web traffic, keeping credentials secure is a primary concern.

---

## 🛡️ Supported Versions

Only the latest stable release of the engine receives security updates and patches. If you are running an older iteration, please update to the current version on the `main` branch immediately.

| Version | Supported |
| :--- | :--- |
| v1.5.x | ✅ Yes |
| < v1.4.0 | ❌ No |

---

## 🚨 Reporting a Vulnerability

**Do not open a public GitHub Issue for security-related flaws or credential leaks.** If you discover a security vulnerability (such as a buffer exploit in the custom JSON tokenizer, a crash vector, or an open relay risk), please report it responsibly:

1. Send a private message or email directly to the repository maintainer.
2. Provide a detailed description of the vulnerability, including a proof of concept (PoC) or steps to reproduce the issue safely.
3. Allow up to 48 hours for a response and a structural review before taking any public action.

Once validated, a patch will be merged directly into the `main` branch, and a security advisory will be logged in the release documentation.

---

## 🔑 Hardcoded Token Prevention (Critical Guidelines)

The most common security vulnerability for this type of system is the **accidental exposure of private API credentials**. Please follow these mandatory deployment precautions:

* **Token Isolation:** Never commit your live `WHAPI_TOKEN` or personal phone IDs directly to a public GitHub branch. The variable spaces inside `WebhookServer.java` are explicitly meant for local execution testing only.
* **Production Recommendation:** For public hosting or production instances, modify the credentials to pull dynamically from system environment variables or an untracked local properties configuration:
  ```java
  // Recommended production credential mapping
  private static final String WHAPI_TOKEN = System.getenv("WHAPI_API_TOKEN");
