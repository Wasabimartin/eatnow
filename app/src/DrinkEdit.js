import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label, Row, Col} from 'reactstrap';
import Select from "react-select";


import AppNavbar from './AppNavbar';

const DrinkEdit = () => {
  const initialFormState = {
    name: '',
    id: '',
    alcohol: false,
    size: 0,
    unit: '',
  };
const options = [
      { value: "cl", label: "Centiliters (cl)" },
      { value: "ml", label: "Millilitres (ml)" },
      { value: "l", label: "Liters (l)" },
    ];
  const [selected, setSelected] = useState(null);
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

  useEffect(() => {
      if (drink.unit) {
        const selectedOption = options.find(option => option.value === drink.unit);
        setSelected(selectedOption);
      }
    }, [drink.unit]);

  const handleChange = (event) => {
    const { name, value } = event.target
    setDrink({ ...drink, [name]: value })
  }

  const handleDropdown = (selectedOption) => {
      setSelected(selectedOption);
      const name = "unit";
      const value = selectedOption.value;
      setDrink({ ...drink, [name]: value })
    };

    const handleCheckbox = (event) => {
        const { name, value, type, checked } = event.target;
        const inputValue = type === 'checkbox' ? checked : value;
        setDrink({ ...drink, [name]: inputValue })
        };


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
        <Row>
                    <Col md={6}>
          <FormGroup>
            <Label for="name">Name</Label>
            <Input type="text" name="name" id="name" value={drink.name || ''}
                   onChange={handleChange} autoComplete="name"/>
          </FormGroup>
    </Col>
                        <Col md={6}>
          <FormGroup className="d-flex flex-column">
            {/* Checkbox und Label werden untereinander angezeigt */}
            <Label className="form-check-label" for="alcohol" id="alcohol">Alcohol</Label>
            <Input type="checkbox" className="form-check-input" id="alcohol" name="alcohol"
                   checked={drink.alcohol} onChange={handleCheckbox} />

          </FormGroup>
    </Col>


  </Row>

        <Row>
                    <Col md={6}>
          <FormGroup>

            <Label for="size">Size</Label>
            <Input type="number" name="size" id="size" value={drink.size || ''}
                   onChange={handleChange} autoComplete="size"/>

            </FormGroup>
                </Col>
                    <Col md={6}>

          <FormGroup>
            <Label for="unit">Unit</Label>
            <Select options={options} onChange={handleDropdown} value={selected}/>
          </FormGroup>
                </Col>
  </Row>

    <Row>
        <Col md={6}>
         <FormGroup>

             <Label for="price">Price (€)</Label>
                                <Input type="number" name="price" id="price" value={drink.price || ''}
                                       onChange={handleChange} autoComplete="size"/>

                                </FormGroup>
        </Col>
</Row>


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