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

  if (loading) {
    return <p>Loading...</p>;
  }

  const foodList = foods.map(food => {
    return <tr key={food.id}>
      <td style={{whiteSpace: 'nowrap'}}>{food.name}</td>
                  <td style={{whiteSpace: 'nowrap'}}>{food.category}</td>

            <td style={{whiteSpace: 'nowrap'}}>{food.price}</td>
      <td>
        <ButtonGroup>
          <Button size="sm" color="primary" tag={Link} to={"/foods/" + food.id}>Edit</Button>
          <Button size="sm" color="danger" onClick={() => remove(food.id)}>Delete</Button>
        </ButtonGroup>
      </td>
    </tr>
  });

  return (
    <div>
      <AppNavbar/>
      <Container fluid>
        <div className="float-end">
          <Button color="success" tag={Link} to="/foods/new">Add Food</Button>
        </div>
        <h3>All Food</h3>
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
      </Container>
    </div>
  );
};

export default FoodList;