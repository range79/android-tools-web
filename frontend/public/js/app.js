const API_BASE_URL = 'http://localhost:8080/api';
let selectedDevice = null;
let adbDevices = [];
let fastbootDevices = [];
let firmwareFiles = [];

const deviceListEl = document.getElementById('deviceList');
const consoleOutputEl = document.getElementById('consoleOutput');
const commandInputEl = document.getElementById('commandInput');
const backendStatusEl = document.getElementById('backendStatus');
const firmwareListEl = document.getElementById('firmwareList');
const flashTargetDeviceEl = document.getElementById('flashTargetDevice');
const flashFirmwareFileEl = document.getElementById('flashFirmwareFile');
const errorSummaryEl = document.getElementById('errorSummary');

async function init() {
    checkBackendStatus();
    refreshDevices();
    loadFirmwareList();
    loadErrorLogs();

    commandInputEl.addEventListener('keypress', (e) => {
        if (e.key === 'Enter' && commandInputEl.value.trim()) {
            executeCustomCommand(commandInputEl.value.trim());
            commandInputEl.value = '';
        }
    });

    document.getElementById('firmwareFile').addEventListener('change', handleFileUpload);
}

function switchTab(tabId) {
    document.querySelectorAll('.tab-content').forEach(el => el.classList.add('hidden'));
    document.getElementById(`tab-${tabId}`).classList.remove('hidden');

    document.querySelectorAll('.tab-btn').forEach(btn => {
        if (btn.dataset.tab === tabId) {
            btn.classList.add('active', 'text-white');
            btn.classList.remove('text-gray-400');
        } else {
            btn.classList.remove('active', 'text-white');
            btn.classList.add('text-gray-400');
        }
    });

    if (tabId === 'flasher') {
        populateFlashSelectors();
    }
}

async function checkBackendStatus() {
    try {
        const res = await fetch(`${API_BASE_URL}/adb/devices/all`);
        if (res.ok) setStatus(true);
    } catch {
        setStatus(false);
    }
}

function setStatus(online) {
    backendStatusEl.innerHTML = online
        ? `<span class="w-2 h-2 bg-android rounded-full"></span><span class="text-android">Backend Connected</span>`
        : `<span class="w-2 h-2 bg-red-500 rounded-full animate-pulse"></span><span>Backend Disconnected</span>`;
}

async function refreshDevices() {
    try {
        const [adbRes, fbRes] = await Promise.all([
            fetch(`${API_BASE_URL}/adb/devices/all`),
            fetch(`${API_BASE_URL}/fastboot/device/all`)
        ]);

        adbDevices = await adbRes.json();
        fastbootDevices = await fbRes.json();

        renderDeviceList();
    } catch (err) {
        appendToConsole(`Error: Could not fetch devices.`, 'error');
    }
}

function renderDeviceList() {
    const all = [...adbDevices.map(d => ({ ...d, mode: 'adb' })), ...fastbootDevices.map(d => ({ ...d, mode: 'fastboot' }))];
    deviceListEl.innerHTML = all.length ? '' : '<div class="text-center py-8 text-gray-500 italic text-sm">No devices found.</div>';

    all.forEach(device => {
        const div = document.createElement('div');
        div.className = `p-4 rounded-2xl border cursor-pointer transition-all ${selectedDevice === device.serial ? 'bg-android/20 border-android/50' : 'bg-white/5 border-white/10 hover:border-android/30'}`;
        div.innerHTML = `
            <div class="flex justify-between items-start">
                <div>
                    <div class="font-bold text-sm text-white">${device.model || 'Unknown Device'}</div>
                    <div class="text-[10px] font-mono text-gray-400">${device.serial}</div>
                </div>
                <span class="px-2 py-0.5 rounded text-[10px] uppercase font-bold ${device.mode === 'fastboot' ? 'bg-blue-500/20 text-blue-400' : 'bg-android/20 text-android'}">
                    ${device.mode}
                </span>
            </div>
        `;
        div.onclick = () => { selectedDevice = device.serial; renderDeviceList(); appendToConsole(`Device Selected: ${device.serial}`, 'system'); };
        deviceListEl.appendChild(div);
    });
}

async function loadFirmwareList() {
    try {
        const res = await fetch(`${API_BASE_URL}/firmware/all`);
        firmwareFiles = await res.json();
        renderFirmwareList();
    } catch (err) {
        console.error('Firmware list loading failed');
    }
}

function renderFirmwareList() {
    firmwareListEl.innerHTML = firmwareFiles.length ? '' : '<div class="text-center py-8 text-gray-500 italic text-sm">No firmware found.</div>';
    firmwareFiles.forEach(file => {
        const div = document.createElement('div');
        div.className = "flex items-center justify-between p-3 bg-white/5 border border-white/10 rounded-2xl hover:border-android/30 transition-all";
        div.innerHTML = `
            <div class="truncate mr-4">
                <div class="text-sm font-medium truncate">${file.name}</div>
                <div class="text-[10px] text-gray-500">${(file.size / 1024 / 1024).toFixed(2)} MB</div>
            </div>
            <button onclick="deleteFirmware('${file.id}')" class="p-2 text-gray-500 hover:text-red-400 transition-colors">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"></path></svg>
            </button>
        `;
        firmwareListEl.appendChild(div);
    });
}

async function handleFileUpload(e) {
    const file = e.target.files[0];
    if (!file) return;

    const formData = new FormData();
    formData.append('file', file);

    const progressArea = document.getElementById('uploadProgress');
    const progressBar = document.getElementById('progressBar');
    const progressText = document.getElementById('progressText');

    progressArea.classList.remove('hidden');
    progressText.innerText = "Uploading: " + file.name;

    try {
        const res = await fetch(`${API_BASE_URL}/firmware/rom/upload`, {
            method: 'POST',
            body: formData
        });

        if (res.ok) {
            appendToConsole(`Success: ${file.name} uploaded.`, 'success');
            loadFirmwareList();
        } else {
            appendToConsole(`Error: Upload failed.`, 'error');
        }
    } catch (err) {
        appendToConsole(`Connection Error: ${err.message}`, 'error');
    } finally {
        progressArea.classList.add('hidden');
        progressBar.style.width = '0%';
    }
}

async function deleteFirmware(id) {
    if (!confirm('Are you sure you want to delete this firmware?')) return;
    try {
        const res = await fetch(`${API_BASE_URL}/firmware/${id}`, { method: 'DELETE' });
        if (res.ok) {
            firmwareFiles = firmwareFiles.filter(f => f.id !== id);
            renderFirmwareList();
            appendToConsole('Firmware deleted.', 'system');
        }
    } catch (err) {
        appendToConsole('Error: Delete operation failed.', 'error');
    }
}

function populateFlashSelectors() {
    flashTargetDeviceEl.innerHTML = [...adbDevices, ...fastbootDevices].map(d => `<option value="${d.serial}">${d.model || d.serial} (${d.serial})</option>`).join('');
    flashFirmwareFileEl.innerHTML = firmwareFiles.map(f => `<option value="${f.id}">${f.name}</option>`).join('');
}

async function startFlashing() {
    const deviceId = flashTargetDeviceEl.value;
    const firmwareId = flashFirmwareFileEl.value;
    const partition = document.getElementById('flashPartition').value;

    if (!deviceId || !firmwareId) return alert('Please select device and firmware.');

    appendToConsole(`FLASHING STARTED...`, 'warning');
    appendToConsole(`Target: ${deviceId}, Firmware: ${firmwareId}`, 'info');

    const isFastboot = fastbootDevices.some(d => d.serial === deviceId);
    let endpoint = isFastboot
        ? `${API_BASE_URL}/fastboot/flash/${deviceId}/${firmwareId}/${partition}`
        : `${API_BASE_URL}/adb/flash/${deviceId}/${firmwareId}`;

    try {
        const res = await fetch(endpoint, { method: 'POST' });
        const result = await res.json();
        if (res.ok) appendToConsole(`Flash Completed: ${result.message}`, 'success');
        else appendToConsole(`Flash Error: ${result.error}`, 'error');
    } catch (err) {
        appendToConsole(`Request Error: ${err.message}`, 'error');
    }
}

async function sendCommand(action) {
    if (!selectedDevice) return appendToConsole('Error: No device selected.', 'error');

    const isAdb = adbDevices.some(d => d.serial === selectedDevice);
    const prefix = isAdb ? 'adb' : 'fastboot';

    let option = action.replace('reboot-', '').replace('adb-', '');
    if (option === 'reboot') option = 'system';

    const endpoint = `${API_BASE_URL}/${prefix}/${selectedDevice}/reboot/${option}`;

    try {
        appendToConsole(`Sending command: ${prefix} reboot ${option}...`, 'info');
        const res = await fetch(endpoint, { method: 'GET' });
        if (res.ok) appendToConsole('Command sent successfully.', 'success');
        else appendToConsole('Error: Command could not be sent.', 'error');
    } catch (err) {
        appendToConsole(`Error: ${err.message}`, 'error');
    }
}

async function triggerBackup(type) {
    try {
        appendToConsole(`${type} backup started...`, 'info');
        const res = await fetch(`${API_BASE_URL}/backup/${type}`, { method: 'POST' });
        if (res.ok) appendToConsole(`Backup successful (${type})`, 'success');
    } catch (err) {
        appendToConsole(`Backup error: ${err.message}`, 'error');
    }
}

async function removePartition() {
    const pName = document.getElementById('partitionName').value.trim();
    if (!selectedDevice || !pName) return alert('Device and partition name required.');

    if (!confirm(`Are you sure you want to delete ${pName} partition?`)) return;

    try {
        appendToConsole(`Deleting ${pName}...`, 'warning');
        const res = await fetch(`${API_BASE_URL}/fastboot/${selectedDevice}/remove/partition/${pName}`, { method: 'DELETE' });
        if (res.ok) appendToConsole(`${pName} deleted successfully.`, 'success');
    } catch (err) {
        appendToConsole(`Delete error: ${err.message}`, 'error');
    }
}

async function loadErrorLogs() {
    try {
        const res = await fetch(`${API_BASE_URL}/error/all`);
        const errors = await res.json();
        errorSummaryEl.innerHTML = errors.length
            ? `<div class="text-red-400 font-bold">${errors.length} recorded errors.</div>`
            : "System clean, no recorded errors.";
    } catch (err) {
        errorSummaryEl.innerText = "Could not load error logs.";
    }
}

async function clearSystemErrors() {
    if (!confirm('Do you want to clear all error logs?')) return;
    try {
        const res = await fetch(`${API_BASE_URL}/error/all`, { method: 'DELETE' });
        if (res.ok) {
            appendToConsole('All system errors cleared.', 'system');
            loadErrorLogs();
        }
    } catch (err) {
        appendToConsole('Error clearing failed.', 'error');
    }
}

function appendToConsole(text, type = 'default') {
    const div = document.createElement('div');
    const time = new Date().toLocaleTimeString([], { hour12: false });
    const colors = { error: 'text-red-400', success: 'text-android', info: 'text-blue-400', system: 'text-yellow-400', warning: 'text-orange-400', input: 'text-white' };
    div.className = `${colors[type] || 'text-gray-300'} text-[12px] leading-tight`;
    div.innerHTML = `<span class="text-gray-600 mr-2">${time}</span> ${text}`;
    consoleOutputEl.appendChild(div);
    consoleOutputEl.scrollTop = consoleOutputEl.scrollHeight;
}

function clearConsole() { consoleOutputEl.innerHTML = ''; }

window.onload = init;
