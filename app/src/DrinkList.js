import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

const DrinkList = () => {

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

  const remove = async (id) => {
    await fetch(`/api/drink/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedDrinks = [...drinks].filter(i => i.id !== id);
      setDrinks(updatedDrinks);
    });
  }

  if (loading) {
    return <p>Loading...</p>;
  }

  const drinkList = drinks.map(drink => {
    return <tr key={drink.id}>
      <td style={{whiteSpace: 'nowrap'}}>{drink.id}</td>
      <td style={{whiteSpace: 'nowrap'}}>{drink.name}</td>
      <td style={{whiteSpace: 'nowrap'}}>{drink.size} {drink.unit}</td>
      <td>
        <ButtonGroup>
          <Button size="sm" color="primary" tag={Link} to={"/drinks/" + drink.id}>Edit</Button>
          <Button size="sm" color="danger" onClick={() => remove(drink.id)}>Delete</Button>
        </ButtonGroup>
      </td>
    </tr>
  });

  return (
    <div>
      <AppNavbar/>
      <Container fluid>
        <div className="float-end">
          <Button color="success" tag={Link} to="/drinks/new">Add Drink</Button>
        </div>
        <h3>All Drinks</h3>
        <Table className="mt-4">
          <thead>
          <tr>
            <th width="5%">ID</th>
            <th width="10%">Drink</th>
            <th width="5%">Size</th>
            <th width="10%">Actions</th>
          </tr>
          </thead>
          <tbody>
          {drinkList}
          </tbody>
        </Table>
      </Container>
    </div>
  );
};

export default DrinkList;