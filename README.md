
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

## 🧩 Roadmap

* [x] Basic ADB / Fastboot integration
* [x] Web interface prototype
* [ ] Predefined command support
* [ ] Command history storage
* [ ] User authentication and access control
* [ ] Multi-device support
* [ ] Advanced error handling

---

## 🧑‍💻 Contributing

Pull requests and issue reports are welcome. For major changes, please open an issue first to discuss what you would like to change.

---

## 📜 License

This project is licensed under the [GNU General Public License v3.0](LICENSE).

---

## 📫 Contact

For any questions, suggestions, or contributions, please reach out via [darkrange6@gmail.com](mailto:darkrange6@gmail.com).
