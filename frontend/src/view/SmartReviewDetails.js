import React, { Component } from "react";
import bookModel from "../model/bookModel";
import reviewDetailsPresenter from "../presenter/reviewDetailsPresenter";
import ReviewDetails from "./ReviewDetails";


const mapMpdelStateToComponentState = (modelState, props) => ({
    review: modelState.reviews[props.match.params.index],
    comments: modelState.comments,
    newCommentText: modelState.newComment.text
})

export default class SmartReviewDetails extends Component {
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
            <ReviewDetails
                id={this.state.review.id}
                text={this.state.review.text}
                username={this.state.review.username}
                date={this.state.review.reviewDate}
                title={this.state.review.title}
                comments={this.state.comments}
                newCommentText={this.state.newCommentText}
                onChange={reviewDetailsPresenter.onChange}
                onAddComment={reviewDetailsPresenter.onAddComment} />
        );
    }

}