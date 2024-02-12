import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

const DrinkEdit = () => {
  const initialFormState = {
    name: '',
    id: ''
  };
  const [drink, setDrink] = useState(initialFormState);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (id !== 'new') {
      fetch(`/api/drink/${id}`)
        .then(response => response.json())
        .then(data => setDrink(data));
    }
  }, [id, setDrink]);

  const handleChange = (event) => {
    const { name, value } = event.target

    setDrink({ ...drink, [name]: value })
  }

  const handleSubmit = async (event) => {
    event.preventDefault();

    await fetch(`/api/drink${drink.id ? `/${drink.id}` : ''}`, {
      method: (drink.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(drink)
    });
    setDrink(initialFormState);
    navigate('/drinks');
  }

  const title = <h2>{drink.id ? 'Edit Drink' : 'Add Drink'}</h2>;

  return (<div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={handleSubmit}>
          <FormGroup>
            <Label for="name">Name</Label>
            <Input type="text" name="name" id="name" value={drink.name || ''}
                   onChange={handleChange} autoComplete="name"/>
          </FormGroup>


          <div className="row">

          </div>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/drinks">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  )
};

export default DrinkEdit;