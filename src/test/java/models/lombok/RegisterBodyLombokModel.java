package models.lombok;

import lombok.Data;

@Data
public class RegisterBodyLombokModel {

    String email, password;

    public String getError() {
        return "";
    }
}