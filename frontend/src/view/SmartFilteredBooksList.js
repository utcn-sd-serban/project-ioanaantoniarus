import React, { Component } from "react";
import bookModel from "../model/bookModel";
import booksListPresenter from "../presenter/booksListPresenter";
import FilteredBooksList from "./FilteredBooksList";

const mapMpdelStateToComponentState = modelState => ({
    filteredBooks: modelState.filteredBooks
});

export default class SmartFilteredBooksList extends Component {
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
            <FilteredBooksList
                onViewDetails={booksListPresenter.onViewDetails}
                filteredBooks={this.state.filteredBooks} />
        );
    }

}