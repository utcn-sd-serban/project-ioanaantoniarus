import React, { Component } from "react";
import bookModel from "../model/bookModel";
import CreateBook from "./CreateBook";
import createBookPresenter from "../presenter/createBookPresenter";

const mapMpdelStateToComponentState = modelState => ({
    name: modelState.newBook.name,
    author: modelState.newBook.author,
    isbn: modelState.newBook.isbn,
    genres: modelState.newBook.genres
});

export default class SmartCreateBook extends Component {
    constructor() {
        super();
        this.state = mapMpdelStateToComponentState(bookModel.state);
        this.listener = modelState => this.setState(mapMpdelStateToComponentState(modelState));
        bookModel.addListener("change", this.listener);
    }

    componentWillUnmount() {
        bookModel.removeListener("change", this.listener);
    }

    render() {
        return (
            <CreateBook
                onCreate={createBookPresenter.onCreate}
                onChange={createBookPresenter.onChange}
                newBookName={this.state.name}
                newBookAuthor={this.state.author}
                newBookIsbn={this.state.isbn}
                newBookGenres={this.state.genres}
                books={this.state.books} />
        );
    }

}
