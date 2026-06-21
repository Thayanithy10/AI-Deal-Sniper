# 🚀 AI Deal Sniper Engine

> **An event-driven Java backend that automatically monitors incoming WhatsApp deal messages, extracts product links, scrapes live product information, filters relevant deals, and instantly notifies users of matching products.**

---

# 📖 Overview

AI Deal Sniper Engine is an automated backend pipeline that continuously listens for incoming WhatsApp webhook events, detects e-commerce product links inside messages, scrapes live product information, performs intelligent keyword filtering, and immediately alerts the user whenever a desired product becomes available.

Instead of manually checking hundreds of promotional messages, the engine automatically identifies only the products matching the user's wishlist while ignoring irrelevant advertisements.

The entire system is designed around an event-driven architecture, making it fast, lightweight, and highly scalable.

---

# ✨ Features

- ⚡ Real-time WhatsApp webhook processing
- 🔍 Automatic URL extraction using Regular Expressions
- 🌐 Live product page scraping using Jsoup
- 🧠 Intelligent keyword filtering with word-boundary matching
- 🚫 Out-of-stock detection before parsing
- 📱 Instant notification delivery
- ⚙️ Lightweight Java HTTP Server
- 🔄 Event-driven backend architecture
- 📦 Minimal memory footprint
- 🚀 High-speed response pipeline

---

# 🏗 System Architecture

```text
                WhatsApp Group
                      │
                      ▼
              Whapi Cloud Webhook
                      │
                      ▼
              ngrok Secure Tunnel
                      │
                      ▼
              Java HTTP Server
                      │
                      ▼
           Message Processing Engine
                      │
          ┌───────────┴───────────┐
          ▼                       ▼
   URL Extraction           Ignore Invalid
      (Regex)
          │
          ▼
   Product Page Scraper
       (Jsoup)
          │
          ▼
 Product Information Parser
          │
          ▼
 Keyword Matching Engine
          │
          ▼
 Inventory Verification
          │
          ▼
 Notification Dispatcher
          │
          ▼
         User
```

---

# ⚙ Workflow

```
Webhook Received

      │

Extract Message

      │

Find Product URLs

      │

Download Product Page

      │

Parse HTML

      │

Extract Product Details

      │

Check Stock Status

      │

Keyword Matching

      │

Product Found

      │

Instant Notification
```

---

# 🛠 Technology Stack

| Technology | Purpose |
|------------|---------|
| Java 21 | Backend Development |
| Java HttpServer | Webhook Listener |
| Jsoup | HTML Parsing |
| Regex | URL Detection |
| Whapi | WhatsApp Webhook Service |
| ngrok | Secure Public Tunnel |
| Git | Version Control |
| GitHub | Repository Hosting |

---

# 📂 Project Structure

```
AI-Deal-Sniper/

│

├── src/
│   ├── Main.java
│   ├── WebhookServer.java
│   ├── DealParser.java
│   ├── RegexFilter.java
│   ├── NotificationService.java
│   └── Utils.java
│
├── README.md
│
├── LICENSE
│
└── .gitignore
```

---

# 🔄 Processing Pipeline

## 1️⃣ Webhook Reception

Incoming WhatsApp messages are forwarded to the Java server using Whapi through an ngrok tunnel.

---

## 2️⃣ Message Extraction

The incoming JSON payload is decoded to extract the complete text message.

---

## 3️⃣ URL Detection

Regular Expressions automatically locate all product URLs contained inside the message.

Example:

```
https://amazon.in/...
https://flipkart.com/...
```

---

## 4️⃣ Product Scraping

Each detected URL is downloaded using Jsoup.

The HTML DOM is parsed to retrieve:

- Product Name
- Price
- Availability
- Seller
- Product Category

---

## 5️⃣ Inventory Verification

Before performing expensive parsing operations, the engine checks for structural "Out of Stock" indicators.

This significantly reduces unnecessary processing time.

---

## 6️⃣ Keyword Matching

The extracted product title is compared against a configurable wishlist.

Example Keywords

```
Boat
OnePlus
Samsung
SSD
Monitor
RTX
Mechanical Keyboard
```

Word-boundary regex prevents false matches such as:

```
Searching:

AC

Incorrect Match:

dancing

Correct Match:

AC Stabilizer
```

---

## 7️⃣ Notification

When a matching product is found:

- Product Name
- Price
- URL
- Availability

are immediately delivered to the configured recipient.

---

# 📥 Example Incoming Message

```
🔥 Limited Time Deal

Boat Rockerz 450

₹699

https://amazon.in/example

Hurry before stock ends!
```

---

# 📤 Example Output

```
Product Found

Product :
Boat Rockerz 450

Price :
₹699

Availability :
In Stock

Matched Keyword :
Boat

Notification Sent Successfully
```

---

# 🚀 Installation

Clone the repository

```bash
git clone https://github.com/yourusername/AI-Deal-Sniper.git
```

Move into the project

```bash
cd AI-Deal-Sniper
```

Compile

```bash
javac Main.java
```

Run

```bash
java Main
```

---

# ⚙ Configuration

Update the following values before running:

- Whapi API Token
- Webhook Endpoint
- ngrok Public URL
- Target Phone Number
- Wishlist Keywords

---

# 📈 Performance Highlights

- Event-driven architecture
- Low latency processing
- Lightweight memory usage
- Automatic URL discovery
- Fast HTML parsing
- Efficient regex filtering
- Early inventory validation
- Easily extendable codebase

---

# 📌 Future Improvements

- Docker deployment
- PostgreSQL support
- MongoDB integration
- REST API dashboard
- Multi-user support
- Price history tracking
- Telegram integration
- Discord notifications
- AI-based deal ranking
- Duplicate deal detection
- Amazon, Flipkart & Myntra support
- Web dashboard
- Cloud deployment (AWS/Azure)

---

# 🤝 Contributing

Contributions are welcome!

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to your branch
5. Open a Pull Request

---

# 📜 License

This project is licensed under the **MIT License**.

---

# 👨‍💻 Author

**Thayanithy S**

Electronics & Communication Engineering 

Sri Manakula Vinayagar Engineering College

GitHub: https://github.com/Thayanithy10

---

## ⭐ Support

If you found this project useful, consider giving it a ⭐ on GitHub!

It helps others discover the project and supports future development.
