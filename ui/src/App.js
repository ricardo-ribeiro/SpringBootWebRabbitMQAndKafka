import React, { useState, useEffect } from "react";
import logo from "./logo.svg";
import "./App.css";

const WebMapping = ({
  handler,
  predicate,
  details: {
    requestMappingConditions: { methods, patterns }
  }
}) => {
  return (
    <div style={{ display: "flex", justifyContent: "center" }}>
      {methods.map(method => {
        return (
          <div
            style={{
              margin: 10,
              padding: 5,
              borderRadius: 5,
              backgroundColor:
                method == "GET"
                  ? "green"
                  : method == "POST"
                  ? "orange"
                  : method == "DELETE"
                  ? "red"
                  : "grey",
              maxWidth: 100
            }}
          >
            {method}
          </div>
        );
      })}
      <div style={{ margin: 10, maxWidth: 300, minWidth: 300 }}>{patterns}</div>
    </div>
  );
};

function App() {
  let [mappings, setMappings] = useState([]);
  let [appHealth, setAppHealth] = useState([]);
  useEffect(() => {
    fetch("/actuator/mappings")
      .then(r => r.json())
      .then(r =>
        setMappings(
          r["contexts"]["ExampleApp"]["mappings"]["dispatcherServlets"][
            "dispatcherServlet"
          ]
        )
      );
    fetch("/actuator/health")
      .then(r => r.json())
      .then(r => setAppHealth(r));
  }, []);
  return (
    <div className="App">
      <header className="App-header">
        <p>A Example Application With RabbitMQ Kafka MariaDb and Spring Boot</p>
        <a
          className="App-link"
          href="http://localhost:9999"
          target="_blank"
          rel="noopener noreferrer"
        >
          Adminer DB Management
        </a>
        <br />
        <a
          className="App-link"
          href="http://localhost:15672"
          target="_blank"
          rel="noopener noreferrer"
        >
          RabbitMQ Management
        </a>
        <br />
        <a
          className="App-link"
          href="http://localhost:9998"
          target="_blank"
          rel="noopener noreferrer"
        >
          MailHog Management
        </a>
      </header>
      <div className="App-health-view">
        <div className="App-Actuator-Health">
          <br></br>
          <h2>App Health</h2>
          {JSON.stringify(appHealth)}
          <br></br>
          <br></br>
        </div>
        <div className="App-Actuator-Mappings">
          <br></br>
          <h2>App Mappings</h2>
          <br></br>
          {mappings
            .filter(m => m.details != null)
            .map(mapping => (
              <>
                <WebMapping {...mapping} />
                <br />
              </>
            ))}
        </div>
      </div>
    </div>
  );
}

export default App;
