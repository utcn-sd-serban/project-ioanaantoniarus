import bookModel from "../model/bookModel";

class CreateBookPresenter {

    onCreate() {
        bookModel.addBook(bookModel.state.newBook.name, bookModel.state.newBook.author, bookModel.state.newBook.isbn,bookModel.state.newBook.genres)
            .then(() => {
                bookModel.changeNewBookProperty("name", "");
                bookModel.changeNewBookProperty("author", "");
                bookModel.changeNewBookProperty("isbn", "");
                bookModel.changeNewBookProperty("genres", "");
                window.location.assign("#/pro-main-page");
            });
    }

    onChange(property, value) {
        bookModel.changeNewBookProperty(property, value);
    }
}

const createBookPresenter = new CreateBookPresenter();

export default createBookPresenter;