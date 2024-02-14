import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label, Row, Col, FormText} from 'reactstrap';
import Select from "react-select";


import AppNavbar from './AppNavbar';

const FoodEdit = () => {
  const initialFormState = {
    name: '',
    id: '',
    price: 0,
    category: '',
    details: '',
  };
  const options = [
        { value: "Starter", label: "Starter" },
        { value: "Main", label: "Main" },
        { value: "Dessert", label: "Dessert" },
      ];
  const [selected, setSelected] = useState(null);
  const [food, setFood] = useState(initialFormState);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (id !== 'new') {
      fetch(`/api/food/${id}`)
        .then(response => response.json())
        .then(data => setFood(data));
    }
  }, [id, setFood]);

    useEffect(() => {
        if (food.category) {
          const selectedOption = options.find(option => option.value === food.category);
          setSelected(selectedOption);
        }
      }, [food.category]);

  const handleChange = (event) => {
    const { name, value } = event.target
    setFood({ ...food, [name]: value })
  }

  const handleDropdown = (selectedOption) => {
      setSelected(selectedOption);
      const name = "category";
      const value = selectedOption.value;
      setFood({ ...food, [name]: value })
    };


  const handleSubmit = async (event) => {
    event.preventDefault();

    await fetch(`/api/food${food.id ? `/${food.id}` : ''}`, {
      method: (food.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(food)
    });
    setFood(initialFormState);
    navigate('/foods');
  }

  const title = <h2>{food.id ? 'Edit Food' : 'Add Food'}</h2>;


  return (<div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={handleSubmit}>
        <Row>
                    <Col md={6}>
          <FormGroup>
            <Label for="name">Name</Label>
            <Input required type="text" name="name" id="name" value={food.name || ''}
                   onChange={handleChange} autoComplete="name"/>
          </FormGroup>
    </Col>
                        <Col md={6}>

                                  <FormGroup>
                                    <Label for="category">Category</Label>
                                    <Select required options={options} onChange={handleDropdown} value={selected}/>
                                  </FormGroup>
                                        </Col>
  </Row>
<Row>
                    <Col md={12}>
<FormGroup>
            <Label for="details">Details</Label>
            <Input required type="textarea" name="details" id="details" value={food.details || ''}
                   onChange={handleChange} />
          </FormGroup>

    </Col>
</Row>

<Row>
                    <Col md={6}>
<FormGroup>

                       <Label for="price">Price (€)</Label>
                                          <Input required type="number" name="price" id="price" value={food.price || ''}
                                                 onChange={handleChange} autoComplete="price"/>

                                          </FormGroup>

</Col>
</Row>
          <div className="row">

          </div>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/foods">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  )
};

export default FoodEdit;