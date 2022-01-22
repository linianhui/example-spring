package example.starter.hbase.dto;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

@Data
public class ConfigDto implements Serializable {
    private static final long serialVersionUID = 5529890722553091139L;

    private Map<String, String> items;
}
