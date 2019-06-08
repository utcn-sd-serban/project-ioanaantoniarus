import React from "react";
import 'bootstrap/dist/css/bootstrap.css';

const ReviewDetails = ({ id, title, username, text, date, comments, newCommentText, onChange, onAddComment }) => (
    <div className="jumbotron text-center">
        <h2 className="bg-danger text-white">Book</h2>
        <label>Title: </label>
        <span>{title}</span>
        <br />
        <label>Username: </label>
        <span>{username}</span>
        <br />
        <label>Text: </label>
        <span>{text}</span>
        <br />
        <label>Date: </label>
        <span>{date}</span>
        <br />
        <br />

        <h2 className="bg-danger text-white">Comments</h2>
        <table className="table table-hover">
            <thead className="thead-light">
                <tr>
                    <th> User </th>
                    <th> Comment </th>
                    <th> Date </th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                {
                    comments.map((comment, index) => (
                        <tr key={index}>
                            <td>{comment.username}</td>
                            <td>{comment.text}</td>
                            <td>{comment.commentDate}</td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
        <br />

        <h2>Add a comment</h2>

        <label>Comment: </label>
        <input value={newCommentText} onChange={e => onChange("text", e.target.value)} />
        <br />
        <button type="button" className="btn btn-danger" onClick={() => onAddComment(id)}>Add Comment!</button>

    </div>

);

export default ReviewDetails;