import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label, Row, Col } from 'reactstrap';

import AppNavbar from './AppNavbar';

const FileEdit = () => {
  const initialFormState = {
    id: '',
    fileName: '',
    fileType: '',
    file: null,
    data: '',
  };
  const [selectedFile, setSelectedFile] = useState(null);

  const [fileData, setFileData] = useState(initialFormState);
  const navigate = useNavigate();
  const { id } = useParams();

useEffect(() => {
    if (id !== 'new') {
      fetch(`/api/files/upload/fields/${id}`)
        .then(response => response.json())
        .then(data => {
          // Set the fileData state with the fetched data
          setFileData(data);
        });
    }
  }, [id, setFileData]);


const handleFileChange = (event) => {
    const file = event.target.files[0];
    setSelectedFile(file);
  };


  const handleChange = (event) => {
  if(event.target.name === "file"){
    setFileData({ ...fileData, "file": event.target.files[0] });
    setSelectedFile(event.target.files[0]);
  } else {
    const { name, value } = event.target;
    setFileData({ ...fileData, [name]: value });
    }
  };

  const handleSubmit = async (event) => {
      event.preventDefault();

      const formData = new FormData();
      formData.append('fileName', fileData.fileName);
            formData.append('fileType', fileData.fileType);
            formData.append('file', fileData.file);
            formData.append('data', fileData.data);
            formData.append('id', fileData.id);

      await fetch(`/api/files/upload${fileData.id ? `/${fileData.id}` : ''}`, {
        method: fileData.id ? 'PUT' : 'POST',
        body: formData,
      });

      setFileData(initialFormState);
      navigate('/files/all');
    }


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

  const title = <h2>{fileData.id ? 'Edit File' : 'Add File'}</h2>;

  return (
    <div>
      <AppNavbar />
      <Container>
        {title}
        <Form onSubmit={handleSubmit}>
          <Row>
            <Col md={6}>
              <FormGroup>
                <Label for="id">ID</Label>
                <Input
                  type="text"
                  name="id"
                  id="id"
                  value={fileData.id || ''}
                  onChange={handleChange}
                />
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

                         <Button size="m" color="link" onClick={() => downloadFile(fileData.id, fileData.fileName)}>{fileData.fileName}</Button>




              </FormGroup>
            </Col>
          </Row>
          <FormGroup>
            <Button color="primary" type="submit">
              Save
            </Button>{' '}
            <Button color="secondary" tag={Link} to="/files/all">
              Cancel
            </Button>
          </FormGroup>
        </Form>
      </Container>
    </div>
  );
};

export default FileEdit;
