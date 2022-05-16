package sd.project.Service.Mappers;

import sd.project.Model.Utils.UserI;
import sd.project.Service.DTO.UserDTO;

//Structural DP: Adapter
public class UserAdapter implements Mapper<UserI, UserDTO> {

    private final String type;
    private final UserI userI;


    public UserAdapter(UserI userI, String type) {
        this.type = type;
        this.userI = userI;
    }


    public UserDTO convertToDTO() {
        UserDTO userDTO =  new UserDTO(userI.getId(),
                userI.getName(),
                type);
        if (type.equals("Publisher"))
            userDTO.setPicture(userI.getPicture());
        return userDTO;
    }

    public UserI convertFromDTO(UserDTO userDTO) {
        return null;
    }
}
