import React from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import DrinkList from './DrinkList';
import DrinkEdit from './DrinkEdit';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Home/>}/>
        <Route path='/drinks' exact={true} element={<DrinkList/>}/>
        <Route path='/drinks/:id' exact={true} element={<DrinkEdit/>}/>
      </Routes>
    </Router>
  )
}

export default App;