package hu.streetartchoir.app.domain;

public enum Role {

    ROLE_ADMIN("admin"),
    ROLE_USER("user");

    private String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public static Role parse(String string){
        Role result = null;
        for (Role type : Role.values()){
            if (type.toString().equals(string.toUpperCase())){
                result = type;
            }
        }
        return result;
    }

}
