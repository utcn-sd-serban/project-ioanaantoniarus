import React, { Component } from "react";
import bookModel from "../model/bookModel";
import BooksList from "./BooksList";
import booksListPresenter from "../presenter/booksListPresenter";

const mapMpdelStateToComponentState = modelState => ({
    books: modelState.books
});

export default class SmartBooksList extends Component {
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
            <BooksList
                onViewDetails={booksListPresenter.onViewDetails}
                books={this.state.books} />
        );
    }

}