package xyz.ziang.mudisk.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceException extends Exception{

    static final long serialVersionUID = -1387516993124229948L;

    private String msg;
}
