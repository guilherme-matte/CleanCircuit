package CC.CleanCircuit.dtos;

import CC.CleanCircuit.entities.UserEntity;

public class UserDTO {
    private UserEntity userEntity;
    private boolean hasProfileImage;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public boolean isHasProfileImage() {
        return hasProfileImage;
    }

    public void setHasProfileImage(boolean hasProfileImage) {
        this.hasProfileImage = hasProfileImage;
    }
}
