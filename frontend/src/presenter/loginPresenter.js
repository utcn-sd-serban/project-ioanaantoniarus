import userModel from "../model/userModel";

class LoginPresenter {

    onLogin(loginUsername, loginPassword) {
        userModel.validateUser();
        userModel.changeUserProperty("username", "");
        userModel.changeUserProperty("password", "");
        userModel.getUserType().then(type => {
            if (type === "pro") {
                window.location.assign("#/pro-main-page");
                //window.location.assign("#/main-page");
            }
            else {
                window.location.assign("#/main-page");
                //window.location.assign("#/pro-main-page");
            }
        })
    }

    onChange(property, value) {
        userModel.changeUserProperty(property, value);
    }

}

const loginPresenter = new LoginPresenter();

export default loginPresenter;