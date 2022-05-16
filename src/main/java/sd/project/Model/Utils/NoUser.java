package sd.project.Model.Utils;

import sd.project.Model.User;

//Behavioral DP: NullObject
public class NoUser implements UserI {

    @Override
    public Integer getId() {
        return -1;
    }

    @Override
    public String getName() {
        return "User not found!";
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public String getPicture() {
        return null;
    }
}
