import bookModel from "../model/bookModel";


class BookDetailsPresenter {

    onAddReview(id) {
        bookModel.addReview(bookModel.state.newReview.title, bookModel.state.newReview.text, id);
        bookModel.changeNewReviewProperty("title", "");
        bookModel.changeNewReviewProperty("text", "");
        window.location.assign("#/books-list");
    }

    onChange(property, value) {
        bookModel.changeNewReviewProperty(property, value);
    }

}

const bookDetailsPresenter = new BookDetailsPresenter();

export default bookDetailsPresenter;