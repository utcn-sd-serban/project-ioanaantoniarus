import userModel from "../model/userModel";

class LoginPresenter {

    onLogin(loginUsername, loginPassword) {
        /*if (userModel.validateUser(loginUsername, loginPassword) === true) {
            userModel.changeUserProperty("username", "");
            userModel.changeUserProperty("password", "");
            window.location.assign("#/questions-list");
        } else {
            userModel.changeUserProperty("username", "");
            userModel.changeUserProperty("password", "");
        }*/
        userModel.validateUser();
        userModel.changeUserProperty("username", "");
        userModel.changeUserProperty("password", "");
        debugger;
        if(userModel.getUserType()==="pro"){
            window.location.assign("#/pro-main-page");
            //window.location.assign("#/main-page");
        }
        else{
            window.location.assign("#/main-page");
            //window.location.assign("#/pro-main-page");
        }
    }

    onChange(property, value) {
        userModel.changeUserProperty(property, value);
    }

}

const loginPresenter = new LoginPresenter();

export default loginPresenter;