import bookModel from "../model/bookModel";


class ReviewDetailsPresenter {

    onAddComment(id) {
        bookModel.addComment(bookModel.state.newComment.text, id);
        bookModel.changeNewCommentProperty("text", "");
        window.location.assign("#/books-list");
    }

    onChange(property, value) {
        bookModel.changeNewCommentProperty(property, value);
    }

}

const reviewDetailsPresenter = new ReviewDetailsPresenter();

export default reviewDetailsPresenter;