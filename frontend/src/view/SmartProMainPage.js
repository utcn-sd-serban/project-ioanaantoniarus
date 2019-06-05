import React, { Component } from "react";
import bookModel from "../model/bookModel";
import ProMainPage from "./ProMainPage";
import proMainPagePresenter from "../presenter/proMainPagePresenter";


const mapMpdelStateToComponentState = modelState => ({
    filterTitle: modelState.filterTitle,
    filterGenre: modelState.filterGenre,
    filterIsbn: modelState.filterIsbn
});

export default class SmartProMainPage extends Component {
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
            <ProMainPage
                onList={proMainPagePresenter.onList}
                onCreateBook={proMainPagePresenter.onCreateBook} />
        );
    }

}