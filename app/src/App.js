import React from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import DrinkList from './DrinkList';
import DrinkEdit from './DrinkEdit';
import FoodList from './FoodList';
import FoodEdit from './FoodEdit';
import OrderList from './OrderList';
import OrderEdit from './OrderEdit';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Home/>}/>
        <Route path='/drinks' exact={true} element={<DrinkList/>}/>
        <Route path='/drinks/:id' exact={true} element={<DrinkEdit/>}/>
        <Route path='/foods' exact={true} element={<FoodList/>}/>
        <Route path='/foods/:id' exact={true} element={<FoodEdit/>}/>
        <Route path='/orders' exact={true} element={<OrderList/>}/>
        <Route path='/orders/:id' exact={true} element={<OrderEdit/>}/>
      </Routes>
    </Router>
  )
}

export default App;