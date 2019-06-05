import React from "react";
import 'bootstrap/dist/css/bootstrap.css';

const CreateBook = ({ newBookName, newBookAuthor, newBookIsbn, newBookGenres, onCreate, onChange }) => (
    <div className="jumbotron text-center">
        <h2 className="bg-warning text-white">Add Question</h2>
        <div>
            <label>Title: </label>
            <input type="text" className="form-control" value={newBookName} onChange={e => onChange("name", e.target.value)} />
            <br />
            <label>Author: </label>
            <input type="text" className="form-control" value={newBookAuthor} onChange={e => onChange("author", e.target.value)} />
            <br />
            <label>ISBN: </label>
            <input type="text" className="form-control" value={newBookIsbn} onChange={e => onChange("isbn", e.target.value)} />
            <br />
            <label>Genres: </label>
            <input type="text" className="form-control" value={newBookGenres} onChange={e => onChange("genres", e.target.value)} />
            <br />
            <button type="button" className="btn btn-warning" onClick={onCreate}>Create!</button>
        </div>
    </div>
);

export default CreateBook;