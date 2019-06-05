import React from "react";
import 'bootstrap/dist/css/bootstrap.css';

const ProMainPage = ({  onCreateBook, onList }) => (
    <div className="jumbotron text-center">
        <h2 className="bg-danger text-white">Welcome!</h2>

        <div>
            <label>List books</label>
            <button type="button" className="btn btn-info" onClick={onList}>List all books</button>
            <br />
            <br />
            <label>Add book</label>
            <button type="button" className="btn btn-primary" onClick={onCreateBook}>Add new book</button>
        </div>

    </div>

);


export default ProMainPage;