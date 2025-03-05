import React, { useState } from "react";
import axios from "axios";
import "./App.css";

function App() {
  const [inputData, setInputData] = useState("");
  const [responseData, setResponseData] = useState(null);
  const [fileName, setFileName] = useState("responseData.json");

  const handleFileUpload = (event) => {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();

      reader.onload = () => {
        try {
          const jsonData = JSON.parse(reader.result);
          setInputData(JSON.stringify(jsonData, null, 2));
        } catch (error) {
          alert("Błąd wczytywania pliku JSON. Upewnij się, że plik jest poprawny.");
        }
      };

      reader.readAsText(file);
    }
  };


  const handleSendData = async () => {
    try {
      const jsonData = JSON.parse(inputData);
      const res = await axios.post("/api/simulation/run", jsonData, {
        headers: { "Content-Type": "application/json" },
      });
      setResponseData(res.data);
    } catch (error) {
      console.error("Błąd:", error);
      alert("Niepoprawny JSON lub problem z backendem!");
    }
  };

  const downloadJson = () => {
    const blob = new Blob([JSON.stringify(responseData, null, 2)], {
      type: "application/json",
    });
    const url = URL.createObjectURL(blob);
    const link = document.createElement("a");
    link.href = url;
    link.download = fileName;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    URL.revokeObjectURL(url);
  };

  return (
      <div className="container">
        <div className="textField">
          <h1>Traffic Lights Simulation🚦</h1>
          <h3>Upload .json file: </h3>

          <input className="button"
                 type="file"
                 accept=".json"
                 onChange={handleFileUpload}
                 style={{marginTop: "10px"}}
          />
          <br/>
          <h3>Choose .json filename: </h3>

          {/* Pole do wpisania nazwy pliku */}
          <input
              className="textField"
              type="text"
              value={fileName}
              onChange={(e) => setFileName(e.target.value)}
              placeholder="Enter output filename"
              style={{marginTop: "10px", padding: "5px"}}
          />

          <br/>
          <button
              className='button'
              onClick={handleSendData}
              style={{marginTop: "10px", padding: "10px"}}
          >
            Send to backend and generate response
          </button>

          {responseData && (
              <div style={{marginTop: "20px"}}>
                <button
                    className="button"
                    onClick={downloadJson}
                    style={{marginTop: "10px", padding: "10px", backgroundColor: "#4CAF50", color: "white"}}
                >
                  Download JSON OUTPUT
                </button>
                <h3>Backend response:</h3>
                <pre>{JSON.stringify(responseData, null, 2)}</pre>


              </div>
          )}
        </div>
      </div>
  );
}

export default App;
