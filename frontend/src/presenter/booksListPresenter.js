import bookModel from "../model/bookModel";

class BooksListPresenter {

    onViewDetails(index) {
        bookModel.loadReviews(index)
            .then(() => {
                window.location.assign("#/book-details/" + index);
            });
    }

}

const booksListPresenter = new BooksListPresenter();
export default booksListPresenter;