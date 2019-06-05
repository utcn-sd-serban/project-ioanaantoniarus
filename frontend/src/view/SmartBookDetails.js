import React, { Component } from "react";
import bookModel from "../model/bookModel";
import BookDetails from "./BookDetails";
import bookDetailsPresenter from "../presenter/bookDetailsPresenter";


const mapMpdelStateToComponentState = (modelState, props) => ({
    book: modelState.books[props.match.params.index],
    reviews: modelState.reviews,
    newReviewTitle: modelState.newReview.title,
    newReviewText: modelState.newReview.text
})

export default class SmartBookDetails extends Component {
    constructor(props) {
        super(props);
        this.state = mapMpdelStateToComponentState(bookModel.state, props);
        this.listener = modelState => this.setState(mapMpdelStateToComponentState(modelState, this.props));
        bookModel.addListener("change", this.listener);
    }

    componentDidUpdate(prev) {
        if (prev.match.params.index !== this.props.match.params.index) {
            this.setState(mapMpdelStateToComponentState(bookModel.state, this.props));
        }
    }

    componentWillUnmount() {
        bookModel.removeListener("change", this.listener);
    }

    render() {
        return (
            <BookDetails
                id={this.state.book.id}
                name={this.state.book.name}
                author={this.state.book.author}
                isbn={this.state.book.isbn}
                genres={this.state.book.genres}
                rating={this.state.book.rating}
                reviews={this.state.reviews}
                newReviewTitle={this.state.newReviewTitle}
                newReviewText={this.state.newReviewText}
                onChange={bookDetailsPresenter.onChange}
                onAddReview={bookDetailsPresenter.onAddReview} />
        );
    }

}