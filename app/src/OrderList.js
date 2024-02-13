import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

const OrderList = () => {

  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);

    fetch('api/orders')
      .then(response => response.json())
      .then(data => {
        setOrders(data);
        setLoading(false);
      })
  }, []);

  const remove = async (id) => {
    await fetch(`/api/order/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedOrders = [...orders].filter(i => i.id !== id);
      setOrders(updatedOrders);
    });
  }

  if (loading) {
    return <p>Loading...</p>;
  }

  const orderList = orders.map(order => {
     return <tr key={order.id}>
       <td style={{whiteSpace: 'nowrap'}}>{order.id}</td>
       <td style={{whiteSpace: 'nowrap'}}>{order.totalprice}</td>


              <td style={{whiteSpace: 'nowrap'}}>{order.drinks.map(drink => <div>{drink.name}</div>)}</td>
                            <td style={{whiteSpace: 'nowrap'}}>{order.foods.map(food => <div>{food.name}</div>)}</td>


       <td>
         <ButtonGroup>
           <Button size="m" color="primary" tag={Link} to={"/orders/" + order.id}>Edit</Button>
           <Button size="m" color="danger" onClick={() => remove(order.id)}>Delete</Button>
         </ButtonGroup>
       </td>
     </tr>
   });

   return (
     <div>
       <AppNavbar/>
       <Container fluid>
         <div className="float-end">
           <Button color="success" tag={Link} to="/orders/new">Add Order</Button>
         </div>
         <h3>All Orders</h3>
         <Table className="mt-4">
           <thead>
           <tr>
             <th width="10%">ID</th>
             <th width="10%">Total Price (€)</th>
             <th width="10%">Drinks</th>
                          <th width="10%">Foods</th>

                        <th width="10%">Actions</th>

           </tr>
           </thead>
           <tbody>
           {orderList}
           </tbody>
         </Table>
       </Container>
     </div>
   );
 };

export default OrderList;