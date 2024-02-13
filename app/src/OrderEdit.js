import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label, Row, Col} from 'reactstrap';

import AppNavbar from './AppNavbar';

const OrderEdit = () => {
  const initialFormState = {
    id: '',
    totalprice: 0,
  };


  const [order, setOrder] = useState(initialFormState);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (id !== 'new') {
      fetch(`/api/order/${id}`)
        .then(response => response.json())
        .then(data => setOrder(data));
    }
  }, [id, setOrder]);



  const handleChange = (event) => {
    const { name, value } = event.target
    setOrder({ ...order, [name]: value })
  }


  const handleSubmit = async (event) => {
    event.preventDefault();

    await fetch(`/api/order${order.id ? `/${order.id}` : ''}`, {
      method: (order.id) ? 'PUT' : 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(order)
    });
    setOrder(initialFormState);
    navigate('/orders');
  }

  const title = <h2>{order.id ? 'Edit Order' : 'Add Order'}</h2>;


  return (<div>
      <AppNavbar/>
      <Container>
        {title}
        <Form onSubmit={handleSubmit}>
        <Row>
                    <Col md={6}>
           <FormGroup>
              <Label for="totalprice">Total Price (€)</Label>
              <Input disabled type="number" name="totalprice" id="totalprice" value={order.totalprice || ''}
                                           onChange={handleChange} autoComplete="totalprice"/>
           </FormGroup>
        </Col>
        <Col md={6}>
        </Col>
  </Row>
<Row>
        <Col md={6}>
        </Col>
</Row>
          <div className="row">

          </div>
          <FormGroup>
            <Button color="primary" type="submit">Save</Button>{' '}
            <Button color="secondary" tag={Link} to="/orders">Cancel</Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  )
};

export default OrderEdit;