import bookModel from "../model/bookModel";

class MainPagePresenter {

    onList() {
        bookModel.loadBooks();
        window.location.assign("#/books-list");
    }

    onSearchTitle() {
        bookModel.filterByTitle();
        bookModel.changeFilterProperty("filterTitle", "");
        window.location.assign("#/searched-books-list");
    }

    onSearchGenre() {
        bookModel.filterByGenre();
        bookModel.changeFilterProperty("filterGenre", "");
        window.location.assign("#/searched-books-list");
    }

    onSearchIsbn() {
        bookModel.filterByIsbn();
        bookModel.changeFilterProperty("filterIsbn", "");
        window.location.assign("#/searched-books-list");
    }

    onChange(property, value) {
        bookModel.changeFilterProperty(property, value);
    }
}

const mainPagePresenter = new MainPagePresenter();
export default mainPagePresenter;