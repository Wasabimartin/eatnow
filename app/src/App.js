import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';

const App = () => {

  const [drinks, setDrinks] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);

    fetch('api/drinks')
      .then(response => response.json())
      .then(data => {
        setDrinks(data);
        setLoading(false);
      })
  }, []);

  if (loading) {
    return <p>Loading...</p>;
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <div className="App-intro">
          <h2>Drink List</h2>
          {drinks.map(drink =>
            <div key={drink.id}>
              {drink.id} - {drink.name}
            </div>
          )}
        </div>
      </header>
    </div>
  );
}

export default App;