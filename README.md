
# 📱 android-tools-web

![GitHub repo size](https://img.shields.io/github/repo-size/range79/android-tools-web?style=flat-square)
![GitHub issues](https://img.shields.io/github/issues/range79/android-tools-web?style=flat-square)
![GitHub license](https://img.shields.io/github/license/range79/android-tools-web?style=flat-square)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat-square&logo=kotlin&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![React](https://img.shields.io/badge/React-20232A?style=flat-square&logo=react&logoColor=61DAFB)
![Tailwind CSS](https://img.shields.io/badge/Tailwind_CSS-38B2AC?style=flat-square&logo=tailwind-css&logoColor=white)
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
## You can follow the commits from this Discord [channel.](https://discord.gg/pbG2E9Qkqv)


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

Then, open your browser and navigate to `http://localhost:8080/swagger-ui/index.html` to access the swagger-ui.

---


## 🧩 Project Roadmap

* [x] **Basic ADB / Fastboot Integration**
  Established the core infrastructure for communicating with Android devices using ADB and Fastboot. Enables basic command execution and device recognition.

* [x] **Fastboot Reboot API**
  Implemented a REST API endpoint to reboot devices in fastboot mode directly through the backend service.

* [x] **Fastboot Device Saving**
  Added functionality to save fastboot device information such as serial number, codename, unlock status, and device type into the database.

* [X] **ADB Reboot API**
  Developed a REST API to trigger device reboots using ADB, allowing remote reboot functionality.

* [X] **Fastboot Image Removal**
  To be implemented: remove or erase specific device partitions (e.g., userdata, cache) through Fastboot via API.

* [x] **Advanced Error Handling**
  Improve error detection and handling for device communication issues, with clear error responses, logging, and graceful fallbacks.




## 🧑‍💻 Contributing

Pull requests and issue reports are welcome. For major changes, please open an issue first to discuss what you would like to change.

---

## 📜 License

This project is licensed under the [GNU General Public License v3.0](LICENSE).

---

## For Contributors click [here](./CONTRIBUTORS.md)

---

## 📫 Contact

For any questions, suggestions, or contributions, please reach out via [darkrange6@gmail.com](mailto:darkrange6@gmail.com).
