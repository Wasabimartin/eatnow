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

  const [selectedFile, setSelectedFile] = useState(null);
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
//  console.log(event.target.files[0])
  if(event.target.name === "file"){
//    setFood({ ...food, "file": event.target.files[0] });
    setSelectedFile(event.target.files[0]);
  } else {
    const { name, value } = event.target;
    setFood({ ...food, [name]: value });
    }
    console.log(food.name);
console.log(food.file?.fileName);
//    console.log(selectedFile);

  };

  const handleDropdown = (selectedOption) => {
      setSelected(selectedOption);
      const name = "category";
      const value = selectedOption.value;
      setFood({ ...food, [name]: value })
    };


//  const handleSubmit = async (event) => {
//    event.preventDefault();
//
//    await fetch(`/api/food${food.id ? `/${food.id}` : ''}`, {
//      method: (food.id) ? 'PUT' : 'POST',
//      headers: {
//        'Accept': 'application/json',
//        'Content-Type': 'application/json'
//      },
//      body: JSON.stringify(food)
//    });
//    setFood(initialFormState);
//    navigate('/foods');
//  }

// ChatGPT
const handleSubmit = async (event) => {
    event.preventDefault();

    const formData = new FormData();
//    formData.append('name', food.name);
//    formData.append('price', food.price);
//    formData.append('category', food.category);
//    formData.append('details', food.details);
//    formData.append('file', selectedFile);
    formData.append('foodDTO', JSON.stringify(food));
    formData.append('file', selectedFile);

    const requestOptions = {
        method: food.id ? 'PUT' : 'POST',
        body: formData,
    };
    await fetch(`/api/food${food.id ? `/${food.id}` : ''}`, requestOptions);

    // After successful submission, you can navigate to the desired route
    navigate('/foods');
};


//  const handleSubmit = async (event) => {
//        event.preventDefault();
//            console.log(food.name);
//
//console.log(food);
//        const formData = new FormData();
//        formData.append('fileName', food.file.fileName);
//              formData.append('fileType', food.file.fileType);
//              formData.append('file', food.file.file);
//              formData.append('data', food.file.data);
//              formData.append('id', food.file.id);

//        await fetch(`/api/files/upload${food.file.id ? `/${food.file.id}` : ''}`, {
//          method: food.file.id ? 'PUT' : 'POST',
//          body: formData,
//        });

//        navigate('/foods');
//      }
const downloadFile = (id, fileName) => {
        fetch(`/api/files/download/${id}`)
          .then(response => response.blob())
          .then(blob => {
            const url = window.URL.createObjectURL(new Blob([blob]));
            const a = document.createElement('a');
            a.href = url;
            a.download = fileName;
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
            window.URL.revokeObjectURL(url);
          })
          .catch(error => {
            console.error('Error downloading file:', error);
          });
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
<Col md={6}>

              <FormGroup>
                <Label for="file">New File</Label>
                <Input
                  type="file"
                  name="file"
                  id="file"
                  onChange={handleChange}
                />
               </FormGroup>
                <FormGroup>
                     <Button size="m" color="link" onClick={() => downloadFile(food.file.id, food.file.fileName)}>{food.file?.fileName}</Button>
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