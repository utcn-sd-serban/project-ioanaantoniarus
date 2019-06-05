import React from "react";
import 'bootstrap/dist/css/bootstrap.css';

const BookDetails = ({ id, name, author, isbn, genres, rating, reviews,newReviewTitle,newReviewText,onChange,onAddReview}) => (
    <div className="jumbotron text-center">
        <h2 className="bg-danger text-white">Book</h2>
        <label>Title: </label>
        <span>{name}</span>
        <br />
        <label>Author: </label>
        <span>{author}</span>
        <br />
        <label>ISBN: </label>
        <span>{isbn}</span>
        <br />
        <label>Genres: </label>
        <span>{genres}</span>
        <br />
        <label>Rating: </label>
        <span>{rating}</span>
        <br />
        <br />
        <h2 className="bg-danger text-white">Reviews</h2>
        <table className="table table-hover">
            <thead className="thead-light">
                <tr>
                    <th> Title</th>
                    <th> User </th>
                    <th> Review </th>
                    <th> Date </th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                {
                    reviews.map((review, index) => (
                        <tr key={index}>
                            <td>{review.title}</td>
                            <td>{review.username}</td>
                            <td>{review.text}</td>
                            <td>{review.reviewDate}</td> 
                        </tr>
                    ))
                }
            </tbody>
        </table>
        <br />
        
        <h2>Add a review</h2>
        
        <label>Title: </label>
        <input value={newReviewTitle} onChange={e => onChange("title", e.target.value)} />
        <label>Review: </label>
        <input value={newReviewText} onChange={e => onChange("text", e.target.value)} />
        <br />
        <button type="button" className="btn btn-danger" onClick={() => onAddReview(id)}>Add Review!</button>

    </div>

);

export default BookDetails;