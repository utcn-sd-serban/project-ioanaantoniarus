import bookModel from "../model/bookModel";

class ProMainPagePresenter {

    onList() {
        bookModel.loadBooks();
        window.location.assign("#/books-list");
    }

    onCreateBook() {
        window.location.assign("#/create-book");
    }
}

const proMainPagePresenter = new ProMainPagePresenter();
export default proMainPagePresenter;