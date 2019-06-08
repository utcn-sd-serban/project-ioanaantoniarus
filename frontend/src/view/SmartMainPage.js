import React, { Component } from "react";
import bookModel from "../model/bookModel";
import mainPagePresenter from "../presenter/mainPagePresenter";
import MainPage from "./MainPage";


const mapMpdelStateToComponentState = modelState => ({
    filterTitle: modelState.filterTitle,
    filterGenre: modelState.filterGenre,
    filterIsbn: modelState.filterIsbn
});

export default class SmartMainPage extends Component {
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
            <MainPage
                onList={mainPagePresenter.onList}
                onSearchTitle={mainPagePresenter.onSearchTitle}
                onSearchGenre={mainPagePresenter.onSearchGenre}
                onSearchIsbn={mainPagePresenter.onSearchIsbn}
                onChange={mainPagePresenter.onChange}
                searchedBookTitle={this.state.filterTitle}
                searchedBookGenre={this.state.filterGenre}
                searchedBookIsbn={this.state.filterIsbn} />
        );
    }

}