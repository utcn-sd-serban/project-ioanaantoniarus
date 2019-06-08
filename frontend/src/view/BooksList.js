import React from "react";

const BooksList = ({ books, title, onViewDetails }) => (
    <div className="jumbotron text-center">
        <h2 className="bg-info text-white">{title || "Books"}</h2>
        <table className="table table-hover">
            <thead className="thead-light">
                <tr>
                    <th>Id</th>
                    <th> Title </th>
                    <th> Author </th>
                    <th> ISBN </th>
                    <th> Genres </th>
                    <th> Rating </th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                {
                    books.map((book, index) => (
                        <tr key={index}>
                            <td>{book.id}</td>
                            <td>{book.name}</td>
                            <td>{book.author}</td>
                            <td>{book.isbn}</td>
                            <td>{book.genres}</td>
                            <td>{book.rating}</td>
                            <td><button type="button" className="btn btn-info" onClick={() => onViewDetails(index)}>View Details</button></td>
                        </tr>
                    ))
                }
            </tbody>
        </table>

        
    </div>

);

export default BooksList;