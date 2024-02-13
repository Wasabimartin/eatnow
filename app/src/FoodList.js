import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

const FoodList = () => {
  const [foods, setFoods] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);

    fetch('api/foods')
      .then(response => response.json())
      .then(data => {
        setFoods(data);
        setLoading(false);
      })
  }, []);

  const remove = async (id) => {
    await fetch(`/api/food/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedFoods = [...foods].filter(i => i.id !== id);
      setFoods(updatedFoods);
    });
  }

  const renderFoodRow = (food) => {
    return (
      <tr key={food.id}>
        {renderTableCell(food.name)}
        {renderTableCell(food.category)}
        {renderTableCell(food.price)}
        <td>
          <ButtonGroup>
            <Button size="m" color="primary" tag={Link} to={`/foods/${food.id}`}>
              Edit
            </Button>
            <Button size="m" color="danger" onClick={() => remove(food.id)}>
              Delete
            </Button>
          </ButtonGroup>
        </td>
      </tr>
    );
  };

  const renderTableCell = (value) => {
    return <td style={{ whiteSpace: 'nowrap' }}>{value}</td>;
  };

  const renderTable = (title, category) => {
    const filteredFoods = foods.filter((food) => food.category === category);
    const foodList = filteredFoods.map(renderFoodRow);

    return (
      <>
        <h3>{title}</h3>
        <Table className="mt-4">
          <thead>
            <tr>
              <th width="10%">Food</th>
              <th width="5%">Category</th>
              <th width="5%">Price (€)</th>
              <th width="10%">Actions</th>
            </tr>
          </thead>
          <tbody>
            {foodList}
          </tbody>
        </Table>
      </>
    );
  };

  return (
    <div>
      <AppNavbar />
      <Container fluid>
        <div className="float-end">
          <Button color="success" tag={Link} to="/foods/new">
            Add Food
          </Button>
        </div>
        {renderTable('Starters', 'Starter')}
        {renderTable('Mains', 'Main')}
        {renderTable('Desserts', 'Dessert')}
      </Container>
    </div>
  );
};

export default FoodList;
