import { EventEmitter } from "events";
import { client } from "./userModel";

class BookModel extends EventEmitter {
    constructor() {
        super();
        this.state = {
            books: [],
            newBook: {
                id: "",
                name: "",
                author: "",
                isbn: "",
                rating: "",
                genres: ""
            },
            filteredBooks: [],
            filterGenre: "",
            filterTitle: "",
            filterIsbn: "",
            reviews: [],
            newReview: {
                id: "",
                title: "",
                username: "",
                text: "",
                reviewDate: ""
            },
            comments: [],
            newComment: {
                id: "",
                username: "",
                text: "",
                commentDate: ""
            }
        };
    }

    loadBooks() {
        return client.loadAllBooks().then(books => {
            this.state = { ...this.state, books: books };
            this.emit("change", this.state);
        });
    }

    addBook(name, author, isbn, genres) {
        return client.createBook(name, author, isbn, genres)
            .then(book => this.appendBook(book));
    }

    appendBook(book) {
        this.state = {
            ...this.state,
            books: this.state.books.concat([book])
        };
        this.emit("change", this.state);
    }

    getBookId(index) {
        return this.state.books[index].id;
    }

    loadReviews(index) {
        var id = this.getBookId(index);
        return client.loadBookReviews(id).then(reviews => {
            this.state = { ...this.state, reviews: reviews };
            this.emit("change", this.state);
        });
    }

    getReviewId(index) {
        return this.state.reviews[index].id;
    }

    loadComments(index) {
        var id = this.getReviewId(index);
        return client.loadReviewComments(id).then(comments => {
            this.state = { ...this.state, comments: comments };
            this.emit("change", this.state);
        });
    }

    changeFilterProperty(property, value) {
        this.state = {
            ...this.state,
            [property]: value
        };
        this.emit("change", this.state);
    }

    changeNewBookProperty(property, value) {
        this.state = {
            ...this.state,
            newBook: {
                ...this.state.newBook,
                [property]: value
            }
        };
        this.emit("change", this.state);
    }

    filterByTitle() {
        return client.loadFilterTitle(this.state.filterTitle).then(filteredBooks => {
            this.state = { ...this.state, filteredBooks: filteredBooks };
            this.emit("change", this.state);
        });
    }

    filterByGenre() {
        return client.loadFilterGenre(this.state.filterGenre).then(filteredBooks => {
            this.state = { ...this.state, filteredBooks: filteredBooks };
            this.emit("change", this.state);
        });
    }

    filterByIsbn() {
        return client.loadFilterIsbn(this.state.filterIsbn).then(filteredBooks => {
            this.state = { ...this.state, filteredBooks: filteredBooks };
            this.emit("change", this.state);
        });
    }

    addReview(title, text, id) {
        return client.createReview(title, text, id)
            .then(review => {
                this.state = {
                    ...this.state,
                    reviews: this.state.reviews.concat([review])
                };
                this.emit("change", this.state);
            });
    }

    addRating(id, rating) {
        return client.updateRating(id, rating);
    }

    changeNewReviewProperty(property, value) {
        this.state = {
            ...this.state,
            newReview: {
                ...this.state.newReview,
                [property]: value
            }
        };
        this.emit("change", this.state);
    }

    changeNewCommentProperty(property, value) {
        this.state = {
            ...this.state,
            newComment: {
                ...this.state.newReview,
                [property]: value
            }
        };
        this.emit("change", this.state);
    }

    addComment(text, id) {
        return client.createComment(text, id)
            .then(comment => {
                this.state = {
                    ...this.state,
                    comments: this.state.comments.concat([comment])
                };
                this.emit("change", this.state);
            });
    }
}

const bookModel = new BookModel();

export default bookModel;