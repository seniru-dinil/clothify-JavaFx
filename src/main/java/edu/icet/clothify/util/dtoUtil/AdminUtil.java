package edu.icet.clothify.util.dtoUtil;

import edu.icet.clothify.dto.Admin;
import lombok.Getter;
import lombok.Setter;

public class AdminUtil {
    private static AdminUtil instance;

    @Setter
    @Getter
    private Admin adminInstance;
    private AdminUtil(){}
    public static AdminUtil getInstance(){
        if (instance==null)instance=new AdminUtil();
        return instance;
    }

}
