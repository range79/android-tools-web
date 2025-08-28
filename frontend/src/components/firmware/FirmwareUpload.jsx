import React, { useState } from 'react';
import { uploadFirmware } from "../../api/firmware.js";

const FirmwareUpload = () => {
  const [firmwareType, setFirmwareType] = useState('');
  const [file, setFile] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleUpload = async () => {
    if (!firmwareType || !file) {
      alert("Please select firmware type and file before uploading.");
      return;
    }
    setLoading(true);
    try {
      await uploadFirmware(firmwareType, file);
      alert("Upload successful!");
      setFirmwareType('');
      setFile(null);
    } catch (error) {
      alert("Upload failed!");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "1rem" }}>
      <h1>Firmware Upload</h1>

      <select
        name="firmwareType"
        id="firmwareType"
        value={firmwareType}
        onChange={(e) => setFirmwareType(e.target.value)}
      >
        <option value="" disabled>
          Select firmware type
        </option>
        <option value="ADB_ROM">ADB_ROM</option>
        <option value="UPDATE_ZIP">UPDATE_ZIP</option>
        <option value="FASTBOOT_ROM">FASTBOOT_ROM</option>
        <option value="RECOVERY_IMAGE">RECOVERY_IMAGE</option>
      </select>

      <label>Select ROM file</label>
      <input type="file" onChange={(e) => setFile(e.target.files[0])} />

      <button onClick={handleUpload} disabled={loading}>
        {loading ? "Uploading..." : "Upload Firmware"}
      </button>
    </div>
  );
};

export default FirmwareUpload;
