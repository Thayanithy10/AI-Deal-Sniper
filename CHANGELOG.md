# Changelog

All notable changes to the **AI Deal Sniper Engine** project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and this project adheres to Semantic Versioning (`MAJOR.MINOR.PATCH`).

---

## [1.5.0] - 2026-06-20

### Added
* **Multi-Line JSON Tokenizer:** Re-engineered the native `extractJsonValue` string parsing method to cleanly process massive, multi-line text blocks containing escape characters (`\"`) and newline markers (`\n`).
* **Emoji Formatting Compatibility:** Enhanced payload isolation to seamlessly read complex administrator deal streams that include emojis, discount markdown metrics, and structural spacing without stripping trailing links.

### Fixed
* **Data Truncation Error:** Fixed a critical bug where multi-line chat logs would prematurely break string arrays, causing the link scanner to miss destination URLs placed at the bottom of long messages.

---

## [1.4.0] - 2026-05-12

### Added
* **Automated Link Radar:** Implemented a background Regex Scanner (`(https?://[^\\s\"'\\}\\],]+)`) that monitors all incoming messages inside target groups, automatically detecting URLs.

### Changed
* **Deprecated Manual Commands:** Completely removed the explicit `!snipe` prefix command requirement, transitioning the app to a fully commandless, passive monitoring asset.

---

## [1.3.0] - 2026-04-05

### Added
* **Regex Word Boundary Filters:** Introduced strict token validation patterns using boundary markers (`\b`) inside the wishlist matrix.
* **Fail-Fast Inventory Checks:** Added an early-exit DOM validation rule that looks for `#outOfStock` wrappers or `"Currently unavailable"` text layers.

### Fixed
* **False-Positive Glitch:** Resolved a text match issue where sub-string scanning (`.contains()`) erroneously triggered a wishlist hit for "AC" on children's toys containing structural terms like "dancing".
* **Cached Pricing Error:** Rectified an exception where out-of-stock items returned incorrect alternative prices (e.g., ₹799 instead of real status) by halting data extraction immediately upon stock depletion detection.

---

## [1.1.0] - 2026-02-18

### Added
* **Core Web Scraper:** Integrated the Jsoup DOM tree extraction framework to parse active Amazon desktop layouts (`.a-price-whole`).
* **ngrok Tunnel Configuration:** Configured local runtime hooks to accept external POST traffic forwarded from public ngrok endpoint mappings to local port `8080`.

---

## [1.0.0] - 2026-01-10

### Added
* **Initial Release:** Engineered the core event-driven backend application using native Java standard libraries (`HttpServer`, `HttpClient`).
* **Whapi Gateway Integration:** Established basic synchronous network handshake protocols to receive incoming WhatsApp messaging payloads and reply with automated text responses.
