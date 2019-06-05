import React from "react";
import 'bootstrap/dist/css/bootstrap.css';

const MainPage = ({ searchedBookTitle, onSearchTitle, searchedBookGenre, onSearchGenre, searchedBookIsbn, onSearchIsbn, onChange, onList }) => (
    <div className="jumbotron text-center">
        <h2 className="bg-danger text-white">Welcome!</h2>

        <div>
            <label>List books</label>
            <button type="button" className="btn btn-info" onClick={onList}>List all books</button>
            <br />
            <br />
            <label>Search by title: </label>
            <input type="text" className="form-control" value={searchedBookTitle} onChange={e => onChange("filterTitle", e.target.value)} />
            <button type="button" className="btn btn-warning" onClick={onSearchTitle}>Search Title!</button>
            <br />
            <br />
            <label>Search by genre: </label>
            <input type="text" className="form-control" value={searchedBookGenre} onChange={e => onChange("filterGenre", e.target.value)} />
            <button type="button" className="btn btn-warning" onClick={onSearchGenre}>Search Genre!</button>
            <br />
            <br />
            <label>Search by ISBN: </label>
            <input type="text" className="form-control" value={searchedBookIsbn} onChange={e => onChange("filterIsbn", e.target.value)} />
            <button type="button" className="btn btn-warning" onClick={onSearchIsbn}>Search ISBN!</button>
        </div>


    </div>

);


export default MainPage;