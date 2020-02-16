import React, { useState, useEffect } from 'react';
import logo from './logo.svg';
import './App.css';


const WebMapping = ({handler,predicate,details:{requestMappingConditions:{methods,patterns}}})=>{
    return <div>
        <div>{handler}</div>
        <div>{predicate}</div>
        <div>{methods}</div>
        <div>{patterns}</div>



    </div>
}

function App() {
   let [mappings,setMappings] = useState([]);
   let [appHealth,setAppHealth] = useState([]);
   useEffect(()=>{
    fetch("/actuator/mappings").then(r=>r.json()).then(r=>setMappings(r['contexts']['ExampleApp']['mappings']['dispatcherServlets']['dispatcherServlet']))
    fetch("/actuator/health").then(r=>r.json()).then(r=>setAppHealth(r))

   },[])
  return (
    <div className="App">
      <header className="App-header">
        <p>
          A Example Application With RabbitMQ Kafka MariaDb and Spring Boot
        </p>
        <a
          className="App-link"
          href="http://localhost:9999"
          target="_blank"
          rel="noopener noreferrer"
        >
          Adminer DB Management
        </a>
        <br/>
        <a
          className="App-link"
          href="http://localhost:15672"
          target="_blank"
          rel="noopener noreferrer"
        >
          RabbitMQ Management
        </a>
      </header>
      <div className="App-health-view">
        <div className="App-Actuator-Health">
                  <h2>App Health</h2>
                  {JSON.stringify(appHealth)}
        </div>
        <div className="App-Actuator-Mappings">
            <h2>App Mappings</h2>
            {mappings.map(mapping=><><WebMapping {...mapping}/><br/></>)}
        </div>
      </div>
    </div>
  );
}

export default App;
