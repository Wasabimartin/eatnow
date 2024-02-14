import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';


const FileList = () => {
  const [files, setFiles] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch('/api/files/all')
      .then(response => response.json())
      .then(data => {
        setFiles(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
        setLoading(false);
      });
  }, []);

  const remove = async (id) => {
    await fetch(`/api/files/delete/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedFiles = files.filter(i => i.id !== id);
      setFiles(updatedFiles);
    });
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

  if (loading) {
    return <p>Loading...</p>;
  }

  const fileList = files.map(file => (
    <tr key={file.id}>
      <td style={{whiteSpace: 'nowrap'}}>{file.id}</td>
      <td style={{whiteSpace: 'nowrap'}}>{file.fileName}</td>
      <td>
        <ButtonGroup>
          <Button size="m" color="primary" onClick={() => downloadFile(file.id, file.fileName)}>Download</Button>
          <Button size="m" color="primary" tag={Link} to={`/files/upload/${file.id}`}>Edit</Button>
          <Button size="m" color="danger" onClick={() => remove(file.id)}>Delete</Button>
        </ButtonGroup>
      </td>
    </tr>
  ));

  return (
    <div>
      <AppNavbar/>
      <Container fluid><div className="float-end">
                                 <Button color="success" tag={Link} to="/files/upload/new">
                                   Add Food
                                 </Button>
                               </div>
        <h3>All Files</h3>
        <Table className="mt-4">
          <thead>
            <tr>
              <th width="5%">ID</th>
              <th width="5%">Name</th>
              <th width="10%">Actions</th>
            </tr>
          </thead>
          <tbody>
            {fileList}
          </tbody>
        </Table>
      </Container>
    </div>
  );
};

export default FileList;
