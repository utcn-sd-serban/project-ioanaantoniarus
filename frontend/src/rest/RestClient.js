const BASE_URL = "http://localhost:8080";

export default class RestClient {
    constructor(username, password) {
        this.authorization = "Basic " + btoa(username + ":" + password);

    }

    checkUser(username, password) {
        fetch(BASE_URL + "/login", {
            method: "GET",
            body: JSON.stringify({
                username: username,
                password: password
            }),
            headers: {
                "Authorization": this.authorization
            }
        }).then(() => { return true })
            .catch(() => { return false });
    }

    loadAllBooks() {
        return fetch(BASE_URL + "/books", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }

    loadFilterTitle(title) {
        return fetch(BASE_URL + "/books?title=" + title, {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }

    loadFilterGenre(genre) {
        return fetch(BASE_URL + "/books?genre=" + genre, {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }

    loadFilterIsbn(isbn) {
        return fetch(BASE_URL + "/books?isbn=" + isbn, {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }

    loadBookReviews(id) {
        return fetch(BASE_URL + "/books/" + id + "/reviews", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }

    createReview(title, text, id) {
        return fetch(BASE_URL + "/books/" + id + "/reviews", {
            method: "POST",
            body: JSON.stringify({
                title: title,
                text: text
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-Type": "application/json"
            }
        }).then(response => response.json());
    }

    createBook(name, author, isbn, genres) {
        return fetch(BASE_URL + "/books", {
            method: "POST",
            body: JSON.stringify({
                name: name,
                author: author,
                isbn: isbn,
                genres: genres
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-Type": "application/json"
            }
        }).then(response => response.json());
    }

    getUserInfo() {
        return fetch(BASE_URL + "/me", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }



}