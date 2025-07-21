
# 📱 android-tools-web

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?logo=kotlin&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=spring-boot&logoColor=white)
![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)

---

## 🚀 About the Project

**android-tools-web** is a Kotlin-based Android web server that allows you to easily execute **Fastboot** and **ADB** commands through a web interface. It provides a simple, practical, and fast solution for developers who want to manage devices directly from their browser.

> **Note:** This project is still under active development. Bugs and missing features may exist.

---

## ✨ Features

- ✅ Run **Fastboot** commands from the web interface
- ✅ Run **ADB** commands from the web interface
- ✅ **Predefined Commands**: Automatically perform common operations (e.g., rebooting, unlocking, locking) with one click
- ✅ **Command History**: View previously executed commands
- ✅ **User-Friendly Interface**: Execute commands without complex terminal knowledge
- ✅ **Automation**: Predefined command sets for frequent tasks
- ⚙️ Powered by Spring Boot
- 📝 Modern codebase written in Kotlin

---

## 🛠️ Technologies Used

- **Kotlin**
- **Spring Boot**
- **ADB / Fastboot**
- **Gradle**

---

## 🎮 Predefined Commands

The web interface includes ready-to-use command sets to simplify frequent operations without requiring terminal expertise.

Example predefined commands:
- 📲 `adb devices`
- 🔄 `adb reboot`
- 🔓 `fastboot oem unlock`
- 🔒 `fastboot oem lock`
- ⚙️ `fastboot flash recovery recovery.img`

> More commands and customizable script support are planned.

---

## 📷 Screenshots

*(Screenshots will be added once development progresses)*

---

## 🏗️ Installation

> Installation steps may change as the project evolves.

### Requirements

- Java 17+
- Android platform-tools
- ADB and Fastboot binaries must be available in your system PATH

### Running the Application

```bash
git clone https://github.com/range79/android-tools-web.git
cd android-tools-web
./gradlew bootRun
````

Then, open your browser and navigate to `http://localhost:8080` to access the web interface.

---


## 🧩 Project Roadmap

* [x] **Basic ADB / Fastboot Integration**
  Established the core infrastructure for communicating with Android devices using ADB and Fastboot. Enables basic command execution and device recognition.

* [x] **Fastboot Reboot API**
  Implemented a REST API endpoint to reboot devices in fastboot mode directly through the backend service.

* [x] **Fastboot Device Saving**
  Added functionality to save fastboot device information such as serial number, codename, unlock status, and device type into the database.

* [ ] **ADB Reboot API**
  Developed a REST API to trigger device reboots using ADB, allowing remote reboot functionality.

* [ ] **ADB ROM Installation**
  To be implemented: ability to install custom ROMs or image files (ZIP or IMG) to a device using ADB commands via the API.

* [ ] **Fastboot Image Flashing**
  To be implemented: flash specific partitions (e.g., boot, system, recovery) using Fastboot and uploaded image files.

* [ ] **Fastboot Image Removal**
  To be implemented: remove or erase specific device partitions (e.g., userdata, cache) through Fastboot via API.

* [ ] **Web Interface Prototype**
  A basic web UI will be developed to allow users to view connected devices, send commands, and monitor activity through a user-friendly interface.

* [ ] **Predefined Command Execution**
  Frequently used ADB and Fastboot commands will be available through predefined buttons or shortcuts in the API and frontend.

* [ ] **Command History Logging**
  A full history of executed commands will be recorded per device and user, with timestamps for audit and debugging purposes.

* [ ] **User Authentication & Access Control**
  Implement secure login and authorization with token-based authentication and role-based access control to restrict features.

* [ ] **Multi-Device Support**
  Add support for managing and interacting with multiple connected devices simultaneously, with proper device isolation.

* [ ] **Advanced Error Handling**
  Improve error detection and handling for device communication issues, with clear error responses, logging, and graceful fallbacks.




## 🧑‍💻 Contributing

Pull requests and issue reports are welcome. For major changes, please open an issue first to discuss what you would like to change.

---

## 📜 License

This project is licensed under the [GNU General Public License v3.0](LICENSE).

---

## 📫 Contact

For any questions, suggestions, or contributions, please reach out via [darkrange6@gmail.com](mailto:darkrange6@gmail.com).
