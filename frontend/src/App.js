import React from 'react';
import './App.css';
import { HashRouter, Switch, Route } from "react-router-dom";
import SmartLogin from './view/SmartLogin';
import SmartMainPage from './view/SmartMainPage';
import SmartBooksList from './view/SmartBooksList';
import SmartFilteredBooksList from './view/SmartFilteredBooksList';
import SmartBookDetails from './view/SmartBookDetails';
import SmartProMainPage from './view/SmartProMainPage';
import SmartCreateBook from './view/SmartCreateBook';
import SmartReviewDetails from './view/SmartReviewDetails';


const App = () => (
  <div className="App">
    <HashRouter>
      <Switch>
        <Route exact={true} component={SmartLogin} path="/" />
        <Route exact={true} component={SmartMainPage} path="/main-page" />
        <Route exact={true} component={SmartBooksList} path="/books-list" />
        <Route exact={true} component={SmartFilteredBooksList} path="/searched-books-list" />
        <Route exact={true} component={SmartBookDetails} path="/book-details/:index" />
        <Route exact={true} component={SmartProMainPage} path="/pro-main-page" />
        <Route exact={true} component={SmartCreateBook} path="/create-book" />
        <Route exact={true} component={SmartReviewDetails} path="/review-details/:index" />
      </Switch>
    </HashRouter>
  </div>
);

export default App;
